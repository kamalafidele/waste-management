package Desktop.Components.Admin;

import Desktop.Components.SideMenuPanel;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class DashBoard extends JFrame{
    private SideMenuPanel sidebar = new SideMenuPanel();
    private JPanel content = new JPanel();

    public void initialize() {
        addSideBar();
        addContent();

        setVisible(true);
        setTitle("Admin - Dashboard");
        setSize(1366,760);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout(FlowLayout.LEFT));
    }

    public void addSideBar(){
        getContentPane().add(sidebar);
    }

    public void addContent(){

        JPanel up = new JPanel();
        JPanel down = new JPanel();

        JPanel one = new JPanel();
        JPanel two = new JPanel();
        JPanel three = new JPanel();
        JPanel transactions = new JPanel();
        JPanel wallet = new JPanel();

        GridLayout layout = new GridLayout(2,3);
        layout.setVgap(10);

        content.setVisible(true);
        content.setSize(800,760);
        content.setLayout(layout);
//      content.setBorder(new EmptyBorder(100, 50, 100, 50));

        //working on up panel
        up.setVisible(true);
        up.setSize(800, 500);
        up.setBackground(Color.black);

        //adding content panels
        content.add(up);

        getContentPane().add(content);
    }
}