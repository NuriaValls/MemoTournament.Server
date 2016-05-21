package model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.Timer;

import controller.MainViewControllerS;

public class Time extends Thread{
	
	private Timer countdownTimer;
	private int countdown;
	private Timer competitionTimer;
	private int competition;
	
	private MainViewControllerS controller;
	
	public Time(){
		countdownTimer = new Timer(1000, new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (countdown > 0){
					countdown--;
					controller.refreshTime(countdown, false);
				}else{
					competitionTimer.start();
					countdownTimer.stop();
					controller.makeDialog("The competition has started!", true);
				}
			}
			
		});
		
		competitionTimer = new Timer(1000, new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (competition > 0){
					competition--;
					controller.refreshTime(competition, true);
				}else{
					competitionTimer.stop();
				}
			}
			
		});
	}
	
	public void startCompetitionTime(int countdown, int competition){
		//this.countdown = countdown;
		this.countdown = 15;
		countdown = countdown-4;
		this.competition = competition;
		competition = competition*60;
		
		countdownTimer.start();
	}
	
	public String getTime(){
		Date date = new GregorianCalendar().getTime();

		String time= new SimpleDateFormat("HH:mm:ss").format(date.getTime());
		
		return time;
	}
	
	public void registerController(MainViewControllerS controller){
		this.controller = controller;
	}
	
	public int getCountdown(){
		return countdown;
	}
	
	public int getCompetition(){
		return competition;
	}
}
