package binchat.network.logic;

import binchat.network.protocol.*;
import binchat.network.protocol.packet.handshake.Handshake;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;

public class Initializer extends ChannelInitializer<Channel> {

    private ServerManager serverManager;

    public Initializer(ServerManager serverManager) {
        this.serverManager = serverManager;
    }

    @Override
    public void initChannel(Channel ch) throws Exception {
        ServerConnection serverConnection = new ServerConnection(serverManager, new ChannelWrapper(ch));
        ch.pipeline().addLast("frame_decoder", new Varint21FrameDecoder());
        ch.pipeline().addLast("packet_decoder", new Decoder(ServerManager.HANDSHAKE));
        ch.pipeline().addLast("frame_encoder", new Varint21LengthFieldPrepender());
        ch.pipeline().addLast("packet_encoder", new Encoder(ServerManager.HANDSHAKE));
        ch.pipeline().addLast("packet_handler", serverConnection);
        this.serverManager.setServerConnection(serverConnection);
        Thread.sleep(100);
        serverConnection.sendPacket(new Handshake(2));
    }

}
