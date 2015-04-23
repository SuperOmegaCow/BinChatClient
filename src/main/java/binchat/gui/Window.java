package binchat.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Window extends JFrame {

    private GUIManager guiManager;
    private JButton send;
    private JTextField messageBox;
    private JTextArea chatBox;

    public Window(GUIManager guiManager) {
        this.guiManager = guiManager;
    }

    public void preLogin() {
        this.setVisible(false);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Credentials");
        this.setSize(1000, 1000);

        JLabel jLabel = new JLabel("Server IP");
        final JTextField jTextField = new JTextField(20);
        JLabel jLabel1 = new JLabel("Server Port");
        final JTextField jTextField1 = new JTextField(20);
        JLabel jLabel2 = new JLabel("Username");
        final JTextField jTextField2 = new JTextField(20);
        JLabel jLabel3 = new JLabel("Password");
        final JTextField jTextField3 = new JTextField(20);

        JButton jButton = new JButton("Enter");
        this.setLayout(new GridLayout(8, 1));
        this.add(jLabel);
        this.add(jTextField);
        this.add(jLabel1);
        this.add(jTextField1);
        this.add(jLabel2);
        this.add(jTextField2);
        this.add(jLabel3);
        this.add(jTextField3);

        this.add(jButton);
        this.pack();
        this.setVisible(true);
        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String serverIp = jTextField.getText();
                String serverPort = jTextField1.getText();
                String userName = jTextField2.getText();
                String password = jTextField3.getText();
                guiManager.handle(serverIp, serverPort, userName, password);
                setVisible(false);
            }
        });
    }

    public void display() {

        this.setVisible(false);
        this.setResizable(false);
        //TODO chat box and graph box
    }

}
