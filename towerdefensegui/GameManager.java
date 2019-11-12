/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package towerdefensegui;

import GameEntity.Enemy;
import GameEntity.EnemyManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.Timer;
import Control.*;
import GameEntity.*;
import GameTile.*;
/**
 *
 * @author TA
 */
public class GameManager {
    private int playerScore = 0;
	private int playerGold;
	private int screenX;
	private int screenY;
	private EnemyManager enemyManager;
	private TowerManager towerManager;
	private Shop shop;
	private Grid grid;	
	private Control control;
	private int minute;
	private int second;
	private int frameRate;
	private String time;
	private int remainingChances;
	private boolean gameLost;
	private boolean gameWon;
	private int numOfWaveEnemy;
	
	private ArrayList<Enemy> graveyard;

	public GameManager(){
		gameLost = false;
		gameWon = false;
		remainingChances = 10;
		graveyard = new ArrayList<Enemy>();
		//
		minute = 0;
		second = 0;
		frameRate = 0;
		time = minute + ":" + second;
		playerGold = 400;
		numOfWaveEnemy = 0;
		shop = new Shop();
		int[][] test = {
				{0,0,0,0,0,0,0,0,0},
				{1,1,1,1,0,0,0,0,0},
				{0,0,0,1,0,0,0,0,0},
				{0,0,0,1,0,0,0,0,0},
				{0,0,0,1,0,0,0,0,0},
				{0,0,0,1,0,0,0,0,0},
				{0,0,0,1,1,1,1,1,0},
				{0,0,0,0,0,0,0,1,0},
				{0,0,0,0,0,1,1,1,0},
				{0,0,0,0,0,1,0,0,0},
				{0,0,0,0,0,1,1,1,0},
				{0,0,0,0,0,0,0,1,0},
				{0,0,0,0,0,0,0,1,0}
			};
			
		//RandomMapGenerator random = new RandomMapGenerator();
		grid = new Grid(test);
		//grid = new Grid(random.randomMap());
		control = new Control();
		enemyManager = new EnemyManager();
		towerManager = new TowerManager();
		screenX = grid.getGridSlotSize() * grid.getGridWidth();
		screenY = grid.getGridSlotSize() * grid.getGridHeight();
		updateObjects();
		
		
	}
	//////////////////////////////////////////////////////////////////////////////
	//////////////////////////////UPDATES/////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////
	public void updateObjects()
	{
		int delay = 100; // ~5 updates per second
		ActionListener taskPerformer = new ActionListener(){
			public void actionPerformed(ActionEvent e){
				//Checking if game is lost
				if(remainingChances < 1)
					gameLost = true;
				//Checking if game is won
				if(enemyManager.getWaveNo() == 14 && enemyManager.enemyList.size() == 0)
					gameWon = true;
				if(gameLost || gameWon)
					return;
				updateTime();
				updateWave();
				updateEnemies(); // updating enemies
				updateTowerTargets(); // updating targets
				updateUserInputs(); // updating the controller
				updateGraveyard(); //
				updateProjectiles();//updating the projectiles
			}
		};
		new Timer(delay,taskPerformer).start();
	}


