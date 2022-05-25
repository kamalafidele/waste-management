package Desktop.Components.Routing;
import Desktop.Components.CreateNotification;
import Desktop.Components.District.DistrictsView;
import Desktop.Components.Registration;
import Desktop.Components.testPanel;
import Desktop.Components.testPanel2;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

public class SystemAdminsRouting extends JFrame{
    ImageIcon analyticsImg,dashboardImg,TransactionsImg,NotificationsImg,DistrictsImg,addAdminImg;
    BufferedImage dashboard,analytics,notifications,Districts,addAdmin,transactions,logo,userAvatarImg;
    private  JPanel SideBar = new JPanel();

    private DataOutputStream toServer;
    private DataInputStream fromServer;
    Logout logout=new Logout();
    //PANELS
    testPanel panel = new testPanel();
    testPanel2 panel2=new testPanel2();
    Registration registerDistrict = new Registration(true,false, false);
    Registration registerUser = new Registration(false,false,true);
    DistrictsView districtsView = new DistrictsView();

    MenuListenerHandler listenerHandler = new MenuListenerHandler();

    public  SystemAdminsRouting(DataOutputStream toServer, DataInputStream fromServer,String username) throws IOException {
        this.toServer = toServer;
        this.fromServer = fromServer;
        registerUser.setStreams(toServer,fromServer);
        registerDistrict.setStreams(toServer,fromServer);

        setTitle("System Admin Board");
        setSize(1366,768);
        setLayout(null);
        SideBar.setVisible(true);
        SideBar.setSize(200,820);
        SideBar.setBackground(Color.decode("#EAEDF3"));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel.setVisible(true);

        add(SideBar);
        add(panel);
        add(panel2);
        add(registerDistrict);
        add(registerUser);
        add(districtsView);

        SidebarDesign();
        setVisible(true);
    }

