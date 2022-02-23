
package com.mycompany.snake.game;

import java.awt.EventQueue;
import javax.swing.JFrame;

public class SnakeGameClass extends JFrame{
    public SnakeGameClass() {
        initializeUI();
    }
    
    private void initializeUI() {
        add(new SnakeGameBoard());
        setResizable(false);
        pack();
        setTitle("Snake Game");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            JFrame snakeFrame = new SnakeGameClass();
            snakeFrame.setVisible(true);
        });
    }
}
