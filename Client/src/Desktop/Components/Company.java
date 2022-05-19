package Desktop.Components;

import javax.swing.*;
import java.awt.*;
import Desktop.Application;

public class Company extends JFrame {
    private Application application = new Application();

    public void initialize() {
        application.close();

        setVisible(true);
        setTitle("LOG IN AS COMPANY");
        setSize(1366,760);
        setBackground(Color.YELLOW);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