    public void SidebarDesign() throws IOException {
        JMenuBar menuBar = new JMenuBar();
        analytics = ImageIO.read(new File("src/Desktop/Images/bar-chart-line.png"));
        dashboard = ImageIO.read(new File("src/Desktop/Images/dashboard-line.png"));
        transactions=ImageIO.read(new File("src/Desktop/Images/exchange-funds-line.png"));
        notifications=ImageIO.read(new File("src/Desktop/Images/notification-2-line.png"));
        addAdmin=ImageIO.read(new File("src/Desktop/Images/user-add-line.png"));
        Districts=ImageIO.read(new File("src/Desktop/Images/team-fill.png"));


        dashboardImg = new ImageIcon(dashboard.getScaledInstance(22,22,BufferedImage.SCALE_DEFAULT));
        NotificationsImg = new ImageIcon(notifications.getScaledInstance(22,22,BufferedImage.SCALE_DEFAULT));
        addAdminImg = new ImageIcon(addAdmin.getScaledInstance(22,22,BufferedImage.SCALE_DEFAULT));
        DistrictsImg = new ImageIcon(Districts.getScaledInstance(22,22,BufferedImage.SCALE_DEFAULT));
        analyticsImg = new ImageIcon(analytics.getScaledInstance(22,22,BufferedImage.SCALE_DEFAULT));
        TransactionsImg = new ImageIcon(transactions.getScaledInstance(22,22,BufferedImage.SCALE_DEFAULT));
        menuBar.setLayout(new GridLayout(0,1));
        menuBar.setPreferredSize(new Dimension(180,350));
        menuBar.setBackground(Color.decode("#EAEDF3"));
        JMenu Dashboard = new JMenu("Dashboard");
        Dashboard.setIcon(dashboardImg);
        Dashboard.setBackground(Color.orange);
        Dashboard.setFont(new Font("Inter", Font.PLAIN, 16));
        Dashboard.addMenuListener(listenerHandler);
        menuBar.add(Dashboard);
        JMenu Districts = new JMenu("Districts");
        Districts.setIcon(DistrictsImg);
        Districts.addMenuListener(listenerHandler);
        Districts.setFont(new Font("Inter", Font.PLAIN, 16));
        menuBar.add(Districts);
        JMenu Analytics = new JMenu("Analytics");
        Analytics.addMenuListener(listenerHandler);
        Analytics.setIcon((analyticsImg));
        Analytics.setFont(new Font("Inter", Font.PLAIN, 16));
        menuBar.add(Analytics);
        JMenu RegisterAdmin = new JMenu("Register Admin");
        RegisterAdmin.addMenuListener(listenerHandler);
        RegisterAdmin.setIcon(addAdminImg);
        RegisterAdmin.setFont(new Font("Inter", Font.PLAIN, 16));
        menuBar.add(RegisterAdmin);
        JMenu Transactions = new JMenu("Transactions");
        Transactions.addMenuListener(listenerHandler);
        Transactions.setIcon(TransactionsImg);
        Transactions.setFont(new Font("Inter", Font.PLAIN, 16));
        menuBar.add(Transactions);

        JMenu notification = new JMenu("Notifications");
        notification.addMenuListener(listenerHandler);
        notification.setIcon(NotificationsImg);
        notification.setFont(new Font("Inter", Font.PLAIN, 16));
        menuBar.add(notification);
        menuBar.revalidate();
        logo=ImageIO.read(new File("src/Desktop/Images/logo.png"));
        userAvatarImg=ImageIO.read(new File("src/Desktop/Images/user-avatar.png"));
        JLabel logoImg=new JLabel(new ImageIcon(logo.getScaledInstance(100,70,BufferedImage.SCALE_DEFAULT)));
        logoImg.setLayout(new GridLayout(1,1));
        JLabel userAvatar=new JLabel(new ImageIcon(userAvatarImg.getScaledInstance(90,90,BufferedImage.SCALE_DEFAULT)));
        JPanel credentials=new JPanel();
        credentials.setLayout(new GridLayout(2,1));
        JLabel userName=new JLabel("NTAKIRUTIMANA");
        userName.setFont(new Font("Inter", Font.BOLD, 18));
        JLabel userRole=new JLabel("           System Admin");
        credentials.add(logoImg);
        credentials.add(userAvatar);
        JPanel credential2=new JPanel();
        credential2.setBackground(Color.decode("#EAEDF3"));
        credentials.setBackground(Color.decode("#EAEDF3"));
        credential2.setLayout(new GridLayout(2,1));
        credential2.add(userName);
        credential2.add(userRole);
        SideBar.add(credentials);
        SideBar.add(credential2);
        SideBar.add(menuBar);
        SideBar.add(logout);
    }

    public  void filter(String chosen){
        switch (chosen) {
            case "Analytics":
                panel2.setVisible(false);
                registerUser.setVisible(false);
                registerDistrict.setVisible(false);
                panel.setVisible(true);
                break;
            case "Transactions":
                registerUser.setVisible(false);
                registerDistrict.setVisible(false);
                break;
            case "Register Admin":
                panel.setVisible(false);
                panel2.setVisible(false);
                registerDistrict.setVisible(false);
                registerUser.setVisible(true);
                break;
            case "Dashboard":
                registerUser.setVisible(false);
                registerDistrict.setVisible(false);
                break;
            case "Districts":
                panel.setVisible(false);
                panel2.setVisible(false);
                registerUser.setVisible(false);
//                registerDistrict.setVisible(true);
                districtsView.setVisible(true);
                break;
            case "Notifications":
                registerUser.setVisible(false);
                registerDistrict.setVisible(false);
                CreateNotification createNotification = new CreateNotification();
                createNotification.setStreams(toServer, fromServer);
                break;
            default:
                System.out.println();
                break;
        }
    }

    class MenuListenerHandler implements MenuListener {
        @Override
        public void menuSelected(MenuEvent e) {

            JMenu clickedMenu = (JMenu) e.getSource();
            String menuText = clickedMenu.getText();
            filter(menuText);
        }

        @Override
        public void menuDeselected(MenuEvent e) {

        }

        @Override
        public void menuCanceled(MenuEvent e) {

        }
    }
}