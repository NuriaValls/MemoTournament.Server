package view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import com.sun.javafx.geom.Rectangle;

import model.UserRanking;
import controller.MainViewControllerS;

public class MainViewServer extends JFrame{
	
	private MainViewControllerS controller;
	
	static final int T_MIN = 0;
	static final int T_MAX = 60;
	static final int T_INIT = 30;  
	private static JPanel jpMenu;
	private static CardLayout cardLayout = new CardLayout();
	
	private static JPanel jpConfigCard = new JPanel();
	private static JPanel jpMenuCard = new JPanel();
	private static JPanel jpRegisterCard = new JPanel();
	private static JPanel jpUserManageCard = new JPanel();
	private static JPanel jpRankingCard = new JPanel();
	private static JPanel jpUserGraphCard = new JPanel();
	
	private ChangeListener clSlider;
	
	private JPanel jpButtonMenu;
	
	private static JButton jbCompetition = new JButton("Create Competition");
	private static JButton jbRegister = new JButton("Competitors Register");
	private static JButton jbRegisterUser = new JButton("Register");
	private static JButton jbUserManage = new JButton("Users Management");
	private static JButton jbRanking = new JButton("Show Ranking");
	private static JButton jbUserGraph = new JButton("Show User Graphic");
	private static JButton jbBack = new JButton("Menu");
	
	//atributs menu
	
	private JLabel tempsmenu = new JLabel(" X mins ");
	private JLabel bestplayer = new JLabel("Nom Jugador", SwingConstants.LEFT);
	
