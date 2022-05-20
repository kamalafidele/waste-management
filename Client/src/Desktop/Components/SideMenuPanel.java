package Desktop.Components;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.*;

public class SideMenuPanel extends JPanel{
    private  JPanel SideBar = new JPanel();
    private  JPanel OtherContent = new JPanel();

    public  SideMenuPanel(){
        JFrame window = new JFrame("Sidebar Panel");
        window.setSize(1370,730);
        window.setLayout(null);
        window.setBackground(Color.WHITE);
        Button logout=new Button("Logout");
        logout.setBounds(180,680,300,300);
        logout.setBackground(Color.decode("#557DF8"));

        SideBar.setVisible(true);
        SideBar.setSize(200,730);
        SideBar.setBackground(Color.decode("#EAEDF3"));


        OtherContent.setVisible(true);
        OtherContent.setSize(1370,730);
        OtherContent.setBackground(Color.decode("#FFFFFF"));
        window.add(SideBar);
        window.add(OtherContent);
        SidebarDesign();
        setRightPanelContent();
        window.setVisible(true);
        SideBar.add(logout);
    }
    public void SidebarDesign(){
        JMenuBar menuBar = new JMenuBar();
        ImageIcon analytics = new ImageIcon("/home/turinumugisha/Documents/waste-management/Client/src/Desktop/Images/bar-chart-line.png");
        ImageIcon dashboard = new ImageIcon("/home/turinumugisha/Documents/waste-management/Client/src/Desktop/Images/dashboard-line.png");
        ImageIcon transactions=new ImageIcon("/home/turinumugisha/Documents/waste-management/Client/src/Desktop/Images/exchange-funds-line.png");
        ImageIcon notifications=new ImageIcon("/home/turinumugisha/Documents/waste-management/Client/src/Desktop/Images/notification-2-line.png");
        ImageIcon addAdmin=new ImageIcon("/home/turinumugisha/Documents/waste-management/Client/src/Desktop/Images/user-add-line.png");
        ImageIcon districts=new ImageIcon("/home/turinumugisha/Documents/waste-management/Client/src/Desktop/Images/team-fill.png");
        Image analyticsImg=analytics.getImage().getScaledInstance(20,20,Image.SCALE_SMOOTH);
        Image dashboardImg=dashboard.getImage().getScaledInstance(20,20,Image.SCALE_SMOOTH);
        Image TransactionsImg=transactions.getImage().getScaledInstance(20,20,Image.SCALE_SMOOTH);
        Image NotificationsImg=notifications.getImage().getScaledInstance(20,20,Image.SCALE_SMOOTH);
        Image addAdminImg=addAdmin.getImage().getScaledInstance(20,20,Image.SCALE_SMOOTH);
        Image districtsImg=districts.getImage().getScaledInstance(20,20,Image.SCALE_SMOOTH);

        menuBar.setLayout(new GridLayout(0,1));
        menuBar.setPreferredSize(new Dimension(180,400));
        menuBar.setBackground(Color.decode("#EAEDF3"));
        JMenu Dashboard = new JMenu("Dashboard");
        Dashboard.setIcon(new ImageIcon(dashboardImg));
        Dashboard.setFont(new Font("Inter", Font.BOLD, 16));
        menuBar.add(Dashboard);
        JMenu Districts = new JMenu("Districts");
        Districts.setIcon(new ImageIcon(districtsImg));
        Districts.setFont(new Font("Inter", Font.PLAIN, 16));
        menuBar.add(Districts);
        JMenu Analytics = new JMenu("Analytics");
        Analytics.setIcon(new ImageIcon(analyticsImg));
        Analytics.setFont(new Font("Inter", Font.PLAIN, 16));
        menuBar.add(Analytics);
        JMenu RegisterAdmin = new JMenu("RegisterAdmin");
        RegisterAdmin.setIcon(new ImageIcon(addAdminImg));
        RegisterAdmin.setFont(new Font("Inter", Font.PLAIN, 16));
        menuBar.add(RegisterAdmin);
        JMenu Transactions = new JMenu("Transactions");
        Transactions.setIcon(new ImageIcon(TransactionsImg));
        Transactions.setFont(new Font("Inter", Font.PLAIN, 16));
        menuBar.add(Transactions);

        JMenu notification = new JMenu("Notifications");
        notification.setIcon(new ImageIcon(NotificationsImg));
        notification.setFont(new Font("Inter", Font.PLAIN, 16));
        menuBar.add(notification);
        menuBar.revalidate();
        menuBar.setSize(500,100);
        SideBar.add(menuBar);
    }
    public void setRightPanelContent(){
        JLabel label=new JLabel("This will be place for the content");
        OtherContent.add(label);
    }
    public static void main(final String args[]) {
        new SideMenuPanel();
    }
}