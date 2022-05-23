package Desktop.Components;


import Desktop.EventHandlers.ActionEventHandler;
import Desktop.Shared.RoundBtn;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class Login extends JFrame {
   private static DataOutputStream toServer;
   private static DataInputStream fromServer;

    private  JPanel leftPanel = new JPanel();
    private  JPanel rightPanel = new JPanel();
   JTextField email = new JTextField("Email");
   JTextField password = new JTextField("Password");

    JButton login = new JButton("Login");

    Color dodgerBlue = new Color(52,143,235);
    Color lightGray = new Color(225, 227, 225);

    public Login(DataOutputStream toServer, DataInputStream fromServer) {
        setTitle("WSMS_Y2_C");
        setSize(1366,760);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        leftPanel.setVisible(true);
        leftPanel.setSize(500,600);
        leftPanel.setBackground(lightGray);
        BoxLayout boxLayout = new BoxLayout(leftPanel, BoxLayout.Y_AXIS);
        leftPanel.setLayout(boxLayout);
        leftPanel.setBorder(new EmptyBorder(new Insets(150, 100, 150, 100)));

        rightPanel.setVisible(true);
        rightPanel.setSize(500,600);
        rightPanel.setBackground(Color.WHITE);
        GridLayout layout = new GridLayout(5,1);
        layout.setVgap(10);
        rightPanel.setLayout(layout);
        rightPanel.setBorder(new EmptyBorder(200, 100, 200, 100));

        add(leftPanel);
        add(rightPanel);

        setLayout(new GridLayout(1,2));
        setVisible(true);
        setSize(1000,700);

        setLeftPanelTexts();
        setRightPanelContent();
    }

    public void setLeftPanelTexts() {
        JLabel label = new JLabel("Welcome to WSMS");
        JLabel label1 = new JLabel("The best online waste and security");
        JLabel label2 = new JLabel("management system in Rwanda");

        label.setBorder(new EmptyBorder(new Insets(30,0,30,0)));
        label.setFont(new Font("Inter", label.getFont().getStyle(), 30));
        label.setForeground(dodgerBlue);

        leftPanel.add(label);
        leftPanel.add(label1);
        leftPanel.add(label2);
    }

    public void setRightPanelContent() {
        email.setSize(40,20);
        login.setSize(40,20);
        login.setBorder(new RoundBtn(15));
        login.setBackground(dodgerBlue);

        //Add action listeners to buttons
        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Login(toServer,fromServer);
                dispose();
            }
        });
        rightPanel.add(email);
        rightPanel.add(password);
        rightPanel.add(login);
    }


}