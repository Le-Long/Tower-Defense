/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameEntity;

import java.io.InputStream;
import sun.audio.AudioStream;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.*;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.Timer;
/**
 *
 * @author TA
 */
public class Enemy extends GameEntity{
		private int armor;  // current armor
		private int health;  // current hp
		private int resourceGiven; // prize
		private int maxHealth;
		private int speed;
		private int[] velocity = new int[2];
        
        public String hitEffectCode;
		private int hitEffectNumber;
		private int hitEffectSize;
		private Timer fpsTimer;
		public boolean isPlayingHitAnimation = false;
		public BufferedImage hitEffectImage;
		private ImageIcon impactImageIcon;
		public int targetCount = 0;
		//private int debuffDPS;  from poison tower, taken per sec
		private boolean isGettingHit = false;
		public InputStream dieBuffer;
		public AudioStream dieSound;
	
		private int enemyImageNumber;
		public BufferedImage enemyImage;
		public BufferedImage enemySidePath;
		private boolean hasSidePath = false;

		public String[] enemyImageBuffer;
		public String[] enemySidePathImageBuffer;

		public BufferedImage enemyHealth;
	
		public boolean isAlive = true;
	
		public Enemy(int locX, int locY)
		{
			hitEffectNumber = 1;
			hitEffectSize = 90;
			hitEffectCode = "/Sequences/64x48/explosion1_00";
		
			maxHealth = 100;
			this.locX = locX;
			this.locY = locY;
			velocity[0] = 0;
			velocity[1] = 0;
			enemyImageBuffer = new String[5];
			enemySidePathImageBuffer = new String[4];

		}

		public void setFPSTimer(int animationFramerate) {
			int delay = (1000/animationFramerate);//(1/attackSpeed)*500; // ~10 updates per second
			ActionListener taskPerformer = new ActionListener(){

				public void actionPerformed(ActionEvent e)
				{
					setHitEffectImage();
				}
			};
			fpsTimer = new Timer(delay,taskPerformer);
			fpsTimer.start();
			//new Timer(delay,taskPerformer).start();
		}

		public void playEffectAnimation(int animFramerate)
		{
			setHitEffectImage();
			hitEffectSize=animFramerate;
			setFPSTimer(animFramerate);
			isPlayingHitAnimation = true;
		}
	
		public void setHitEffectImage()
		{
			//System.out.println(hitEffectCode + hitEffectNumber + ".png");
		
			try {
				hitEffectImage = ImageIO.read(getClass().getResourceAsStream(hitEffectCode + hitEffectNumber + ".png"));
			}	catch(IOException exc) { //"/Sequences/64x48/explosion1_007.png")

					exc.printStackTrace();
			}
			//System.out.println("DRAWN THE IMAGE");
			hitEffectNumber = hitEffectNumber + 1;
			if(hitEffectNumber >= hitEffectSize-1)
			{
				isGettingHit = false;
				isPlayingHitAnimation = false;
				hitEffectNumber = 1;
				fpsTimer.stop();
			}
			
		}
	
		public void playEnemyDie(){
		}
	
