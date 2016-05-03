package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import network.Server;
import view.MainViewServer;

public class MainViewControllerS implements ActionListener{

	private static MainViewServer view;
	
	public MainViewControllerS(MainViewServer view){
		this.view = view;
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		String message = new String();
		
		if (((JButton)e.getSource()).getText().equals("Create Competition")){
			message = "START:18/00/5";
			if(Server.sendCompetition(message)){
				MainViewServer.showMenu();
			}else{
				makeDialog("Server could not send the competition.",false);
			}
		}
		
		if (((JButton)e.getSource()).getText().equals("Competitors Register")){
			MainViewServer.showRegister();
		}
		
		if (((JButton)e.getSource()).getText().equals("Users Management")){
			MainViewServer.showUserManage();
		}
		
		if (((JButton)e.getSource()).getText().equals("Show Ranking")){
			MainViewServer.showRanking();
		}
		
		if (((JButton)e.getSource()).getText().equals("Show User Graphic")){
			MainViewServer.showUserGraph();
		}
		
		if (((JButton)e.getSource()).getText().equals("Menu")){
			MainViewServer.showMenu();
		}
	}
	
	public static void makeDialog(String message, boolean type){
		view.makeDialog(message,type);
	}

}
