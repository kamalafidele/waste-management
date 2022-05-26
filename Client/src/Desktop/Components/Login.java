package Desktop.Components;


import Desktop.Components.Routing.*;
import Desktop.EventHandlers.ActionEventHandler;
import Desktop.Screens.RoundBtn;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.sql.*;

public class Login extends JFrame {
    private static DataOutputStream toServer;
    private static DataInputStream fromServer;

    private  JPanel leftPanel = new JPanel();
    private  JPanel rightPanel = new JPanel();
    private  static  JTextField myemail = new JTextField("Email");
    private  static  JPasswordField mypassword = new JPasswordField("Password");

    JLabel welcomeMsg = new JLabel("Welcome to WSMS");
    JLabel emailLabel = new JLabel("Email");

    JLabel passwordLabel = new JLabel("Password");
    JLabel errorMsg = new JLabel("Invalid email or password");
    JButton login = new JButton("Login");


    Color dodgerBlue = new Color(52,143,235);
    Color lightGray = new Color(225, 227, 225);

    public Login(DataOutputStream toServer, DataInputStream fromServer) throws IOException {
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
        GridLayout layout = new GridLayout(7,1);
        layout.setVgap(10);
        rightPanel.setLayout(layout);
        rightPanel.setBorder(new EmptyBorder(200, 80, 200, 80));

        add(leftPanel);
        add(rightPanel);

        welcomeMsg.setForeground(dodgerBlue);
        welcomeMsg.setFont(new Font("Inter", Font.BOLD, 30));
      mypassword.setText("");
      myemail.setText("");
      errorMsg.setForeground(Color.RED);
      errorMsg.setVisible(false);
        setLayout(new GridLayout(1,2));
        setVisible(true);
        this.setFont(new Font("Inter", Font.PLAIN, 18));
        setSize(1000,700);

        setLeftPanelTexts();
        setRightPanelContent();
    }

    public void setLeftPanelTexts() throws IOException {
        leftPanel.setBorder(new EmptyBorder(250, 80, 200, 80));
        BufferedImage myPicture = ImageIO.read(new File("src/Desktop/Images/logo.png"));
        JLabel picLabel = new JLabel(new ImageIcon(myPicture));

        leftPanel.add(picLabel);
    }

    public void setRightPanelContent() {
        myemail.setSize(40,20);
        login.setSize(40,20);
        login.setBorder(new RoundBtn(15));
        login.setBackground(dodgerBlue);

        //Add action listeners to buttons
        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = myemail.getText();
                String password = mypassword.getText();
                int userId;
                int userRole;
                String username;
                try {
                    Connection connection =  DriverManager.getConnection("jdbc:mysql://remotemysql.com:3306/LGMxUJ3u44",
                            "LGMxUJ3u44", "gAzBLwXOq8");

                    PreparedStatement st = connection
                            .prepareStatement("SELECT * FROM `users` WHERE `email` =? AND `password` =?");
                    st.setString(1, email);
                    st.setString(2, password);
                    ResultSet rs = st.executeQuery();

                    if (rs.next()) {
                        userId = rs.getInt("id");
                        username = rs.getString("name");
                        userRole = rs.getInt("role");
                        if(userRole == 1){
                            setVisible(false);
                            new SystemAdminsRouting(toServer, fromServer);
                        }else if(userRole == 2) {
                            setVisible(false);
                            new ConfirmerRouting();
                        }else if(userRole == 3){
                            setVisible(false);
                            new CitizenRouting();
                        } else if(userRole == 4){
                            setVisible(false);
                            new CompanyRouting();
                        }else if(userRole == 5){
                            setVisible(false);
                            new DistrictRouting(toServer,fromServer);
                        }
                    } else {
                 errorMsg.setVisible(true);
                 myemail.setText("");
                 mypassword.setText("");
                    }
                } catch (SQLException sqlException) {

                    sqlException.printStackTrace();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        rightPanel.add(welcomeMsg);
        rightPanel.add(errorMsg);
        rightPanel.add(emailLabel);
        rightPanel.add(myemail);
        rightPanel.add(passwordLabel);
        rightPanel.add(mypassword);
        rightPanel.add(login);
    }


}