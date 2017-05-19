/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;
import javax.swing.*;
import java.awt.Graphics;

/**
 *
 * @author Estudiante
 */
public class Game extends JFrame{

    public Game() {
        add(new NewPanel());
    }
    public static void main(String[] args) {
        Game frame=new Game();
        frame.setTitle("TestPaintComponent");
        frame.setSize(1024,725);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        
    }
    
}
