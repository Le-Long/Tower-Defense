/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameEntity;

//import sun.audio.AudioStream;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import towerdefensegui.SoundManager;

/**
 * @author TA
 */
public class Enemy extends GameEntity {
    //public AudioStream dieSound;
    public ImageIcon enemyImage;
    public ImageIcon enemySidePath;
    public String[] enemyImageBuffer;
    public String[] enemySidePathImageBuffer;
    public ImageIcon enemyHealth;
    public boolean isAlive = true;
    private int armor;  // current armor
    private int health;  // current hp
    private int resourceGiven; // prize
    private int maxHealth;
    private int speed;
    private int velocityX;
    private int velocityY;
    private int enemyImageNumber;
    private boolean hasSidePath = false;

    public Enemy(int locX, int locY) {
        maxHealth = 100;
        this.locX = locX;
        this.locY = locY;
        velocityX = 0;
        velocityY = 0;
        enemyImageBuffer = new String[10];
        enemySidePathImageBuffer = new String[4];
        enemyImageBuffer[4] = "/images/towerDefense_explosion_1.png";
        enemyImageBuffer[5] = "/images/towerDefense_explosion_2.png";
        enemyImageBuffer[6] = "/images/towerDefense_explosion_3.png";
        enemyImageBuffer[7] = "/images/towerDefense_explosion_4.png";
        enemyImageBuffer[8] = "/images/towerDefense_explosion_5.png";
        enemyImageBuffer[9] = "/images/towerDefense_explosion_6.png";
    }

    public void playEnemyDie() {
        new SoundManager("explosion_sound.wav");
    }

    //////////Setters
    public void setEnemyHB(double i) {
        if (i >= 1.00) {
            try {
                enemyHealth = new ImageIcon(getClass().getResource("/images/hb100.png"));
            } catch (Exception exc) {
                exc.printStackTrace();
            }
        }
        if (i > 0.8 && i <= 0.9) {
            try {
                enemyHealth = new ImageIcon (getClass().getResource("/images/hb90.png"));
            } catch (Exception exc) {
                exc.printStackTrace();
            }
        }
        if (i > 0.7 && i <= 0.8) {
            try {
                enemyHealth = new ImageIcon (getClass().getResource("/images/hb80.png"));
            } catch (Exception exc) {
                exc.printStackTrace();
            }
        }
        if (i > 0.6 && i <= 0.7) {
            try {
                enemyHealth = new ImageIcon (getClass().getResource("/images/hb70.png"));
            } catch (Exception exc) {
                exc.printStackTrace();
            }
        }
        if (i > 0.5 && i <= 0.6) {
            try {
                enemyHealth = new ImageIcon(getClass().getResource("/images/hb60.png"));
            } catch (Exception exc) {
                exc.printStackTrace();
            }
        }
        if (i > 0.4 && i <= 0.5) {
            try {
                enemyHealth = new ImageIcon(getClass().getResource("/images/hb50.png"));
            } catch (Exception exc) {
                exc.printStackTrace();
            }
        }
        if (i > 0.3 && i <= 0.4) {
            try {
                enemyHealth = new ImageIcon(getClass().getResource("/images/hb40.png"));
            } catch (Exception exc) {
                exc.printStackTrace();
            }
        }
        if (i > 0.2 && i <= 0.3) {
            try {
                enemyHealth = new ImageIcon(getClass().getResource("/images/hb30.png"));
            } catch (Exception exc) {
                exc.printStackTrace();
            }
        }
        if (i > 0.1 && i <= 0.2) {
            try {
                enemyHealth = new ImageIcon(getClass().getResource("/images/hb20.png"));
            } catch (Exception exc) {
                exc.printStackTrace();
            }
        }
        if (i > 0.0 && i <= 0.1) {
            try {
                enemyHealth = new ImageIcon(getClass().getResource("/images/hb10.png"));
            } catch (Exception exc) {
                exc.printStackTrace();
            }
        }
        if (i <= 0.0) {
            try {
                enemyHealth = new ImageIcon(getClass().getResource("/images/hb0.png"));
            } catch (Exception exc) {
                exc.printStackTrace();
            }
        }
    }


    public void onDamageTaken(int dmg) {
        if (!isAlive)
            return;

        health = health - dmg;

        if (health < 1) {
            isAlive = false;
        }
    }


    public void move(int velocityX, int velocityY) {
        locX = locX + velocityX;
        locY = locY + velocityY;
    }

    //////////Setters
    public void setEnemyImage(int i) {
        try {
            enemyImage = new ImageIcon(getClass().getResource(enemyImageBuffer[i]));
            enemyImageNumber = i;
        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }

    public void setEnemySidePathImage(int i) {
        if (hasSidePath == true) {
            try {
                enemySidePath = new ImageIcon(getClass().getResource(enemySidePathImageBuffer[i]));
            } catch (Exception exc) {
                exc.printStackTrace();
            }
        }
    }

    public void setX(int x) {

        this.locX = x;

    }

    public void setY(int y) {

        this.locY = y;

    }

    public void setVelocity(int i, int j) {
        velocityX = i;
        velocityY = j;
    }

    public int getLocX() {

        return this.locX;

    }

    public int getLocY() {

        return this.locY;

    }

    public int getSpeed() {

        return this.speed;

    }

    public void setSpeed(int speed) {

        this.speed = speed;

    }

    public int getArmor() {

        return this.armor;

    }

    //////////Getters

    public void setArmor(int armor) {

        this.armor = armor;

    }

    public int getHealth() {

        return this.health;

    }

    public void setHealth(int health) {

        this.health = health;

    }

    public int getResourceGiven() {

        return this.resourceGiven;

    }

    public void setResourceGiven(int resourceGiven) {

        this.resourceGiven = resourceGiven;

    }

    public boolean isHasSidePath() {
        return hasSidePath;
    }

    public void setHasSidePath(boolean hasSidePath) {
        this.hasSidePath = hasSidePath;
    }

    public int getMaxHealth() {
        return this.maxHealth;
    }

    public void setMaxHealth(int i) {
        maxHealth = i;
    }

    public int getVelocityX() {
        return velocityX;
    }

    public int getVelocityY() {
        return velocityY;
    }

    public int getEnemyImageNumber() {
        return enemyImageNumber;
    }

    public int getResource() {
        return resourceGiven;
    }
}
