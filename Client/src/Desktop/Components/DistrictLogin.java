package Desktop.Components;
import javax.swing.*;
import java.awt.*;
import java.text.ParseException;


public class DistrictLogin extends JFrame{
  JLabel l1, l2, l3;
  JTextField tf1;

  JButton btn1;

  JPasswordField p1;

  DistrictLogin() throws ParseException {


    setTitle("WSMS District Login");

    setVisible(true);

    setSize(4000, 800);

    setLayout(null);

    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    districtLogin();

  }
  public void districtLogin(){

    l1 = new JLabel("WSMS District Login:");

    l1.setForeground(Color.blue);

    l1.setFont(new Font("Serif", Font.BOLD, 20));

    l2 = new JLabel("Enter District Name:");

    l3 = new JLabel("Enter Password:");

    tf1 = new JTextField();

    p1 = new JPasswordField();

    btn1 = new JButton("Submit");

    l1.setBounds(500, 100, 600, 70);
    l1.setFont(new Font("Serif", Font.BOLD, 40));
    l2.setBounds(300, 250, 400, 40);
    l2.setFont(new Font("Serif", Font.BOLD, 20));
    l3.setBounds(300, 300, 400, 40);
    l3.setFont(new Font("Serif", Font.BOLD, 20));
    tf1.setBounds(500, 250, 400, 40);

    p1.setBounds(500, 300, 400, 40);

    btn1.setBounds(500, 350, 400, 40);
    btn1.setForeground(Color.WHITE);
    btn1.setBackground(Color.blue);

    add(l1);

    add(l2);

    add(tf1);

    add(l3);

    add(p1);

    add(btn1);

  }
}



