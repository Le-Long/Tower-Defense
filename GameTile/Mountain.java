/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameTile;

import java.awt.image.BufferedImage;

/**
 * @author TA
 */
public class Mountain implements GameTile {
    public boolean hasTower;
    public BufferedImage towerImage;
    public BufferedImage turretImage;
    private Tower towerInThisSlot;

    public boolean mouseHitThisSlot(boolean isInBuyMode, Tower towerToPlace, int x, int y) {
        if (isInBuyMode) {
            if (hasTower == true) {
                return false;
            } else if (hasTower == false) {
                towerInThisSlot = towerToPlace;
                towerInThisSlot.setLocX(x);
                towerInThisSlot.setLocY(y);
                hasTower = true;
                towerImage = towerToPlace.getImage();
                turretImage = towerToPlace.getTowerTurretImageFile();

            }
        }
        return true;
    }

    public Tower getTower() {
        return towerInThisSlot;
    }
}
