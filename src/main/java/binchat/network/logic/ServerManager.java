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

        this.serverConnection = new ServerConnection(this, new ChannelWrapper(ch));

        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast("frame_decoder", new FrameDecoder());
        pipeline.addLast("frame_encoder", new FieldPrepender());
        pipeline.addLast("packet_decoder", new Encoder(Packets.HANDSHAKE));
        pipeline.addLast("packet_handler", this.serverConnection);

    }
    
    public boolean isConnected() {
        return serverConnection.isConnected();
    }

    public ServerConnection getServerConnection() {
        return serverConnection;
    }

}
