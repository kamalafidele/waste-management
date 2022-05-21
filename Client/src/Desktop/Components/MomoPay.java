package Desktop.Components;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MomoPay extends JFrame implements ActionListener {
    private JFrame window;
    private JLabel phoneNumber;
    private JLabel amount;
    private JLabel spiral;
    private JLabel title;
    private JTextField number;
    private JTextField amt;
    private JPanel titlePanel;
    private JPanel formPanel;


    private JButton btn;
//    Socket socket;

    MomoPay() {
        window = new JFrame("Mobile Money Deposition");
        window.setSize(1370,730);
        window.setLayout(null);
        window.setBackground(Color.WHITE);

        titlePanel = new JPanel();
        titlePanel.setBounds(0,0,400,720);
        titlePanel.setBackground(Color.decode("#3674D0"));
        spiral = new JLabel("WSMS");
        spiral.setFont(new Font("Nunito", Font.BOLD, 30));
        spiral.setForeground(Color.white);
        spiral.setBounds(150,300,120,40);
        formPanel = new JPanel();
        formPanel.setBounds(400,0,960,720);
        formPanel.setBackground(Color.white);


        title = new JLabel("Deposit By Momo Account");
        title.setBounds(610,40,450,40);
        title.setFont(new Font("verdana", Font.PLAIN, 30));
        title.setForeground(Color.decode("#3674D0"));

        phoneNumber = new JLabel("Enter Phone Number");
        phoneNumber.setBounds(610,100,150,40);
        phoneNumber.setForeground(Color.decode("#202020"));
        phoneNumber.setFont(new Font("verdana", Font.PLAIN, 14));
        number = new JTextField();
        number.setBounds(610,150,400,40);
        number.setBorder(new LineBorder(Color.BLACK,1,true));


        amount = new JLabel("Enter Amount");
        amount.setBounds(610,200,150,40);
        amount.setForeground(Color.decode("#202020"));
        amount.setFont(new Font("verdana", Font.PLAIN, 14));
        amt = new JTextField();
        amt.setBounds(610,250,400,40);
        amt.setBorder(new LineBorder(Color.BLACK,1,true));



        btn = new JButton("Finish Up");
        btn.setBounds(610,320,400,70);
        btn.setBackground(Color.decode("#3674D0"));
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
//        btn.setSize(400, 90);
        btn.setFont(new Font("verdana", Font.PLAIN, 25));
        btn.addActionListener(this);
//        btn.setActionCommand("Complete");

        window.add(spiral);
        window.add(title);
        window.add(phoneNumber);
        window.add(number);
        window.add(amount);
        window.add(amt);

        window.add(btn);
        window.add(titlePanel);
        window.add(formPanel);
        window.setVisible(true);
    }
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btn) {
            String nbr;
            nbr = phoneNumber.getText();
            if (nbr.equalsIgnoreCase("0790880013")) {
                JOptionPane.showMessageDialog(this, "Deposit Recorded ");
            } else {
                JOptionPane.showMessageDialog(this, "There seems to be no momo account registered with that number");
            }
        }
    }
    public static void main(String[] args) {
        MomoPay m = new MomoPay();
    }
}


