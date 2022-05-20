package Desktop.Screens.RegisterClient;

import Desktop.Components.SideMenuPanel;

import javax.swing.*;
import java.awt.*;

public class RegisterClientIndex extends JFrame{
    public static MajaorPanel mainPanel = new MajaorPanel();
    public RegisterClientIndex() {
        setTitle("Register Client");
        setSize(1366,760);
        add(mainPanel);
        setVisible(true);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        new RegisterClientIndex();

        JLabel breadCrumb = new JLabel("Dashboard / Citizens / Register new");
        breadCrumb.setBounds(50, 10, 500, 100);
        mainPanel.insertIn(breadCrumb);

        JPanel form = new JPanel();
        form.setBounds(300, 150, 450, 500);
        form.setBackground(Color.decode("#EAEDF3"));
        form.setLayout(null);

        JLabel registerLabel = new JLabel("Register Client");
        registerLabel.setForeground(new Color(51, 102, 255));
        registerLabel.setBounds(50, 0, 400, 100);

        JTextField citizenName = new JTextField();
        citizenName.setText("Citizen name");

        form.add(citizenName);
        form.add(registerLabel);
        mainPanel.insertIn(form);
    }
}
