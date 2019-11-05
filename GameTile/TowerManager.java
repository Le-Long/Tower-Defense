/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameTile;

import java.util.ArrayList;

/**
 *
 * @author TA
 */
public class TowerManager {
    public ArrayList<Tower> towerList;
	
	public int towerCount = 0;
	
	public TowerManager()
	{
		towerList = new ArrayList<>();
	}
	
	public void addTower(Tower towerToAdd)
	{

		towerList.add(towerToAdd);
		towerCount++;
	}

}
