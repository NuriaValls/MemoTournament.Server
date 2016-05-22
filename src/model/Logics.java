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
/**
 * Aquesta classe conte els metodes que duen a terme tota la logica del programa.
 * @author nvall
 *
 */
public class Logics {
	/**
	 * Usuari auxiliar per guardar comparadors amb altres usuaris.
	 */
	private UserRanking comparator = new UserRanking();
	/**
	 * Llista de usuaris registrats a la competicico actual.
	 */
	private static ArrayList<UserRanking> competitionUsers = new ArrayList<UserRanking>();
	/**
	 * Boolea que indica si hi ha una competicio actualment.
	 */
	private static boolean competition = false;
	/**
	 * instancia de la classe time.
	 */
	private Time time;
	/**
	 * marca el temps en segons del compte enrere per la competicio.
	 */
	private long difference;
	/**
	 * marca el temps en segons del temps que durar‡ la competicio.
	 */
	private int duration;
	/**
	 * instancia de la classe controller.
	 */
	private static MainViewControllerS controller;
	
	public Logics(Time time){
		this.time = time;
	}
	
	public void registerController(MainViewControllerS controller){
		this.controller = controller;
	}
	/**
	 * Metode que envia un usuari per afegira la base de dades que rep com a par‡metre.
	 */
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
	/**
	 * Metode que envia un usuari que rep com a par‡metre perque la base de dades comprovi si esta registrat.
	 */
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
	/**
	 * Metode que envia la informacio de la partida que rep com a paramentre per inserirla  a la base de edades.
	 */
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
	/**
	 * Comprova si un usuari ha estat bloquejat o no.
	 */
	public boolean checkBlocked(String message){
		message = message.substring(8);
		for (UserRanking user: competitionUsers){
			if (user.getNickname().equals(message)){
				return user.getBlocked();
			}
		}
		return false;
	}
	/**
	 * Connecta amb el servidor per rebre l'informaci√≥ dels usuaris i guarda-la en un array.
	 * @return
	 */
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
	/**
	 * retorna l'array que conte l'informaci√≥ dels competidors
	 * @return
	 */
	public ArrayList<UserRanking> getCompetitors(){
		return competitionUsers;
	}
	/**
	 * Guarda i concatena en una String tota l'informacio dels competidors
	 */
	public String toString(){
		Collections.sort(competitionUsers, comparator);
		String ranking = new String();
		
		for (int i = 0; i<10 && i<competitionUsers.size();i++){
			ranking += competitionUsers.get(i).getNickname()+"/"+competitionUsers.get(i).getPunctuation()+"#";
		}
		
		return ranking;
	}
	/**
	 * Estableis la durada de la competicio i el temps de compta entere en funcio dels paramentres qu erep i del temps local.
	 */
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
	/**
	 * comprova si el nom de l'usuari que es vol inserir es GUEST.
	 */
	public static boolean checkNickname(String nickname){
		return !nickname.equals("GUEST");
	}
	/**
	 * comprova que el password que ha introduit l'usuari sigui valig peer registrar.
	 */
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
	/**
	 * bloqueja un usuari.
	 */
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
	/**
	 * elimina un usuari de la competicio.
	 */
	public void deleteUser(String nickname){
		for(int i=0; i<competitionUsers.size(); i++){
			if(competitionUsers.get(i).getNickname().equals(nickname)){
				competitionUsers.remove(i);
			}
		}
	}
	/**
	 * retorna totes les partides que un usuari concret ha jugat en una modalitat concreta.
	 */
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