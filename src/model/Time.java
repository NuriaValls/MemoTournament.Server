package model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

public class Time extends Thread{
	
	private Timer countdownTimer;
	private int countdown;
	private Timer competitionTimer;
	private int coompetition;
	
	public Time(){
		countdownTimer = new Timer(1000, new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (countdown >= 0){
					countdown--;
				}
			}
			
		});
	}
}
