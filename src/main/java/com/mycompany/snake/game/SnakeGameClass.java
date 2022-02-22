/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.snake.game;

import java.awt.EventQueue;
import javax.swing.JFrame;

/**
 *
 * @author chinm
 */
public class SnakeGameClass extends JFrame{
    public SnakeGameClass() {
        
        initUI();
    }
    
    private void initUI() {
        
        add(new SnakeGameBoard());
               
        setResizable(false);
        pack();
        
        setTitle("Snake Game");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    

    public static void main(String[] args) {
        
        EventQueue.invokeLater(() -> {
            JFrame ex = new SnakeGameClass();
            ex.setVisible(true);
        });
    }
}
