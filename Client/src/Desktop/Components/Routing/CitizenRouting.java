package Desktop.Components.Routing;
import Desktop.Components.testPanel;
import Desktop.Components.testPanel2;
import Desktop.Components.viewNotifications;

import Desktop.Components.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

public class CitizenRouting extends JFrame{
    ImageIcon analyticsImg,dashboardImg,TransactionsImg,NotificationsImg,DebtsImg,addAdminImg;
    BufferedImage dashboard,analytics,notifications,Debts,addAdmin,transactions,logo,userAvatarImg;
    private  JPanel SideBar = new JPanel();

    Logout logout=new Logout(this);

    private DataOutputStream toServer;
    private DataInputStream fromServer;

    //PANELS

    testPanel panel = new testPanel();
    testPanel2 panel2=new testPanel2();
    AnalyticsPanel mainAnalyticsPanel = new AnalyticsPanel();
    viewNotifications viewNoti =new viewNotifications();

    MenuListenerHandler listenerHandler = new MenuListenerHandler();
    StepOneDeposit step1ToDeposit =  new StepOneDeposit();
    StepTwoDeposit step2ToDeposit = new StepTwoDeposit();
    public CitizenRouting(DataOutputStream toServer, DataInputStream fromServer,String username) throws IOException{
        setTitle("Citizen Board");
        setSize(1366,768);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        SideBar.setVisible(true);
        SideBar.setSize(200,820);
        SideBar.setBackground(Color.decode("#EAEDF3"));
        add(SideBar);
        panel.setVisible(true);

        // analytics
        // invoices
        JPanel invoicesPanel = new InvoicesPanel();
        // wallet
        mainAnalyticsPanel.add(new Wallet(toServer, fromServer));
        // analytics panel
        JPanel analyticsPanel = new Analytics(invoicesPanel, new ReportsPanel(), new NewReportPanel() ,mainAnalyticsPanel);
        mainAnalyticsPanel.add(analyticsPanel);
        // invoices panel
        mainAnalyticsPanel.add(invoicesPanel);

        add(panel);
        add(panel2);
        add(mainAnalyticsPanel);
        add(viewNoti);
        add(step1ToDeposit);
//        add(step2ToDeposit);

        SidebarDesign(username);
        setVisible(true);
    }

    public void SidebarDesign(String username) throws IOException {
        JMenuBar menuBar = new JMenuBar();
        menuBar.setPreferredSize(new Dimension(180,350));

        analytics = ImageIO.read(new File("src/Desktop/Images/bar-chart-line.png"));
        dashboard = ImageIO.read(new File("src/Desktop/Images/dashboard-line.png"));
        transactions=ImageIO.read(new File("src/Desktop/Images/exchange-funds-line.png"));
        notifications=ImageIO.read(new File("src/Desktop/Images/notification-2-line.png"));
        addAdmin=ImageIO.read(new File("src/Desktop/Images/user-add-line.png"));
        Debts=ImageIO.read(new File("src/Desktop/Images/money-dollar-circle-line.png"));

        dashboardImg = new ImageIcon(dashboard.getScaledInstance(22,22,BufferedImage.SCALE_DEFAULT));
        NotificationsImg = new ImageIcon(notifications.getScaledInstance(22,22,BufferedImage.SCALE_DEFAULT));
        addAdminImg = new ImageIcon(addAdmin.getScaledInstance(22,22,BufferedImage.SCALE_DEFAULT));
        DebtsImg = new ImageIcon(Debts.getScaledInstance(22,22,BufferedImage.SCALE_DEFAULT));
        analyticsImg = new ImageIcon(analytics.getScaledInstance(22,22,BufferedImage.SCALE_DEFAULT));
        TransactionsImg = new ImageIcon(transactions.getScaledInstance(22,22,BufferedImage.SCALE_DEFAULT));
        menuBar.setLayout(new GridLayout(0,1));

        menuBar.setBackground(Color.decode("#EAEDF3"));
        JMenu Dashboard = new JMenu("Dashboard");
        Dashboard.setIcon(dashboardImg);
        Dashboard.setBackground(Color.orange);
        Dashboard.setFont(new Font("Inter", Font.PLAIN, 16));
        Dashboard.addMenuListener(listenerHandler);
        menuBar.add(Dashboard);
        JMenu Debts = new JMenu("Debts");
        Debts.setIcon(DebtsImg);
        Debts.addMenuListener(listenerHandler);
        Debts.setFont(new Font("Inter", Font.PLAIN, 16));
        menuBar.add(Debts);
        JMenu Analytics = new JMenu("Analytics");
        Analytics.addMenuListener(listenerHandler);
        Analytics.setIcon((analyticsImg));
        Analytics.setFont(new Font("Inter", Font.PLAIN, 16));
        menuBar.add(Analytics);


        JMenu Transactions = new JMenu("Transactions");
        Transactions.addMenuListener(listenerHandler);
        Transactions.setIcon(TransactionsImg);
        Transactions.setFont(new Font("Inter", Font.PLAIN, 16));
        menuBar.add(Transactions);


        JMenu Choose_service_to_deposit = new JMenu("choose service");
        Choose_service_to_deposit.addMenuListener(listenerHandler);
        Choose_service_to_deposit.setIcon(TransactionsImg);
        Choose_service_to_deposit.setFont(new Font("Inter", Font.PLAIN, 16));
        menuBar.add(Choose_service_to_deposit);





//        JMenu Choose_deposit_method = new JMenu("");
//        Choose_deposit_method.addMenuListener(listenerHandler);
//        Choose_deposit_method.setIcon(TransactionsImg);
//        Choose_deposit_method.setFont(new Font("Inter", Font.PLAIN, 16));
//        menuBar.add(Choose_deposit_method);



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
        JLabel userRole=new JLabel("           System Client");
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
                panel.setVisible(false);
                panel2.setVisible(false);
                mainAnalyticsPanel.setVisible(true);
                viewNoti.setVisible(false);
                step1ToDeposit.setVisible(false);
                break;
            case "Transactions":
                viewNoti.setVisible(false);
                mainAnalyticsPanel.setVisible(false);
                step1ToDeposit.setVisible(false);
                break;
            case "Dashboard":
                panel.setVisible(false);
                panel2.setVisible(true);
                viewNoti.setVisible(false);
                step1ToDeposit.setVisible(false);
                mainAnalyticsPanel.setVisible(false);

                break;
            case "Debts":
                System.out.println("Debts clicked");
                viewNoti.setVisible(false);
                step1ToDeposit.setVisible(false);
                mainAnalyticsPanel.setVisible(false);

                break;
            case "Notifications":
                System.out.println("Notifications clicked");
                viewNoti.setVisible(true);
                panel.setVisible(false);
                panel2.setVisible(false);
                step1ToDeposit.setVisible(false);
                mainAnalyticsPanel.setVisible(false);

                break;
            case "choose service":
                panel2.setVisible(false);
                panel.setVisible(false);
                viewNoti.setVisible(false);
                mainAnalyticsPanel.setVisible(false);
                step1ToDeposit.setVisible(true);
                break;
            case "Invoices":
                System.out.println("Invoices clicled");
                break;
            default:
                mainAnalyticsPanel.setVisible(false);

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