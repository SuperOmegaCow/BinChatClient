package binchat.graphing;

import javax.swing.plaf.ColorUIResource;
import java.awt.*;
import java.awt.image.BufferedImage;

public class GraphingManager extends Thread {

    int SCREEN_WIDTH = 1200;
    int SCREEN_HEIGHT = 800;
    public GraphingManager() {
        super("Graphing Thread");
    }

    @Override
    public void run() {

    }
    // gets the x double value for a give pixel
    public double xValue(int x_pixel_index, double lower_x, double upper_x){
        return lower_x + x_pixel_index*((upper_x-lower_x)/SCREEN_WIDTH);
    }
    // gets the y double value of a given pixel
    public double yValue(int y_pixel_index, double lower_y, double upper_y){
        return upper_y - y_pixel_index*((upper_y-lower_y)/SCREEN_HEIGHT);
    }
    public int xIndex(double x_value, double lower_x, double upper_x){
        return (int)((x_value-lower_x)/((upper_x-lower_x)/SCREEN_WIDTH));
    }
    public int yIndex(double y_value, double lower_y, double upper_y){
        return (int)((upper_y-y_value) / ((upper_y-lower_y)/SCREEN_HEIGHT));
    }

        // TODO if no max/min given, choose fitting frame
    public BufferedImage plotFunction(Polynomial poly, double xmin, double xmax, double ymin, double ymax){
        BufferedImage buffimg = new BufferedImage(SCREEN_WIDTH, SCREEN_HEIGHT, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = (Graphics2D) buffimg.getGraphics();
        g.setBackground(Color.white);
        g.setColor(Color.white);
        g.fillRect(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
        g.setColor(Color.BLACK);
        g.setFont(new Font("Serif", Font.PLAIN, 20));
        g.drawLine(0, yIndex(0, ymin, ymax), SCREEN_WIDTH - 20, yIndex(0, ymin, ymax));
        g.drawString("X", SCREEN_WIDTH - 20, yIndex(0, ymin, ymax));
        // x axis ticks
        int scale_position;
        if(yIndex(0, ymin, ymax) < 0 ||SCREEN_HEIGHT<yIndex(0,ymin,ymax))
            scale_position = SCREEN_HEIGHT;
        else
            scale_position = yIndex(0, ymin, ymax);
        for (int i = 0; i <= 7; i++) {
            double x = xValue(i*SCREEN_WIDTH/7,xmin,xmax);
            x = Math.round(x*10);
            x = x/10;
            g.drawString(String.valueOf(x),i*SCREEN_WIDTH/7,scale_position);
        }
        g.drawLine(xIndex(0, xmin, xmax), 20, xIndex(0, xmin, xmax), SCREEN_HEIGHT);
        g.drawString("Y", xIndex(0,xmin,xmax),20);
        if(xIndex(0,xmin,xmax) < 0 ||SCREEN_WIDTH<xIndex(0,xmin,xmax))
            scale_position = 0;
        else
            scale_position = xIndex(0,xmin,xmax);
        for (int i = 0; i <= 7; i++) {
            double y = yValue(i * SCREEN_HEIGHT / 7, ymin, ymax);
            y = Math.round(y*10);
            y = y/10;
            g.drawString(String.valueOf(y),scale_position,i*SCREEN_HEIGHT/7);
        }
        g.setColor(Color.RED);
        double previous_x = xValue(0,xmin,xmax);
        double previous_y = poly.evaluate(previous_x);
        for (int i = 1; i < SCREEN_WIDTH; i+= 1) {
            double current_x = xValue(i,xmin,xmax);
            double current_y = poly.evaluate(current_x);
            g.drawLine(i-1,yIndex(previous_y,ymin,ymax), i,yIndex(current_y,ymin,ymax));
            previous_x = current_x;
            previous_y = current_y;
        }
        g.drawString(poly.equation, 10, 40);
        return buffimg;
    }
}
