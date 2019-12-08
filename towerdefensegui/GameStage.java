/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package towerdefensegui;

import javax.swing.*;
import java.awt.*;

/**
 * @author TA
 */

public class GameStage extends JFrame {
    static String title = "Tower Defense";
    // grid width 13 * 60 / grid height 9 * 60 + 200
    static Dimension size = new Dimension(780, 740);
    MainMenu menu;
    GameField game;
    Information instruct;
    HighScore scoreboard;
    SoundManager music;

    public GameStage() {
        music = new SoundManager();
        menu = new MainMenu();
        add(menu);
        init();
        setVisible(true);
        pack();
    }
    
    public GameStage(GameField gamefield) {
        this.game = gamefield;
        add(game);
        init();
        setVisible(true);
        pack();
    }
    
    public GameStage(Information instruction) {
        this.instruct = instruction;
        add(this.instruct);
        init();
        setVisible(true);
        pack();
    }
    
    public GameStage(HighScore score) {
        this.scoreboard = score;
        add(this.scoreboard);
        init();
        setVisible(true);
        pack();
    }

    private void init() {
        setLayout(new GridLayout(1, 1, 0, 0));
        setTitle(title);
        setSize(size);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
