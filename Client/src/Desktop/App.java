package Desktop;

import javax.swing.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class App extends JFrame {
    DataOutputStream toServer;
    DataInputStream fromServer;

    public static void main(String[] args) {
        App application = new App();
        try {
            Socket socket = new Socket("localhost",2500);
            application.setStreams(socket);

        } catch (Exception exception) {}
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
              application.initialize();
            }
        });
    }

    public void initialize() {
        setTitle("WSMS_Y2_C");
        setSize(1366,760);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        Role_Selection role_selection = new Role_Selection();
//        add(role_selection);
    }

    public void close() {
        setVisible(false);
        dispose();
    }

    public void setStreams(Socket socket) {
         try {
             toServer = new DataOutputStream(socket.getOutputStream());
             fromServer = new  DataInputStream(socket.getInputStream());
         } catch (Exception exception){}
    }
}
