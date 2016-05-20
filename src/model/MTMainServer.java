package model;

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
				Time time = new Time();
				Logics logics = new Logics(time);
				ServerS server = new ServerS();
				MainViewControllerS controller = new MainViewControllerS(serverView,server,logics);
				
				serverView.registerController(controller);
				server.registerController(controller);
				time.registerController(controller);
				logics.registerController(controller);
				server.startServer();
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
