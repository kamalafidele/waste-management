package Desktop.Components;

import Desktop.Components.Routing.CitizenRouting;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StepTwoDeposit extends JPanel {
    public StepTwoDeposit() {
        setVisible(false);
        setBounds(200, 0, 1166, 768);
        setBackground(Color.white);
        setBorder(new EmptyBorder(new Insets(20, 30, 20, 30)));

        JLabel heading1  = new JLabel("Choose Deposit Method");
        heading1.setFont(new Font("Inter", Font.BOLD, 20));
        add(heading1);

        // setting the dodge blue color
        Color dodgerBlue = new Color(52,143,235);


//        JButton wasteCollection = new JButton("1. Waste Collection");
//        wasteCollection.setSize(100, 200);
//        wasteCollection.setBackground(dodgerBlue);
//        wasteCollection.setPreferredSize(new Dimension(40, 40));
//        add(wasteCollection);

        JPanel  wasteCollectionButton = new JPanel();
        JButton wasteCollection = new JButton("1. Momo");
        wasteCollection.setBackground(Color.decode("#557DF8"));
        wasteCollection.setBorder(new EmptyBorder(new Insets(12,40,12,40)));
        wasteCollection.setFont(new Font("Inter", Font.PLAIN, 16));
        wasteCollection.setForeground(Color.WHITE);
        wasteCollection.setFocusPainted(false);
        wasteCollectionButton.setBorder(new EmptyBorder(new Insets(60,0,0,0)));
        wasteCollectionButton.add(wasteCollection);
        add(wasteCollection);



        JPanel  securityButton = new JPanel();
        JButton security = new JButton("2. Bank");
        security.setBackground(Color.decode("#557DF8"));
        security.setBorder(new EmptyBorder(new Insets(12,40,12,40)));
        security.setFont(new Font("Inter", Font.PLAIN, 16));
        security.setForeground(Color.WHITE);
        security.setFocusPainted(false);
        securityButton.setBorder(new EmptyBorder(new Insets(60,0,0,0)));
        securityButton.add(security);
        add(security);


        security.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    System.out.println("Security button clicked !");
                } catch (Exception exception) {
                    System.out.println("Something went wrong !");
                    exception.printStackTrace();
                }
            }
        });



    }
}