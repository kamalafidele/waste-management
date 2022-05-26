package Desktop.Components.Routing;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class Logout extends JPanel {
        public Logout(){
                JButton logout = new JButton("Logout");
                logout.setBackground(Color.decode("#557DF8"));
                logout.setBorder(new EmptyBorder(new Insets(12,40,12,40)));
                logout.setFont(new Font("Inter", Font.PLAIN, 16));
                logout.setForeground(Color.WHITE);
                logout.setFocusPainted(false);
                setBorder(new EmptyBorder(new Insets(60,0,0,0)));
                setBackground(Color.decode("#EAEDF3"));
                add(logout);
        }
}
