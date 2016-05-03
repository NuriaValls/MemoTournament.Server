package model;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.SwingUtilities;

import controller.MainViewControllerS;
import view.MainViewServer;
import network.*;

public class MTMainServer {

	public static void main(String[] args) {
		
		SwingUtilities.invokeLater(new Runnable(){

			@Override
			public void run() {
				
				MainViewServer serverView = new MainViewServer();
				MainViewControllerS controller = new MainViewControllerS(serverView);
				
				//Server server = new Server();
				
				serverView.registerController(controller);
				//server.startServer();
				serverView.setVisible(true);
				
				ConectorDB conn = new ConectorDB("root", "", "memotournamentdb", 3306);
				conn.connect();
				
				/* CONNEXIO BASE DE DADES
				ResultSet consulta;
				ConectorDB conn = new ConectorDB("root", "", "memotournamentdb", 3306);
				conn.connect();
				conn.insertUser("Nuria", "12234");
				conn.disconnect();
				*/
				
			}
		});
	}
}
