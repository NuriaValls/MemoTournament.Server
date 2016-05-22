package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

import com.sun.xml.internal.ws.util.StringUtils;

import controller.MainViewControllerS;
import network.ConectorDB;

public class Logics {
	private UserRanking comparator = new UserRanking();
	private static ArrayList<UserRanking> competitionUsers = new ArrayList<UserRanking>();
	private static boolean competition = false;
	
	private Time time;
	private long difference;
	private int duration;
	
	private static MainViewControllerS controller;
	
	public Logics(Time time){
		this.time = time;
	}
	
	public void registerController(MainViewControllerS controller){
		this.controller = controller;
	}
	
	public static boolean addUser(String message){
		String[] array = new String[2];
		boolean ok = false;
		
		message = message.substring(4);
		array = message.split("/");
		
		if(checkPasword(array[1]) && checkNickname(array[0])){
			if (ConectorDB.insertUser(array[0],array[1])){
				if (competition){
					competitionUsers.add(new UserRanking(array[0],0));
				}
				ok = true;
			}
		}
		
		return ok;
	}
	
	public static String checkUser(String message){
		boolean found = false;
		String[] array = new String[2];
		
		String answer = new String();
		
		message = message.substring(4);
		array = message.split("/");
		try {	
			ResultSet user = ConectorDB.selectUser(array[0]);
			
			try {
				while(user.next()){
					if(user.getObject("pasword").equals(array[1])){
						if (!competitionUsers.isEmpty()){
							for (UserRanking u: competitionUsers){
								if (u.getNickname().equalsIgnoreCase(array[0])){
									found = true;
								}
							}
						}
						if (!found){
							competitionUsers.add(new UserRanking(array[0],0));
							answer = "OK:0";
							controller.refreshList();
						}else{
							answer = "OK:"+user.getObject("score");
						}
					}else{
						answer = "KO";
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}catch (Exception e2){
			e2.printStackTrace();
		}
		return answer;
	}
	
	public static boolean updateScore(String message){
		boolean ok = false;
		int score = 0;
		String[] array = new String[3];
		
		message = message.substring(7);
		array = message.split("/");
		
		ResultSet user = ConectorDB.selectUser(array[0]);
		
		try {
			while(user.next()){
				score = (int) user.getObject("score")+Integer.parseInt(array[2]);
			}
			
			ConectorDB.insertGame(array[0],array[1],Integer.parseInt(array[2]));
			ConectorDB.updateScore(array[0],score);
			
			for (UserRanking u: competitionUsers){
				if (u.getNickname().equals(array[0])){
					u.setPunctuation(score);
				}
			}
			ok = true;
			
		} catch (SQLException e) {
			e.printStackTrace();
			ok = false;
		}

		return ok;
	}
	
	public boolean checkBlocked(String message){
		message = message.substring(8);
		for (UserRanking user: competitionUsers){
			if (user.getNickname().equals(message)){
				return user.getBlocked();
			}
		}
		return false;
	}
	
	public ArrayList<UserRanking> toArray(){
		
		ArrayList<UserRanking> allUsers = new ArrayList<UserRanking>();
		ResultSet user = ConectorDB.selectAllUsers();
		try {
			while(user.next()){
				UserRanking u = new UserRanking((String)user.getObject("nickname"),(int)user.getObject("score"));
				allUsers.add(u);
			}	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return allUsers;
	}
	
	public ArrayList<UserRanking> getCompetitors(){
		return competitionUsers;
	}
	
	public String toString(){
		Collections.sort(competitionUsers, comparator);
		String ranking = new String();
		
		for (int i = 0; i<10 && i<competitionUsers.size();i++){
			ranking += competitionUsers.get(i).getNickname()+"/"+competitionUsers.get(i).getPunctuation()+"#";
		}
		
		return ranking;
	}
	
	public boolean createTimeComp(int hour, int minute, int duration){
		boolean timeOK = false;
		
		String localTime = time.getTime();
		
		String startTime = hour+":"+minute+":00";
		
		SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
		try {
			Date localDate = format.parse(localTime);
			Date startDate = format.parse(startTime);
			
			long difference = startDate.getTime() - localDate.getTime();
			if (difference > 0){
				difference = difference/1000;
				duration = duration*60;
				this.difference = difference;
				this.duration = duration;
				time.startCompetitionTime((int)difference, duration);
				timeOK = true;
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return timeOK;
	}
	
	public long getDifference(){
		return time.getCountdown();
	}
	
	public int getDuration(){
		return time.getCompetition();
	}
	
	public boolean getCompetition(){
		return competition;
	}
	
	public void setCompetition(boolean competition){
		this.competition = competition;
	}
	
	public static boolean checkNickname(String nickname){
		return !nickname.equals("GUEST");
	}
	
	public static boolean checkPasword(String pasword){
		boolean num = false;
		boolean letter = false;
		if(pasword.length()>6){
			if(pasword.matches(".*\\d+.*")){
				num = true;
			}
			if(pasword.matches(".*[a-zA-Z]+.*")){
				letter = true;
			}
			if(num && letter){
				return true;
			}
		}
		return false;
	}
	
	public void blockUser(String nickname){
		for(int i=0; i<competitionUsers.size(); i++){
			CharSequence blocked = "/blocked";
			if(nickname.contains(blocked)){;
				if (nickname.contains((CharSequence)competitionUsers.get(i).getNickname())){
					competitionUsers.get(i).setBlocked(false);
				}
			}
			if(competitionUsers.get(i).getNickname().equals(nickname)){
				competitionUsers.get(i).setBlocked(true);
			}
		}
	}
	
	public void deleteUser(String nickname){
		for(int i=0; i<competitionUsers.size(); i++){
			if(competitionUsers.get(i).getNickname().equals(nickname)){
				competitionUsers.remove(i);
			}
		}
		System.out.println("abans de refresh");
	}
	
	public int[] getGames(String nickname, boolean concentration){
		
		ResultSet game = ConectorDB.selectGames(nickname);
		int[] array = new int[15];
		int i = 0;
		try {
			while(game.next()){
				if(concentration){
					if(game.getObject("mode").equals("concentracio") && i<15){
						array[i] = (int)game.getObject("score");
						i++;
					}
				}else{
					if(game.getObject("mode").equals("memoria") && i<15){
						array[i] = (int)game.getObject("score");
						i++;
					}
				}
			}	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return array;
	}
}