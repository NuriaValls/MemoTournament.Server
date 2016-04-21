package view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.MainViewControllerS;

public class MainViewServer extends JFrame{
	
	private static JPanel menu;
	private static CardLayout cardLayout = new CardLayout();
	
	private static JPanel menuCard = new JPanel();
	private static JPanel registerCard = new JPanel();
	private static JPanel userManageCard = new JPanel();
	private static JPanel rankingCard = new JPanel();
	private static JPanel userGraphCard = new JPanel();
	
	private JPanel buttonMenu;
	private static JButton registerB = new JButton("Competitors Register");
	private static JButton userManageB = new JButton("Users Management");
	private static JButton rankingB = new JButton("Show Ranking");
	private static JButton userGraphB = new JButton("Show User Graphic");
	private static JButton backB = new JButton("Menu");
	
	private JLabel label = new JLabel("meh");
	
	public MainViewServer(){
		setTitle("Memory Tournament");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setSize(400, 300);
		
		createMenuCard();
		createRegisterCard();
		//createUserManageCard();
		//createRankingCard();
		//createUserGraphCard(); 
		
		menu = new JPanel();
		menu.setLayout(cardLayout);
		
		menu.add(menuCard, "1");
		menu.add(registerCard, "2");
		//menu.add(userManageCard, "3");
		//menu.add(rankingCard, "4");
		//menu.add(userGraphCard, "5");
		
		buttonMenu = new JPanel();
		
		buttonMenu.add(registerB);
		buttonMenu.add(userManageB);
		buttonMenu.add(rankingB);
		buttonMenu.add(userGraphB);
		buttonMenu.add(backB);
		
		buttonMenu.setVisible(true);
		
		add(buttonMenu, BorderLayout.SOUTH); 
		add(menu, BorderLayout.NORTH);
		pack();
	}
	
	public void registerController(MainViewControllerS actionListener){
		registerB.addActionListener(actionListener);
		userManageB.addActionListener(actionListener);
		rankingB.addActionListener(actionListener);
		userGraphB.addActionListener(actionListener);
		backB.addActionListener(actionListener);
	}
	
	public void createMenuCard(){
		menuCard.add(label);
	}
	
	public void createRegisterCard(){
		registerCard.add(new JLabel("nunu"));
	}
	
	public void createUserManageCard(){
		
	}
	
	public void createRankingCard(){
		
	}
	
	public void createUserGraphCard(){
		
	}
	
	public static void showRegister(){
		cardLayout.show(menu, "2");
		
		registerB.setVisible(false);
		userManageB.setVisible(false);
		rankingB.setVisible(false);
		userGraphB.setVisible(false);
		
		backB.setVisible(true); 
	}
	
	public static void showUserManage(){
		cardLayout.show(menu, "3");
		
		registerB.setVisible(false);
		userManageB.setVisible(false);
		rankingB.setVisible(false);
		userGraphB.setVisible(false);
		
		backB.setVisible(true);
	}
	
	public static void showRanking(){
		cardLayout.show(menu, "4");
		
		registerB.setVisible(false);
		userManageB.setVisible(false);
		rankingB.setVisible(false);
		userGraphB.setVisible(false);
		
		backB.setVisible(true);
	}
	
	public static void showUserGraph(){
		cardLayout.show(menu, "5");
		
		registerB.setVisible(false);
		userManageB.setVisible(false);
		rankingB.setVisible(false);
		userGraphB.setVisible(false);
		
		backB.setVisible(true);
	}
	
	public static void showMenu(){
		cardLayout.show(menu, "1");
		
		registerB.setVisible(true);
		userManageB.setVisible(true);
		rankingB.setVisible(true);
		userGraphB.setVisible(true);
		
		backB.setVisible(false);
	}
}
