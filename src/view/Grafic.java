package view;

import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;

import model.UserRanking;

public class Grafic extends JPanel{
	private int[] score1;
	private int[] score2;
	private int typeGraph;
	
	public Grafic(){
		int[] array = new int[10];
		for(int i=0;i<10;i++){
			array[i] = i;
		}
		this.score1 = array;
		this.score2 = array;
	}
	
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
				
		Dimension x = this.getSize();
		System.out.println("Dimensio x " + x.getWidth() + " y " + x.getHeight() );
		
		
		//eixos
		
		g.drawLine(0, 20, 0, 500);
        g.drawLine(0, 250, 500, 250); 
        g.drawString("Score",0 , 500);
        g.drawString("Game", 250, 0);
        
        if (typeGraph == 1){
        	int maxPunctuation = 0;
        	for(int i=0;i<score1.length;i++){
        		if (score1[i]>maxPunctuation){
        			maxPunctuation = score1[i];
        		}
        	}
        	//Pintar numeros eix Y:
        	int incrementy=0;
        	incrementy = maxPunctuation/5;
        	g.drawString("0", 0, 0);
        	int y = 0;
        	for(int i=0;i<score1.length;i++){
            	g.drawString(String.valueOf(incrementy),0,y + 50);
            	y=y+100;
            	incrementy = incrementy + incrementy;
            }
        	
        	
        	//Pintar numeros eix X:
        	
        	int incrementx=0;
        	incrementx = score1.length/5;
        	g.drawString("0", 0, 0);
        	int eixX = 0;
        	for(int i=0;i<score1.length;i++){
            	g.drawString(String.valueOf(incrementx),0,eixX+ 100);
            	eixX=eixX+100;
            	incrementx = incrementx + incrementx;
            }
        	
        	
        	//Pintar puntuacions
        	int scale = 500/maxPunctuation;
        	int ix = 0;
        	for(int i=0;i<score1.length;i++){
        		g.drawLine(ix,score1[i]*scale-500,ix+30,score1[i+1]*scale-500);
        		ix= ix+30;
        	}
        }else{
        	int maxPunctuation = 0;
        	for(int i=0;i<score2.length;i++){
        		if (score2[i]>maxPunctuation){
        			maxPunctuation = score2[i];
        		}
        	}
        	
        	//Pintar numeros eix Y:
        	int incrementy=0;
        	incrementy = maxPunctuation/5;
        	g.drawString("0", 0, 0);
        	int y = 0;
        	for(int i=0;i<score1.length;i++){
            	g.drawString(String.valueOf(incrementy),0,y + 50);
            	y=y+100;
            	incrementy = incrementy + incrementy;
            }
        	
        	
        	//Pintar numeros eix X:
        	
        	int incrementx=0;
        	incrementx = score1.length/5;
        	g.drawString("0", 0, 0);
        	int eixX = 0;
        	for(int i=0;i<score1.length;i++){
            	g.drawString(String.valueOf(incrementx),0,eixX+ 100);
            	eixX=eixX+100;
            	incrementx = incrementx + incrementx;
            }
        	
        	int scale = 500/maxPunctuation;
        	int ix = 0;
        	for(int i=0;i<score2.length-1;i++){
        		g.drawLine(ix,score2[i]*scale-500,ix+30,score2[i+1]*scale-500);
        		ix= ix+30;
        	}
        }
		
	}

}
