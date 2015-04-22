package binchat;

import binchat.gui.GUIManager;
import binchat.network.logic.ServerManager;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetSocketAddress;

public class BinChat {

    public static void main(String[] args) {

        GUIManager guiManager = new GUIManager();

    }

    public static void initNetwork(String address, String port) {

        ServerManager serverManager = new ServerManager();

        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group)
                    .channel(NioSocketChannel.class)
                    .handler(serverManager)
                    .connect(new InetSocketAddress(address, Integer.parseInt(port))).syncUninterruptibly();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            group.shutdownGracefully();
        }
    }

}
