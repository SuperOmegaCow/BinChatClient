package binchat.network.logic;

import binchat.gui.GUIManager;
import binchat.network.protocol.*;
import binchat.network.protocol.packet.handshake.Handshake;
import binchat.network.protocol.packet.login.LoginStart;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;

public class ServerManager extends ChannelInitializer<Channel> {

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

    @Override
    public void initChannel(Channel ch) throws Exception {
        this.serverConnection = new ServerConnection(this, new ChannelWrapper(ch));
        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast("frame_decoder", new FrameDecoder());
        pipeline.addLast("packet_decoder", new Decoder(HANDSHAKE));
        pipeline.addLast("frame_encoder", new FieldPrepender());
        pipeline.addLast("packet_encoder", new Encoder(HANDSHAKE));
        pipeline.addLast("packet_handler", this.serverConnection);
        Thread.sleep(30);
        serverConnection.sendPacket(new Handshake(2));
        serverConnection.setState(State.HANDSHAKE);
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
