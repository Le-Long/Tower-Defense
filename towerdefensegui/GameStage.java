/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package towerdefensegui;

import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JFrame;

/**
 * @author TA
 */

public class GameStage extends JFrame {

	public static GameStage instance = new GameStage();
	static String title = "Tower Defense";
	// grid width 13 * 60 / grid height 9 * 60 + 200
	static Dimension size = new Dimension(780, 740);
	MainMenu menu;
	GameField game;
	Instruction instruct;
	HighScore scoreboard;
	SoundManager music;

	private GameStage() {
		music = new SoundManager("background.wav");
		menu = new MainMenu();
		add(menu);
		init();
		setVisible(true);
		pack();
	}

	public static GameStage getInstance() {
		return instance;
	}

	public void gameToMenu() {
		music = new SoundManager("background.wav");
		remove(game);
		add(menu);
		init();
		setVisible(true);
		pack();
	}

	public void highscoreToMenu() {
		remove(scoreboard);
		add(menu);
		init();
		setVisible(true);
		pack();
	}

	public void infoToMenu() {
		remove(instruct);
		add(menu);
		init();
		setVisible(true);
		pack();
	}

	public void playGame(GameField gamefield) {
		this.game = gamefield;
		remove(menu);
		add(game);
		init();
		setVisible(true);
		pack();
	}

	public void showInstruct(Instruction instruction) {
		this.instruct = instruction;
		remove(menu);
		add(this.instruct);
		init();
		setVisible(true);
		pack();
	}

	public void showScore(HighScore score) {
		this.scoreboard = score;
		remove(menu);
		add(this.scoreboard);
		init();
		setVisible(true);
		pack();
	}

	private void init() {
		setLayout(new GridLayout(1, 1, 0, 0));
		setTitle(title);
		//setSize(size);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}