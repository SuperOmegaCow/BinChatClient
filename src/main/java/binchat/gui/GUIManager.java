package binchat.gui;

import binchat.BinChat;
import binchat.network.logic.ServerManager;

public class GUIManager extends Thread {

    private ServerManager serverManager;
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

    public void login() {

    }

    public void setServerManager(ServerManager serverManager) {
        this.serverManager = serverManager;
    }

    public synchronized void inbound(String chat) {

    }

    public synchronized void outbound(String message) {
        this.serverManager.outbound(message);
    }

    public synchronized void accepted() {
        this.window.setVisible(false);
        this.window = new Window(this);
        this.window.display();
    }

    public synchronized void declined() {
        this.window.setVisible(false);
        this.window = new Window(this);
        this.window.preLogin();
    }

    public void handle(String serverIp, String serverPort, String username, String password) {

        BinChat.serverIp = serverIp;
        BinChat.serverPort = serverPort;
        BinChat.username = username;
        BinChat.password = password;
        BinChat.waiting = false;

    }

}
