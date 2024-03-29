package Desktop.Components.Routing;
import Desktop.Components.*;

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

public class CompanyRouting extends JFrame{
     ImageIcon analyticsImg,dashboardImg,TransactionsImg,NotificationsImg,ShiftsImg,addAdminImg;
     BufferedImage dashboard,analytics,notifications,Shifts,addAdmin,transactions,logo,userAvatarImg;
    private  JPanel SideBar = new JPanel();
    private DataOutputStream toServer;
    private DataInputStream fromServer;


    Logout logout=new Logout(this);
    //PANELS
    testPanel panel = new testPanel();
    testPanel2 panel2=new testPanel2();
    Employees employees=new Employees();
    Registration registration = new Registration(false,false,true);
    Registration registerUser = new Registration(false,false,true);
    AnalyticsPanel mainAnalyticsPanel = new AnalyticsPanel();

    MenuListenerHandler listenerHandler = new MenuListenerHandler();

    public  CompanyRouting(String username) throws IOException {
//        this.fromServer = fromServer;
//        this.toServer = toServer;
//        registerUser.setStreams(toServer,fromServer);

        setTitle("Company Board");
        setSize(1366,768);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        SideBar.setVisible(true);
        SideBar.setSize(200,820);
        SideBar.setBackground(Color.decode("#EAEDF3"));

        add(SideBar);
        panel.setVisible(true);
        add(panel);
        add(panel2);
        // analytics
        // invoices
        JPanel invoicesPanel = new InvoicesPanel();
        // wallet
        JPanel wallet = new Wallet(toServer, fromServer);
        mainAnalyticsPanel.add(wallet);
        // analytics panel
        JPanel analyticsPanel = new Analytics(invoicesPanel, new ReportsPanel(), new NewReportPanel() ,mainAnalyticsPanel);
        mainAnalyticsPanel.add(analyticsPanel);
        // invoices panel
        mainAnalyticsPanel.add(invoicesPanel);
        add(mainAnalyticsPanel);
        add(registration);
        add(employees);
        add(registerUser);

        SidebarDesign(username);
        setVisible(true);
    }

    public void SidebarDesign(String username) throws IOException {
        JMenuBar menuBar = new JMenuBar();
        analytics = ImageIO.read(new File("src/Desktop/Images/bar-chart-line.png"));
        dashboard = ImageIO.read(new File("src/Desktop/Images/dashboard-line.png"));
        transactions=ImageIO.read(new File("src/Desktop/Images/exchange-funds-line.png"));
        notifications=ImageIO.read(new File("src/Desktop/Images/notification-2-line.png"));
        addAdmin=ImageIO.read(new File("src/Desktop/Images/user-add-line.png"));
        Shifts=ImageIO.read(new File("src/Desktop/Images/stack-line.png"));


        dashboardImg = new ImageIcon(dashboard.getScaledInstance(22,22,BufferedImage.SCALE_DEFAULT));
        NotificationsImg = new ImageIcon(notifications.getScaledInstance(22,22,BufferedImage.SCALE_DEFAULT));
        addAdminImg = new ImageIcon(addAdmin.getScaledInstance(22,22,BufferedImage.SCALE_DEFAULT));
        ShiftsImg = new ImageIcon(Shifts.getScaledInstance(22,22,BufferedImage.SCALE_DEFAULT));
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
        JMenu Shifts = new JMenu("Shifts");
        Shifts.setIcon(ShiftsImg);
        Shifts.addMenuListener(listenerHandler);
        Shifts.setFont(new Font("Inter", Font.PLAIN, 16));
        menuBar.add(Shifts);
        JMenu Analytics = new JMenu("Analytics");
        Analytics.addMenuListener(listenerHandler);
        Analytics.setIcon((analyticsImg));
        Analytics.setFont(new Font("Inter", Font.PLAIN, 16));
        menuBar.add(Analytics);
        JMenu RegisterEmployee = new JMenu("Employees");
        RegisterEmployee.addMenuListener(listenerHandler);
        RegisterEmployee.setIcon(addAdminImg);
        RegisterEmployee.setFont(new Font("Inter", Font.PLAIN, 16));
        menuBar.add(RegisterEmployee);
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
        JLabel userName=new JLabel(username);
        userName.setFont(new Font("Inter", Font.BOLD, 18));
        JLabel userRole=new JLabel("           Company Admin");
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
                panel.setVisible(false);
                mainAnalyticsPanel.setVisible(true);
                break;
            case "Transactions":
                break;
            case "Register Employee":
                panel2.setVisible(false);
                panel.setVisible(false);
                registerUser.setVisible(true);
                break;
            case "Dashboard":
                registerUser.setVisible(false);
                panel.setVisible(false);
                panel2.setVisible(true);
                break;
            case "Employees":
                panel.setVisible(false);
                panel2.setVisible(false);
                registration.setVisible(false);
                employees.setVisible(true);
                System.out.println("employees");
            case "Shifts":
                registerUser.setVisible(false);
                break;
            case "Notifications":
                registerUser.setVisible(false);
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