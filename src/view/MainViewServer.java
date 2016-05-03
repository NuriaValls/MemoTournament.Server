package view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import controller.MainViewControllerS;

public class MainViewServer extends JFrame{
	
	private static JPanel jpMenu;
	private static CardLayout cardLayout = new CardLayout();
	
	private static JPanel jpConfigCard = new JPanel();
	private static JPanel jpMenuCard = new JPanel();
	private static JPanel jpRegisterCard = new JPanel();
	private static JPanel jpUserManageCard = new JPanel();
	private static JPanel jpRankingCard = new JPanel();
	private static JPanel jpUserGraphCard = new JPanel();
	
	private JPanel jpButtonMenu;
	
	private static JButton jbCompetition = new JButton("Create Competition");
	private static JButton jbRegister = new JButton("Competitors Register");
	private static JButton jbUserManage = new JButton("Users Management");
	private static JButton jbRanking = new JButton("Show Ranking");
	private static JButton jbUserGraph = new JButton("Show User Graphic");
	private static JButton jbBack = new JButton("Menu");
	
	private JLabel label = new JLabel("meh");
	
	public MainViewServer(){
		setTitle("MemoTournament");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setSize(1000, 400);
		
		createConfigCard();
		createMenuCard();
		createRegisterCard();
		createUserManageCard();
		createRankingCard();
		createUserGraphCard(); 
		
		jpMenu = new JPanel();
		jpMenu.setLayout(cardLayout);
		
		jpMenu.add(jpConfigCard, "1");
		jpMenu.add(jpMenuCard, "2");
		jpMenu.add(jpRegisterCard, "3");
		jpMenu.add(jpUserManageCard, "4");
		jpMenu.add(jpRankingCard, "5");
		jpMenu.add(jpUserGraphCard, "6");
		
		jpButtonMenu = new JPanel();
		
		jpButtonMenu.add(jbCompetition);
		jpButtonMenu.add(jbRegister);
		jpButtonMenu.add(jbUserManage);
		jpButtonMenu.add(jbRanking);
		jpButtonMenu.add(jbUserGraph);
		jpButtonMenu.add(jbBack);
		
		jbRegister.setVisible(false);
		jbUserManage.setVisible(false);
		jbRanking.setVisible(false);
		jbUserGraph.setVisible(false);
		jbBack.setVisible(false);
		jpButtonMenu.setVisible(true);
		
		add(jpButtonMenu, BorderLayout.SOUTH); 
		add(jpMenu, BorderLayout.NORTH);
	}
	
	public void registerController(MainViewControllerS actionListener){
		jbCompetition.addActionListener(actionListener);
		jbRegister.addActionListener(actionListener);
		jbUserManage.addActionListener(actionListener);
		jbRanking.addActionListener(actionListener);
		jbUserGraph.addActionListener(actionListener);
		jbBack.addActionListener(actionListener);
	}
	
