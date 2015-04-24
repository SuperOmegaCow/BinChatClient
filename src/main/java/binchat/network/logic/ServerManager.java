package binchat.network.logic;

import binchat.gui.GUIManager;
import binchat.network.protocol.*;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;

public class ServerManager extends ChannelInitializer<Channel> {

    private ServerConnection serverConnection;
    private GUIManager guiManager;
    private String name;
    private String password;

    public ServerManager(GUIManager guiManager, String name, String password) {
        this.guiManager = guiManager;
        this.name = name;
        this.password = password;
    }

    @Override
    public void initChannel(Channel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast("frame_decoder", new FrameDecoder());
        pipeline.addLast("packet_decoder", new Decoder(Packets.HANDSHAKE));
        pipeline.addLast("frame_encoder", new FieldPrepender());
        pipeline.addLast("packet_encoder", new Encoder(Packets.HANDSHAKE));
        this.serverConnection = new ServerConnection(this, new ChannelWrapper(ch));
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
