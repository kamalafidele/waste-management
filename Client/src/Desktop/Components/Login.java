package Desktop.Components;


import Desktop.EventHandlers.ActionEventHandler;
import Desktop.Shared.RoundBtn;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.sql.*;

public class Login extends JFrame {
   private static DataOutputStream toServer;
   private static DataInputStream fromServer;

    private  JPanel leftPanel = new JPanel();
    private  JPanel rightPanel = new JPanel();
   private  static  JTextField myemail = new JTextField("Email");
   private  static  JTextField mypassword = new JTextField("Password");

    JButton login = new JButton("Login");

    Color dodgerBlue = new Color(52,143,235);
    Color lightGray = new Color(225, 227, 225);

    public Login(DataOutputStream toServer, DataInputStream fromServer) {
        setTitle("WSMS_Y2_C");
        setSize(1366,760);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        leftPanel.setVisible(true);
        leftPanel.setSize(500,600);
        leftPanel.setBackground(lightGray);
        BoxLayout boxLayout = new BoxLayout(leftPanel, BoxLayout.Y_AXIS);
        leftPanel.setLayout(boxLayout);
        leftPanel.setBorder(new EmptyBorder(new Insets(150, 100, 150, 100)));

        rightPanel.setVisible(true);
        rightPanel.setSize(500,600);
        rightPanel.setBackground(Color.WHITE);
        GridLayout layout = new GridLayout(5,1);
        layout.setVgap(10);
        rightPanel.setLayout(layout);
        rightPanel.setBorder(new EmptyBorder(200, 100, 200, 100));

        add(leftPanel);
        add(rightPanel);

        setLayout(new GridLayout(1,2));
        setVisible(true);
        setSize(1000,700);

        setLeftPanelTexts();
        setRightPanelContent();
    }

    public void setLeftPanelTexts() {
        JLabel label = new JLabel("Welcome to WSMS");
        JLabel label1 = new JLabel("The best online waste and security");
        JLabel label2 = new JLabel("management system in Rwanda");

        label.setBorder(new EmptyBorder(new Insets(30,0,30,0)));
        label.setFont(new Font("Inter", label.getFont().getStyle(), 30));
        label.setForeground(dodgerBlue);

        leftPanel.add(label);
        leftPanel.add(label1);
        leftPanel.add(label2);
    }

    public void setRightPanelContent() {
        JLabel createText = new JLabel("Create account instead");
        createText.setForeground(dodgerBlue);
        createText.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        createText.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                new Registration(false,false,true);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                createText.setForeground(Color.black);
            }

            @Override
            public void mouseExited(MouseEvent e) {
               createText.setForeground(dodgerBlue);
            }
        });
        myemail.setSize(40,20);
        login.setSize(20,10);
        login.setBorder(new RoundBtn(15));
        login.setBackground(dodgerBlue);
        login.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                login.setBackground(Color.white);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                login.setBackground(dodgerBlue);
            }
        });

        //Add action listeners to buttons
        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = myemail.getText();
                String password = mypassword.getText();
                String driverName = "com.mysql.cj.jdbc.Driver";

                try {
                    Class.forName(driverName);

                    Connection connection =  DriverManager.getConnection("jdbc:mysql://remotemysql.com:3306/LGMxUJ3u44",
                            "LGMxUJ3u44", "gAzBLwXOq8");

                    PreparedStatement st = connection.prepareStatement("Select email, password from users where email=" + email+"and password=" + password );
                    System.out.println("connected");
                    st.setString(1, email);
                    st.setString(2, password);
                    ResultSet rs = st.executeQuery();

                    if (rs.next()) {
                        dispose();
                         System.out.println("connected");
//                        JOptionPane.showMessageDialog(btnNewButton, "You have successfully logged in");
                    } else {

//                        JOptionPane.showMessageDialog(btnNewButton, "Wrong Username & Password");
                    }
                } catch (SQLException sqlException) {
                    System.out.println("failed to connect");
                    sqlException.printStackTrace();
                } catch (ClassNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        rightPanel.add(myemail);
        rightPanel.add(mypassword);
        rightPanel.add(createText);
        rightPanel.add(login);
    }


}