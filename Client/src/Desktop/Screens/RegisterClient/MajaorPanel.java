package Desktop.Screens.RegisterClient;

import javax.swing.*;
import java.awt.*;

public class MajaorPanel extends JPanel{
    private  JPanel leftPanel = new JPanel();
    private  JPanel rightPanel = new JPanel();

    public MajaorPanel() {
        leftPanel.setVisible(true);
        leftPanel.setSize(500,600);
        leftPanel.setBackground(new Color(237, 238, 242));

        rightPanel.setVisible(true);
        rightPanel.setSize(500,600);
        rightPanel.setBackground(Color.WHITE);

        add(leftPanel);
        add(rightPanel);


        setLayout(new GridLayout(1,2));
        setVisible(true);
        setSize(1000,700);
    }
}
