/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameTile;

/**
 *
 * @author TA
 */
public class Spawner extends Road{
    	private int enemySpawnLocX;
		private int enemySpawnLocY;
        
        public Spawner() {
            	enemySpawnLocX = 76;
				enemySpawnLocY = 0;
        }

	public int getEnemySpawnLocX() {
		return enemySpawnLocX;
	}

	public int getEnemySpawnLocY() {
		return enemySpawnLocY;
	}
}
