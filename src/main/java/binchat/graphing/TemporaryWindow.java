package binchat.graphing;

import binchat.parser.ParserManager;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by Kevin on 2015-04-23.
 */
public class TemporaryWindow extends JFrame{
    String chat;
    double xmin, xmax, ymin, ymax;
    @Override
    public void paint(Graphics g) {
        GraphingManager graphingManager = new GraphingManager();
        ParserManager parserManager = new ParserManager();
        Polynomial p = parserManager.mathParser(chat);
        Image img = graphingManager.plotFunction(p, xmin,xmax,ymin,ymax);
        g.drawImage(img, 0, 0, this);
    }
    public void display(String chat, double xmin,double xmax, double ymin, double ymax){
        this.chat = chat;
        this.setUndecorated(true);
        this.setSize(1920, 1080);
        this.xmin = xmin;
        this.xmax = xmax;
        this.ymin = ymin;
        this.ymax = ymax;
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}
