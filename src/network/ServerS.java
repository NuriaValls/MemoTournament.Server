package network;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import controller.MainViewControllerS;
import model.Logics;
/**
 * Aquesta classe permet establir una connexio amb el programa client i escoltar el que ens diu
 * per poder respondre amb la informacio corresponent.
 * @author nvall
 *
 */
public class ServerS extends Thread{
	/**
	 * indica si el servidor esta activat.
	 */
	private boolean isOn;
	/**
	 * permet establir la connexio amb el cient.
	 */
	private static ServerSocket sServer;
	/**
	 * instancia del client que escoltem.
	 */
	private static Socket sClient;
	/**
	 * permet rebre la informacio que ens envia el client.
	 */
	private DataInputStream dataIn;
	/**
	 * permet enviar informacio al client.
	 */
	private static DataOutputStream dataOut;
	/**
	 * instancia del controlador.
	 */
	private MainViewControllerS controller;
	
	public ServerS(int portClient){
		try{
			sServer = new ServerSocket(portClient);
			isOn = false;
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	public void registerController(MainViewControllerS controller){
		this.controller = controller;
	}
	/**
	 * inicia el servidor.
	 */
	public void startServer(){
		isOn = true;
		super.start();
		System.out.println("Obrint servidor...");
	}
	/**
	 * tanca el servidor.
	 */
	public void stopServer(){
		isOn = false;
	}
	/**
	 * escolta les peticons de connexio del client i en funcio del que rep respon amb la infromacio correpsonent.
	 */
	public void run(){

		String message = new String();
		
		while(isOn){
			try{
				sClient = sServer.accept();
				dataIn = new DataInputStream(sClient.getInputStream());
				dataOut = new DataOutputStream(sClient.getOutputStream());
				message = dataIn.readUTF();
				
				if (message.startsWith("ADD")){
					if(Logics.addUser(message)){
						dataOut.writeUTF("OK");
						controller.refreshList();
						controller.refreshAllUsers();
						controller.refreshTop1();
					}else{
						dataOut.writeUTF("KO");
					}
				}
				
				if (message.startsWith("LOG")){
					dataOut.writeUTF(Logics.checkUser(message));
					controller.refreshAllUsers();
					controller.refreshTop1();
				}
				
				if (message.startsWith("RANK")){
					//retorna una string amb tots els usuaris i puntuacions
					String ranking = new String();
					ranking = controller.createRankingServer();
					dataOut.writeUTF("RANK:"+ranking);
				}
				
				if (message.startsWith("UPDATE")){
					//actualitza puntuacio de la partida
					if(Logics.updateScore(message)){
						dataOut.writeUTF("OK");
						controller.refreshTop1();
					}else{
						dataOut.writeUTF("KO");
					}
				}
				
				if (message.startsWith("START")){
					if (!controller.getComp()){
						dataOut.writeUTF("NOT STARTED");
					}else{
						dataOut.writeUTF(controller.setStartMessage());
					}
				}
				
				if (message.startsWith("BLOCKED")){
					if (controller.checkBlocked(message)){
						dataOut.writeUTF("BLOCKED");
					}else{
						dataOut.writeUTF("NOT BLOCKED");
					}
				}
				
				dataIn.close();
				dataOut.close();
				sClient.close();
			}catch(IOException e){
				
			}
		}
	}
}
