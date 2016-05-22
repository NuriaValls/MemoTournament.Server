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
		for(int i=0;i<10;i= i + 1){
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
		
		this.setSize(250, 250);
				
		Dimension x = this.getSize();
		System.out.println("Dimensio x " + x.getWidth() + " y " + x.getHeight() );
		
		
		//eixos
		typeGraph = 1;
		g.drawLine(30, 20, 30, 240);
        g.drawLine(30, 239, 240, 239); 
        g.drawString("Score",5 ,10);
        g.drawString("Games", 210, 230);
        g.drawString("0", 10, 250);
       
        
        if (typeGraph == 1){
        	
        	g.drawString("MEMORY",120,20);
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
        	for(int i=0;i<score1.length;i++){
       			if (n<4){
       				g.drawString(String.valueOf(incrementy + incrementp),0,y - 53);
                  	y=y-53;
                  	n++;
                  	incrementy = incrementy + incrementp;
       			}
            }
        	
        	
        	//Pintar numeros eix X:
        	
        	int partides = score1.length;
        	int incrementP = 210/partides;
        	int aux = incrementP;
        	System.out.println(incrementP);
        	for(int i=1;i<score1.length;i++){
            	g.drawString(String.valueOf(i),incrementP+15,250);
            	incrementP=incrementP+aux;
            }
        	
        	
        	//Pintar puntuacions
        	if (maxPunctuation>240){
        		int scale = maxPunctuation/240;
            	System.out.println(scale);
            	int ix = 30;
            	//quan maxPunctuation>240 no pinta dins del panell
            	for(int j=0;j<score1.length;j++){
            		g.drawLine(ix,260-(score1[j]/scale),ix+(210/score1.length),260-(score1[j+1]/scale));
            		ix= ix+(210/score1.length);
            	}
        	}else{
        		int ix=30;
        		for(int j=0;j<score1.length-1;j++){
            		g.drawLine(ix,260-(score1[j]),ix+(210/score1.length),260-(score1[j+1]));
            		ix= ix+(210/score1.length);
            	}
        	}
        	
        	
        	
        	
        	
        	
        	
        	
        	
     //aquesta part es igual que l'anterior pel cas typeGraph=2
        }else{
        	g.drawString("CONCENTRATION",120,20);

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
        	for(int i=0;i<score1.length;i++){
       			if (n<4){
       				g.drawString(String.valueOf(incrementy + incrementp),0,y - 53);
                  	y=y-53;
                  	n++;
                  	incrementy = incrementy + incrementp;
       			}
            }
        	
        	
        	//Pintar numeros eix X:
        	
        	int partides = score1.length;
        	int incrementP = 210/partides;
        	int aux = incrementP;
        	System.out.println(incrementP);
        	for(int i=1;i<score1.length;i++){
            	g.drawString(String.valueOf(i),incrementP+15,252);
            	incrementP=incrementP+aux;
            }
        	
        	
        	//Pintar puntuacions
        	if (maxPunctuation>240){
        		int scale = maxPunctuation/240;
            	System.out.println(scale);
            	int ix = 30;
            	//quan maxPunctuation>240 no pinta dins del panell
            	for(int j=0;j<score1.length;j++){
            		g.drawLine(ix,260-(score1[j]/scale),ix+(210/score1.length),260-(score1[j+1]/scale));
            		ix= ix+(210/score1.length);
            	}
        	}else{
        		int ix=30;
        		for(int j=0;j<score1.length;j++){
            		g.drawLine(ix,260-(score1[j]),ix+(210/score1.length),260-(score1[j+1]));
            		ix= ix+(210/score1.length);
            	}
        	}
        	
        }
		
	}

}