	public void createConfigCard(){
		
		JPanel titol = new JPanel();
		
		titol.setLayout(new BoxLayout(titol, BoxLayout.PAGE_AXIS));
	
		JLabel nomtitol = new JLabel("Server Configuration");
		nomtitol.setFont(new java.awt.Font("Geneva", 1, 34));
		titol.add(Box.createVerticalStrut(15));
		nomtitol.setAlignmentX(Component.CENTER_ALIGNMENT);
		titol.add(nomtitol);
		titol.add(Box.createVerticalStrut(25));
		
		JPanel config = new JPanel();
		config.setLayout(new BoxLayout(config, BoxLayout.PAGE_AXIS));
		
		JLabel jlname = new JLabel("Database");
		jlname.setFont(new java.awt.Font("Geneva", 1, 14));
		JTextField jtfname = new JTextField();
		JPanel jpname = new JPanel();
		jpname.setLayout(new GridLayout(1,4));
		jpname.add(new JPanel());
		jpname.add(jlname);
		jpname.add(jtfname);
		jpname.add(new JPanel());
		
		JLabel jluser = new JLabel("User");
		jluser.setFont(new java.awt.Font("Geneva", 1, 14));
		JTextField jtfuser = new JTextField();
		JPanel jpuser = new JPanel();
		jpuser.setLayout(new GridLayout(1,4));
		jpuser.add(new JPanel());
		jpuser.add(jluser);
		jpuser.add(jtfuser);
		jpuser.add(new JPanel());
		
		JLabel jlpassword = new JLabel("Password");
		jlpassword.setFont(new java.awt.Font("Geneva", 1, 14));
		JPasswordField jpfpassword = new JPasswordField();
		JPanel jppassword = new JPanel();
		jppassword.setLayout(new GridLayout(1,4));
		jppassword.add(new JPanel());
		jppassword.add(jlpassword);
		jppassword.add(jpfpassword);
		jppassword.add(new JPanel());
		
		JLabel jlport = new JLabel("Port");
		jlport.setFont(new java.awt.Font("Geneva", 1, 14));
		JTextField jtfport = new JTextField();
		JPanel jpport = new JPanel();
		jpport.setLayout(new GridLayout(1,4));
		jpport.add(new JPanel());
		jpport.add(jlport);
		jpport.add(jtfport);
		jpport.add(new JPanel());
		
		jpport.setAlignmentX(Component.CENTER_ALIGNMENT);
		jpname.setAlignmentX(Component.CENTER_ALIGNMENT);
		jppassword.setAlignmentX(Component.CENTER_ALIGNMENT);
		jpuser.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		config.add(jpname);
		config.add(jpuser);
		config.add(jppassword);
		config.add(jpport);
		
		config.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		titol.add(config);
		
		jpConfigCard.add(titol);
		
	}
	
	public void createMenuCard(){
		jpMenuCard.add(label);
	}
	
	public void createRegisterCard(){
		
JPanel titol = new JPanel();
		
		titol.setLayout(new BoxLayout(titol, BoxLayout.PAGE_AXIS));
	
		JLabel nomtitol = new JLabel("Register User");
		nomtitol.setFont(new java.awt.Font("Geneva", 1, 34));
		titol.add(Box.createVerticalStrut(15));
		nomtitol.setAlignmentX(Component.CENTER_ALIGNMENT);
		titol.add(nomtitol);
		titol.add(Box.createVerticalStrut(25));
		
		JPanel register = new JPanel();
		register.setLayout(new BoxLayout(register, BoxLayout.PAGE_AXIS));
		
		JLabel jlname = new JLabel("Name");
		jlname.setFont(new java.awt.Font("Geneva", 1, 14));
		JTextField jtfname = new JTextField();
		JPanel jpname = new JPanel();
		jpname.setLayout(new GridLayout(1,4));
		jpname.add(new JPanel());
		jpname.add(jlname);
		jpname.add(jtfname);
		jpname.add(new JPanel());
		
		JLabel jllastname = new JLabel("Last Name");
		jllastname.setFont(new java.awt.Font("Geneva", 1, 14));
		JTextField jtflastname = new JTextField();
		JPanel jplastname = new JPanel();
		jplastname.setLayout(new GridLayout(1,4));
		jplastname.add(new JPanel());
		jplastname.add(jllastname);
		jplastname.add(jtflastname);
		jplastname.add(new JPanel());
		
		JLabel jlnickname = new JLabel("Nickname");
		jlnickname.setFont(new java.awt.Font("Geneva", 1, 14));
		JTextField jtfnickname = new JTextField();
		JPanel jpnickname = new JPanel();
		jpnickname.setLayout(new GridLayout(1,4));
		jpnickname.add(new JPanel());
		jpnickname.add(jlnickname);
		jpnickname.add(jtfnickname);
		jpnickname.add(new JPanel());
		
		JLabel jlpassword = new JLabel("Password");
		jlpassword.setFont(new java.awt.Font("Geneva", 1, 14));
		JPasswordField jpfpassword = new JPasswordField();
		JPanel jppassword = new JPanel();
		jppassword.setLayout(new GridLayout(1,4));
		jppassword.add(new JPanel());
		jppassword.add(jlpassword);
		jppassword.add(jpfpassword);
		jppassword.add(new JPanel());
		
		JLabel jlage = new JLabel("Age");
		String [] agestring = { "1-10", "11-20", "21-30", "31-40", "41-50", "51-60", "61-70", "71-80", "81-90", "91-99"};
		JComboBox agelist = new JComboBox(agestring);
		//agelist.setEditable(true);
		JPanel jpage = new JPanel();
		jpage.setLayout(new GridLayout(1,4));
		jpage.add(new JPanel());
		jpage.add(jlage);
		jpage.add(agelist);
		jpage.add(new JPanel());
		
		jpnickname.setAlignmentX(Component.CENTER_ALIGNMENT);
		jpname.setAlignmentX(Component.CENTER_ALIGNMENT);
		jplastname.setAlignmentX(Component.CENTER_ALIGNMENT);
		jppassword.setAlignmentX(Component.CENTER_ALIGNMENT);
		jpage.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		register.add(jpname);
		register.add(jplastname);
		register.add(jpnickname);
		register.add(jppassword);
		register.add(jpage);
		
		register.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		titol.add(register);
		
		jpRegisterCard.add(titol);
	}
	
