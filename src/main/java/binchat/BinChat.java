package binchat;

import binchat.gui.GUIManager;
import binchat.network.logic.ServerManager;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetSocketAddress;

public class BinChat {

    public volatile static boolean waiting = true;
    public volatile static String serverIp;
    public volatile static String serverPort;
    public volatile static String username;
    public volatile static String password;
    private static GUIManager guiManager;

    public static void main(String[] args) {

        guiManager = new GUIManager();
        while (waiting) {
            try {
                Thread.sleep(10);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        initNetwork(serverIp, serverPort, username, password);
    }

    public static void initNetwork(String address, String port, String username, String password) {

        try {
            Integer.parseInt(port);
        } catch (Exception e) {
            guiManager.declined();
            return;
        }

        ServerManager serverManager = new ServerManager(guiManager, username, password);

        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group)
                    .channel(NioSocketChannel.class)
                    .handler(serverManager)
                    .connect(new InetSocketAddress(address, Integer.parseInt(port))).syncUninterruptibly();
            guiManager.accepted();
        } catch (Exception e) {
            e.printStackTrace();
            guiManager.declined();
        } finally {
            group.shutdownGracefully();
        }
    }

}
