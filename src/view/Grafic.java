package view;

import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;

import model.UserRanking;

public class Grafic extends JPanel{
	private ArrayList<UserRanking> allUsers;
	private int typeGraph;
	public Grafic(){
		
	}
	
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		//pintar eixos.
		g.drawLine(0, 20, 0, 500);
        g.drawLine(0, 300, 0, 300);
        if (typeGraph == 1){
        	for(int i=0;i<allUsers.size();i++){
        		
        	}
        }else{
        	
        }
		
	}

}
