package Desktop.Components;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class testPanel extends JPanel {

    public testPanel(){
        setVisible(false);
        setBounds(206,10,1350,770);
        setBackground(Color.orange);
        setBorder(new EmptyBorder(new Insets(20,30,20,30)));
    }
}
