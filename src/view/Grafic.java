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
		int[] array = new int[13];
		for(int i=0;i<13;i= i + 1){
			array[i] = i+100;
		}
		this.score1 = array;
		this.score2 = array;
		score1[2]=220;
		score1[3]=100;
		score1[7]=40;

	}
	
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
				
		Dimension x = this.getSize();
		System.out.println("Dimensio x " + x.getWidth() + " y " + x.getHeight() );
		
		
		//eixos
		typeGraph = 1;
		g.drawLine(30, 20, 30, 240);
        g.drawLine(30, 240, 240, 240); 
        g.drawString("Score",0 ,10);
        g.drawString("Game", 240, 240);
        g.drawString("0", 10, 232);
        
        if (typeGraph == 1){
        	int maxPunctuation = 0;
        	for(int i=0;i<score1.length;i++){
        		if (score1[i]>maxPunctuation){
        			maxPunctuation = score1[i];
        		}
        	}
        	System.out.println(maxPunctuation);
        	//Pintar numeros eix Y:
        	int incrementy=0;
        	int incrementp = maxPunctuation/4;
        	
        	int y = 240;
        	int n=0;
        	for(int i=0;i<score1.length-1;i++){
       			if (n<4){
       				g.drawString(String.valueOf(incrementy + incrementp),0,y - 54);
                  	y=y-54;
                  	n++;
                  	incrementy = incrementy + incrementp;
       			}
            }
        	
        	
        	//Pintar numeros eix X:
        	
        	int partides = score1.length;
        	int incrementP = 210/partides;
        	int aux = incrementP;
        	System.out.println(incrementP);
        	for(int i=1;i<score1.length-1;i++){
            	g.drawString(String.valueOf(i),incrementP+15,252);
            	incrementP=incrementP+aux;
            }
        	
        	
        	//Pintar puntuacions
        	int scale = maxPunctuation/210;
        	System.out.println(scale);
        	int ix = 30;
        	for(int j=0;j<score1.length-1;j++){
        		g.drawLine(ix,240-(score1[j]*scale),ix+(200/score1.length),240-(score1[j+1]*scale));
        		ix= ix+(200/score1.length);
        	}
        	
        	
        	
        	
        	
        	
        	
     //aquesta part es igual que l'anterior pel cas typeGraph=2
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

