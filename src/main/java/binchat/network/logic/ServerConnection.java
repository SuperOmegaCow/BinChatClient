package binchat.network.logic;

import binchat.network.exception.BadPacketException;
import binchat.network.protocol.AbstractPacketHandler;
import binchat.network.protocol.ChannelWrapper;
import binchat.network.protocol.PacketWrapper;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.ReadTimeoutException;

import java.io.IOException;

public class ServerConnection extends ChannelInboundHandlerAdapter {

    private ServerManager serverManager;
    private AbstractPacketHandler packetHandler;
    private ChannelWrapper channel;

    public ServerConnection(ServerManager serverManager, ChannelWrapper channel) {
        this.serverManager = serverManager;
        this.channel = channel;
    }

    public ServerManager getServerManager() {
        return serverManager;
    }

    public void disconnect() {
        this.channel.close();
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        this.disconnect();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        PacketWrapper packet = (PacketWrapper) msg;
        try {
            if (packet.packet != null) {
                packet.packet.handle(packetHandler);
            }
        } finally {
            packet.trySingleRelease();
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        if (ctx.channel().isActive()) {
            if (cause instanceof ReadTimeoutException) {
                //TODO error logger remember the cause
            } else if (cause instanceof BadPacketException) {
                //TODO error logger remember the cause
            } else if (cause instanceof IOException) {
                //TODO error logger remember the cause
            } else {
                //TODO error logger remember the cause
            }
            ctx.close();
        }
    }


}