	//UPDATE TIME
	private void updateTime(){
		frameRate++;
		if(frameRate%5 == 0){
			second++;
			if(second>59){
				minute++;
				second = 0;
			}
		}
		time = minute+":"+second;
	}
        //UPDATING WAVES
	public void updateWave() {
            //Truong's coding part
			if (frameRate % 10 == 0) {
				if (enemyManager.getWaveNo() != 3) {
					if (numOfWaveEnemy < 10) {
						enemyManager.initializeEnemies();
						numOfWaveEnemy++;
					}
					else if (enemyManager.enemyList.isEmpty()) {
						numOfWaveEnemy = 0;
						enemyManager.setWaveNo(enemyManager.getWaveNo() + 1);
					}
				}
				else {
					enemyManager.initializeEnemies();
					enemyManager.setWaveNo(enemyManager.getWaveNo() + 1);
				}
			}
	}
        //UPDATE ENEMIES
	private void updateEnemies(){
		//update alive enemies
		for(int i = 0; i < enemyManager.enemyList.size();i++){
			if(!(enemyManager.enemyList.get(i).isAlive)){
				graveyard.add(enemyManager.enemyList.get(i));
				playerScore = playerScore + enemyManager.enemyList.get(i).getResourceGiven()*3;
				playerGold = playerGold + enemyManager.enemyList.get(i).getResource();
				enemyManager.enemyList.get(i).playEnemyDie();
				enemyManager.killEnemy(i);
			}
		}
		if(enemyManager.enemyCount != 0){
			for(int i=0; i<enemyManager.enemyList.size(); i++)
			{	
				if(enemyManager.enemyList.get(i).getVelocity()[0] == 0 && enemyManager.enemyList.get(i).getVelocity()[1] == enemyManager.enemyList.get(i).getSpeed()){
					enemyManager.enemyList.get(i).setEnemyImage(2);
					if (enemyManager.enemyList.get(i).isHasSidePath()) enemyManager.enemyList.get(i).setEnemySidePathImage(2);
				}
				else if(enemyManager.enemyList.get(i).getVelocity()[0] == enemyManager.enemyList.get(i).getSpeed() && enemyManager.enemyList.get(i).getVelocity()[1] == 0){
					enemyManager.enemyList.get(i).setEnemyImage(3);
					if (enemyManager.enemyList.get(i).isHasSidePath()) enemyManager.enemyList.get(i).setEnemySidePathImage(3);
				}
				else if(enemyManager.enemyList.get(i).getVelocity()[0] == 0&& enemyManager.enemyList.get(i).getVelocity()[1] == -enemyManager.enemyList.get(i).getSpeed()){
 					enemyManager.enemyList.get(i).setEnemyImage(1);
					if (enemyManager.enemyList.get(i).isHasSidePath()) enemyManager.enemyList.get(i).setEnemySidePathImage(1);
				}
				else {
					enemyManager.enemyList.get(i).setEnemyImage(0);
					if (enemyManager.enemyList.get(i).isHasSidePath()) enemyManager.enemyList.get(i).setEnemySidePathImage(0);
				}
			}
		}
		for(int i=0; i<enemyManager.enemyList.size(); i++)
			{	
				double hppercent = ((double)enemyManager.enemyList.get(i).getHealth() / (double)enemyManager.enemyList.get(i).getMaxHealth());
				enemyManager.enemyList.get(i).setEnemyHB(hppercent);
				int gridX = enemyManager.enemyList.get(i).locX / 64;
				int gridY = enemyManager.enemyList.get(i).locY / 64;
				if(enemyManager.enemyList.get(i).getVelocity()[1] == -enemyManager.enemyList.get(i).getSpeed() && grid.getGridSlot(gridX+1, gridY) instanceof Road
                                        && enemyManager.enemyList.get(i).getLocY() > 5 + gridY * grid.getGridSlotSize())
					gridY++;

				if(gridX == grid.getGridWidth()-1 && enemyManager.enemyList.get(i).getVelocity()[0] == enemyManager.enemyList.get(i).getSpeed()){
					enemyManager.enemyList.remove(i);
					remainingChances--;
					if(remainingChances<1)
					{
						gameLost = true;
					}
						
					continue;
				}
				
				int switchCase;
				if(gridY +  1< 9 ){
					if(gridY == 0)
						switchCase = -1; //start
					else if(grid.getGridSlot(gridX, gridY + 1) instanceof Road && enemyManager.enemyList.get(i).getVelocity()[1] == enemyManager.enemyList.get(i).getSpeed())
						switchCase = 1; //DOWN
					else if(grid.getGridSlot(gridX , gridY - 1) instanceof Road && enemyManager.enemyList.get(i).getVelocity()[1] != enemyManager.enemyList.get(i).getSpeed() && !(grid.getGridSlot(gridX+1, gridY ) instanceof Road))
						switchCase = 2; //UP
					else if(grid.getGridSlot(gridX + 1, gridY) instanceof Road)
						switchCase = 3; //RIGHT
					else 
						switchCase = 0; //DEFAULT
				}
				else {
					if(grid.getGridSlot(gridX + 1, gridY) instanceof Road)
						switchCase = 3; //RIGHT
					else
						switchCase = 2; //UP
						
				}
				switch(switchCase){
					case -1:{
						enemyManager.enemyList.get(i).setVelocity(0, (enemyManager.enemyList.get(i).getSpeed()));
						enemyManager.enemyList.get(i).move(enemyManager.enemyList.get(i).getVelocity()[0], enemyManager.enemyList.get(i).getVelocity()[1]);
						break;
					}
					case 1:{
						enemyManager.enemyList.get(i).setVelocity(0, (enemyManager.enemyList.get(i).getSpeed()));
						enemyManager.enemyList.get(i).move(enemyManager.enemyList.get(i).getVelocity()[0], enemyManager.enemyList.get(i).getVelocity()[1]);
						break;
					}
					case 2:{
						enemyManager.enemyList.get(i).setVelocity(0, -(enemyManager.enemyList.get(i).getSpeed()));
						enemyManager.enemyList.get(i).move(enemyManager.enemyList.get(i).getVelocity()[0], enemyManager.enemyList.get(i).getVelocity()[1]);
						break;
					}
					case 3:{
						enemyManager.enemyList.get(i).setVelocity((enemyManager.enemyList.get(i).getSpeed()),0 );
						enemyManager.enemyList.get(i).move(enemyManager.enemyList.get(i).getVelocity()[0], enemyManager.enemyList.get(i).getVelocity()[1]);
						break;
					}
					default:{
						enemyManager.enemyList.get(i).setVelocity(0, (enemyManager.enemyList.get(i).getSpeed()));
						enemyManager.enemyList.get(i).move(enemyManager.enemyList.get(i).getVelocity()[0], enemyManager.enemyList.get(i).getVelocity()[1]);
						break;
					}
				
				}	
			

			}
		}
	//UPDATING GRAVEYARD
	private void updateGraveyard(){
		int updateFrequency = frameRate % 2;
		if(updateFrequency == 0)
			updateDyingEnemy();
	}
	//UPDATE DYING ENEMY
	private void updateDyingEnemy(){
		
		for(int i = 0; i < graveyard.size(); i++){
                    /*Truong's coding part
			int timeForCorpse = frameRate % 30;
			if(graveyard.get(i).getEnemyImageNumber() == 15)
			graveyard.get(i).setEnemyImage(16);
			else if(graveyard.get(i).getEnemyImageNumber() == 16)
			graveyard.get(i).setEnemyImage(17);
			else if(graveyard.get(i).getEnemyImageNumber() == 17 && timeForCorpse == 28){
				graveyard.remove(i);
			}
			else if(graveyard.get(i).getEnemyImageNumber() == 17 && timeForCorpse < 29){
				graveyard.get(i).setEnemyImage(17);	
			}
			else
				graveyard.get(i).setEnemyImage(15);*/	
		}	
	}
	int testNumber=1;
	//UPDATING TOWER TARGETS
	private void updateTowerTargets(){
			for(int i=0; i<towerManager.towerList.size(); i++){	
				for(int j=0; j<enemyManager.enemyList.size(); j++){

					if(!towerManager.towerList.get(i).hasTarget()
							&&
							Math.sqrt((towerManager.towerList.get(i).getLocX() - enemyManager.enemyList.get(j).locX)*
									(towerManager.towerList.get(i).getLocX() - enemyManager.enemyList.get(j).locX)+
									(towerManager.towerList.get(i).getLocY() - enemyManager.enemyList.get(j).locY)*
									(towerManager.towerList.get(i).getLocY() - enemyManager.enemyList.get(j).locY))
							< towerManager.towerList.get(i).getTowerRange()
						)		
					{

						if(enemyManager.enemyList.get(j).isAlive==true)
						{
							testNumber++;
							towerManager.towerList.get(i).setTarget(enemyManager.enemyList.get(j));

							j = enemyManager.enemyList.size();					
						}

					}
					
					else if(towerManager.towerList.get(i).hasTarget())
					{
						if(towerManager.towerList.get(i).getTarget().isAlive==false)
						{
							towerManager.towerList.get(i).clearTarget();
							return;
						}
						testNumber++;
						if(Math.sqrt((towerManager.towerList.get(i).getLocX() - towerManager.towerList.get(i).getTarget().locX)*
								(towerManager.towerList.get(i).getLocX() - towerManager.towerList.get(i).getTarget().locX)+
								(towerManager.towerList.get(i).getLocY() - towerManager.towerList.get(i).getTarget().locY)*
								(towerManager.towerList.get(i).getLocY() - towerManager.towerList.get(i).getTarget().locY))
						> towerManager.towerList.get(i).getTowerRange()
							)				
						{
							towerManager.towerList.get(i).clearTarget();
							j=enemyManager.enemyList.size();
						}
											
					}
				}
			}
		
		
	}
	//UPDATE PROJECTILES
	private void updateProjectiles(){
		int time = frameRate;
		if(towerManager.towerList.size() == 0)
			return;
		for(int i=0; i<towerManager.towerList.size(); i++){
			for(int j = 0; j < towerManager.towerList.get(i).getProjectilesSpawned().size(); j++){
				
				towerManager.towerList.get(i).getProjectilesSpawned().get(j).update();
				if(towerManager.towerList.get(i).getTarget() == null )
					towerManager.towerList.get(i).getProjectilesSpawned().remove(j);
			}
		}
	}
	
	
	private void updateUserInputs(){
		boolean b;
		int gridNoX = (control.getMouseX())/64;
		int gridNoY = (control.getMouseY())/64;
		if(control.getMouseX() == 0 && control.getMouseY() == 0)
			return;
		if(control.getMouseY()>screenY)
		{
			shop.buyTower(control.getMouseX(),control.getMouseY(), playerGold);
			control.setMouseX(0);
			control.setMouseY(0);
			return;
		}
		if(grid.getGridSlot(gridNoX, gridNoY) instanceof Mountain && control.getMouseX() != 0 && control.getMouseY() != 0 ){
			if(((Mountain)grid.getGridSlot(gridNoX, gridNoY)).hasTower == true){
				if(playerGold >= ((Mountain)grid.getGridSlot(gridNoX, gridNoY)).getTower().getCost()){
				((Mountain)grid.getGridSlot(gridNoX, gridNoY)).getTower().upgradeTower();
				control.setMouseX(0);
				control.setMouseY(0);
				playerGold = playerGold - ((Mountain)grid.getGridSlot(gridNoX, gridNoY)).getTower().getCost();
				return;
				}
			}
			
		}
		
			if(!(grid.getGridSlot(gridNoX, gridNoY) instanceof Mountain))
					return;
			b = ((Mountain) grid.getGridSlot(gridNoX,gridNoY)).mouseHitThisSlot(shop.getTowerBought(), shop.getTowerToPlace(), gridNoX*grid.getGridHeight(), gridNoY*grid.getGridWidth());
			if(shop.getTowerToPlace() != null){
				if(b && playerGold >= shop.getTowerToPlace().getCost())
				{
					towerManager.addTower(shop.getTowerToPlace());
					shop.getTowerToPlace().setLocX(gridNoX * 64);
					shop.getTowerToPlace().setLocY(gridNoY * 64);
					shop.setTowerBought(false);
					control.setMouseX(0);
					control.setMouseY(0);
					playerGold = playerGold - shop.getTowerToPlace().getCost();
					shop.setTowerToPlace(null);
				}

		}
	}
	//getters
	public Grid getGrid(){
		return grid;
	}
	public EnemyManager getEnemyManager(){
		return enemyManager;
	}
	public TowerManager getTowerManager(){
		return towerManager;
	}
	public Shop getShop(){
		return shop;
	}
	public Control getControl(){
		return control;
	}
	public String getTime(){
		return time;
	}
	public int getPlayerGold(){
		return playerGold;
	}
	public ArrayList<Enemy> getGraveyard(){
		return graveyard;
	}
	public int getRemainingChances(){
		return remainingChances;
	}
	public boolean getIsGameWon(){
		return gameWon;
	}
	public boolean getIsGameLost(){
		return gameLost;
	}
}
