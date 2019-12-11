/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameEntity;

import javax.swing.ImageIcon;
import towerdefensegui.SoundManager;

/**
 * @author TA
 */
public class Bullet extends GameEntity {
    public ImageIcon projectileImage;
    public boolean isAlive = true;
    private double speed;
    private int damage;
    private int armorReduce;
    private double timeTillDie;
    private Enemy target;
    private int targetLocX;
    private int targetLocY;
    public boolean shootAir = false;

    public Bullet(int x, int y, Enemy target, int damage, int projectileType, ImageIcon projectileImage) {
        this.projectileImage = projectileImage;
        setProjectileProperties(projectileType);
        timeTillDie = 20;
        this.damage = damage;
        armorReduce = 0;
        speed = 1.5;
        this.locX = x;
        this.locY = y;
        this.target = target;
        targetLocX = target.locX;
        targetLocY = target.locY;
    }

    public void dealDamage() {
        //if (shootAir && target instanceof AirEnemy)
        target.onDamageTaken(damage - target.getArmor());
    }

    //<<<<<<< HEAD
    public void setProjectileProperties(int projectileType) {
        collision();
        if (projectileType == 1) {
            SoundManager rocketSound = new SoundManager("shot_sound.wav");
            shootAir = true;
        } else if (projectileType == 2) {
            this.armorReduce = 5;
        }
    }

    public void update() {
        if (target == null)
            return;
        targetLocX = target.locX;
        targetLocY = target.locY;
        double dx = target.locX - this.locX;
        double dy = target.locY - this.locY;
        double dist = Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));
        dx = dx / dist;
        dy = dy / dist;

        if (Math.abs(target.locX - this.getLocX()) < 15 && Math.abs(target.locY - this.getLocY()) < 15) {
            dealDamage();
            isAlive = false;
            return;
        } else {
            locX += (int) (dx * timeTillDie*speed);
            locY += (int) (dy * timeTillDie*speed);
        }
    }

    public void collision() {
        //if x and y is equal to the enemy's x y // enemy's health/armor etc is reduced
        onHitDebuff();
    }

    public void onHitDebuff() {
        //this.target.setArmor(this.target.getArmor() - this.armorReduce); //updating target's armor
    }

    /////Getters
    public int getLocX() {
        return this.locX;
    }

    public int getLocY() {
        return this.locY;
    }

    public int getDamage() {
        return this.damage;
    }

    public int getArmorReduce() {
        return this.armorReduce;
    }

    public Enemy getTarget() {
        return this.target;
    }
}