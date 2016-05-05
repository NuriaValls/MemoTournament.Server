package network;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import model.Logics;

public class Prova extends Thread{
	
	private boolean isOn;
	private static ServerSocket sServer;
	private static Socket sClient;
	private DataInputStream dataIn;
	private static DataOutputStream dataOut;
	
	public Prova(){
		try{
			sServer = new ServerSocket(5200);
			isOn = false;
		}catch(IOException e){
			e.printStackTrace();
		}
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
				System.out.println("Buscant client");
				sClient = sServer.accept();
				System.out.println("client connectat");
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
					ranking = Logics.createRanking();
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
				
				dataIn.close();
				dataOut.close();
				sClient.close();
			}catch(IOException e){
				
			}
		}
	}
	
	public boolean sendCompetition(String message){
		boolean ok = false;
		
		try {
			System.out.println("1");
			sClient = sServer.accept();
			System.out.println("2");
			dataOut = new DataOutputStream(sClient.getOutputStream());
			System.out.println("client trobat");
			dataOut.writeUTF(message);
			ok = true;
			
			dataOut.close();
			sClient.close();
		} catch (IOException e) {
			e.printStackTrace();
			ok = false;
		}
		
		return ok;
		
	}
	//dodengaeojrng
}
