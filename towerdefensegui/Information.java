/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package towerdefensegui;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

/**
 * @author TA
 */
public class Information extends javax.swing.JPanel {

	// Variables declaration - do not modify
	private javax.swing.JButton backBut;
	private javax.swing.JComboBox<String> enemyComboBox;
	private javax.swing.JComboBox<String> towerComboBox;
	private javax.swing.JLabel unitLabelText;
	private javax.swing.JComboBox<String> unitTypeComboBox;

	BufferedImage backgroundImage;
	String imageBuffer = "/images/Instructions.png";

	public Information(){
		try {
			backgroundImage = ImageIO.read(getClass().getResourceAsStream(imageBuffer));
		} catch (IOException exc) {
			exc.printStackTrace();
		}
	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(backgroundImage, 0, 0, this);
	}

}
