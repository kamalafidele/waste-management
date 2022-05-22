package Desktop.Screens.RegisterClient;

//import Desktop.Components.SideMenuPanel;

import javax.swing.*;
import java.awt.*;

public class MajaorPanel extends JPanel{
    private  JPanel leftPanel = new JPanel();
    private  JPanel rightPanel = new JPanel();

    public MajaorPanel() {
        leftPanel.setVisible(true);
        leftPanel.setBounds(0, 0,200,760);
        leftPanel.setBackground(Color.decode("#EAEDF3"));
//        leftPanel.add(new SideMenuPanel());

        rightPanel.setVisible(true);
        rightPanel.setBounds(200,0,1136,760);
        rightPanel.setBackground(Color.WHITE);
        rightPanel.setLayout(null);

        add(leftPanel);
        add(rightPanel);


        setLayout(null);
        setVisible(true);
        setSize(1000,700);
    }

    void insertIn(Component component){
        rightPanel.add(component);
    }
}
