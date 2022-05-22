package Desktop.Components.Routing;
import Desktop.Components.testPanel;
import Desktop.Components.testPanel2;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

public class DistrictRouting extends JFrame{
     ImageIcon analyticsImg,dashboardImg,TransactionsImg,NotificationsImg,districtsImg,addAdminImg;
     BufferedImage dashboard,analytics,notifications,districts,addAdmin,transactions;
    private  JPanel SideBar = new JPanel();
    private  JPanel OtherContent = new JPanel();

    //PANELS
    testPanel panel = new testPanel();
    testPanel2 panel2=new testPanel2();
    MenuListenerHandler listenerHandler = new MenuListenerHandler();

    public  DistrictRouting() throws IOException {
        setTitle("Sidebar Panel");
        setSize(1366,768);
        setLayout(null);
        setBackground(Color.WHITE);
        Button logout=new Button("Logout");
        logout.setBounds(180,180,300,300);
        logout.setSize(400,300);
        logout.setBackground(Color.decode("#557DF8"));
        SideBar.setVisible(true);
        SideBar.setSize(200,730);
        SideBar.setBackground(Color.decode("#EAEDF3"));
        add(SideBar);
        add(panel);
        add(panel2);
        SidebarDesign();
        setVisible(true);
        SideBar.add(logout);
    }

    public void SidebarDesign() throws IOException {
        JMenuBar menuBar = new JMenuBar();
        analytics = ImageIO.read(new File("src/Desktop/Images/bar-chart-line.png"));
        dashboard = ImageIO.read(new File("src/Desktop/Images/dashboard-line.png"));
        transactions=ImageIO.read(new File("src/Desktop/Images/exchange-funds-line.png"));
        notifications=ImageIO.read(new File("src/Desktop/Images/notification-2-line.png"));
        addAdmin=ImageIO.read(new File("src/Desktop/Images/user-add-line.png"));
        districts=ImageIO.read(new File("src/Desktop/Images/team-fill.png"));
        dashboardImg = new ImageIcon(dashboard.getScaledInstance(22,22,BufferedImage.SCALE_DEFAULT));
        NotificationsImg = new ImageIcon(notifications.getScaledInstance(22,22,BufferedImage.SCALE_DEFAULT));
        addAdminImg = new ImageIcon(addAdmin.getScaledInstance(22,22,BufferedImage.SCALE_DEFAULT));
        districtsImg = new ImageIcon(districts.getScaledInstance(22,22,BufferedImage.SCALE_DEFAULT));
        analyticsImg = new ImageIcon(analytics.getScaledInstance(22,22,BufferedImage.SCALE_DEFAULT));
        TransactionsImg = new ImageIcon(transactions.getScaledInstance(22,22,BufferedImage.SCALE_DEFAULT));
        menuBar.setLayout(new GridLayout(0,1));
        menuBar.setPreferredSize(new Dimension(180,400));
        menuBar.setBackground(Color.decode("#EAEDF3"));
        JMenu Dashboard = new JMenu("Dashboard");
        Dashboard.setIcon(dashboardImg);
        Dashboard.setBackground(Color.orange);
        Dashboard.setFont(new Font("Inter", Font.BOLD, 16));
        Dashboard.addMenuListener(listenerHandler);
        menuBar.add(Dashboard);
        JMenu Districts = new JMenu("Districts");
        Districts.setIcon(districtsImg);
        Districts.addMenuListener(listenerHandler);
        Districts.setFont(new Font("Inter", Font.PLAIN, 16));
        menuBar.add(Districts);
        JMenu Analytics = new JMenu("Analytics");
        Analytics.addMenuListener(listenerHandler);
        Analytics.setIcon((analyticsImg));
        Analytics.setFont(new Font("Inter", Font.PLAIN, 16));
        menuBar.add(Analytics);
        JMenu RegisterAdmin = new JMenu("RegisterAdmin");
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
        SideBar.add(menuBar);
    }
    

    public static void main(final String args[]) throws IOException {
       new DistrictRouting();
    }

    public  void filter(String chosen){
        switch (chosen) {
            case "Analytics":
                panel2.setVisible(false);
                panel.setVisible(true);
                break;
            case "Transactions":
                break;
            case "RegisterAdmin":
                System.out.println("RegisterAdmin clicked");
                break;
            case "Dashboard":
                panel.setVisible(false);
                panel2.setVisible(true);
                break;
            case "Districts":
                System.out.println("Districts clicked");
                break;
            case "Notifications":
                System.out.println("Notifications clicked");
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