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
	private int waveNo;
        Spawner starter;

	public EnemyManager(){
		enemyList = new ArrayList<Enemy>();
                starter = new Spawner();
	}

	
	public void initializeEnemies(int waveNo, int enemyNo){
            //Truong's coding part 
        }
        public void killEnemy(int index){
		enemyList.remove(index);
		enemyCount--;
	}
	
	//GETTERS
	public int getWaveNo(){
		return waveNo;
	}
}
