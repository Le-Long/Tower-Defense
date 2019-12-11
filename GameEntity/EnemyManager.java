/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameEntity;

import GameTile.Spawner;

import java.util.ArrayList;

/**
 * @author TA
 */
public class EnemyManager implements java.io.Serializable {
    public ArrayList<Enemy> enemyList;
    public int enemyCount = 0;
    Spawner starter = new Spawner();
    private int waveNo = 0;

    public EnemyManager() {
        enemyList = new ArrayList<Enemy>();
        starter = new Spawner();
    }

    public void initializeEnemies() {
        //Truong's coding part
        if (waveNo == 0) enemyList.add(new NormalEnemy(starter.getEnemySpawnLocX(), starter.getEnemySpawnLocY()));
        if (waveNo == 1) enemyList.add(new TankerEnemy(starter.getEnemySpawnLocX(), starter.getEnemySpawnLocY()));
        if (waveNo == 2) enemyList.add(new AirEnemy(starter.getEnemySpawnLocX(), starter.getEnemySpawnLocY()));
        if (waveNo == 3) enemyList.add(new BossEnemy(starter.getEnemySpawnLocX(), starter.getEnemySpawnLocY()));
        enemyCount++;
    }

    public void killEnemy(int index) {
        enemyList.remove(index);
        enemyCount--;
    }

    public int getWaveNo() {
        return waveNo;
    }

    public void setWaveNo(int waveNo) {
        this.waveNo = waveNo;
    }
}
