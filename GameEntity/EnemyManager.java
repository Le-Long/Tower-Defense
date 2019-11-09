/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameEntity;

import java.util.ArrayList;

import GameTile.*;
/**
 *
 * @author TA
 */
public class EnemyManager {
	public ArrayList<Enemy> enemyList;
	public int enemyCount = 0;
	private int waveNo = 0;
	Spawner starter = new Spawner();

	public EnemyManager(){
		enemyList = new ArrayList<Enemy>();
                starter = new Spawner();
	}

	
	public void initializeEnemies(){
            //Truong's coding part
		if (waveNo == 0) enemyList.add(new NormalEnemy(starter.getEnemySpawnLocX(), starter.getEnemySpawnLocY()));
		if (waveNo == 1) enemyList.add(new TankerEnemy(starter.getEnemySpawnLocX(), starter.getEnemySpawnLocY()));
		if (waveNo == 2) enemyList.add(new AirEnemy(starter.getEnemySpawnLocX(), starter.getEnemySpawnLocY()));
		if (waveNo == 3) enemyList.add(new BossEnemy(starter.getEnemySpawnLocX(), starter.getEnemySpawnLocY()));
		enemyCount++;
	}

	public void killEnemy(int index){
		enemyList.remove(index);
		enemyCount--;
	}

	public void setWaveNo(int waveNo){this.waveNo = waveNo;}
	public int getWaveNo(){
		return waveNo;
	}
}
