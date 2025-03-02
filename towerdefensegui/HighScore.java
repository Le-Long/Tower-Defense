/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package towerdefensegui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import javax.imageio.ImageIO;
import javax.swing.JButton;

/**
 * @author TA
 */
public class HighScore extends javax.swing.JPanel {

	BufferedImage backgroundImage;
	String imageBuffer = "/images/Highscore.png";

	private String savePath;
	private String filename = "Highscore.txt";
	private Font scoreFont = new Font("TimesRoman", Font.PLAIN, 48);
	private List<Integer> scoreList = new ArrayList<Integer>();

	public HighScore() {
		this.setPreferredSize(new Dimension(832, 776));
		try {
			backgroundImage = ImageIO.read(getClass().getResourceAsStream(imageBuffer));
			//   savePath = "/towerdefensegui/";
		} catch (IOException exc) {
			exc.printStackTrace();
		}
		loadHighscore();

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
		GameStage.getInstance().highscoreToMenu();
	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(backgroundImage, 0, 0, this);
		g.setColor(Color.RED);
		g.setFont(scoreFont);
		for (int i = 0; i < scoreList.size(); i++) {
			g.drawString("" + (i + 1) + '.' + scoreList.get(i), 250, 160 + i * 50);
		}
	}

	private void CreateSave() {
		try {
			File file = new File(filename);
			file.createNewFile();
			FileWriter fr = null;
			fr = new FileWriter(file);
			fr.write("" + 0);
			fr.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void loadHighscore() {
		try {
			File f = new File(filename);
			if (!f.isFile()) {
				CreateSave();
			}
			Scanner sc = new Scanner(f);
			while (sc.hasNext()) {
				if (sc.hasNextInt()) {
					int x = sc.nextInt();
					scoreList.add(x);
				}
			}
			sc.close();
			Collections.sort(scoreList);
			Collections.reverse(scoreList);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}