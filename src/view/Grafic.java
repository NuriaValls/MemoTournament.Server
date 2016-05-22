package view;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;



public class Grafic extends JPanel{
	private int[] score1 = new int[15];
	private int[] score2 = new int[15];
	private int typeGraph;
	
	public Grafic(){
	}
	

	public void setInfo(int[] arrayM, int[] arrayC){
		this.score1 = arrayM;
		this.score2 = arrayC;
	}
	
	/**
	 * Aquest atribut dibuixa les 2 gràfiques de les puntuacions de les diferents partides dels usuaris.
	 */
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		
		this.setSize(600, 250);
				
		Dimension x = this.getSize();
		
		typeGraph = 1;

		g.drawLine(30, 20, 30, 240);
        g.drawLine(30, 239, 240, 239); 
        g.drawString("Score",5 ,10);
        g.drawString("Games", 210, 230);
        g.drawString("0", 10, 250);
        	
    	g.drawString("MEMORY",120,20);
    	int maxPunctuation = 0;
    	for(int i=0;i<score1.length;i++){
    		if (score1[i]>maxPunctuation){
    			maxPunctuation = score1[i];
    		}
    	}

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
        	        	
        	int partides = score1.length;
        	int incrementP = 210/partides;
        	int aux = incrementP;
        	for(int i=1;i<score1.length;i++){
            	g.drawString(String.valueOf(i),incrementP+15,250);
            	incrementP=incrementP+aux;
            }
        	
        	
        	//Pintar puntuacions
        	if (maxPunctuation>240){
        		int scale = maxPunctuation/240;
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
        	g.drawString("CONCENTRATION",100+250,20);

          	int maxPunctuation2 = 0;
        	for(int i=0;i<score2.length;i++){
        		if (score1[i]>maxPunctuation2){
        			maxPunctuation2 = score1[i];
        		}
        	}
        	
        	//Pintar numeros eix Y:
        	int incrementy2=0;
        	int incrementp2 = maxPunctuation2/4;

        	g.drawLine(250+30, 20, 250+30, 240);
            g.drawLine(250+30, 239, 250+240, 239); 
            g.drawString("Score",250+5 ,10);
            g.drawString("Games", 250+200, 230);
            g.drawString("0", 250+10, 250);
            
        	int y2 = 240;
        	int n2=0;
        	for(int i=0;i<score2.length;i++){
       			if (n2<4){
       				g.drawString(String.valueOf(incrementy2 + incrementp2),250,y2 - 53);
                  	y2=y2-53;
                  	n2++;
                  	incrementy2 = incrementy2 + incrementp2;
       			}
            }
        	

        	
        	//Pintar numeros eix X:
        	
        	int partides2 = score2.length;
        	int incrementP2 = 210/partides2;
        	int aux2 = incrementP2;
        	for(int i=1;i<score2.length;i++){
            	g.drawString(String.valueOf(i),250+incrementP2+15,250);
            	incrementP2 = incrementP2 + aux2;

            }
        	

        	//Pintar puntuacions
        	if (maxPunctuation2>240){
        		int scale = maxPunctuation2/240;
            	int ix2 = 250+30;
            	//quan maxPunctuation>240 no pinta dins del panell
            	for(int j=0;j<score2.length;j++){
            		if(score2[j]<10){
            			g.drawLine(ix2,239-(score2[j]),ix2+(210/score2.length),260-(score2[j+1]));
                		ix2 = ix2+(210/score2.length);
            		}else{
            			g.drawLine(ix2,260-(score2[j]/scale),ix2+(210/score2.length),260-(score2[j+1]/scale));
                		ix2 = ix2+(210/score2.length);
            		}
            		

            	}
        	}else{
        		int ix2=250+30;
        		for(int j=0;j<score2.length-1;j++){
        			if(score2[j]<10){
        				g.drawLine(ix2,239-(score2[j]),ix2+(210/score2.length),260-(score2[j+1]));
                		ix2 = ix2+(210/score2.length);
        			}else{
        				g.drawLine(ix2,260-(score2[j]),ix2+(210/score2.length),260-(score2[j+1]));
                		ix2= ix2+(210/score2.length);
        			}
            	}
        	}
        	
     //   }
		
	}

}

