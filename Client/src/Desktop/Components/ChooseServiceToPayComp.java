package Desktop.Components;

import javax.swing.*;
import java.awt.*;

public class ChooseServiceToPayComp extends JPanel {

    ChooseServiceToPayComp(){
        JPanel panel = new JPanel();
        panel.setVisible(true);
        panel.setSize(500,600);
        panel.setBackground(Color.LIGHT_GRAY);

        JLabel title = new JLabel("Choose service to pay");
        panel.add(title);





        add(panel);

        setLayout(new GridLayout(1,2));
        setVisible(true);
        setSize(1000,700);
    }


}
