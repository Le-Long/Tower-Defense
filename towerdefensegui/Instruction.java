/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package towerdefensegui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JButton;

/**
 * @author TA
 */
public class Instruction extends javax.swing.JPanel {

	BufferedImage backgroundImage;
	String imageBuffer = "/images/Instructions.png";
	// Variables declaration - do not modify
	private javax.swing.JButton backBut;
	private javax.swing.JComboBox<String> enemyComboBox;
	private javax.swing.JComboBox<String> towerComboBox;
	private javax.swing.JLabel unitLabelText;
	private javax.swing.JComboBox<String> unitTypeComboBox;

	public Instruction() {
		try {
			backgroundImage = ImageIO.read(getClass().getResourceAsStream(imageBuffer));
		} catch (IOException exc) {
			exc.printStackTrace();
		}

		JButton backBut = new JButton();
		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
		this.setLayout(layout);
		layout.setHorizontalGroup(
			layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
					.addContainerGap(672, Short.MAX_VALUE)
					.addComponent(backBut, javax.swing.GroupLayout.PREFERRED_SIZE, 100,
						javax.swing.GroupLayout.PREFERRED_SIZE)
					.addGap(101, 101, 101))
		);
		layout.setVerticalGroup(
			layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
					.addContainerGap(714, Short.MAX_VALUE)
					.addComponent(backBut, javax.swing.GroupLayout.PREFERRED_SIZE, 33,
						javax.swing.GroupLayout.PREFERRED_SIZE)
					.addGap(85, 85, 85))
		);

		backBut.setText("Back");
		backBut.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				backButActionPerformed(evt);
			}
		});
	}

	private void backButActionPerformed(java.awt.event.ActionEvent evt) {
		removeAll();
		revalidate();
		GameStage.getInstance().infoToMenu();
	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(backgroundImage, 0, 0, this);
	}

}
