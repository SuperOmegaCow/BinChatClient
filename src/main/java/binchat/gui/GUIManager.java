package binchat.gui;

import binchat.BinChat;

public class GUIManager extends Thread {

    private volatile boolean running = false;
    private Window window;

    public GUIManager() {
        this.window = new Window(this);
        this.start();
    }

    @Override
    public void run() {
        this.running = true;
        this.window.preLogin();
        while (running) {
            this.window.repaint();
            try {
                Thread.sleep(2);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized void accepted() {
        this.window.display();
    }

    public synchronized void declined() {
        this.window.setVisible(true);
    }

    public void handle(String serverIp, String serverPort, String username, String password) {

        BinChat.serverIp = serverIp;
        BinChat.serverPort = serverPort;
        BinChat.username = username;
        BinChat.password = password;
        BinChat.waiting = false;

    }

}
