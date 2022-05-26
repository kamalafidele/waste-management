package Desktop;
import Desktop.Components.Login;

import Desktop.Components.Registration;
import Desktop.Screens.RegisterClient.RegisterClientIndex;
import Desktop.Shared.RoundBtn;


import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.Socket;

public class App extends JFrame {
    DataOutputStream toServer;
    DataInputStream fromServer;


    JButton login = new JButton("Login");

    Color dodgerBlue = new Color(52, 143, 235);
    Color lightGray = new Color(225, 227, 225);

    public static void main(String[] args) {
        App application = new App();
        try {
            Socket socket = new Socket("localhost", 2500);
            application.setStreams(socket);

        } catch (Exception exception) {
        }
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    application.initialize();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    public void initialize() throws IOException {
        BoxLayout boxLayout = new BoxLayout(this, BoxLayout.Y_AXIS);
        setTitle("WSMS_Y2_C");
        setSize(1366, 760);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setBackground(lightGray);

        setLayout(null);

        BufferedImage myPicture = ImageIO.read(new File("src/Desktop/Images/logo.png"));
        JLabel picLabel = new JLabel(new ImageIcon(myPicture));
;

        JLabel signUp = new JLabel("Sign Up");
        signUp.setForeground(dodgerBlue);
        signUp.setFont(new Font("Inter", Font.PLAIN, 20));

        JLabel label = new JLabel("Welcome to WSMS");
        JLabel label1 = new JLabel("The best online waste and security management system in Rwanda");
      JButton getStarted = new JButton("Get Started");

      getStarted.addMouseListener(new MouseListener() {


    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        getStarted.setCursor(new Cursor(Cursor.HAND_CURSOR));
     getStarted.setBackground(Color.LIGHT_GRAY);
     getStarted.setForeground(Color.BLACK);
    }

    @Override
    public void mouseExited(MouseEvent e) {
      getStarted.setForeground(Color.WHITE);
      getStarted.setBackground(dodgerBlue);
    }
});
      login.addMouseListener(new MouseListener() {


            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                login.setCursor(new Cursor(Cursor.HAND_CURSOR));
               login.setBackground(Color.LIGHT_GRAY);
               login.setForeground(Color.BLACK);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                login.setForeground(Color.WHITE);
               login.setBackground(dodgerBlue);
            }
        });
        getStarted.addActionListener(new ActionListener() {
                                                 @Override
                                                 public void actionPerformed (ActionEvent e){

                                                     new Registration(false,false,true);
                                                     dispose();
                                                 }
                                             });

        picLabel.setBounds(30, 30, 150, 40);
        signUp.setBounds(1000, 40, 100, 30);
        login.setBounds(1100, 30, 120,40);
        label.setBounds(400,300, 600,50);
        label1.setBounds(450,350, 500, 30);
        getStarted.setBounds(550, 420, 180, 40);

        getStarted.setBackground(dodgerBlue);
        getStarted.setForeground(Color.WHITE);

        label.setForeground(dodgerBlue);

        label.setFont(new Font(label.getFont().getName(),label.getFont().getStyle(), 60));
        login.setBackground(dodgerBlue);

        //Add action listeners to buttons
        login.addActionListener(new

                                        ActionListener() {
                                            @Override
                                            public void actionPerformed (ActionEvent e){
                                                new Login(toServer, fromServer);
                                                dispose();
                                            }
                                        });

        add(picLabel);
        add(signUp);
        add(label);
        add(label1);
      add(getStarted);

        add(login);


    }

    public void setStreams(Socket socket) {
        try {
            toServer = new DataOutputStream(socket.getOutputStream());
            fromServer = new DataInputStream(socket.getInputStream());
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }



}