package Desktop.Components;

import Desktop.Components.Routing.CitizenRouting;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;

public class StepTwoDeposit extends JPanel {
    DataOutputStream toServer;
    DataInputStream fromServer;
    public StepTwoDeposit() {

        System.out.println("StepTwoDeposit constructor called !");
       setVisible(false);
        setBounds(200, 0, 1166, 768);
        setBackground(Color.white);
        setBorder(new EmptyBorder(new Insets(200, 30, 20, 30)));

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

        JPanel  momoButton = new JPanel();
        JButton momo = new JButton("1. Momo");
        momo.setBackground(Color.decode("#557DF8"));
        momo.setBorder(new EmptyBorder(new Insets(12,40,12,40)));
        momo.setFont(new Font("Inter", Font.PLAIN, 16));
        momo.setForeground(Color.WHITE);
        momo.setFocusPainted(false);
        momoButton.setBorder(new EmptyBorder(new Insets(60,0,0,0)));
        momoButton.add(momo);
        add(momo);



        JPanel  bankButton = new JPanel();
        JButton bank = new JButton("2. Bank");
        bank.setBackground(Color.decode("#557DF8"));
        bank.setBorder(new EmptyBorder(new Insets(12,40,12,40)));
        bank.setFont(new Font("Inter", Font.PLAIN, 16));
        bank.setForeground(Color.WHITE);
        bank.setFocusPainted(false);
        bankButton.setBorder(new EmptyBorder(new Insets(60,0,0,0)));
        bankButton.add(bank);
        bankButton.setBorder(new EmptyBorder(new Insets(100, 100, 100, 100)));
        add(bank);

//        Deposit depo = new Deposit(true,false);
//
//
//        add(depo);
//        add(depo2);


        bank.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    System.out.println("bank button clicked !");
                    Deposit depo2= new Deposit(false,true,toServer,fromServer);
                    depo2.setVisible(true);


                } catch (Exception exception) {
                    System.out.println("Something went wrong !");
                    exception.printStackTrace();
                }
            }
        });

        momo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    System.out.println("Momo button clicked !");
                    Deposit depo2= new Deposit(true,false,toServer,fromServer);
                    depo2.setVisible(true);


                } catch (Exception exception) {
                    System.out.println("Something went wrong !");
                    exception.printStackTrace();
                }
            }
        });


    }
}