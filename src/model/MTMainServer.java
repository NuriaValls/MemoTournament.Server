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
				
				Configuration config = new Configuration();
				
				if(config.configurate()){
					
					MainViewServer serverView = new MainViewServer();
					Time time = new Time();
					Logics logics = new Logics(time);
					ServerS server = new ServerS(config.getPortClient());
					MainViewControllerS controller = new MainViewControllerS(serverView,server,logics);
					
					serverView.registerController(controller);
					server.registerController(controller);
					time.registerController(controller);
					logics.registerController(controller);
					server.startServer();
					serverView.setVisible(true);
					
					ConectorDB conn = new ConectorDB(config.getUser(), config.getPassword(), config.getNameDB(), Integer.parseInt(config.getPortDB()), config.getIP());
					conn.connect();
					
				}
			}
		});
	}
}
