package Desktop.Components;
import java.awt.*;
import java.awt.event.KeyEvent;
import javax.swing.*;

public class SideMenuPanel extends JPanel{
    private  JPanel SideBar = new JPanel();
    private  JPanel OtherContent = new JPanel();
    public  SideMenuPanel(){
        JFrame window = new JFrame("Sidebar");
        window.setSize(1370,730);
        window.setLayout(null);
        window.setBackground(Color.WHITE);

        SideBar.setVisible(true);
        SideBar.setSize(200,730);
        SideBar.setBackground(Color.decode("#EAEDF3"));

//        OtherContent.setVisible(true);
//        OtherContent.setSize(1370,730);
//        OtherContent.setBackground(Color.decode("#FFFFFF"));

        window.add(SideBar);
//        window.add(OtherContent);
        SidebarDesign();
        setRightPanelContent();
        window.setVisible(true);
    }
    public void SidebarDesign(){
        JMenuBar menuBar = new JMenuBar();
        menuBar.setLayout(new GridLayout(0,1));
        menuBar.setPreferredSize(new Dimension(180,400));
        menuBar.setBackground(Color.decode("#EAEDF3"));
        JMenu Dashboard = new JMenu("Dashboard");
        Dashboard.setFont(new Font("Inter", Font.BOLD, 16));
        menuBar.add(Dashboard);
        JMenu Districts = new JMenu("Districts");
        Districts.setFont(new Font("Inter", Font.PLAIN, 16));
        menuBar.add(Districts);
        JMenu Analytics = new JMenu("Analytics");
        Analytics.setFont(new Font("Inter", Font.PLAIN, 16));
        menuBar.add(Analytics);
        JMenu RegisterAdmin = new JMenu("RegisterAdmin");
        RegisterAdmin.setFont(new Font("Inter", Font.PLAIN, 16));
        menuBar.add(RegisterAdmin);
        JMenu Transactions = new JMenu("Transactions");
        Transactions.setFont(new Font("Inter", Font.PLAIN, 16));
        menuBar.add(Transactions);

        JMenu notification = new JMenu("Notifications");
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