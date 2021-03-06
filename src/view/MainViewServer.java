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
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import com.sun.javafx.geom.Rectangle;

import model.UserRanking;
import controller.MainViewControllerS;

/**
 * Crea el Frame corresponent a la vista del servidor, fa visibles o oculta les diferents cartes i
 * llegeix les interaccions de l'usuari amb la vista.
 * @author nvall
 *
 */
public class MainViewServer extends JFrame{

	/**
	 * instancia del controlador.
	 */
	private MainViewControllerS controller;
	
	/**
	 * les T_ son constants per el jslider
	 */
	static final int T_MIN = 0;
	static final int T_MAX = 60;
	static final int T_INIT = 30;  
	private static JPanel jpMenu;
	private static CardLayout cardLayout = new CardLayout();
	
	/**
	 * Atributs que serveixen per mostrar els panells que toquin en cada carta.
	 */
	private static JPanel jpConfigCard = new JPanel();
	private static JPanel jpMenuCard = new JPanel();
	private static JPanel jpRegisterCard = new JPanel();
	private static JPanel jpUserManageCard = new JPanel();
	private static JPanel jpRankingCard = new JPanel();
	private static JPanel jpUserGraphCard = new JPanel();
	
	
	/**
	 * Atribut per poder mostrar el valor de el slider de la duracio de la competicio.
	 */
	private ChangeListener clSlider;
	
