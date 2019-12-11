/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package towerdefensegui;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.*;
import java.util.List;

import javax.imageio.ImageIO;

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

    public HighScore(){
       // this.setPreferredSize(new Dimension(832, 776));
        try {
            backgroundImage = ImageIO.read(getClass().getResourceAsStream(imageBuffer));
         //   savePath = "/towerdefensegui/";
        } catch (IOException exc) {
            exc.printStackTrace();
        }
        loadHighscore();
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, this);
        g.setColor(Color.RED);
        g.setFont(scoreFont);
        for (int i = 0; i < scoreList.size(); i++)
            g.drawString(""+(i+1)+ '.'+scoreList.get(i), 250, 160 + i * 50);
    }

    private void CreateSave(){
        try{
            File file = new File(filename);
            file.createNewFile();
            FileWriter fr = null;
            fr = new FileWriter(file);
            fr.write(""+0);
            fr.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void loadHighscore(){
        try{
            File f = new File(filename);
            if (!f.isFile()) CreateSave();
            Scanner sc = new Scanner(f);
            while (sc.hasNext() ) {
                if (sc.hasNextInt()) {
                    int x = sc.nextInt();
                    scoreList.add(x);
                }
            }
            sc.close();
            Collections.sort(scoreList);
            Collections.reverse(scoreList);

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
