package Desktop.Components;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class Employees extends JPanel {
    JLabel searchLbl=new JLabel("Search employee");
    JTextField search=new JTextField();
    JButton addNew=new JButton("Add new Employee");
    public Employees(){
        setVisible(false);
        setBounds(200,0,1166,768);
        setBackground(Color.WHITE);
        setBorder(new EmptyBorder(new Insets(20,30,20,30)));
        add(searchLbl);
        add(search);
        add(addNew);
    }
    public void setStyles(){
        Color dodgerBlue = new Color(52,143,235);
        search.setSize(200,30);
        searchLbl.setFont(new Font("POPPINS",Font.PLAIN,15));
        addNew.setBackground(dodgerBlue);
    }
}
