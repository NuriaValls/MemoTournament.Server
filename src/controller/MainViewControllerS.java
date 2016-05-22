package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JMenuItem;

import model.Logics;
import model.Time;
import model.UserRanking;
import network.ConectorDB;
import network.ServerS;
import view.MainViewServer;
/**
 * Classe que escolta els listeners vinculats a la vista del servidor i gestiona els events.
 * @author nvall
 *
 */
public class MainViewControllerS implements ActionListener{
	/**
	 * INstancia de la vista.
	 */
	private static MainViewServer view;
	/**
	 * Instancia de la comunicaio amb el client.
	 */
	private ServerS server;
	/**
	 * Instancia de laa logica del programa.
	 */
	private Logics logics;
	
	public MainViewControllerS(MainViewServer view, ServerS server, Logics logics){
		this.view = view;
		this.server = server;
		this.logics = logics;
	}
	/**
	 * Metode que gestiona els events creats per els listeners d ela vista.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		
		String message = new String();  
		
		if (e.getSource().toString().startsWith("javax.swing.JButton")){
			
			if (((JButton)e.getSource()).getText().equals("Create Competition")){
				if (logics.createTimeComp(view.getHourConfig(),view.getMinuteConfig(),view.getDurationConfig())){
					message = "START:"+(int)logics.getDifference()+"/"+logics.getDuration();
					makeDialog("The countdown fot the competition has started!", true);
					logics.setCompetition(true);
					view.showMenu();
				}else{
					makeDialog("The time of the competition is not valid.", false);
				}
			}
			
			if (((JButton)e.getSource()).getText().equals("Competitors Register")){
				view.showRegister();
			}
			
			if (((JButton)e.getSource()).getText().equals("Register")){
				if(logics.checkNickname(view.getNickname()) && logics.checkPasword(view.getPasword())){
					ConectorDB.insertUser(view.getNickname(), view.getPasword());
					makeDialog("The user has been successfuly registered!", true);
					view.showMenu();
				}else{
					makeDialog("The user couldn't be registered.", false);
				}
			}
			
			if (((JButton)e.getSource()).getText().equals("Users Management")){
				view.showUserManage();
			}
			
			if (((JButton)e.getSource()).getText().equals("Show Ranking")){
				view.showRanking();
			}
			
			if (((JButton)e.getSource()).getText().equals("Show User Graphic")){
				refreshAllUsers();
				view.showUserGraph();
			}
			
			if (((JButton)e.getSource()).getText().equals("Menu")){
				view.showMenu();
			}
		
		}else{
			
			if (((JMenuItem)e.getSource()).getText().equals("Block")){
				String userNickname = new String(view.getSelectedUser(true));
				logics.blockUser(userNickname);
				refreshList();
			}
			
			if (((JMenuItem)e.getSource()).getText().equals("Delete")){
				String userNickname = new String(view.getSelectedUser(true));
				logics.deleteUser(userNickname);
				refreshList();
			}
		}
	}
	/**
	 * metode que crea un dialg amb un missatge concret i una tipologia.
	 */
	public static void makeDialog(String message, boolean type){
		view.makeDialog(message,type);
	}
	/**
	 * Metode que crea un ranquing i el passa a la vista perque el mostri.
	 */
	public String createRankingServer(){
		String ranking = new String(logics.toString());
		view.refreshRanking(ranking);
		return ranking;
	}
	/**
	 * Retorna si hi ha una competico acrualment iniciada.
	 */
	public boolean getComp(){
		return logics.getCompetition();
	}
	/**
	 * Crea un missatge en cas que hi haigui una competico iniciada amb el temps de competicio per enviar al client.
	 */
	public String setStartMessage(){
		String message = "START:"+(int)logics.getDifference()+"/"+logics.getDuration();
		return message;
	}
	/**
	 * Mtode que actualitza el temps actual de competicio i el passa a la vista per mostrar-lo.
	 */
	public void refreshTime(int time, boolean comp){
		String print = new String();
		String hour = new String();
		String min = new String();
		String sec = new String();
		if(time/3600<10){
			hour = "0"+time/3600+":";
		}else{
			hour = time/3600+":";
		}
		if((time%3600)/60<10){
			min = "0"+(time%3600)/60+":";
		}else{
			min = (time%3600)/60+":";
		}
		if((time%3600)%60<10){
			sec = "0"+(time%3600)%60+" ";
		}else{
			sec = (time%3600)%60+" ";
		}
		
		if(comp){
			print = "The competition will end in: "+min+sec+"minutes.";
		}else{
			print = "The competition will start in: "+hour+min+sec+"hours.";
		}
		view.refreshTime(print);
	}
	/**
	 * Metode que actualitza la llista d'usuaris a la competicio i li passa a la vista.
	 */
	public void refreshList(){
		ArrayList<UserRanking> competitionUsers;
		competitionUsers = logics.getCompetitors();
		view.refreshList(competitionUsers);
	}
	/**
	 * Metode qua acutalitza una llista amb tots els usuaris i li pass a la vista.
	 */
	public void refreshAllUsers(){
		ArrayList<UserRanking> allUsers;
		allUsers = logics.toArray();
		if(!allUsers.isEmpty()){
			view.refreshAllUsers(allUsers);
		}
	}
	/**
	 * Metode que actualitza el competidor amb mes punts de la competicio i li passa a la vista.
	 */
	public void refreshTop1(){
		ArrayList<UserRanking> top = logics.getCompetitors();
		String top1 = new String("The best player of the competition is "+top.get(0).getNickname()+" with "+top.get(0).getPunctuation()+" points.");
		view.refreshTop1(top1);
	}
	/**
	 * Comptova si un usuari esta bloquejat.
	 */
	public boolean checkBlocked(String message){
		return logics.checkBlocked(message);
	}
	/**
	 * Actualitza la informacio del grafic i li envia a la vista.
	 */
	public void refreshGraphicInfo(String nickname){
		int[] arrayM = new int[15];
		int[] arrayC = new int[15];
		
		arrayM = logics.getGames(nickname, false);
		arrayC = logics.getGames(nickname, true);
		
		view.refreshGraphic(arrayM, arrayC);
	}
}