	public void createUserManageCard(){
		
	}
	
	public void createRankingCard(){
		
		JPanel title = new JPanel();
		JLabel nameTitle = new JLabel("Top 10 Ranking");
		nameTitle.setFont(new java.awt.Font("Geneva", 1, 34));
		//title.add(Box.createVerticalStrut(15));
		title.add(nameTitle);
		//title.add(Box.createVerticalStrut(25));
		title.setLayout(new FlowLayout());
		jpRankingCard.add(title, BorderLayout.PAGE_START);
		
		
		
		
		String[] columnNames = {"First Name",
                "Last Name",
                "Sport",
                "# of Years",
                "Vegetarian"};
		
		Object[][] data = {
			    {"Kathy", "Smith",
			     "Snowboarding", new Integer(5), new Boolean(false)},
			    {"John", "Doe",
			     "Rowing", new Integer(3), new Boolean(true)},
			    {"Sue", "Black",
			     "Knitting", new Integer(2), new Boolean(false)},
			    {"Jane", "White",
			     "Speed reading", new Integer(20), new Boolean(true)},
			    {"Joe", "Brown",
			     "Pool", new Integer(10), new Boolean(false)},
			    {"John", "Doe",
				     "Rowing", new Integer(3), new Boolean(true)},
				    {"Sue", "Black",
				     "Knitting", new Integer(2), new Boolean(false)},
				    {"Jane", "White",
				     "Speed reading", new Integer(20), new Boolean(true)},
				    {"Joe", "Brown",
				     "Pool", new Integer(10), new Boolean(false)},
				    {"John", "Doe",
					     "Rowing", new Integer(3), new Boolean(true)},
					    {"Sue", "Black",
					     "Knitting", new Integer(2), new Boolean(false)},
					    {"Jane", "White",
					     "Speed reading", new Integer(20), new Boolean(true)},
					    {"Joe", "Brown",
					     "Pool", new Integer(10), new Boolean(false)},
					    {"John", "Doe",
						     "Rowing", new Integer(3), new Boolean(true)},
						    {"Sue", "Black",
						     "Knitting", new Integer(2), new Boolean(false)},
						    {"Jane", "White",
						     "Speed reading", new Integer(20), new Boolean(true)},
						    {"Joe", "Brown",
						     "Pool", new Integer(10), new Boolean(false)},
						    {"John", "Doe",
							     "Rowing", new Integer(3), new Boolean(true)},
							    {"Sue", "Black",
							     "Knitting", new Integer(2), new Boolean(false)},
							    {"Jane", "White",
							     "Speed reading", new Integer(20), new Boolean(true)},
							    {"Joe", "Brown",
							     "Pool", new Integer(10), new Boolean(false)},
							    {"John", "Doe",
								     "Rowing", new Integer(3), new Boolean(true)},
								    {"Sue", "Black",
								     "Knitting", new Integer(2), new Boolean(false)},
								    {"Jane", "White",
								     "Speed reading", new Integer(20), new Boolean(true)},
								    {"Joe", "Brown",
								     "Pool", new Integer(10), new Boolean(false)}
			};
		
		JTable table = new JTable(data, columnNames);
		
		
		JScrollPane panell = new JScrollPane(table);
		panell.setSize(500, 400);
		panell.setPreferredSize(new Dimension(500, 300));

		
		panell.setWheelScrollingEnabled(true);
		
		
		
		/*JPanel pRanking = new JPanel();	
		GridLayout glRanking = new GridLayout(11,2);
		pRanking.setLayout(glRanking);
		
		//pRanking.add(top10);

		JLabel nickName = new JLabel("NICKNAME");
		nickName.setBorder(BorderFactory.createLineBorder(Color.black));
		pRanking.add(nickName);
		//pRanking.setBorder(new EmptyBorder(10, 10, 10, 10));
		JLabel punctuation = new JLabel("PUNTUACIO");
		punctuation.setBorder(BorderFactory.createLineBorder(Color.black));
		pRanking.add(punctuation);
		
		
		for(int i=0; i<20; i++){
			nickName = new JLabel("POL");
			//pRanking.add(nickName);
			nickName.setBorder(BorderFactory.createLineBorder(Color.black));
			pRanking.add(nickName);
			i++;
			punctuation = new JLabel("350");
			punctuation.setBorder(BorderFactory.createLineBorder(Color.black));
			pRanking.add(punctuation);
			//pRanking.add(punctuation);
		}
			
		//JScrollPane scrollPane = new JScrollPane();
		//scrollPane.setBorder(BorderFactory.createTitledBorder("Llista Paraules"));
		//pRanking.add(scrollPane);
		
		//pRanking.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		//pRanking.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		//pRanking.setLayout(new FlowLayout());
		jpRankingCard.setLayout(new BoxLayout(jpRankingCard,BoxLayout.PAGE_AXIS));
		//jpRankingCard.add(pRanking, BorderLayout.CENTER);
		jpRankingCard.add(pRanking);*/
		jpRankingCard.add(panell);
	}
	
