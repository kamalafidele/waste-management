package Desktop.Components.Routing;
import Desktop.App;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Logout extends JPanel {
JFrame currentFrame;
        public Logout(JFrame frame){
                this.currentFrame=frame;
                JButton logout = new JButton("Logout");
                logout.setBackground(Color.decode("#557DF8"));
                logout.setBorder(new EmptyBorder(new Insets(12,40,12,40)));
                logout.setFont(new Font("Inter", Font.PLAIN, 16));
                logout.setForeground(Color.WHITE);
                logout.setFocusPainted(false);
                logout.addActionListener(new LogoutHandler());
                setBorder(new EmptyBorder(new Insets(60,0,0,0)));
                setBackground(Color.decode("#EAEDF3"));
                add(logout);
        }
        public class LogoutHandler implements ActionListener {
                @Override
                public void actionPerformed(ActionEvent e) {
                   currentFrame.dispose();
                   new App();
                }
        }

}
