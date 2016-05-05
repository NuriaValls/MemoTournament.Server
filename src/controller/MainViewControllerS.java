package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import model.Logics;
import network.ServerS;
import view.MainViewServer;

public class MainViewControllerS implements ActionListener{

	private MainViewServer view;
	
	private ServerS server;
	
	public MainViewControllerS(MainViewServer view, ServerS server, Logics logics){
		this.view = view;
		this.server = server;
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		String message = new String();
		
		if (((JButton)e.getSource()).getText().equals("Create Competition")){
			message = "START:18/00/5";
			if(server.sendCompetition(message)){
				view.showMenu();
			}else{
				makeDialog("Server could not send the competition.",false);
			}
		}
		
		if (((JButton)e.getSource()).getText().equals("Competitors Register")){
			view.showRegister();
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
	
	public void makeDialog(String message, boolean type){
		view.makeDialog(message,type);
	}

}