		//////////Setters
		public void setEnemyHB(double i)
		{
			if(i >= 1.00){
				try {
					enemyHealth = ImageIO.read(getClass().getResourceAsStream("/images/hb100.png"));
				}	catch(IOException exc) {
						exc.printStackTrace();
				}
			}
			if(i > 0.8 && i <= 0.9){
				try {
					enemyHealth = ImageIO.read(getClass().getResourceAsStream("/images/hb90.png"));
				}	catch(IOException exc) {
						exc.printStackTrace();
				}
			}
			if(i > 0.7 && i <= 0.8){
				try {
					enemyHealth = ImageIO.read(getClass().getResourceAsStream("/images/hb80.png"));
				}	catch(IOException exc) {
						exc.printStackTrace();
				}
			}
			if(i > 0.6 && i <= 0.7){
				try {
					enemyHealth = ImageIO.read(getClass().getResourceAsStream("/images/hb70.png"));
				}	catch(IOException exc) {
						exc.printStackTrace();
				}
			}
			if(i > 0.5 && i <= 0.6){
				try {
					enemyHealth = ImageIO.read(getClass().getResourceAsStream("/images/hb60.png"));
				}	catch(IOException exc) {
						exc.printStackTrace();
				}
			}
			if(i > 0.4 && i <= 0.5){
				try {
					enemyHealth = ImageIO.read(getClass().getResourceAsStream("/images/hb50.png"));
				}	catch(IOException exc) {
						exc.printStackTrace();
				}
			}
				if(i > 0.3 && i <= 0.4){
				try {
					enemyHealth = ImageIO.read(getClass().getResourceAsStream("/images/hb40.png"));
				}	catch(IOException exc) {
						exc.printStackTrace();
				}
			}
			if(i > 0.2 && i <= 0.3){
				try {
					enemyHealth = ImageIO.read(getClass().getResourceAsStream("/images/hb30.png"));
				}	catch(IOException exc) {
						exc.printStackTrace();
				}
			}
			if(i > 0.1 && i <= 0.2){
				try {
					enemyHealth = ImageIO.read(getClass().getResourceAsStream("/images/hb20.png"));
				}	catch(IOException exc) {
						exc.printStackTrace();
				}
			}
			if(i > 0.0 && i <= 0.1){
				try {
					enemyHealth = ImageIO.read(getClass().getResourceAsStream("/images/hb10.png"));
				}	catch(IOException exc) {
						exc.printStackTrace();
				}
			}
			if(i <= 0.0){
				try {
					enemyHealth = ImageIO.read(getClass().getResourceAsStream("/images/hb0.png"));
				}	catch(IOException exc) {
						exc.printStackTrace();
				}
			}
		}

	
		public void onDamageTaken(int dmg, int onHitEffectFramerate, String hitEffectCode, int hitEffectNumber)
		{
			if(!isAlive)
				return;
		
			health = health - dmg;
		
			isGettingHit=true;
			hitEffectNumber=1;
			this.hitEffectCode = hitEffectCode;
			this.hitEffectNumber = hitEffectNumber;
			this.hitEffectSize = onHitEffectFramerate;
		
			if(!isPlayingHitAnimation)
				playEffectAnimation(onHitEffectFramerate);

		
		
			if(health<1)
			{
				isAlive = false;
			}

		}
		
	
		public void move(int velocityX, int velocityY)
		{
			locX = locX + velocityX;
			locY = locY + velocityY;
		}
	
		//////////Setters
		public void setEnemyImage(int i)
		{
				try {
					enemyImage = ImageIO.read(getClass().getResourceAsStream(enemyImageBuffer[i]));
					enemyImageNumber = i;
				}	catch(IOException exc) {
						exc.printStackTrace();
				}
		}

		public void setEnemySidePathImage(int i) {
			if (hasSidePath == true) {
				try {
					enemySidePath = ImageIO.read(getClass().getResourceAsStream(enemySidePathImageBuffer[i]));
				} catch(IOException exc) {
					exc.printStackTrace();
				}
			}
		}
		public void setX(int x){

		this.locX = x;

		}

		public void setY(int y){

			this.locY = y;

		}

		public void setSpeed(int speed){

			this.speed = speed;

		}

		public void setArmor(int armor){

			this.armor = armor;

		}

		public void setHealth(int health){

			this.health = health;

		}

		public void setResourceGiven(int resourceGiven){

			this.resourceGiven = resourceGiven;

		}
		public void setVelocity(int i,int j){
			velocity[0] = i;
			velocity[1] = j;
		}
		public void setMaxHealth(int i){
		maxHealth = i;
	}

		//////////Getters

		public int getLocX(){

			return this.locX;

		}

		public int getLocY(){

			return this.locY;

		}

		public int getSpeed(){

			return this.speed;

		}

		public int getArmor(){

			return this.armor;

		}

		public int getHealth(){

			return this.health;

		}

		public int getResourceGiven(){

			return this.resourceGiven;

		}
		public void setHasSidePath(boolean hasSidePath){
		    this.hasSidePath = hasSidePath;
        }

        public boolean isHasSidePath(){
		    return hasSidePath;
        }

		public int getMaxHealth(){
		return this.maxHealth;
	}
	
		public ImageIcon getImpactImageIcon()
	{
		return impactImageIcon;
	}
	
		public boolean isGettingHit()
	{
		return isGettingHit;
	}
		public int[] getVelocity(){
		return velocity;
	}
		public int getEnemyImageNumber(){
		return enemyImageNumber;
	}
		public int getResource(){
		return resourceGiven;
	}
}
