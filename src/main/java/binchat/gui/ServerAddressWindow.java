package binchat.gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

public class ServerAddressWindow extends JFrame {

    private JButton blogin = new JButton("Login");
    private JPanel panel = new JPanel();
    private JTextField txuser = new JTextField(20);
    private JTextField port = new JTextField(6);

    private GUIManager guiManager;

    public ServerAddressWindow(GUIManager guiManager) {
        this.guiManager = guiManager;
        this.setTitle("Enter Server Address and Port");
    }

    public void init() {
        this.setSize(400, 200);
        this.setLocation(500, 280);
        panel.setLayout(null);

        txuser.setBounds(70, 30, 150, 20);
        port.setBounds(70, 65, 150, 20);
        blogin.setBounds(110, 100, 80, 20);

        panel.add(blogin);
        panel.add(txuser);
        panel.add(port);

        getContentPane().add(panel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        final JFrame frame = this;

        blogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String sIp = txuser.getText();
                String sPort = port.getText();
                try {
                    Integer.parseInt(sPort);
                } catch (Exception t) {
                    setVisible(false);
                    frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
                    new ServerAddressWindow(guiManager);
                    return;
                }
                guiManager.handleServerAddress(sIp, sPort);
                setVisible(false);
                frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
            }
        });

        this.setVisible(true);

    }

}
