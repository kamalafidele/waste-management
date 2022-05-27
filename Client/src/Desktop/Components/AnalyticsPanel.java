package Desktop.Components;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class AnalyticsPanel extends JPanel {
    public AnalyticsPanel(){
        setVisible(false);
        setBounds(200,0,1166,768);
        setBackground(Color.white);
        setBorder(new EmptyBorder(new Insets(20,30,20,30)));
        setLayout(null);
    }
}
