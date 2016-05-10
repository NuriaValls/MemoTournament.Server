package network;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import controller.MainViewControllerS;
import model.Logics;

public class ServerS extends Thread{
	
	private boolean isOn;
	private static ServerSocket sServer;
	private static Socket sClient;
	private DataInputStream dataIn;
	private static DataOutputStream dataOut;
	private MainViewControllerS controller;
	
	private String startMessage = new String();
	
	public ServerS(){
		try{
			sServer = new ServerSocket(5200);
			isOn = false;
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	public void registerController(MainViewControllerS controller){
		this.controller = controller;
	}
	
	public void startServer(){
		isOn = true;
		super.start();
		System.out.println("Obrint servidor...");
	}
	
	public void stopServer(){
		isOn = false;
	}
	
	public void run(){

		String message = new String();
		
		while(isOn){
			try{
				//System.out.println("Buscant client");
				sClient = sServer.accept();
				//System.out.println("client connectat");
				dataIn = new DataInputStream(sClient.getInputStream());
				dataOut = new DataOutputStream(sClient.getOutputStream());
				message = dataIn.readUTF();
				
				if (message.startsWith("ADD")){
					if(Logics.addUser(message)){
						dataOut.writeUTF("OK");
					}else{
						dataOut.writeUTF("KO");
					}
				}
				
				if (message.startsWith("LOG")){
					if(Logics.checkUser(message)){
						dataOut.writeUTF("OK");
					}else{
						dataOut.writeUTF("KO");
					}
				}
				
				if (message.startsWith("RANK")){
					//retorna una string amb tots els usuaris i puntuacions
					String ranking = new String();
					//ranking = 
					controller.createRankingServer();
					dataOut.writeUTF("RANK:"+ranking);
				}
				
				if (message.startsWith("UPDATE")){
					//actualitza puntuacio de la partida
					if(Logics.updateScore(message)){
						dataOut.writeUTF("OK");
					}else{
						dataOut.writeUTF("KO");
					}
				}
				
				if (message.startsWith("START")){
					if (startMessage == null){
						dataOut.writeUTF("NOT STARTED");
					}else{
						dataOut.writeUTF(startMessage);
					}
				}
				
				dataIn.close();
				dataOut.close();
				sClient.close();
			}catch(IOException e){
				
			}
		}
	}

	public void setStartMessage(String startMessage) {
		this.startMessage = startMessage;
	}
}
