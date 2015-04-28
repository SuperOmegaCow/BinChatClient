package binchat.network.logic;

import binchat.gui.GUIManager;
import binchat.network.protocol.*;
import binchat.network.protocol.packet.handshake.Handshake;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;

public class ServerManager {

    private ServerConnection serverConnection;
    private GUIManager guiManager;
    private String name;
    private String password;

    public static Packets HANDSHAKE = Packets.HANDSHAKE;
    public static Packets LOGIN = Packets.LOGIN;
    public static Packets CHAT = Packets.CHAT;

    public ServerManager(GUIManager guiManager, String name, String password) {
        this.guiManager = guiManager;
        this.name = name;
        this.password = password;
    }

    public void setServerConnection(ServerConnection serverConnection) {
        this.serverConnection = serverConnection;
    }

    public GUIManager getGuiManager() {
        return guiManager;
    }

    public void outbound(String message) {

    }

    public void inbound(String message) {
        this.guiManager.inbound(message);
    }

    public boolean isConnected() {
        return serverConnection.isConnected();
    }

    public ServerConnection getServerConnection() {
        return serverConnection;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }
}
