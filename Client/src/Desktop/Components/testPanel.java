package Desktop.Components;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class testPanel extends JPanel {

    public testPanel(){
        setVisible(false);
        setBounds(200,0,1366,820);
        setBackground(Color.WHITE);
        setBorder(new EmptyBorder(new Insets(20,30,20,30)));
    }
}
