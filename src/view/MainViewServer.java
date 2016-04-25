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
	
	private static JPanel jpMenu;
	private static CardLayout cardLayout = new CardLayout();
	
	private static JPanel jpMenuCard = new JPanel();
	private static JPanel jpRegisterCard = new JPanel();
	private static JPanel jpUserManageCard = new JPanel();
	private static JPanel jpRankingCard = new JPanel();
	private static JPanel jpUserGraphCard = new JPanel();
	
	private JPanel jpButtonMenu;
	
	private static JButton jbRegister = new JButton("Competitors Register");
	private static JButton jbUserManage = new JButton("Users Management");
	private static JButton jbRanking = new JButton("Show Ranking");
	private static JButton jbUserGraph = new JButton("Show User Graphic");
	private static JButton jbBack = new JButton("Menu");
	
	private JLabel label = new JLabel("meh");
	
	public MainViewServer(){
		setTitle("Memory Tournament");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setSize(400, 300);
		
		createMenuCard();
		createRegisterCard();
		createUserManageCard();
		createRankingCard();
		createUserGraphCard(); 
		
		jpMenu = new JPanel();
		jpMenu.setLayout(cardLayout);
		
		jpMenu.add(jpMenuCard, "1");
		jpMenu.add(jpRegisterCard, "2");
		jpMenu.add(jpUserManageCard, "3");
		jpMenu.add(jpRankingCard, "4");
		jpMenu.add(jpUserGraphCard, "5");
		
		jpButtonMenu = new JPanel();
		
		jpButtonMenu.add(jbRegister);
		jpButtonMenu.add(jbUserManage);
		jpButtonMenu.add(jbRanking);
		jpButtonMenu.add(jbUserGraph);
		jpButtonMenu.add(jbBack);
		
		jbBack.setVisible(false);
		jpButtonMenu.setVisible(true);
		
		add(jpButtonMenu, BorderLayout.SOUTH); 
		add(jpMenu, BorderLayout.NORTH);
		pack();
	}
	
	public void registerController(MainViewControllerS actionListener){
		jbRegister.addActionListener(actionListener);
		jbUserManage.addActionListener(actionListener);
		jbRanking.addActionListener(actionListener);
		jbUserGraph.addActionListener(actionListener);
		jbBack.addActionListener(actionListener);
	}
	
	public void createMenuCard(){
		jpMenuCard.add(label);
	}
	
	public void createRegisterCard(){
		jpRegisterCard.add(new JLabel("nunu"));
	}
	
	public void createUserManageCard(){
		
	}
	
	public void createRankingCard(){
		
	}
	
	public void createUserGraphCard(){
		
	}
	
	public static void showRegister(){
		cardLayout.show(jpMenu, "2");
		
		jbRegister.setVisible(false);
		jbUserManage.setVisible(false);
		jbRanking.setVisible(false);
		jbUserGraph.setVisible(false);
		
		jbBack.setVisible(true); 
	}
	
	public static void showUserManage(){
		cardLayout.show(jpMenu, "3");
		
		jbRegister.setVisible(false);
		jbUserManage.setVisible(false);
		jbRanking.setVisible(false);
		jbUserGraph.setVisible(false);
		
		jbBack.setVisible(true);
	}
	
	public static void showRanking(){
		cardLayout.show(jpMenu, "4");
		
		jbRegister.setVisible(false);
		jbUserManage.setVisible(false);
		jbRanking.setVisible(false);
		jbUserGraph.setVisible(false);
		
		jbBack.setVisible(true);
	}
	
	public static void showUserGraph(){
		cardLayout.show(jpMenu, "5");
		
		jbRegister.setVisible(false);
		jbUserManage.setVisible(false);
		jbRanking.setVisible(false);
		jbUserGraph.setVisible(false);
		
		jbBack.setVisible(true);
	}
	
	public static void showMenu(){
		cardLayout.show(jpMenu, "1");
		
		jbRegister.setVisible(true);
		jbUserManage.setVisible(true);
		jbRanking.setVisible(true);
		jbUserGraph.setVisible(true);
		
		jbBack.setVisible(false);
	}
}
