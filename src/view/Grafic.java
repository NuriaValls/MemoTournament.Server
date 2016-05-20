package view;

import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;

import model.UserRanking;

public class Grafic extends JPanel{
	private int[] punctuations;
	private int typeGraph;
	public Grafic(){
		
	}
	
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
				
		Dimension x = this.getSize();
		System.out.println("Dimensio x " + x.getWidth() + " y " + x.getHeight() );
		
		
		//eixos
		g.drawLine(0, 20, 0, 500);
        g.drawLine(0, 250, 500, 250);
        
        if (typeGraph == 1){
        	int maxPunctuation = 0;
        	for(int i=0;i<punctuations.length;i++){
        		if (punctuations[i]>maxPunctuation){
        			maxPunctuation = punctuations[i];
        		}
        	}
        	
        	int scale = 500/maxPunctuation;
        	int ix = 0;
        	for(int i=0;i<punctuations.length;i++){
        		g.drawLine(ix,punctuations[i]*scale-500,ix+30,punctuations[i+1]*scale-500);
        		ix= ix+30;
        	}
        }else{
        	
        }
		
	}

}
