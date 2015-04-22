package binchat.gui;

public class GUIManager extends Thread {


    private volatile boolean running = false;
    private Window window;

    public GUIManager() {
        this.start();
    }

    @Override
    public void run() {
        this.running = true;
        this.window = new Window(this, WindowState.SERVERADDRESSWINDOW);
        this.window.init();
        while (running) {
            this.window.update();
            try {
                Thread.sleep(2);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
