/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameTile;

import java.util.ArrayList;

/**
 * @author TA
 */
public class TowerManager implements java.io.Serializable {
    public ArrayList<Tower> towerList;
    private static TowerManager INSTANCE = new TowerManager();
    public int towerCount = 0;

    private TowerManager() {
        towerList = new ArrayList<>();
    }
    
    public static TowerManager getInstance() {
        return INSTANCE;
    }

    public void addTower(Tower towerToAdd) {

        towerList.add(towerToAdd);
        towerCount++;
    }

}
