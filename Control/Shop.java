/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import java.awt.Graphics;
import java.awt.image.*;
import java.io.IOException;
import javax.imageio.ImageIO;

import GameTile.*;
/**
 *
 * @author TA
 */
public class Shop {
        private int itemCount;
	private BufferedImage[] itemImage;
	private Tower towerToPlace;
	private boolean towerBought = false;
	private String backgroundImageBuffer;
	private BufferedImage backgroundImage; 
	
	public Shop()
	{
		backgroundImageBuffer = "/images/shopBackground.jpg";
		try {
			backgroundImage = ImageIO.read(getClass().getResourceAsStream(backgroundImageBuffer));
		}	catch(IOException exc) {
				exc.printStackTrace();
		}
		itemCount = 8;
		setShopItems();
	}
	public void buyTower(int mouseX, int mouseY, int playerGold)
	{
                /*Long's coding part
		if((mouseX > 0 && mouseX<60) && (mouseY < 636 && mouseY > 576))
		{
			towerToPlace = new ArcaneTower();
			if(playerGold >= towerToPlace.getCost()){
				towerBought = true;
				return;
			}
			else{
				towerToPlace = null;
				towerBought = false;
				return;
			}
		}
		else if((mouseX>60&&mouseX<120) && (mouseY < 636 && mouseY > 576) )
		{
			towerToPlace = new ArrowTower();
			if(playerGold >= towerToPlace.getCost()){
				towerBought = true;
				return;
			}
			else{
				towerToPlace = null;
				towerBought = false;
				return;
			}
		}
		else if((mouseX>120&&mouseX<180) && (mouseY < 636 && mouseY > 576))
		{
			towerToPlace = new BalistaTower();
			if(playerGold >= towerToPlace.getCost()){
				towerBought = true;
				return;
			}
			else{
				towerToPlace = null;
				towerBought = false;
				return;
			}
		}
		else if((mouseX>180&&mouseX<240) && (mouseY < 636 && mouseY > 576))
		{
			towerToPlace = new CannonTower();
			if(playerGold >= towerToPlace.getCost()){
				towerBought = true;
				return;
			}
			else{
				towerToPlace = null;
				towerBought = false;
				return;
			}
		}
		else if((mouseX>0&&mouseX<60) && (mouseY < 696 && mouseY > 636))
		{
			towerToPlace = new IceTower();
			if(playerGold >= towerToPlace.getCost()){
				towerBought = true;
				return;
			}
			else{
				towerToPlace = null;
				towerBought = false;
				return;
			}
		}
            else */ if((mouseX>60&&mouseX<120) && (mouseY < 696 && mouseY > 636))
		{
			towerToPlace = new NormalTower();
			if(playerGold >= towerToPlace.getCost()){
				towerBought = true;
				return;
			}
			else{
				towerToPlace = null;
				towerBought = false;
				return;
			}
		}
		else if((mouseX>120&&mouseX<180) && (mouseY < 696 && mouseY > 636))
		{
			towerToPlace = new SniperTower();
			if(playerGold >= towerToPlace.getCost()){
				towerBought = true;
				return;
			}
			else{
				towerToPlace = null;
				towerBought = false;
				return;
			}
		}
		else if((mouseX>180&&mouseX<240) && (mouseY < 696 && mouseY > 636))
		{
			towerToPlace = new MachinegunTower();
			if(playerGold >= towerToPlace.getCost()){
				towerBought = true;
				return;
			}
			else{
				towerToPlace = null;
				towerBought = false;
				return;
			}
		}
	}
	
	public void setShopItems()
	{
                /*Long's coding part*/
		itemImage = new BufferedImage[itemCount];
		try {
			itemImage[0] = ImageIO.read(getClass().getResourceAsStream("/images/towerDefense_NormalTower.png"));
		}	catch(IOException exc) {
				exc.printStackTrace();
		}
		try {
			itemImage[1] = ImageIO.read(getClass().getResourceAsStream("/images/towerDefense_SniperTower.png"));
		}	catch(IOException exc) {
				exc.printStackTrace();
		}
		try {
			itemImage[2] = ImageIO.read(getClass().getResourceAsStream("/images/towerDefense_MachinegunTower.png"));
		}	catch(IOException exc) {
				exc.printStackTrace();
		}
	}
	//GETTERS
	public int getItemCount(){
		return itemCount;
	}
	public boolean getTowerBought(){
		return towerBought;
	}
	public Tower getTowerToPlace(){
		return towerToPlace;
	}
	//SETTERS
	public void setTowerBought(boolean inp){
		towerBought = inp;
	}
	public void setTowerToPlace(Tower inp){
		towerToPlace = inp;
	}
	public void draw(Graphics g){
		g.drawImage(backgroundImage,0,576,null);
		//g.drawImage(itemImage[0],0,576,null);
		//g.drawImage(itemImage[1],60,576,null);
		//g.drawImage(itemImage[1],120,576,null);
		//g.drawImage(itemImage[3],180,576,null);
		//g.drawImage(itemImage[3],0,636,null);
		g.drawImage(itemImage[0],60,636,null);
		g.drawImage(itemImage[1],120,636,null);
		g.drawImage(itemImage[2],180,636,null);
                g.drawString("200",80, 636);
                g.drawString("400",140, 636);
                g.drawString("500",200, 636);
	}
}
