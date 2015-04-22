package binchat.gui;

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

    public void handle(String serverIp, String serverPort, String username, String password) {
        //TODO check if serverIp and serverPort are good. If not reopen the window
    }

}
