/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameEntity;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
/**
 *
 * @author TA
 */
public class Bullet extends GameEntity{
	private int speed;
	private int damage;
	private int armorReduce;
	private double timeTillDie;
    
        private int onHitEffectFramerate;
	private int projectileFramerate;
	public BufferedImage projectileImage;
	public int projectileImageNumber;
	//private int damagePerSec;
	private Enemy target;
	private int targetLocX;
	private int targetLocY;
	public boolean isAlive = true;
        private boolean shootAir = false;
	
	private String projectileEffectBuffer;
	private int projectileSequenceStartNumber = 295;
	
	public Bullet(int x, int y, Enemy target, int damage, int projectileType, BufferedImage projectileImage){
		this.projectileImage = projectileImage;
		setProjectileProperties(projectileType);
		timeTillDie = 20;
		this.damage = damage;
		armorReduce = 0;
		speed = 30;
		this.locX = x;
		this.locY = y;
		this.target = target;
		projectileEffectBuffer = "/images/towerDefense_tile";
		targetLocX = target.locX;
		targetLocY =  target.locY;
                
	}
	
	
	public void dealDamage(){
            //if (shootAir && target instanceof AirEnemy)
		target.onDamageTaken(damage);
	}
	
//<<<<<<< HEAD
	public void setProjectileProperties(int projectileType)
	{
                setOnHitEffectFramerate(4);
		if(projectileType==0)
		{
			//hit on ground enemy
		}
		else if(projectileType==1)
		{
			//hit on air enemy
		}
		else if(projectileType==2)
		{
			collision();	
			//hit on both
		}
	}
	
	public void setProjectileFramerate(int projectileFramerate)
	{
		this.projectileFramerate = projectileFramerate;
	}
	
	public void setOnHitEffectFramerate(int onHitEffectFramerate)
	{
		this.onHitEffectFramerate = onHitEffectFramerate;
	}

	
	public void update(){
		if(target==null)
			return;
		targetLocX = target.locX;
		targetLocY =  target.locY;
		double dx = target.locX - this.locX;
		double dy = target.locY - this.locY;
		double dist = Math.sqrt(Math.pow(dx, 2)+Math.pow(dy, 2));
		dx = dx/dist;
		dy = dy/dist;

                if(Math.abs(target.locX - this.getLocX()) < 15 && Math.abs(target.locY - this.getLocY()) < 15 ) {
			dealDamage();
			isAlive = false;
			return;
		}
		else { 
                        locX += (int) (dx*timeTillDie);
                        locY += (int) (dy*timeTillDie);
		}
	}

	public void collision(){
		//if x and y is equal to the enemy's x y // enemy's healt/armor etc is reduced
		onHitDebuff();
	}

	public void onHitDebuff(){
		this.target.setArmor(this.target.getArmor() - this.armorReduce); //updating target's armor
	}
	
	/////Getters
	public int getLocX(){
		return this.locX;
	}
	public int getLocY(){
		return this.locY;
	}
	public int getDamage(){
		return this.damage;
	}
	public int getArmorReduce(){
		return this.armorReduce;
	}
	public Enemy getTarget(){
		return this.target;
	}
}
