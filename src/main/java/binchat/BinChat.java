package binchat;

import binchat.graphing.Polynomial;
import binchat.graphing.TemporaryWindow;
import binchat.gui.GUIManager;
import binchat.network.logic.ServerManager;
import binchat.parser.ParserManager;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import javax.swing.*;
import java.net.InetSocketAddress;
import java.util.Scanner;

public class BinChat {

    public volatile static boolean waiting = true;
    public volatile static String serverIp;
    public volatile static String serverPort;
    public volatile static String username;
    public volatile static String password;
    private static GUIManager guiManager;

    public static void main(String[] args) {
        /*SwingUtilities.invokeLater(new Runnable(){
            public void run(){
                JFrame frame = new JFrame("Title");
                frame.setSize(1000,600);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);

            }
        });*/
        Polynomial p = new Polynomial(new double[]{1,2,3,4});
        System.out.println(p.getEquation());
        Polynomial q = new Polynomial(new double[]{2,3,4,5});
        System.out.println(q.getEquation());
        Polynomial mult = p.multiply(q);
        System.out.println(mult.getEquation());
        Polynomial add = p.add(q);
        System.out.println(add.getEquation());
        Polynomial sub = p.subtract(q);
        System.out.println(sub.getEquation());
        Scanner in = new Scanner(System.in);
        /*System.out.println("Please input the equation of the line you wish to draw, then the window coordinates.");
        String chat = in.nextLine();
        double xmin = in.nextDouble();
        double xmax = in.nextDouble();
        double ymin = in.nextDouble();
        double ymax = in.nextDouble();
        System.out.println(xmin + "    " + xmax + "    " + ymin + "    " + ymax);
        TemporaryWindow temporaryWindow = new TemporaryWindow();
        temporaryWindow.display(chat,xmin,xmax,ymin,ymax);*/
        boolean chatting = true;
        ParserManager parserManager = new ParserManager();
        while(chatting){
            parserManager.parseChatLine(in.nextLine());
        }


        /*
/graph x^8+ 8x^7 - 111x^6 -792x^5 + 4371x^4 + 23520x^3 -70117x^2-192080x + 235200,-9, 9, -350000, 900000
        */

        /*guiManager = new GUIManager();
        while (waiting) {
            try {
                Thread.sleep(10);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        initNetwork(serverIp, serverPort, username, password);*/
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
                    .connect(new InetSocketAddress(address, Integer.parseInt(port))).channel().close().syncUninterruptibly();
            guiManager.accepted();
        } catch (Exception e) {
            e.printStackTrace();
            guiManager.declined();
        } finally {
            group.shutdownGracefully();
        }
    }

}
