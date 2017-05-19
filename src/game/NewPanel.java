/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.Color;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Estudiante
 */
public class NewPanel extends JPanel implements KeyListener, ActionListener{
    
    
    private Timer timer;
    private int secuencia1;
    private int secuencia2;
    private int x, x1, gravity;
    private int y, coinsq; 
    private int floor, d;
    private boolean up, left, right,f, coinsw;
    int vely=60;
    private int x2, secuencia3;
    
    public NewPanel() {
        addKeyListener(new TAdapter());
        setFocusable(true);
        this.timer=new Timer(25,this);
        this.timer.start();
        this.x=0;
        this.x1=0;
        this.y=0;
        this.f=true;
        this.floor=632;
        this.secuencia1=0;
        this.secuencia2=0;
        this.up=false;
        this.right=false;
        this.d=0;
        this.coinsw=false;
        this.gravity=6;
        this.x2=0;
        this.secuencia3=0;
    }
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Image background = loadImage("blue_background.png");  
        Image cloud = loadImage("clouds.png");
        Image ground = loadImage("ground_loop.png");
        Image ground2 = loadImage("ground_single.png");
        Image coin=loadImage("coin.png");
        Image enemy = loadImage("enemy_run.png");

        this.timer.setDelay(25);
        
        for(int i=0; i<=46;i++){   
            g.drawImage(background, i*22, 0, this);
        }
        for(int i=0; i<=200; i++){
            g.drawImage(cloud, x+(i*335), 70, this);
        }
        g.drawString("Puntos: " + this.coinsq, 50, 50);
        for(int i=0;i<=10;i++){
            g.drawImage(ground, x1+(i*112), 632, this);
        }
        for(int i=0;i<=10;i++){
            g.drawImage(ground, x1+(i*112)+1640, 632, this);
        }
        g.drawImage(ground2, x1+1400, 500, this);
        if(!this.coinsw){
            g.drawRect(x1+500, 632-330, 55, 55);
            g.drawImage(coin, x1+500, 632-330, this);}
        if(right && y==0 && !up && d==0){
            walk(g);
        }
        if(d==1 || y>0){
            g.drawRect(200, y+498, 96, 142); 
            jump(g);
        }
        if(y==0 && (!up && !right) && d==0){
            stand(g);
        }
        g.drawImage(enemy,  400+x2 , 542, 400+x2+105, 542+101, secuencia2*105, 0, (secuencia2*105)+105, 101, this);
    }
    public void walk(Graphics g){
//        this.timer.setDelay(50);
        Image walk= loadImage("walking.png");
        g.drawImage(walk, 200, y+floor-134, 200+116, y+floor, secuencia1*116, 0, (secuencia1*116)+116, 134, this);
    }
    public void stand(Graphics g){
        this.timer.setDelay(100);
        Image stand=loadImage("standing.png");
        g.drawImage(stand, 200, y+floor-134, 200+143, y+floor, secuencia2*143, 0, (secuencia2*143)+143, 134, this);
    }
    public void jump(Graphics g){
        this.timer.setDelay(20);
        Image jump=loadImage("jump.png");
        g.drawImage(jump, 200, y+498, 200+96, y+640, 0, 0, 96, 142, this);
    }
    public Image loadImage(String imageName){
        ImageIcon ii=new ImageIcon(imageName);
        Image image = ii.getImage();
        return image;
    }
    
   
//    public void checkCollision1(){
//        Rectangle floor=ground1();
//        Rectangle ch=characterwalk();
//        if(ch.intersects(floor)){
//            System.out.println("floor");
//            this.timer.stop();
//            f=true;
//            try {
//                Thread.sleep(20);
//            } catch (InterruptedException ex) {
//                Logger.getLogger(NewPanel.class.getName()).log(Level.SEVERE, null, ex);
//            }
//            timer.start();
//        }else{f=false;}
//    }
    
    public void checkcolission(Graphics g){
        
           if (this.Renemigo().intersects(this.characterwalk())){
               this.timer.stop();
               g.drawString("PERDISTE", 250, 250);
           }     
                
    }
    public void moveJump(){
        y-=vely;
        vely-=gravity;
        if(y==0){
            d=0;
            vely=60;
        }
    }
    public void coins(){
        Rectangle w=characterwalk();
        Rectangle j=characterjump();
        Rectangle c=reward();
        if(w.intersects(c) || j.intersects(c)){
            coinsw=true;
            this.coinsq+=1;
        }
    }
    public void xxh(){
        if(x1<-1008 && x1>-1162){f=false;}
        if(x1<=-1162 && x1>=-1262){f=true; floor=500;}
        if(x1>=-1498 && x1<-1262){f=false;}
        if(x1<-1498){f=true; floor=632;}
    }
    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
       
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {   
        coins();
        xxh();
        if(!up && !right){secuencia2++;}
        if(right){x-=5; x1-=14; secuencia1++; System.out.println(x1);}
        if(this.secuencia1==4){this.secuencia1=0;}       
        if(this.secuencia2==2){this.secuencia2=0;}  
//        if(up){y-=100;}
        if(d==1){moveJump();}
        if(!f){y+=gravity;}  
        if(this.secuencia3==1){this.secuencia3=0;}
        
        this.x2=x2-5;
        repaint();
    }
   private class TAdapter extends KeyAdapter{
        @Override
        public void keyReleased(KeyEvent e){
            int key = e.getKeyCode();
            if(key==KeyEvent.VK_SPACE){
                System.out.println("VK_SPACE");
            }
            if(key==KeyEvent.VK_UP){
                up=false;
                d=1;
            }
            if(key==KeyEvent.VK_LEFT){
                left=false;
            }
            if(key==KeyEvent.VK_RIGHT){
                right=false;
            }
        }
        @Override
        public void keyPressed(KeyEvent e){
            int key = e.getKeyCode();
            if(key==KeyEvent.VK_SPACE){
                System.out.println("VK_SPACE");
            }
            if(key==KeyEvent.VK_UP){
                up=true;
                d=1;
            }
            if(key==KeyEvent.VK_LEFT){
                left=true;
            }
            if(key==KeyEvent.VK_RIGHT){
                right=true;
                
            }     
        }  
        
    }
    public Rectangle ground1(){
        return new Rectangle(x1,632,1234,50);
    }
    public Rectangle characterwalk(){
        return new Rectangle(200, floor-134, 200+116, floor);
    }
    public Rectangle characterjump(){
        return new Rectangle(200, y+498, 96, 142);
    }
    public Rectangle reward(){
        return new Rectangle(x1+500,632-330,55,55);
    }
    public Rectangle Renemigo(){
        return new Rectangle(400+x2,542,105,101);
    }
}
