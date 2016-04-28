package model;

import java.sql.ResultSet;
import java.sql.SQLException;

import network.ConectorDB;

public class Logics {
	
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
			if(user.getObject("pasword").equals(array[1])){
				ok = true;
			}else{
				ok = false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ok;
	}
}
