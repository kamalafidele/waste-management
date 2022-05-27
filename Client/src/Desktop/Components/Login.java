package Desktop.Components;


import Desktop.Components.Routing.*;
import Desktop.EventHandlers.ActionEventHandler;
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
import java.io.*;
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

    Color lightGray = Color.decode("#EAEDF3");

    public Login(DataOutputStream toServer, DataInputStream fromServer) throws IOException {
        setTitle("WSMS_Y2_C");
        setSize(1366,768);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        leftPanel.setVisible(true);
        leftPanel.setSize(500,600);
        leftPanel.setBackground(lightGray);
        BoxLayout boxLayout = new BoxLayout(leftPanel, BoxLayout.Y_AXIS);
        leftPanel.setLayout(boxLayout);
        leftPanel.setBorder(new EmptyBorder(new Insets(150, 200, 150, 150)));

        rightPanel.setVisible(true);
        rightPanel.setSize(500,600);
        rightPanel.setBackground(Color.WHITE);
        GridLayout layout = new GridLayout(7,1);
        layout.setVgap(10);
        rightPanel.setLayout(layout);
        rightPanel.setBorder(new EmptyBorder(200, 120, 200, 120));

        add(leftPanel);
        add(rightPanel);

        welcomeMsg.setForeground(dodgerBlue);
        welcomeMsg.setFont(new Font("Inter", Font.BOLD, 40));
      mypassword.setText("");
      myemail.setText("");
      errorMsg.setForeground(Color.RED);
      errorMsg.setVisible(false);
        setLayout(new GridLayout(1,2));
        setVisible(true);
        this.setFont(new Font("Inter", Font.PLAIN, 18));


        setLeftPanelTexts();
        setRightPanelContent();
    }

    public void setLeftPanelTexts() throws IOException {
        leftPanel.setBorder(new EmptyBorder(280, 200, 200, 80));
        BufferedImage myPicture = ImageIO.read(new File("src/Desktop/Images/logo.png"));
        JLabel picLabel = new JLabel(new ImageIcon(myPicture));


        leftPanel.add(picLabel);
    }

    public void setRightPanelContent() {
        myemail.setSize(40,40);
        mypassword.setSize(40,40);
        login.setSize(40,40);
        login.setBorder(new RoundBtn(15));
        login.setBackground(dodgerBlue);

        //Add action listeners to buttons
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
                    FileWriter fw= new FileWriter("src/Desktop/Files/"+email);


                    if (rs.next()) {
                        userId = rs.getInt("id");
                        username = rs.getString("name");
                        userRole = rs.getInt("role");
                        String userUppercase=username.toUpperCase();
                        fw.write("true");
                        fw.close();
                        if(userRole == 1){
                            setVisible(false);
                            new SystemAdminsRouting(toServer, fromServer,userUppercase);
                        }else if(userRole == 2) {
                            setVisible(false);
                            new ConfirmerRouting(userUppercase);
                        }else if(userRole == 3){
                            setVisible(false);
                            new CitizenRouting(toServer,fromServer,userUppercase);
                        } else if(userRole == 4){
                            setVisible(false);
                            new CompanyRouting(userUppercase);
                        }else if(userRole == 5){
                            setVisible(false);
                            new DistrictRouting(toServer,fromServer,userUppercase);
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