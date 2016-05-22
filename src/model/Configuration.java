package model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Configuration {
	private String portDB = new String();
	private String IP = new String();
	private String nameDB = new String();
	private String user = new String();
	private String password = new String();
	private int portClient;
	
	public boolean configurate(){
		Gson gson = new GsonBuilder().create();
		BufferedReader br;
		try {
		   br = new BufferedReader(new FileReader("config.json"));
		   Configuration aux = gson.fromJson(br, Configuration.class);
		   
		   this.portDB = aux.portDB;
		   this.IP = aux.IP;
		   this.nameDB = aux.nameDB;
		   this.user = aux.user;
		   this.password = aux.password;
		   this.portClient = aux.portClient;
		   
		  } catch (FileNotFoundException e) {
		   return false;
		  } catch (Exception e) {
		   e.printStackTrace();
		   return false;
		  }
		  return true;
	}

	public String getPortDB() {
		return portDB;
	}

	public String getIP() {
		return IP;
	}

	public String getNameDB() {
		return nameDB;
	}

	public String getUser() {
		return user;
	}

	public String getPassword() {
		return password;
	}

	public int getPortClient() {
		return portClient;
	}
}
