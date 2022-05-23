package Desktop.Components;

import Desktop.Components.Routing.*;
import Desktop.Shared.RoundBtn;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;

public class Login extends JFrame {
    private DataOutputStream toServer;
    private DataInputStream fromServer;

    Color dodgerBlue = new Color(52,143,235);

    JButton loginBtn = new JButton("Login");

    public Login(DataOutputStream toServer, DataInputStream fromServer){
        this.toServer = toServer;
        this.fromServer = fromServer;
        setTitle("LOGIN");
        setVisible(true);
        setLayout(null);
        setBackground(dodgerBlue);
        setSize(1366,760);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addComponents();
    }

    public void addComponents(){
        loginBtn.setBackground(dodgerBlue);
        loginBtn.setSize(100,80);
        loginBtn.setBorder(new RoundBtn(10));

        loginBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new DistrictRouting(toServer,fromServer);
                    dispose();
                } catch (Exception exception) {}
            }
        });
        add(loginBtn);
    }
}
