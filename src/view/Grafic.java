package view;

import java.awt.Graphics;

import javax.swing.JPanel;

public class Grafic extends JPanel{
	
	
	public Grafic(){
		
	}
	
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		
		g.drawLine(30, 20, 200, 20);
        
		
	}

}
