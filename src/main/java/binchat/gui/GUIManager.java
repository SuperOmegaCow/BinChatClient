package binchat.gui;

public class GUIManager extends Thread {

    private WindowState windowState;
    private volatile boolean running = false;

    public GUIManager() {
        this.start();
    }

    @Override
    public void run() {
        this.windowState = WindowState.SERVERADDRESSWINDOW;
        this.running = true;
        while (running) {

        }
    }

    public WindowState getWindowState() {
        return windowState;
    }

    public void setWindowState(WindowState windowState) {
        this.windowState = windowState;
    }
}