	public void createUserGraphCard(){
		
	}
	
	public static void showRegister(){
		cardLayout.show(jpMenu, "3");
		
		jbRegister.setVisible(false);
		jbUserManage.setVisible(false);
		jbRanking.setVisible(false);
		jbUserGraph.setVisible(false);
		
		jbBack.setVisible(true); 
	}
	
	public static void showUserManage(){
		cardLayout.show(jpMenu, "4");
		
		jbRegister.setVisible(false);
		jbUserManage.setVisible(false);
		jbRanking.setVisible(false);
		jbUserGraph.setVisible(false);
		
		jbBack.setVisible(true);
	}
	
	public static void showRanking(){
		cardLayout.show(jpMenu, "5");
		
		jbRegister.setVisible(false);
		jbUserManage.setVisible(false);
		jbRanking.setVisible(false);
		jbUserGraph.setVisible(false);
		
		jbBack.setVisible(true);
	}
	
	public static void showUserGraph(){
		cardLayout.show(jpMenu, "6");
		
		jbRegister.setVisible(false);
		jbUserManage.setVisible(false);
		jbRanking.setVisible(false);
		jbUserGraph.setVisible(false);
		
		jbBack.setVisible(true);
	}
	
	public static void showMenu(){
		cardLayout.show(jpMenu, "2");
		
		jbRegister.setVisible(true);
		jbUserManage.setVisible(true);
		jbRanking.setVisible(true);
		jbUserGraph.setVisible(true);
		
		jbCompetition.setVisible(false);
		jbBack.setVisible(false);
	}
}
