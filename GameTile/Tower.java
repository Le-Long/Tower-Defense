/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameTile;

import GameEntity.Bullet;
import GameEntity.Enemy;
import GameEntity.GameEntity;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.InputStream;
import java.util.ArrayList;

//import sun.audio.AudioStream;

/**
 * @author TA
 */
public class Tower extends GameEntity implements GameTile {
    public InputStream shootBuffer;
    protected String[] projectilesImageBuffer;
    private int towerRange;
    private int attackSpeed;
    private int dmg;
    private Enemy target;
    private ArrayList<Bullet> projectilesSpawned;
    private String towerImageFile;
    private Timer myTimer;
    private boolean hasTarget;
    private int cost;
    private int projectileType;
    //public AudioStream shootSound;

    public Tower() {
        projectilesImageBuffer = new String[4]; //0 down //1 up //2 right //3 left
        projectilesSpawned = new ArrayList<Bullet>();
    }

    public void playTowerShoot() {
    }

    public void clearTarget() {
        hasTarget = false;
        deactivateTower();
    }

    //Activating the listener
    public void activateTower() {
        int delay = 750;//(1/attackSpeed)*500; // ~10 updates per second

        ActionListener taskPerformer = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (target != null)
                    if (target.isAlive) {
                        spawnProjectile(target);
                    }
            }
        };
        myTimer = new Timer(delay, taskPerformer);
        myTimer.start();
        //new Timer(delay,taskPerformer).start();
    }

    public void deactivateTower() {
        myTimer.stop();
    }

    public void spawnProjectile(Enemy target) {
        Bullet spawnedProjectile = new Bullet(locX, locY, target, dmg, projectileType, attackSpeed);
		/*Duy's coding part
		if (target.locY >= locY){
			try {
				spawnedProjectile.projectileImage = ImageIO.read(getClass().getResourceAsStream(projectilesImageBuffer[0]));
				spawnedProjectile.projectileImageNumber = 0;
			}	catch(IOException exc) {
					exc.printStackTrace();
			}
		}
		else if (Math.abs(target.locY - locY) < 40 && target.locX > locX){
			try {
				spawnedProjectile.projectileImage = ImageIO.read(getClass().getResourceAsStream(projectilesImageBuffer[2]));
				spawnedProjectile.projectileImageNumber = 2;
			}	catch(IOException exc) {
					exc.printStackTrace();
			}
		}
		else if (Math.abs(target.locY - locY) < 40 && target.locX < locX){
			try {
				spawnedProjectile.projectileImage = ImageIO.read(getClass().getResourceAsStream(projectilesImageBuffer[3]));
				spawnedProjectile.projectileImageNumber = 3;
			}	catch(IOException exc) {
					exc.printStackTrace();
			}
		}
		else{
			try {
				spawnedProjectile.projectileImage = ImageIO.read(getClass().getResourceAsStream(projectilesImageBuffer[1]));
				spawnedProjectile.projectileImageNumber = 1;
			}	catch(IOException exc) {
					exc.printStackTrace();
			}
		}*/
        playTowerShoot();
        projectilesSpawned.add(spawnedProjectile);
    }

    public void upgradeTower() {
        setDamage(dmg + 20);
        setAttackSpeed(attackSpeed + 1);
        setTowerRange(towerRange + 2);
    }

    public void setHasTarget(boolean hasTarget) {
        this.hasTarget = hasTarget;
    }

    public void setProjectileType(int projectileType) {
        this.projectileType = projectileType;
    }

    //Getters
    public ArrayList<Bullet> getProjectilesSpawned() {
        return projectilesSpawned;
    }

    public int getProjectileCount() {
        return projectilesSpawned.size();
    }

    public boolean hasTarget() {
        return hasTarget;
    }

    public String getImage() {
        return towerImageFile;
    }

    public void setImage(String input) {
        towerImageFile = input;
    }

    public int getDamage() {
        return dmg;
    }

    public void setDamage(int dmg) {
        this.dmg = dmg;
    }

    public int getLocX() {
        return locX;
    }

    //Setters
    public void setLocX(int x) {
        locX = x;
    }

    public int getLocY() {
        return locY;
    }

    public void setLocY(int y) {
        locY = y;
    }

    public Enemy getTarget() {
        return target;
    }

    public void setTarget(Enemy target) {
        this.target = target;
        hasTarget = true;
        activateTower();
    }

    public int getTowerRange() {
        return towerRange;
    }

    public void setTowerRange(int range) {
        towerRange = range;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getAttackSpeed() {
        return attackSpeed;
    }

    public void setAttackSpeed(int speed) {
        attackSpeed = speed;
    }
}
