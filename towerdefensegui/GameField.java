/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package towerdefensegui;

import GameEntity.AirEnemy;
import GameTile.GameTile;
import GameTile.Mountain;
import GameTile.Road;
import GameTile.Tower;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.*;

/**
 * @author TA
 */
public class GameField extends JPanel {
    private GameManager game;
    
    private BufferedImage roadImage;
    private BufferedImage mountainImage;
    private String timeImageBuffer;
    private String resourceImageBuffer;
    private BufferedImage timeImage;
    private BufferedImage resourceImage;
    private String layoutBackgroundBuffer;
    private BufferedImage layoutBackground;
    private String waveImageBuffer;
    private BufferedImage waveImage;
    private String lifeImageBuffer;
    private BufferedImage lifeImage;
    private BufferedImage gameWonImage;
    private BufferedImage gameLostImage;
    
    private javax.swing.JButton backBut;
    private javax.swing.JButton saveBut;
    //private ImageIcon myImageIcon = new ImageIcon("/Sequences/64x48/explosion1_003.png");

    public GameField(GameManager game) {
        timeImageBuffer = "/images/time_icon.png";
        resourceImageBuffer = "/images/resource_icon.png";
        waveImageBuffer = "/images/wave_icon.png";
        lifeImageBuffer = "/images/life_icon.png";
        layoutBackgroundBuffer = "/images/layout_background.jpg";
        try {
            timeImage = ImageIO.read(getClass().getResourceAsStream(timeImageBuffer));
        } catch (IOException exc) {
            exc.printStackTrace();
        }
        try {
            resourceImage = ImageIO.read(getClass().getResourceAsStream(resourceImageBuffer));
        } catch (IOException exc) {
            exc.printStackTrace();
        }
        try {
            waveImage = ImageIO.read(getClass().getResourceAsStream(waveImageBuffer));
        } catch (IOException exc) {
            exc.printStackTrace();
        }
        try {
            lifeImage = ImageIO.read(getClass().getResourceAsStream(lifeImageBuffer));
        } catch (IOException exc) {
            exc.printStackTrace();
        }
        try {
            layoutBackground = ImageIO.read(getClass().getResourceAsStream(layoutBackgroundBuffer));
        } catch (IOException exc) {
            exc.printStackTrace();
        }
        //
        try {
            gameWonImage = ImageIO.read(getClass().getResourceAsStream("/images/game_won.png"));
        } catch (IOException exc) {
            exc.printStackTrace();
        }
        try {
            gameLostImage = ImageIO.read(getClass().getResourceAsStream("/images/game_lost.png"));
        } catch (IOException exc) {
            exc.printStackTrace();
        }
        String name = "towerDefense_tile";

        try {
            roadImage = ImageIO.read(getClass().getResourceAsStream("/images/" + name + "093" + ".png"));
            mountainImage = ImageIO.read(getClass().getResourceAsStream("/images/" + name + "024" + ".png"));
        } catch (IOException exc) {
            exc.printStackTrace();
        }
        //////////////////////////
        this.game = game;
        backBut = new JButton();
        saveBut = new JButton();
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap(672, Short.MAX_VALUE)
                                .addComponent(saveBut, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(backBut, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(101, 101, 101))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap(714, Short.MAX_VALUE)
                                .addComponent(backBut, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(20, 20, 20)
                                .addComponent(saveBut, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(85, 85, 85))
        );
        this.setPreferredSize(new Dimension(832, 776));
        setVisible(true);
        addMouseListener(game.getControl());
        addMouseMotionListener(game.getControl());
        
        saveBut.setText("Save Game");
        saveBut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveButActionPerformed(evt);
            }
        });
        backBut.setText("Back");
        backBut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButActionPerformed(evt);
            }
        });
    }

    //////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////PAINT OBJECTS//////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        //DRAW GRIDS AND TOWERS 
        drawGridsAndTowers(g);
        drawTowers(g);
        //DRAW ENEMIES
        drawEnemies(g);
        //DRAW PROJECTILES
        drawProjectiles(g);
        //DRAW SHOP
        game.getShop().draw(g);
        //DRAW LAYOUT ELEMENTS
        drawLayoutElements(g);
        //DRAW BACK BUTTON
        //backBut.repaint();
        repaint();
        
    }

    private void drawTowers(Graphics g) {
        for (int i = 0; i < game.getTowerManager().towerList.size(); i++) {
            g.drawImage(game.getTowerManager().towerList.get(i).getImage(), game.getTowerManager().towerList.get(i).getLocX(), game.getTowerManager().towerList.get(i).getLocY(), this);
            if (game.getTowerManager().towerList.get(i).hasTarget()) {
                double rad = Math.atan2(game.getTowerManager().towerList.get(i).getTarget().getLocY() + 32 - (game.getTowerManager().towerList.get(i).getLocY() + 32), game.getTowerManager().towerList.get(i).getTarget().getLocX() + 32 - (game.getTowerManager().towerList.get(i).getLocX() + 32));
                Graphics2D g2d = (Graphics2D) g.create();
                AffineTransform backup = g2d.getTransform();
                AffineTransform a = AffineTransform.getRotateInstance(rad, game.getTowerManager().towerList.get(i).getLocX() + 32, game.getTowerManager().towerList.get(i).getLocY() + 32);
                g2d.setTransform(a);
                g2d.drawImage(game.getTowerManager().towerList.get(i).getTowerTurretImageFile(), game.getTowerManager().towerList.get(i).getLocX(), game.getTowerManager().towerList.get(i).getLocY(), this);
            } else
                g.drawImage(game.getTowerManager().towerList.get(i).getTowerTurretImageFile(), game.getTowerManager().towerList.get(i).getLocX(), game.getTowerManager().towerList.get(i).getLocY(), this);
        }
    }

    private void drawGridsAndTowers(Graphics g) {
        //Duy's coding part
        int width = game.getGrid().getGridWidth();
        int height = game.getGrid().getGridHeight();
        int slotSize = game.getGrid().getGridSlotSize();
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                GameTile cur = game.getGrid().getGridSlot(i, j);
                if (cur instanceof Tower) {
                    System.out.println(1);
                    continue;
                }

                if (cur instanceof Road) {
                    g.drawImage(roadImage, slotSize * i, slotSize * j, this);
                }
				
                else g.drawImage(mountainImage, slotSize * i, slotSize * j, this);

                if (game.getGrid().getGridSlot(i, j) instanceof Mountain) {
                    g.drawImage(((Mountain) (game.getGrid().getGridSlot(i, j))).towerImage, game.getGrid().getGridSlotSize() * i, game.getGrid().getGridSlotSize() * j, this);
                    g.drawImage(((Mountain) (game.getGrid().getGridSlot(i, j))).turretImage, game.getGrid().getGridSlotSize() * i, game.getGrid().getGridSlotSize() * j, this);

                }
            }
        }
    }

    private void drawEnemies(Graphics g) {
        for (int i = 0; i < game.getEnemyManager().enemyList.size(); i++) {
            //DRAW ENEMIES & SIDEPATH
            if (game.getEnemyManager().enemyList.get(i) instanceof AirEnemy) {
                if (game.getEnemyManager().enemyList.get(i).isHasSidePath())
                    g.drawImage(game.getEnemyManager().enemyList.get(i).enemySidePath, game.getEnemyManager().enemyList.get(i).getLocX() - 30, game.getEnemyManager().enemyList.get(i).getLocY(), this);
                g.drawImage(game.getEnemyManager().enemyList.get(i).enemyImage, game.getEnemyManager().enemyList.get(i).getLocX(), game.getEnemyManager().enemyList.get(i).getLocY(), this);
            } else {
                g.drawImage(game.getEnemyManager().enemyList.get(i).enemyImage, game.getEnemyManager().enemyList.get(i).getLocX(), game.getEnemyManager().enemyList.get(i).getLocY(), this);
                if (game.getEnemyManager().enemyList.get(i).isHasSidePath())
                    g.drawImage(game.getEnemyManager().enemyList.get(i).enemySidePath, game.getEnemyManager().enemyList.get(i).getLocX(), game.getEnemyManager().enemyList.get(i).getLocY(), this);
            }
            //DRAW HEALTHBAR
            g.drawImage(game.getEnemyManager().enemyList.get(i).enemyHealth, game.getEnemyManager().enemyList.get(i).getLocX() - 15, game.getEnemyManager().enemyList.get(i).getLocY() - 12, this);

        }
    }

    private void drawProjectiles(Graphics g) {
        for (int i = 0; i < game.getTowerManager().towerCount; i++) {
            for (int j = 0; j < game.getTowerManager().towerList.get(i).getProjectileCount(); j++) {
                if (game.getTowerManager().towerList.get(i).getProjectilesSpawned().get(j).getLocY() < 580
                        && game.getTowerManager().towerList.get(i).getProjectilesSpawned().get(j) != null) {
                    if (game.getTowerManager().towerList.get(i).getProjectilesSpawned().get(j).isAlive == false)
                        game.getTowerManager().towerList.get(i).getProjectilesSpawned().remove(j);
                    else {
                        g.drawImage(game.getTowerManager().towerList.get(i).getProjectilesSpawned().get(j).projectileImage,
                                game.getTowerManager().towerList.get(i).getProjectilesSpawned().get(j).getLocX(),
                                game.getTowerManager().towerList.get(i).getProjectilesSpawned().get(j).getLocY(),
                                this);
                    }
                }
            }
        }
    }

    private void drawLayoutElements(Graphics g) {
        if (game.getIsGameWon()) {
            g.drawImage(gameWonImage, 300, 300, this);
        }
        if (game.getIsGameLost()) {
            g.drawImage(gameLostImage, 300, 300, this);
        }

        g.drawImage(layoutBackground, 0, 576, this);
        g.drawImage(timeImage, 170, 590, this);
        g.drawString(game.getTime(), 202, 610);
        g.drawImage(resourceImage, 270, 590, this);
        g.drawString(game.getPlayerGold() + "", 302, 610);
        g.drawImage(waveImage, 370, 587, this);
        g.drawString((game.getEnemyManager().getWaveNo() + 1) + "" + " / 4", 410, 610); // will be updated
        g.drawImage(lifeImage, 470, 590, this);
        g.drawString(game.getRemainingChances() + " / 10", 502, 610);
    }
    
    private void saveButActionPerformed(java.awt.event.ActionEvent evt) {  
    	try {   
        FileOutputStream fileStream = new FileOutputStream("GameState.txt");   
        ObjectOutputStream objectStream = new ObjectOutputStream(fileStream);   

        objectStream.writeObject(game);  
        objectStream.close();   
        fileStream.close();   

        JOptionPane.showConfirmDialog(this, 
            "Save game state successfully.");   
        } catch (Exception e) {   
            JOptionPane.showConfirmDialog(this, 
                e.toString() + "\nFail to save game state.");   
        }   
    }
    
    private void backButActionPerformed(java.awt.event.ActionEvent evt) {  
    	GameStage settingsTriggered = new GameStage();
    } 
}