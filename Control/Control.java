/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

/**
 *
 * @author TA
 */
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Control extends MouseAdapter{
	
	private int mouseX;
	private int mouseY;
	
	public void mousePressed(MouseEvent e) {
	mouseX = e.getX();
	mouseY = e.getY();              	
	}
	
	public int getMouseX(){
		return mouseX;
	}
	public int getMouseY(){
		return mouseY;
	}
	public void setMouseX(int x){
		mouseX = x;
	}
	public void setMouseY(int y){
		mouseY = y;
	}
}
