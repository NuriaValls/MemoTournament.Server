package view;

import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainViewServer extends JFrame{
	
	JPanel menu;
	
	JPanel registerCard = new JPanel();
	JPanel userManageCard = new JPanel();
	JPanel rankingCard = new JPanel();
	JPanel userGraphCard = new JPanel();
	
	public MainViewServer(){
		setTitle("Memory Tournament");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
		createRegisterCard();
		createUserManageCard();
		createRankingCard();
		createUserGraphCard();
		
		menu = new JPanel(new CardLayout());
		
		menu.add(registerCard, "Competitors Register");
		menu.add(userManageCard, "Users Managenement");
		menu.add(rankingCard, "Show Ranking");
		menu.add(userGraphCard, "Show Graphic");
		
	}
	
	public void createRegisterCard(){
		
	}
	
	public void createUserManageCard(){
		
	}
	
	public void createRankingCard(){
		
	}
	
	public void createUserGraphCard(){
		
	}
}
