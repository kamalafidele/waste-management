package Desktop;

import Desktop.Components.Role_Selection;

import javax.swing.*;
import java.awt.*;

public class Application extends JFrame {


    public static void main(String[] args){
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new JFrame("WSMS_Y2_C");
                frame.setSize(1366,760);
                frame.setVisible(true);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                Role_Selection role_selection = new Role_Selection();

                frame.add(role_selection);
            }
        });
    }
}
