package model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.Timer;

import controller.MainViewControllerS;
/**
 * Aquesta classe permet controlar els temps del programa.
 * @author nvall
 *
 */
public class Time extends Thread{
	/**
	 *Timer que cada segon resta un segon ala comptpa enrere perquee comenci la competicio.
	 */
	private Timer countdownTimer;
	/**
	 * comptador del compte enrereper la competicio.
	 */
	private int countdown;
	/**
	 * TImer que cada segon resta un segon al temps de la competicio.
	 */
	private Timer competitionTimer;
	/**
	 * temps que resta de la competicio.
	 */
	private int competition;
	/**
	 * instancia del controlador.
	 */
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
	/**
	 * inicia el timmer de la competicio amb un compte enrere i un temps de competicio que rep per referencia.
	 */
	public void startCompetitionTime(int countdown, int competition){
		this.countdown = countdown;
		countdown = countdown-4;
		this.competition = competition;
		competition = competition*60;
		
		countdownTimer.start();
	}
	/**
	 * retorna el temps local.
	 */
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
