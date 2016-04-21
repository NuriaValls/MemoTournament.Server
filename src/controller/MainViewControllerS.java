package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import view.MainViewServer;

public class MainViewControllerS implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		
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

}
