package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;

import model.Logics;
import model.Time;
import model.UserRanking;
import network.ConectorDB;
import network.ServerS;
import view.MainViewServer;

public class MainViewControllerS implements ActionListener{

	private static MainViewServer view;
	private ServerS server;
	private Logics logics;
	
	public MainViewControllerS(MainViewServer view, ServerS server, Logics logics){
		this.view = view;
		this.server = server;
		this.logics = logics;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		String message = new String();  
		
		if (((JButton)e.getSource()).getText().equals("Create Competition")){
			if (logics.createTimeComp(view.getHourConfig(),view.getMinuteConfig(),view.getDurationConfig())){
				message = "START:"+(int)logics.getDifference()+"/"+logics.getDuration();
				makeDialog("The countdown fot the competition has started!", true);
				logics.setCompetition(true);
				view.showMenu();
			}else{
				makeDialog("The time of the competition is not valid.", false);
			}
		}
		
		if (((JButton)e.getSource()).getText().equals("Competitors Register")){
			view.showRegister();
		}
		
		if (((JButton)e.getSource()).getText().equals("Register")){
			if(logics.checkNickname(view.getNickname()) && logics.checkPasword(view.getPasword())){
				ConectorDB.insertUser(view.getNickname(), view.getPasword());
				makeDialog("The user has been successfuly registered!", true);
				view.showMenu();
			}else{
				makeDialog("The user couldn't be registered.", false);
			}
		}
		
		if (((JButton)e.getSource()).getText().equals("Users Management")){
			view.showUserManage();
		}
		
		if (((JButton)e.getSource()).getText().equals("Show Ranking")){
			view.showRanking();
		}
		
		if (((JButton)e.getSource()).getText().equals("Show User Graphic")){
			view.showUserGraph();
		}
		
		if (((JButton)e.getSource()).getText().equals("Menu")){
			view.showMenu();
		}
	}
	
	public static void makeDialog(String message, boolean type){
		view.makeDialog(message,type);
	}
	
	public String createRankingServer(){
		String ranking = new String(logics.toString());
		view.refreshRanking(ranking);
		return ranking;
	}
	
	public boolean getComp(){
		return logics.getCompetition();
	}
	
	public String setStartMessage(){
		String message = "START:"+(int)logics.getDifference()+"/"+logics.getDuration();
		return message;
	}

	public void refreshTime(int time, boolean comp){
		String print = new String();
		String hour = new String();
		String min = new String();
		String sec = new String();
		if(time/3600<10){
			hour = "0"+time/3600+":";
		}else{
			hour = time/3600+":";
		}
		if((time%3600)/60<10){
			min = "0"+(time%3600)/60+":";
		}else{
			min = (time%3600)/60+":";
		}
		if((time%3600)%60<10){
			sec = "0"+(time%3600)%60+" ";
		}else{
			sec = (time%3600)%60+" ";
		}
		
		if(comp){
			print = "The competition will end in: "+min+sec+"minutes.";
		}else{
			print = "The competition will start in: "+hour+min+sec+"hours.";
		}
		view.refreshTime(print);
	}
	
	public void refreshList(){
		ArrayList<UserRanking> competitionUsers;
		competitionUsers = logics.getCompetitors();
		if(!competitionUsers.isEmpty()){
			view.refreshList(competitionUsers);
		}
	}
	
	public void refreshAllUsers(){
		ArrayList<UserRanking> allUsers;
		allUsers = logics.toArray();
		if(!allUsers.isEmpty()){
			view.refreshList(allUsers);
		}
	}
}
