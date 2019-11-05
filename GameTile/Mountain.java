/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameTile;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author TA
 */
public class Mountain implements GameTile{
        private Tower towerInThisSlot;
	public boolean hasTower;
        public BufferedImage towerImage;
        
        public boolean mouseHitThisSlot(boolean isInBuyMode, Tower towerToPlace, int x, int y)
	{
		if(isInBuyMode)
		{
			if(hasTower==true)
			{
				return false;
			}
			else if(hasTower==false)
			{
				towerInThisSlot = towerToPlace;
				towerInThisSlot.setLocX(x);
				towerInThisSlot.setLocY(y);
				hasTower=true;
				try {
					towerImage = ImageIO.read(getClass().getResourceAsStream(towerToPlace.getImage()));
				}	catch(IOException exc) {
						exc.printStackTrace();
				}
			}
		}
		return true;
	}	
	public Tower getTower(){
		return towerInThisSlot;
	}
}
