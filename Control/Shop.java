/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import GameTile.MachinegunTower;
import GameTile.NormalTower;
import GameTile.SniperTower;
import GameTile.Tower;

import javax.imageio.ImageIO;
import java.awt.*;
import javax.swing.ImageIcon;

/**
 * @author TA
 */
public class Shop implements java.io.Serializable {
    private int itemCount;
    private ImageIcon[] itemImage;
    private Tower towerToPlace;
    private boolean towerBought = false;
    private String backgroundImageBuffer;
    private ImageIcon backgroundImage;
    private static Shop INSTANCE = new Shop();
    
    private Shop() {
        backgroundImageBuffer = "/images/shopBackground.jpg";
        try {
            backgroundImage = new ImageIcon(getClass().getResource(backgroundImageBuffer));
        } catch (Exception exc) {
            exc.printStackTrace();
        }
        itemCount = 3;
        setShopItems();
    }
    
    public static Shop getInstance() {
        return INSTANCE;
    }

    public void buyTower(int mouseX, int mouseY, int playerGold) {
        if ((mouseX > 60 && mouseX < 120) && (mouseY < 696 && mouseY > 636)) {
            towerToPlace = new NormalTower();
            if (playerGold >= towerToPlace.getCost()) {
                towerBought = true;
                return;
            } else {
                towerToPlace = null;
                towerBought = false;
                return;
            }
        } else if ((mouseX > 120 && mouseX < 180) && (mouseY < 696 && mouseY > 636)) {
            towerToPlace = new SniperTower();
            if (playerGold >= towerToPlace.getCost()) {
                towerBought = true;
                return;
            } else {
                towerToPlace = null;
                towerBought = false;
                return;
            }
        } else if ((mouseX > 180 && mouseX < 240) && (mouseY < 696 && mouseY > 636)) {
            towerToPlace = new MachinegunTower();
            if (playerGold >= towerToPlace.getCost()) {
                towerBought = true;
                return;
            } else {
                towerToPlace = null;
                towerBought = false;
                return;
            }
        }
    }

    public void setShopItems() {
        /*Long's coding part*/
        itemImage = new ImageIcon[itemCount];
        try {
            itemImage[0] = new ImageIcon(getClass().getResource("/images/towerDefense_NormalTower.png"));
        } catch (Exception exc) {
            exc.printStackTrace();
        }
        try {
            itemImage[1] = new ImageIcon(getClass().getResource("/images/towerDefense_SniperTower.png"));
        } catch (Exception exc) {
            exc.printStackTrace();
        }
        try {
            itemImage[2] = new ImageIcon(getClass().getResource("/images/towerDefense_MachinegunTower.png"));
        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }

    //GETTERS
    public int getItemCount() {
        return itemCount;
    }

    public boolean getTowerBought() {
        return towerBought;
    }

    //SETTERS
    public void setTowerBought(boolean inp) {
        towerBought = inp;
    }

    public Tower getTowerToPlace() {
        return towerToPlace;
    }

    public void setTowerToPlace(Tower inp) {
        towerToPlace = inp;
    }

    public void draw(Graphics g) {
        backgroundImage.paintIcon(null, g, 0, 576);
        itemImage[0].paintIcon(null, g, 60, 636);
        itemImage[1].paintIcon(null, g, 120, 636);
        itemImage[2].paintIcon(null, g, 180, 636);
        g.drawString("200", 80, 636);
        g.drawString("400", 140, 636);
        g.drawString("500", 200, 636);
    }
}
