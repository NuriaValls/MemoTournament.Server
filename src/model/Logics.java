package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import network.ConectorDB;

public class Logics {
	private ArrayList<UserRanking> usersRanking;
	
	
	public static boolean addUser(String message){
		String[] array = new String[2];
		
		message = message.substring(4);
		array = message.split("/");
		
		return ConectorDB.insertUser(array[0],array[1]);
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
				score = (int) user.getObject("score");
			}
			
			ConectorDB.insertGame(array[0],array[1],Integer.parseInt(array[2]));
			ConectorDB.updateScore(array[0],score);
			ok = true;
			
		} catch (SQLException e) {
			e.printStackTrace();
			ok = false;
		}

		return ok;
	}
	
	public static String createRanking(){
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
	}
	//public void toArray(createRanking(),usersRanking){
		
	//}
	
	
	//public static String startCompetition(){
		
	//}
}
