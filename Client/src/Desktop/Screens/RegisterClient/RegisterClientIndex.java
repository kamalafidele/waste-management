package Desktop.Screens.RegisterClient;

import javax.swing.*;

public class RegisterClientIndex extends JFrame{
        MajaorPanel mainPanel = new MajaorPanel();
        public RegisterClientIndex() {
            setTitle("Register Client");
            setSize(1366,760);
            add(mainPanel);
            setVisible(true);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }

    public static void main(String[] args) {
        new RegisterClientIndex();
    }
}
