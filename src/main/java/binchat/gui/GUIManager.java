package binchat.gui;

public class GUIManager {

    private WindowState windowState;

    public GUIManager() {
        this.windowState = WindowState.SERVERADDRESSWINDOW;
        ServerAddressWindow serverAddressWindow = new ServerAddressWindow(new GUIManager());
        serverAddressWindow.init();
    }

    public void handleServerAddress(String ip, String port) {
        System.out.println(ip + " " + port);
        this.windowState = WindowState.NAMEPASSWINDOW;

    }

    public void handleNamePass(String name, String password) {
        this.windowState = WindowState.CHATWINDOW;
        //TODO create a window
    }

    public void handleInput(String data) {

    }

    public WindowState getWindowState() {
        return windowState;
    }

    public void setWindowState(WindowState windowState) {
        this.windowState = windowState;
    }
}