	private JPanel jpButtonMenu;
	
	
	/**
	 * Atributs que serveixen per posar els botons a les cartes pertintnts
	 */
	private static JButton jbCompetition = new JButton("Create Competition");
	private static JButton jbRegister = new JButton("Competitors Register");
	private static JButton jbRegisterUser = new JButton("Register");
	private static JButton jbUserManage = new JButton("Users Management");
	private static JButton jbRanking = new JButton("Show Ranking");
	private static JButton jbUserGraph = new JButton("Show User Graphic");
	private static JButton jbBack = new JButton("Menu");
	
	
	/**
	 * Atributs que serveixen per mostrar el temps de competicio i el millor jugador.
	 */
	//atributs menu
	private JLabel tempsmenu = new JLabel(" X mins ");
	private JLabel bestplayer = new JLabel("There are no users on the competition.");
	
	
	/**
	 * Atributs de la Configuration Card que serveixen per fer poder seleccionar l'hora i minut de la partida i la seva duracio.
	 */
	//atributs de config
	private JComboBox starthlist;
	private String [] starthstring = { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23"};
	private JComboBox startmlist;
	private String [] startmstring = { "00", "05", "10", "15", "20", "25", "30", "35", "40", "45", "50", "55" };
	private JLabel jlselecttime;
	private JSlider jsduration;
	
	
	/**
	 * Atributs del raking.
	 */
	//atributs ranking
	private JPanel panell;
	private JTable table;
	private String[] columnNames = {"NickName","Score",};
	private JPanel title;
	
	
	/**
	 * Atributs de la carta de configuracio per poder afegir noms i contraseya d'un jugador.
	 */
	//atributs registre
	private JTextField jtfnickname;
	private JPasswordField jpfpassword;
	private JTextField jtfname;
	private JTextField jtflastname;
	/**
	 * atributs per la carta de user manage.
	 */
	//atributs manage
	private String[] columnNamesL = {"NickName"};
	private JTable tableL;
	private JScrollPane panellL;
	private JMenuItem jmiblock;
	private JMenuItem jmidelete;
	/**
	 * atrobuts per la carte de mostrar el grafic dels usuaris.
	 */
	//atributs graphic
	private String[] columnNamesG = {"Users"};
	private JTable jtabUsers;
	private Grafic g1;
	private Grafic g2;
	
	/**
	 * Construeix el Frame i inicialitza cadascuna de les Cards mostrant la primera d'elles.
	 */
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
	
 	/**
 	 * Vincula el controlador de la vista amb els elements que l'usuari pot utilitzar per interactuar amb la vista.
 	 * @param actionListener
 	 */
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
	
	/**
	 * Crea la vista de la carta de configuracio del servidor.
	 */
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
	
	/**
	 * Retorna el valor corresponent a l'element seleccionat en la JComboBox corresponent a la hora d'inici de la competicio.
	 * @return
	 */
	public int getHourConfig(){
		return Integer.parseInt(starthstring[starthlist.getSelectedIndex()]);
	}
	
	/**
	 * Retorna el valor corresponent a l'element seleccionat en la JComboBoc corresponent al minut d'inici de la competicio.
	 * @return
	 */
	public int getMinuteConfig(){
		return Integer.parseInt(startmstring[startmlist.getSelectedIndex()]);
	}
	
	/**
	 * Retorna el valor corresponent a l'element JSlider que correspon a la duracio en minuts de la competicio.
	 * @return
	 */
	public int getDurationConfig(){
		return jsduration.getValue();
	}
	
	/**
	 * Crea la vista de la carta que mostrara el menu principal del servidor.
	 */
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

		Border border2 = BorderFactory.createLineBorder(Color.CYAN, 2);
		//JLabel jlplayer = new JLabel("Best Player", SwingConstants.CENTER);
		//jlplayer.setFont(new java.awt.Font("Geneva", 1, 14));
		bestplayer.setFont(new java.awt.Font("Geneva", 1, 14));
		bestplayer.setBackground(Color.WHITE);
		bestplayer.setOpaque(true);
		bestplayer.setBorder(border2);
		JPanel jpplayer = new JPanel();
		//jpplayer.setLayout(new GridLayout(1,4));
		//jpplayer.add(new JPanel());
		//jpplayer.add(jlplayer);
		//jpplayer.add(new JPanel());
		jpplayer.add(bestplayer);
		//jpplayer.add(new JPanel());
		
		jptcompetition.setAlignmentX(Component.CENTER_ALIGNMENT);
		jpplayer.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		jpmenu.add(jptcompetition);
		jpmenu.add(Box.createVerticalStrut(25));
		jpmenu.add(jpplayer);
		
		jpmenu.setAlignmentX(Component.CENTER_ALIGNMENT);
		titol.add(jpmenu);
		jpMenuCard.add(titol);
		
	}
	/**
	 * actualitza el temps de la competicio.
	 */
	public void refreshTime(String time){
		tempsmenu.setText(time);
	}
	/**
	 * actualitza l'usuari amb mes puntuacio de la competicio.
	 */
	public void refreshTop1(String top1){
		bestplayer.setText(top1);
	}
	
	
	/**
	 * Crea la vista de la carta del registre on s'introdueixen les dades de l'usuari que es vol afegir.
	 */
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
	/**
	 * Aquest metode actualitza la llista de tots els usuaris de la competicio amb les seves respectives puntuacions.
	 * @param competitionUsers
	 */
	public void refreshList(ArrayList<UserRanking> competitionUsers){
		
		if(!competitionUsers.isEmpty()){
			String matrix[][] = new String [competitionUsers.size()][1];
			for(int i=0;i<competitionUsers.size();i++){
				if(competitionUsers.get(i).getBlocked()){
					matrix[i][0] = competitionUsers.get(i).getNickname()+" /blocked";
				}else{
					matrix[i][0] = competitionUsers.get(i).getNickname();
				}
			}
			tableL.setEnabled(true);
			
			tableL.clearSelection();
			DefaultTableModel model = new DefaultTableModel(matrix,columnNamesL);
			tableL.setModel(model);
			model.fireTableDataChanged();
		}else{
			String matrix[][] = new String [1][1];
			matrix[0][0] = "There are no competitors registered on the competition.";
			tableL.setEnabled(false);
			
			tableL.clearSelection();
			DefaultTableModel model = new DefaultTableModel(matrix,columnNamesL);
			tableL.setModel(model);
			model.fireTableDataChanged();
		}
	}
	/**
	 * Aquest metode genera la vista per a gestionar els usuaris. Es mostra una llista amb tots els usuaris on en podrem afegir-ne mes o eliminar algun directament.
	 */
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
	/**
	 * Retorna el nom de l'usuari seleccionat de la tauala corresponent a la carta de manage o de graphic segons infica el boolea.
	 */
	public String getSelectedUser(boolean manage){
		int index;
		int column;
		String selectedUser = new String();
		
		if (manage){
			index = tableL.getSelectedRow();
			column = tableL.getSelectedColumn();
			if(index != -1){
				selectedUser = tableL.getModel().getValueAt(index, column).toString();
			}else{
				makeDialog("Select a user and then click the left button.", true);
			}
		}else{
			index = jtabUsers.getSelectedRow();
			if(index != -1){
				selectedUser = jtabUsers.getModel().getValueAt(index, 1).toString();
			}else{
				makeDialog("Select a user and then click the left button.", true);
			}
		}
		return selectedUser;
	}
	/**
	 * Actualitza la vista del Ranking a partir de l'String sTopTen que contindra els 10 millors usuaris.
	 * @param sTopTen : String que conte en nickname i la puntuacio dels usuaris concatenats.
	 */
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
	/**
	 * Genera la vista del Ranking sense omplirlo.
	 */
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
	/**
	 * Actualitza la matriu dels usuaris amb les seves funcions desde l'array allUsers.
	 * @param allUsers
	 */
	public void refreshAllUsers(ArrayList<UserRanking> allUsers){
		String matrix[][] = new String [allUsers.size()][1];
		for(int i=0;i<allUsers.size();i++){
			matrix[i][0] = allUsers.get(i).getNickname();
		}
		
		DefaultTableModel model = new DefaultTableModel(matrix,columnNamesG);
		jtabUsers.setModel(model);
		model.fireTableDataChanged();
	}
	/**
	 * Crea les dues grafiques en funcio dels usuaris seleccionats.
	 */
	public void createUserGraphCard(){
		
		JPanel title = new JPanel();
		title.setLayout(new BoxLayout(title, BoxLayout.PAGE_AXIS));
		JLabel nameTitle = new JLabel("User Graphic");
		nameTitle.setFont(new java.awt.Font("Geneva", 1, 34));	
		title.add(Box.createVerticalStrut(15));
		nameTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
		title.add(nameTitle);
		title.add(Box.createVerticalStrut(55));
		
		JPanel jpUGC = new JPanel(new GridLayout(1,8));
		JPanel jpGraph = new JPanel(); 
		
        g1 = new Grafic();
        //g2 = new Grafic();
    
		
		String[][] list = new String [14][1];
		
		jtabUsers = new JTable(list,columnNamesG);
		jtabUsers.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
			public void valueChanged(ListSelectionEvent e){
				if(!e.getValueIsAdjusting()){
					controller.refreshGraphicInfo(getSelectedUserG());
				}
			}
		});
		
		JScrollPane panell = new JScrollPane(jtabUsers);
		panell.setMaximumSize(new Dimension(100, 200));
		panell.setWheelScrollingEnabled(true);
		panell.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		jpGraph.setAlignmentX(Component.CENTER_ALIGNMENT);
		jpUGC.setPreferredSize(new Dimension(1000, 300));
		
		jpUGC.add(new JPanel());
		jpUGC.add(panell);
		jpUGC.add(new JPanel());
		g1.setPreferredSize(new Dimension(250, 300));
		g1.setAlignmentY(Component.LEFT_ALIGNMENT);
		//g2.setPreferredSize(new Dimension(250, 300));
		//g2.setAlignmentX(Component.CENTER_ALIGNMENT);
		jpUGC.add(g1);
		jpUGC.add(new JPanel());
		jpUGC.add(new JPanel());
		//jpUGC.add(g2);
		jpUGC.add(new JPanel());
		jpUGC.add(new JPanel());
		

		jpUGC.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.add(jpUGC);
      
        jpUserGraphCard.add(title);
	}
	/**
	 * retonra el nom de l'usuari seleccionat a la taula de la carta de la grafica.
	 */
	public String getSelectedUserG(){
		int index = jtabUsers.getSelectedRow();
		int column = jtabUsers.getSelectedColumn();
		return jtabUsers.getModel().getValueAt(index, column).toString();
	}
	/**
	 * actualitza els valors de les puntuacions des usuaris i pinta els grafics.
	 */
	public void refreshGraphic(int[] arrayM, int[] arrayC){
		g1.setInfo(arrayM, arrayC);
		g1.repaint();
		//g2.setInfo(arrayC, false);
		//g2.repaint();
	}
	
	/**
	 * mostra la carta de registre.
	 */
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
	/**
	 * mostra la carte de gestio d'usuaris.
	 */
	public void showUserManage(){
		cardLayout.show(jpMenu, "4");
		
		jbRegister.setVisible(false);
		jbUserManage.setVisible(false);
		jbRanking.setVisible(false);
		jbUserGraph.setVisible(false);
		
		jbBack.setVisible(true);
	}
	/**
	 * mostra la carta de ranquing.
	 */
	public void showRanking(){
		cardLayout.show(jpMenu, "5");
		
		jbRegister.setVisible(false);
		jbUserManage.setVisible(false);
		jbRanking.setVisible(false);
		jbUserGraph.setVisible(false);
		
		jbBack.setVisible(true);
	}
	/**
	 * mostra la carta de grafics.
	 */
	public void showUserGraph(){
		cardLayout.show(jpMenu, "6");
		
		jbRegister.setVisible(false);
		jbUserManage.setVisible(false);
		jbRanking.setVisible(false);
		jbUserGraph.setVisible(false);
		
		jbBack.setVisible(true);
	}
	/**
	 * mostra la carta de menu principal.
	 */
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
	/**
	 * crea un dialeg amb un missatge concret i una tipologia definida per un boolea.
	 */
	public void makeDialog(String message, boolean type){
		if(type){
			Dialog.DialogOK(message);
		}else{
			Dialog.DialogKO(message);
		}
	}
}
