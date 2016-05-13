package model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.Timer;

public class Time extends Thread{
	
	private Timer countdownTimer;
	private int countdown;
	private Timer competitionTimer;
	private int competition;
	
	public Time(){
		countdownTimer = new Timer(1000, new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (countdown >= 0){
					countdown--;
				}else{
					competitionTimer.start();
					countdownTimer.stop();
				}
			}
			
		});
		
		competitionTimer = new Timer(1000, new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (competition >= 0){
					competition--;
				}else{
					competitionTimer.stop();
				}
			}
			
		});
	}
	
	public void startCompetitionTime(int countdown, int competition){
		this.countdown = countdown;
		this.competition = competition;
		
		countdownTimer.start();
	}
	
	public String getTime(){
		Date date = new GregorianCalendar().getTime();

		String time= new SimpleDateFormat("dd MMM yyyy HH:mm:ss").format(date.getTime());
		
		return time;
	}
}
