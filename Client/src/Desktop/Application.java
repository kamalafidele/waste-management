package Desktop;

import Desktop.Components.Role_Selection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;

public class Application extends JFrame {

    public static void main(String[] args){
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
              Application application = new Application();
              application.initialize();
            }
        });
    }

    public void initialize() {
        setTitle("WSMS_Y2_C");
        setSize(1366,760);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Role_Selection role_selection = new Role_Selection();
        add(role_selection);
    }

    public void close() {
        setVisible(false);
        dispose();
    }
}
