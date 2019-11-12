/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package towerdefensegui;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import GameEntity.AirEnemy;
import GameTile.*;

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
    //private ImageIcon myImageIcon = new ImageIcon("/Sequences/64x48/explosion1_003.png");

    public GameField() {
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
        game = new GameManager();
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap(672, Short.MAX_VALUE)
                                .addGap(101, 101, 101))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap(714, Short.MAX_VALUE)
                                .addGap(37, 37, 37))
        );
        this.setPreferredSize(new Dimension(832, 776));
        setVisible(true);
        addMouseListener(game.getControl());
        addMouseMotionListener(game.getControl());
        
    }

    //////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////PAINT OBJECTS//////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////
    public void paintComponent(Graphics g) {
        
        //DRAW GRIDS AND TOWERS 
        drawGridsAndTowers(g);
        drawTowers(g);
        //DRAW ENEMIES
        drawEnemies(g);
        //DRAW GRAVEYARD
        //drawGraveyard(g);
        //DRAW PROJECTILES
        drawProjectiles(g);
        //DRAW SHOP
        game.getShop().draw(g);
        //DRAW LAYOUT ELEMENTS
        drawLayoutElements(g);
        repaint();
    }

    private void drawTowers(Graphics g) {
        for (int i = 0; i < game.getTowerManager().towerList.size(); i++) {
            BufferedImage towerLayoutImage = null;
            BufferedImage towerTurretImage = null;
            try {
                towerLayoutImage = ImageIO.read(getClass().getResourceAsStream(game.getTowerManager().towerList.get(i).getImage()));
                towerTurretImage = ImageIO.read(getClass().getResourceAsStream(game.getTowerManager().towerList.get(i).getTowerTurretImageFile()));
            } catch(IOException exc) {
                exc.printStackTrace();
            }
            g.drawImage(towerLayoutImage, game.getTowerManager().towerList.get(i).getLocX(), game.getTowerManager().towerList.get(i).getLocY(), this);
            if (game.getTowerManager().towerList.get(i).hasTarget()) {
                double rad = Math.atan2(game.getTowerManager().towerList.get(i).getTarget().getLocY() - (game.getTowerManager().towerList.get(i).getLocY() + 32), game.getTowerManager().towerList.get(i).getTarget().getLocX() - (game.getTowerManager().towerList.get(i).getLocX() + 32));
                Graphics2D g2d = (Graphics2D) g.create();
                AffineTransform backup = g2d.getTransform();
                AffineTransform a = AffineTransform.getRotateInstance(rad, game.getTowerManager().towerList.get(i).getLocX() + 32, game.getTowerManager().towerList.get(i).getLocY() + 32);
                g2d.setTransform(a);
                g2d.drawImage(towerTurretImage, game.getTowerManager().towerList.get(i).getLocX(), game.getTowerManager().towerList.get(i).getLocY(), this);
            }
            else g.drawImage(towerTurretImage, game.getTowerManager().towerList.get(i).getLocX(), game.getTowerManager().towerList.get(i).getLocY(), this);
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
				/*
				if (cur instanceof Mountain){
					boolean up=false;
					if (i > 0){
						up=(game.getGrid().getGridSlot(i-1,j) instanceof Road);
					}
					boolean down=false;
					if (i < width-1){
						down=(game.getGrid().getGridSlot(i+1,j) instanceof Road);
					}
					boolean right=false;
					if (j > 0){
						right=(game.getGrid().getGridSlot(i,j-1) instanceof Road);
					}
					boolean left=false;
					if (j < height-1){
						right=(game.getGrid().getGridSlot(i,j+1) instanceof Road);
					}
					if ((up)&&(!down)&&(!left)&&(!right)){
						id="023";
					}
					if ((!up)&&(down)&&(!left)&&(!right)){
						id="025";
					}
					if ((!up)&&(!down)&&(left)&&(!right)){
						id="001";
					}
					if ((!up)&&(!down)&&(!left)&&(right)){
						id="047";
					}
					if ((up)&&(!down)&&(left)&&(!right)){
						id="299";
					}
					if ((up)&&(!down)&&(!left)&&(right)){
						id="004";
					}
					if ((!up)&&(down)&&(left)&&(right)){
						id="046";
					}
					if ((!up)&&(down)&&(!left)&&(right)){
						id="048";
					}
				}
				boolean ok=(name == "299")||(name == "002")||(name == "046")||(name == "048");
				if (ok)
					System.out.println(i + " " + j + " " + id);
				//name = name of picture
				 
                name += id;*/
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
                    g.drawImage(game.getEnemyManager().enemyList.get(i).enemySidePath, game.getEnemyManager().enemyList.get(i).getLocX()-30, game.getEnemyManager().enemyList.get(i).getLocY(),this);
                g.drawImage(game.getEnemyManager().enemyList.get(i).enemyImage, game.getEnemyManager().enemyList.get(i).getLocX(), game.getEnemyManager().enemyList.get(i).getLocY(), this);
            }
            else {
                g.drawImage(game.getEnemyManager().enemyList.get(i).enemyImage, game.getEnemyManager().enemyList.get(i).getLocX(), game.getEnemyManager().enemyList.get(i).getLocY(), this);
                if (game.getEnemyManager().enemyList.get(i).isHasSidePath())
                    g.drawImage(game.getEnemyManager().enemyList.get(i).enemySidePath, game.getEnemyManager().enemyList.get(i).getLocX(), game.getEnemyManager().enemyList.get(i).getLocY(),this);
            }
            //DRAW HEALTHBAR
            g.drawImage(game.getEnemyManager().enemyList.get(i).enemyHealth,game.getEnemyManager().enemyList.get(i).getLocX()-15,game.getEnemyManager().enemyList.get(i).getLocY()-12, this);

        }
    }

    //
    private void drawGraveyard(Graphics g) {
        for (int i = 0; i < game.getGraveyard().size(); i++) {
            g.drawImage(game.getGraveyard().get(i).enemyImage, game.getGraveyard().get(i).getLocX(), game.getGraveyard().get(i).getLocY(), this);

        }
    }

    //
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
	    
		g.drawImage(layoutBackground,0,576,this);		
		g.drawImage(timeImage,170,590,this);
		g.drawString(game.getTime(),202,610);
		g.drawImage(resourceImage,270,590,this);
		g.drawString(game.getPlayerGold()+"",302,610);
		g.drawImage(waveImage,370,587,this);
		g.drawString((game.getEnemyManager().getWaveNo() + 1) + "" +" / 4" ,410,610); // will be updated
		g.drawImage(lifeImage,470,590,this);
		g.drawString(game.getRemainingChances()+" / 10",502,610);

    }
}

