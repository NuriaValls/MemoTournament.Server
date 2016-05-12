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
			message = "START:18/00/5";
			server.setStartMessage(message);
			view.showMenu();
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
	
	public String createRankingServer(){
		return logics.toArray();
	}

}
