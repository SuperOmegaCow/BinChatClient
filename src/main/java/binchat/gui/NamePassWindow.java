package binchat.gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

public class NamePassWindow extends JFrame {

    private JButton blogin = new JButton("Login");
    private JPanel panel = new JPanel();
    private JTextField txuser = new JTextField(20);
    private JPasswordField pass = new JPasswordField(20);

    private GUIManager guiManager;

    public NamePassWindow(GUIManager guiManager) {
        this.guiManager = guiManager;
        this.setTitle("Enter Name and Password");
    }

    public void init() {
        this.setSize(400, 200);
        this.setLocation(500, 280);
        panel.setLayout(null);

        txuser.setBounds(70, 30, 150, 20);
        pass.setBounds(70, 65, 150, 20);
        blogin.setBounds(110, 100, 80, 20);

        panel.add(blogin);
        panel.add(txuser);
        panel.add(pass);

        getContentPane().add(panel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        final JFrame frame = this;

        blogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = txuser.getText();
                String password = new String(pass.getPassword());
                guiManager.handleNamePass(name, password);
                setVisible(false);
                frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
            }
        });

        this.setVisible(true);

    }

}
