package Desktop;

import Desktop.Components.Login;
import Desktop.EventHandlers.ActionEventHandler;
import Desktop.Shared.RoundBtn;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class App extends JFrame {
    DataOutputStream toServer;
    DataInputStream fromServer;

    private  JPanel leftPanel = new JPanel();
    private  JPanel rightPanel = new JPanel();

    JButton login = new JButton("Login");

    Color dodgerBlue = new Color(52,143,235);
    Color lightGray = new Color(225, 227, 225);

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
        leftPanel.setVisible(true);
        leftPanel.setSize(500,600);
        leftPanel.setBackground(lightGray);
        BoxLayout boxLayout = new BoxLayout(leftPanel, BoxLayout.Y_AXIS);
        leftPanel.setLayout(boxLayout);
        leftPanel.setBorder(new EmptyBorder(new Insets(150, 200, 150, 200)));

        rightPanel.setVisible(true);
        rightPanel.setSize(500,600);
        rightPanel.setBackground(Color.WHITE);
        GridLayout layout = new GridLayout(5,1);
        layout.setVgap(10);
        rightPanel.setLayout(layout);
        rightPanel.setBorder(new EmptyBorder(200, 50, 200, 50));

        add(leftPanel);
        add(rightPanel);

        setLayout(new GridLayout(1,2));
        setVisible(true);


    }

    public void setStreams(Socket socket) {
         try {
             toServer = new DataOutputStream(socket.getOutputStream());
             fromServer = new  DataInputStream(socket.getInputStream());
         } catch (Exception exception){}
    }





}