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
