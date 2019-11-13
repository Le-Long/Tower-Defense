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
    GameField game;
    SoundManager music;

    public GameStage() {
        music = new SoundManager();
        game = new GameField();
        add(game);
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
