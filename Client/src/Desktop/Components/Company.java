package Desktop.Components;

import javax.swing.*;
import java.awt.*;
import Desktop.App;

public class Company extends JFrame {
    private App application = new App();

    public void initialize() {

        setVisible(true);
        setTitle("LOG IN AS COMPANY");
        setSize(1366,760);
        setBackground(Color.YELLOW);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