	//atributs de config
	private JComboBox starthlist;
	private String [] starthstring = { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23"};
	private JComboBox startmlist;
	private String [] startmstring = { "00", "05", "10", "15", "20", "25", "30", "35", "40", "45", "50", "55" };
	private JLabel jlselecttime;
	private JSlider jsduration;
	
	//atributs ranking
	private JPanel panell;
	private JTable table;
	private String[] columnNames = {"NickName","Score",};
	private JPanel title;
	
	//atributs registre
	private JTextField jtfnickname;
	private JPasswordField jpfpassword;
	private JTextField jtfname;
	private JTextField jtflastname;
	
	//atributs manage
	private String[] columnNamesL = {"NickName"};
	private JTable tableL;
	private JScrollPane panellL;
	private JMenuItem jmiblock;
	private JMenuItem jmidelete;
	
	//atributs graphic
	private String[] columnNamesG = {"Users"};
	private JTable jtabUsers;
	
 	public MainViewServer(){
		setTitle("MemoTournament");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setSize(1000, 500);
		
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
		jpButtonMenu.add(jbRegisterUser);
		
		jbRegister.setVisible(false);
		jbRegisterUser.setVisible(false);
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
		jbRegisterUser.addActionListener(actionListener);
		
		jmiblock.addActionListener(actionListener);
		jmidelete.addActionListener(actionListener);
		
		this.controller = actionListener;
	}
	
	public void createConfigCard(){
		
		JPanel titol = new JPanel();
		
		titol.setLayout(new BoxLayout(titol, BoxLayout.PAGE_AXIS));
	
		JLabel nomtitol = new JLabel("New Competition");
		nomtitol.setFont(new java.awt.Font("Geneva", 1, 34));
		titol.add(Box.createVerticalStrut(15));
		nomtitol.setAlignmentX(Component.CENTER_ALIGNMENT);
		titol.add(nomtitol);
		titol.add(Box.createVerticalStrut(20));
		JLabel textinfo = new JLabel("Set a time to beggin and the total duration of the competition.");
		textinfo.setFont(new java.awt.Font("Geneva", 2, 16));
		textinfo.setAlignmentX(Component.CENTER_ALIGNMENT);
		titol.add(textinfo);
		titol.add(Box.createVerticalStrut(40));
		
		JPanel config = new JPanel();
		config.setLayout(new BoxLayout(config, BoxLayout.PAGE_AXIS));
		
		JLabel jlstart = new JLabel("Start (hh/mm)");
		jlstart.setFont(new java.awt.Font("Geneva", 1, 14));
		
		starthlist = new JComboBox(starthstring);
		startmlist = new JComboBox(startmstring);
		
		JPanel jpstart = new JPanel();
		jpstart.setLayout(new GridLayout(1,5));
		jpstart.add(new JPanel());
		jpstart.add(jlstart);
		jpstart.add(starthlist);
		jpstart.add(startmlist);
		jpstart.add(new JPanel());
		
		JLabel jltime = new JLabel("Duration (mins)");
		jltime.setFont(new java.awt.Font("Geneva", 1, 14));
		
		jsduration = new JSlider(JSlider.HORIZONTAL, T_MIN, T_MAX, T_INIT);
		
		jsduration.setMajorTickSpacing(10);
		jsduration.setMinorTickSpacing(2);
		jsduration.setPaintTicks(true);
		jsduration.setPaintLabels(true);
		Font font = new Font("Geneva", 1, 12);
		jsduration.setFont(font);
		
		jlselecttime = new JLabel("30 mins",  SwingConstants.CENTER);
		jlselecttime.setFont(new java.awt.Font("Geneva", 1, 14));
		
		// common listener for all sliders
		clSlider = new ChangeListener(){
			@Override
			public void stateChanged(ChangeEvent event){
				// update text field when the slider value changes
				JSlider source = (JSlider) event.getSource();
				jlselecttime.setText("" + source.getValue() + " mins");
			}
		};
		
		jsduration.addChangeListener(clSlider);
		JPanel jptime = new JPanel();
		jptime.setLayout(new GridLayout(1,5));
		jptime.add(new JPanel());
		jptime.add(jltime);
		jptime.add(jsduration);
		jptime.add(jlselecttime);
		jptime.add(new JPanel());

		jpstart.setAlignmentX(Component.CENTER_ALIGNMENT);
		jptime.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		config.add(jpstart);
		config.add(Box.createVerticalStrut(25));
		config.add(jptime);
		
		config.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		titol.add(config);
		
		jpConfigCard.add(titol);
		
	}
	
	public int getHourConfig(){
		return Integer.parseInt(starthstring[starthlist.getSelectedIndex()]);
	}
	
	public int getMinuteConfig(){
		return Integer.parseInt(startmstring[startmlist.getSelectedIndex()]);
	}
	
	public int getDurationConfig(){
		return jsduration.getValue();
	}
	
	public void createMenuCard(){
		
		JPanel titol = new JPanel();
		titol.setLayout(new BoxLayout(titol, BoxLayout.PAGE_AXIS));
		JLabel nomtitol = new JLabel("Menu");
		nomtitol.setFont(new java.awt.Font("Geneva", 1, 34));
		titol.add(Box.createVerticalStrut(15));
		nomtitol.setAlignmentX(Component.CENTER_ALIGNMENT);
		titol.add(nomtitol);
		titol.add(Box.createVerticalStrut(10));
		
		JPanel jpmenu = new JPanel();
		jpmenu.setLayout(new BoxLayout(jpmenu, BoxLayout.PAGE_AXIS));
		
		Border border = BorderFactory.createLineBorder(Color.YELLOW, 2);
		tempsmenu.setFont(new java.awt.Font("Geneva", 1, 16));
		tempsmenu.setBackground(Color.WHITE);
		tempsmenu.setOpaque(true);
		tempsmenu.setBorder(border);
		
		JPanel jptcompetition = new JPanel();
		jptcompetition.add(tempsmenu);
	
		JLabel jlplayer = new JLabel("Best Player", SwingConstants.RIGHT);
		jlplayer.setFont(new java.awt.Font("Geneva", 1, 14));
		bestplayer.setFont(new java.awt.Font("Geneva", 1, 14));
		JPanel jpplayer = new JPanel();
		jpplayer.setLayout(new GridLayout(1,5));
		jpplayer.add(new JPanel());
		jpplayer.add(jlplayer);
		jpplayer.add(new JPanel());
		jpplayer.add(bestplayer);
		jpplayer.add(new JPanel());
		
		jptcompetition.setAlignmentX(Component.CENTER_ALIGNMENT);
		jpplayer.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		jpmenu.add(jptcompetition);
		jpmenu.add(Box.createVerticalStrut(25));
		jpmenu.add(jpplayer);
		
		jpmenu.setAlignmentX(Component.CENTER_ALIGNMENT);
		titol.add(jpmenu);
		jpMenuCard.add(titol);
		
	}
	
	public void refreshTime(String time){
		tempsmenu.setText(time);
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
		jtfname = new JTextField();
		JPanel jpname = new JPanel();
		jpname.setLayout(new GridLayout(1,4));
		jpname.add(new JPanel());
		jpname.add(jlname);
		jpname.add(jtfname);
		jpname.add(new JPanel());
		
		JLabel jllastname = new JLabel("Last Name");
		jllastname.setFont(new java.awt.Font("Geneva", 1, 14));
		jtflastname = new JTextField();
		JPanel jplastname = new JPanel();
		jplastname.setLayout(new GridLayout(1,4));
		jplastname.add(new JPanel());
		jplastname.add(jllastname);
		jplastname.add(jtflastname);
		jplastname.add(new JPanel());
		
		JLabel jlnickname = new JLabel("Nickname");
		jlnickname.setFont(new java.awt.Font("Geneva", 1, 14));
		jtfnickname = new JTextField();
		JPanel jpnickname = new JPanel();
		jpnickname.setLayout(new GridLayout(1,4));
		jpnickname.add(new JPanel());
		jpnickname.add(jlnickname);
		jpnickname.add(jtfnickname);
		jpnickname.add(new JPanel());
		
		JLabel jlpassword = new JLabel("Password");
		jlpassword.setFont(new java.awt.Font("Geneva", 1, 14));
		jpfpassword = new JPasswordField();
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
	
	public String getNickname(){
		return jtfnickname.getText();
	}
	
	@SuppressWarnings("deprecation")
	public String getPasword(){
		return jpfpassword.getText();
	}
	
	public void refreshList(ArrayList<UserRanking> competitionUsers){
		String matrix[][] = new String [competitionUsers.size()][1];
		boolean[] array = new boolean[competitionUsers.size()];
		
		for(int i=0;i<competitionUsers.size();i++){
			matrix[i][0] = competitionUsers.get(i).getNickname();
			if(competitionUsers.get(i).getBlocked()){
				array[i] = true;
			}
		}
		
		tableL.clearSelection();
		DefaultTableModel model = new DefaultTableModel(matrix,columnNamesL);
		tableL.setModel(model);
		model.fireTableDataChanged();
		
	}
	
	public void createUserManageCard(){
		JPopupMenu popup = new JPopupMenu();
		JMenu jm = new JMenu();
		JMenuItem iadd = new JMenuItem("Add");
		JMenuItem idelete = new JMenuItem("Delete");
		
		
		JPanel title = new JPanel();
		title.setLayout(new BoxLayout(title, BoxLayout.PAGE_AXIS));
		JLabel nameTitle = new JLabel("Users Management");
		nameTitle.setFont(new java.awt.Font("Geneva", 1, 34));
		title.add(Box.createVerticalStrut(15));
		nameTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
		title.add(nameTitle);
		title.add(Box.createVerticalStrut(15));
		
		String[][] list = new String [10][1];
		tableL = new JTable(list,columnNamesL);
		//tableL.setEnabled(false);
		
		
		JPopupMenu popupMenu = new JPopupMenu();
		jmiblock = new JMenuItem("Block");
		jmidelete = new JMenuItem("Delete");
		popupMenu.add(jmiblock);
		popupMenu.add(jmidelete);
		
		tableL.setComponentPopupMenu(popupMenu);
		
		panellL = new JScrollPane(tableL);

		panellL.setSize(500, 400);
		panellL.setPreferredSize(new Dimension(500, 250));
		panellL.setWheelScrollingEnabled(true);
		//panell.add(table);
		panellL.setAlignmentX(Component.CENTER_ALIGNMENT);
		title.add(panellL);
		jpUserManageCard.add(title);
	}
	
	public String getSelectedUser(boolean manage){
		int index;
		int column;
		String selectedUser;
		
		if (manage){
			index = tableL.getSelectedRow();
			column = tableL.getSelectedColumn();
			selectedUser = tableL.getModel().getValueAt(index, column).toString();
		}else{
			index = jtabUsers.getSelectedRow();
			selectedUser = jtabUsers.getModel().getValueAt(index, 1).toString();
		}
		return selectedUser;
	}

	public void refreshRanking(String sTopTen){
		String matrix[][] = new String [11][2];
		String[] users = sTopTen.split("#");
		int j = 0;
		for(int i=0;i<users.length;i++){				
			String[] aux = users[i].split("/");
			matrix[j] = aux;
			j++;
		}
		DefaultTableModel model = new DefaultTableModel(matrix,columnNames);
		table.setModel(model);
		model.fireTableDataChanged();
	}
	
	public void createRankingCard(){
		
		title = new JPanel();
		title.setLayout(new BoxLayout(title, BoxLayout.PAGE_AXIS));
		JLabel nameTitle = new JLabel("Top 10 Ranking");
		nameTitle.setFont(new java.awt.Font("Geneva", 1, 34));	
		title.add(Box.createVerticalStrut(15));
		nameTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
		title.add(nameTitle);
		title.add(Box.createVerticalStrut(15));
		
		panell = new JPanel();
		
		String [][] mTopTen = new String [10][2];
		table = new JTable(mTopTen, columnNames);
		
		table.setPreferredSize(new Dimension(500, 250));
		table.setOpaque(false);
		table.setAlignmentX(Component.CENTER_ALIGNMENT);
		panell.add(table);
		panell.setAlignmentX(Component.CENTER_ALIGNMENT);
		title.add(panell);
		jpRankingCard.add(title);
		
	}
	
	public void refreshAllUsers(ArrayList<UserRanking> allUsers){
		String matrix[][] = new String [allUsers.size()][1];
		for(int i=0;i<allUsers.size();i++){
			matrix[i][0] = allUsers.get(i).getNickname();
		}
		
		DefaultTableModel model = new DefaultTableModel(matrix,columnNamesG);
		jtabUsers.setModel(model);
		model.fireTableDataChanged();
	}
	
	public void createUserGraphCard(){
		
		JPanel title = new JPanel();
		title.setLayout(new BoxLayout(title, BoxLayout.PAGE_AXIS));
		JLabel nameTitle = new JLabel("User Graphic");
		nameTitle.setFont(new java.awt.Font("Geneva", 1, 34));	
		title.add(Box.createVerticalStrut(15));
		nameTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
		title.add(nameTitle);
		title.add(Box.createVerticalStrut(15));
		
		JPanel jpUGC = new JPanel(new GridLayout(1,4));
		JPanel jpGraph = new JPanel(); 
		JTabbedPane tabbedPane = new JTabbedPane();

        JComponent panel1 = makeTextPanel("Panel #1");
        Grafic g1 = new Grafic();
        //panel1.add(g1);
        tabbedPane.addTab("Tab 1", panel1);
        
        JComponent panel2 = makeTextPanel("Panel #2");
        Grafic g2 = new Grafic();
        //panel2.add(g2);
        tabbedPane.addTab("Tab 2", panel2);
        
        jpGraph.add(tabbedPane);
		
		String[][] list = new String [14][1];
		jtabUsers = new JTable(list,columnNamesG);
		
		JScrollPane panell = new JScrollPane(jtabUsers);
		
		panell.setPreferredSize(new Dimension(200, 300));
		panell.setWheelScrollingEnabled(true);
		panell.setAlignmentX(Component.LEFT_ALIGNMENT);
		jpGraph.setAlignmentX(Component.CENTER_ALIGNMENT);
		jpUGC.add(panell);
		jpUGC.add(new JPanel());
		jpUGC.add(jpGraph);
		jpUGC.add(new JPanel());
		
		jpUGC.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.add(jpUGC);
        
        Grafic g = new Grafic();
        jpUGC.add(g);
        jpUserGraphCard.add(title);
	}
	
	private void refreshGraphic(){
		
	}
	
	private JComponent makeTextPanel(String string) {
		// TODO Auto-generated method stub
		return null;
	}

	public void showRegister(){
		jtfnickname.setText("");
		jpfpassword.setText("");
		
		cardLayout.show(jpMenu, "3");
		
		jbRegister.setVisible(false);
		jbUserManage.setVisible(false);
		jbRanking.setVisible(false);
		jbUserGraph.setVisible(false);
		
		jbBack.setVisible(true);
		//jbRegisterUser.setLocation(0,0);
		jbRegisterUser.setVisible(true);
		
	}
	
	public void showUserManage(){
		cardLayout.show(jpMenu, "4");
		
		jbRegister.setVisible(false);
		jbUserManage.setVisible(false);
		jbRanking.setVisible(false);
		jbUserGraph.setVisible(false);
		
		jbBack.setVisible(true);
	}
	
	public void showRanking(){
		cardLayout.show(jpMenu, "5");
		
		jbRegister.setVisible(false);
		jbUserManage.setVisible(false);
		jbRanking.setVisible(false);
		jbUserGraph.setVisible(false);
		
		jbBack.setVisible(true);
	}
	
	public void showUserGraph(){
		cardLayout.show(jpMenu, "6");
		
		jbRegister.setVisible(false);
		jbUserManage.setVisible(false);
		jbRanking.setVisible(false);
		jbUserGraph.setVisible(false);
		
		jbBack.setVisible(true);
	}
	
	public void showMenu(){
		cardLayout.show(jpMenu, "2");
		
		jbRegister.setVisible(true);
		jbUserManage.setVisible(true);
		jbRanking.setVisible(true);
		jbUserGraph.setVisible(true);
		
		jbCompetition.setVisible(false);
		jbBack.setVisible(false);
		jbRegisterUser.setVisible(false);
	}
	
	public void makeDialog(String message, boolean type){
		if(type){
			Dialog.DialogOK(message);
		}else{
			Dialog.DialogKO(message);
		}
	}
}
