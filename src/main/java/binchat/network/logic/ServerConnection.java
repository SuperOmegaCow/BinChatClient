package binchat.network.logic;

import binchat.network.exception.BadPacketException;
import binchat.network.protocol.*;
import binchat.network.protocol.packet.handler.ChatHandler;
import binchat.network.protocol.packet.handler.HandshakeHandler;
import binchat.network.protocol.packet.handler.LoginHandler;
import binchat.network.protocol.packet.handshake.Handshake;
import binchat.network.protocol.packet.login.LoginStart;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.timeout.ReadTimeoutException;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServerConnection extends ChannelInboundHandlerAdapter {

    private ServerManager serverManager;
    private AbstractPacketHandler packetHandler;
    private ChannelWrapper channel;
    ExecutorService executorService = Executors.newSingleThreadExecutor();
    private State state = State.HANDSHAKE;

    public ServerConnection(ServerManager server, ChannelWrapper channel) {
        this.serverManager = server;
        this.packetHandler = new HandshakeHandler(this.serverManager, this);
        this.channel = channel;
        this.executorService.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(10);
                    sendPacket(new Handshake(2));
                    setState(State.LOGIN);
                    Thread.sleep(10);
                    sendPacket(new LoginStart(serverManager.getName()));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void setState(State state) {
        this.state = state;
        this.setProtocolState(state);
    }

    private void setProtocolState(State state) {
        ChannelPipeline pipeline = this.channel.getHandle().pipeline();
        if(state == State.HANDSHAKE) {
            this.setHandler(new HandshakeHandler(this.serverManager, this));
            ((Decoder)pipeline.get("packet_decoder")).setProtocolData(Packets.HANDSHAKE);
            ((Encoder)pipeline.get("packet_encoder")).setProtocolData(Packets.HANDSHAKE);
        } else if(state == State.LOGIN) {
            this.setHandler(new LoginHandler(this.serverManager, this));
            ((Decoder)pipeline.get("packet_decoder")).setProtocolData(Packets.LOGIN);
            ((Encoder)pipeline.get("packet_encoder")).setProtocolData(Packets.LOGIN);
        } else if(state == State.CHAT) {
            this.setHandler(new ChatHandler(this.serverManager, this));
            ((Decoder)pipeline.get("packet_decoder")).setProtocolData(Packets.CHAT);
            ((Encoder)pipeline.get("packet_encoder")).setProtocolData(Packets.CHAT);
        }
    }

    public void setHandler(AbstractPacketHandler handler) {
        this.packetHandler = handler;
    }

    public ServerManager getServerManager() {
        return serverManager;
    }

    public boolean isConnected() {
        return !channel.isClosed();
    }

    public void disconnect() {
        this.channel.close();
    }

    public void sendPacket(DefinedPacket definedPacket) {
        this.channel.write(definedPacket);
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
