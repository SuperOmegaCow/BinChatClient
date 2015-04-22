package binchat.gui;

import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {

    private GUIManager guiManager;
    private WindowState windowState;
    private JPanel left;
    private JPanel leftTextBox;
    private JPanel leftChatBox;
    private JPanel right;
    private JPanel rightGraph;
    private JPanel rightTextBox;

    public Window(GUIManager guiManager, WindowState windowState) {
        this.guiManager = guiManager;
        this.windowState = windowState;
    }

    public WindowState getWindowState() {
        return windowState;
    }

    public void setWindowState(WindowState windowState) {
        this.windowState = windowState;
    }

    public void init() {

        this.setSize(1000, 800);
        this.setResizable(false);
        this.setVisible(true);

        this.left = new JPanel();
        this.right = new JPanel();

        left.setSize(500, 800);

        this.leftTextBox = new JPanel();
        leftTextBox.setSize(500, 600);
        JTextArea textArea = new JTextArea();
        leftTextBox.add(new JTextArea());


        right.setSize(500, 800);


        this.add(left, BorderLayout.WEST);
        this.add(right, BorderLayout.EAST);



        switch (windowState) {
            case SERVERADDRESSWINDOW:
                break;
            case NAMEPASSWINDOW:
                break;
            case CHATWINDOW:
                break;
        }
    }

    public void update() {

    }

}
