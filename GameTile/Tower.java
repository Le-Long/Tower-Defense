/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameTile;

import GameEntity.Bullet;
import GameEntity.Enemy;
import GameEntity.GameEntity;

import javax.imageio.ImageIO;
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
    protected ImageIcon projectilesImageBuffer;
    private int towerRange;
    private int attackSpeed;
    private int dmg;
    private Enemy target;
    private ArrayList<Bullet> projectilesSpawned;
    private ImageIcon towerImageFile;
    private ImageIcon towerTurretImageFile;
    private Timer myTimer;
    private boolean hasTarget;
    private int cost;
    private int projectileType;
    public String[] upgradedPath;
    public boolean upgraded;
    //public AudioStream shootSound;

    public Tower() {
        projectilesSpawned = new ArrayList<Bullet>();
        upgradedPath = new String[2];
        upgraded = false;
    }

    public void playTowerShoot() {
        //play shooting sound
    }

    public void clearTarget() {
        hasTarget = false;
        deactivateTower();
    }

    //Activating the listener
    public void activateTower() {
        int delay = (int) (1000.0/attackSpeed); // ~10 updates per second

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
        Bullet spawnedProjectile = new Bullet(locX, locY, target, dmg, projectileType, projectilesImageBuffer);
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
        try {
            setTowerTurretImageFile(getClass().getResource(upgradedPath[0]));
            setProjectilesImageBuffer(getClass().getResource(upgradedPath[1]));
        } catch (Exception exc) {
            exc.printStackTrace();
        }
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

    public ImageIcon getImage() {
        return towerImageFile;
    }

    public void setImage(java.net.URL input) {
        towerImageFile = new ImageIcon(input);
    }

    public ImageIcon getTowerTurretImageFile() {
        return towerTurretImageFile;
    }

    public void setTowerTurretImageFile(java.net.URL towerTurretImageFile) {
        this.towerTurretImageFile = new ImageIcon(towerTurretImageFile);
    }

    public void setProjectilesImageBuffer(java.net.URL projectilesImageBuffer) {
        this.projectilesImageBuffer = new ImageIcon(projectilesImageBuffer);
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

    public void setUpgraded(boolean upgraded) {
        this.upgraded = upgraded;
    }

    public boolean isUpgraded() {
        return upgraded;
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
