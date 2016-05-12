package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;

import network.ConectorDB;

public class Logics {
	private ArrayList<UserRanking> usersRanking;
	private UserRanking comparator = new UserRanking();
	private static ArrayList<UserRanking> competitionUsers;
	private static boolean competition = false;
	
	public static boolean addUser(String message){
		String[] array = new String[2];
		boolean ok = false;
		
		message = message.substring(4);
		array = message.split("/");
		
		if (ConectorDB.insertUser(array[0],array[1])){
			if (competition){
				competitionUsers.add(new UserRanking(array[0],0));
			}
			ok = true;
		}else{
			ok = false;
		}
		
		return ok;
	}
	
	public static boolean checkUser(String message){
		boolean ok = false;
		String[] array = new String[2];
		
		message = message.substring(4);
		array = message.split("/");
		
		ResultSet user = ConectorDB.selectUser(array[0]);
		
		try {
			while(user.next()){
				if(user.getObject("pasword").equals(array[1])){
					UserRanking u = new UserRanking(array[0],Integer.parseInt(array[1]));
					if (!competitionUsers.contains(u)){
						competitionUsers.add(u);
					}
					ok = true;
				}else{
					ok = false;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ok;
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
	
	/*public static String createRanking(){
		String ranking = new String();
		
		ResultSet user = ConectorDB.selectAllUsers();
		
		try {
			while(user.next()){
				ranking += user.getObject("nickname")+"/"+user.getObject("pasword")+"/"+user.getObject("score")+"#";
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return ranking;
	}*/
	
	public String toArray(){
		//quan el client afegeixi informacio s'ha d'actualitzar
		/*usersRanking = new ArrayList<UserRanking>();
		
		ResultSet user = ConectorDB.selectAllUsers();
		
		try {
			while(user.next()){
				UserRanking u = new UserRanking((String)user.getObject("nickname"),(int)user.getObject("score"));
				usersRanking.add(u);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		*/
		Collections.sort(competitionUsers, comparator);
		String ranking = new String();
		
		for (int i = 0; i<10 && i<competitionUsers.size();i++){
			ranking += competitionUsers.get(i).getNickname()+"/"+competitionUsers.get(i).getPunctuation()+"#";
		}
		
		return ranking;
	}
	
	public String rankingToString(){
		String ranking = new String();
		
		for (int i = 0; i<usersRanking.size();i++){
			ranking += usersRanking.get(i).getNickname()+"/"+usersRanking.get(i).getPunctuation()+"#";
		}
		return ranking;
	}
}