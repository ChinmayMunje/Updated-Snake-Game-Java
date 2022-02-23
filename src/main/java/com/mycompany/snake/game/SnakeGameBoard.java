/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.snake.game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author chinm
 */
public class SnakeGameBoard extends JPanel implements ActionListener{

    private final int BOARD_WIDTH = 600;
    private final int BOARD_HEIGHT = 600;
    private final int DOT_SIZE = 10;
    private final int ALL_DOTS = 900;
    private final int RAND_POS = 29;
    private final int DELAY = 140;

    private final int xPosition[];
    private final int yPosition[];

    private int snakeSize;
    private int apple_x_pos;
    private int apple_y_pos;

    private boolean left = false;
    private boolean right = true;
    private boolean up = false;
    private boolean down = false;
    private boolean gameON = true;

    private Timer gameTimer;
    private Image BallImg;
    private Image appleImg;
    private Image headImg;

    public SnakeGameBoard() {
        this.yPosition = new int[ALL_DOTS];
        this.xPosition = new int[ALL_DOTS];
        initializeSnakeBoard();
    }
    
    private void initializeSnakeBoard() {
        addKeyListener(new UserClickAdapter());
        setBackground(Color.black);
        setFocusable(true);
        setPreferredSize(new Dimension(BOARD_WIDTH, BOARD_HEIGHT));
        loadAllImages();
        initializeGame();
    }

    private void loadAllImages() {
        ImageIcon ballIcon = new ImageIcon("src/resources/dot.png");
        BallImg = ballIcon.getImage();
        ImageIcon appleIcon = new ImageIcon("src/resources/apple.png");
        appleImg = appleIcon.getImage();
        ImageIcon headIcon = new ImageIcon("src/resources/head.png");
        headImg = headIcon.getImage();
    }

    private void initializeGame() {
        snakeSize = 3;
        for (int z = 0; z < snakeSize; z++) {
            xPosition[z] = 50 - z * 10;
            yPosition[z] = 50;
        }
        locateNewApple();
        gameTimer = new Timer(DELAY, this);
        gameTimer.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        doDrawing(g);
    }
    
    private void doDrawing(Graphics g) {
        
        if (gameON) {

            g.drawImage(appleImg, apple_x_pos, apple_y_pos, this);

            for (int z = 0; z < snakeSize; z++) {
                if (z == 0) {
                    g.drawImage(headImg, xPosition[z], yPosition[z], this);
                } else {
                    g.drawImage(BallImg, xPosition[z], yPosition[z], this);
                }
            }

            Toolkit.getDefaultToolkit().sync();

        } else {

            gameOver(g);
        }        
    }

    private void gameOver(Graphics g) {
        g.setColor(Color.red);
        g.setFont(new Font("TimesRoman",Font.BOLD,40));
        FontMetrics metrics =  getFontMetrics(g.getFont());
       
        g.drawString("Game Over", (BOARD_WIDTH-metrics.stringWidth("Game Over"))/2, BOARD_HEIGHT/2);

    }

    private void checkApple() {
        if ((xPosition[0] == apple_x_pos) && (yPosition[0] == apple_y_pos)) {
            snakeSize++;
            locateNewApple();
        }
    }

    private void moveSnake() {
        for (int z = snakeSize; z > 0; z--) {
            xPosition[z] = xPosition[(z - 1)];
            yPosition[z] = yPosition[(z - 1)];
        }
        if (left) {
            xPosition[0] -= DOT_SIZE;
        }
        if (right) {
            xPosition[0] += DOT_SIZE;
        }
        if (up) {
            yPosition[0] -= DOT_SIZE;
        }
        if (down) {
            yPosition[0] += DOT_SIZE;
        }
    }

    private void checkSnakeCollision() {
        for (int z = snakeSize; z > 0; z--) {
            if ((z > 4) && (xPosition[0] == xPosition[z]) && (yPosition[0] == yPosition[z])) {
                gameON = false;
            }
        }
        if (yPosition[0] >= BOARD_HEIGHT) {
            gameON = false;
        }
        if (yPosition[0] < 0) {
            gameON = false;
        }
        if (xPosition[0] >= BOARD_WIDTH) {
            gameON = false;
        }
        if (xPosition[0] < 0) {
            gameON = false;
        }
        if (!gameON) {
            gameTimer.stop();
        }
    }

    private void locateNewApple() {
        int r = (int) (Math.random() * RAND_POS);
        apple_x_pos = ((r * DOT_SIZE));
        r = (int) (Math.random() * RAND_POS);
        apple_y_pos = ((r * DOT_SIZE));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (gameON) {
            checkApple();
            checkSnakeCollision();
            moveSnake();
        }
        repaint();
    }

    private class UserClickAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();
            if ((key == KeyEvent.VK_LEFT) && (!right)) {
                left = true;
                up = false;
                down = false;
            }
            if ((key == KeyEvent.VK_RIGHT) && (!left)) {
                right = true;
                up = false;
                down = false;
            }
            if ((key == KeyEvent.VK_UP) && (!down)) {
                up = true;
                right = false;
                left = false;
            }
            if ((key == KeyEvent.VK_DOWN) && (!up)) {
                down = true;
                right = false;
                left = false;
            }
        }
    }   
}
