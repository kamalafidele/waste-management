package Desktop.Components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.print.PrinterException;
import java.sql.*;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.lang.*;
import javax.swing.border.EmptyBorder;

public class BillingSystem extends JFrame
{
    ArrayList<Double> amount = new ArrayList<Double>();
    private JFrame f = new JFrame("Invoice view");
    JPanel panel1 = new JPanel();
    public JTextArea textArea;
    Color dodgerBlue = new Color(52,143,235);
    JButton submitdata, download_pdf, doneTextArea, intotextarea, clearpanel,done;
    JLabel customername,customerphone,serviceLabel,transName, price;
    JTextField entername, enterphone,service,transaction,enter_price;
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
    LocalDateTime now = LocalDateTime.now();
    BillingSystem()
    {
        setBounds(300,100,1020,740);
        getContentPane().setBackground(Color.LIGHT_GRAY);


        JLabel mainheading = new JLabel("WSMS");
        mainheading.setBounds(350,20,500,50);
        mainheading.setFont(new Font("Rockwell Extra Bold",Font.PLAIN,50));
        add(mainheading);

        customername = new JLabel("Enter Name");
        customername.setBounds(20,86,500,50);
        customername.setFont(new Font("Charcoal CY",Font.PLAIN,20));
        add(customername);

        entername = new JTextField();
        entername.setBounds(150,96,180,40);
        entername.setFont(new Font("Charcoal CY",Font.PLAIN,18));
        add(entername);

        customerphone = new JLabel("Phone number");
        customerphone.setBounds(380,86,200,50);
        customerphone.setFont(new Font("Charcoal CY",Font.PLAIN,20));
        add(customerphone);

        enterphone = new JTextField();
        enterphone.setBounds(530,96,180,40);
        enterphone.setFont(new Font("Charcoal CY",Font.PLAIN,18));
        add(enterphone);

        submitdata = new JButton("Submit Data");
        submitdata.setBounds(770,96,200,50);
        submitdata.setFocusable(false);
        submitdata.setFont(new Font("Mongolian Baiti",Font.BOLD,16));
        submitdata.setBackground(dodgerBlue);
        submitdata.setForeground(Color.WHITE);
        add(submitdata);
        submitdata.addActionListener(this::submit_Button_Action);

        JPanel panel1 = new JPanel();
        panel1.setBounds(20,170,460,450);
        panel1.setBorder(BorderFactory.createBevelBorder(1));
        panel1.setLayout(null);
        f.add(panel1);

        panel1.setVisible(true);
        panel1.setBounds(300,100,1020,740);
        f.getContentPane().setBackground(Color.LIGHT_GRAY);

        textArea = new JTextArea();
        textArea.setBounds(590,170,360,450);
        textArea.setFont(new Font("Charcoal CY",Font.PLAIN,18));
        textArea.setBorder(BorderFactory.createBevelBorder(1));
        panel1.add(textArea);


        download_pdf = new JButton("Print Bill");
        download_pdf.setBounds(750,640,200,50);
        download_pdf.setFocusable(false);
        download_pdf.setFont(new Font("Mongolian Baiti",Font.BOLD,16));
        download_pdf.setBackground(dodgerBlue);
        download_pdf.setForeground(Color.WHITE);
        download_pdf.addActionListener(this::download_pdf_Button_Action);
        panel1.add(download_pdf);

        JPanel panel = new JPanel();
        panel.setBounds(20,170,460,450);
        panel.setBorder(BorderFactory.createBevelBorder(1));
        panel.setLayout(null);
        add(panel);

        serviceLabel = new JLabel("Enter Service:-");
        serviceLabel.setBounds(10,30,200,50);
        serviceLabel.setFont(new Font("Charcoal CY",Font.PLAIN,20));
        panel.add(serviceLabel);

        service = new JTextField();
        service.setBounds(170,30,250,40);
        service.setFont(new Font("Charcoal CY",Font.PLAIN,18));
        panel.add(service);

        transName = new JLabel("Transaction:-");
        transName.setBounds(10,110,200,50);
        transName.setFont(new Font("Charcoal CY",Font.PLAIN,20));
        panel.add(transName);

        transaction = new JTextField();
        transaction.setBounds(170,110,250,40);
        transaction.setFont(new Font("Charcoal CY",Font.PLAIN,18));
        panel.add(transaction);

        price = new JLabel("Enter Amount:-");
        price.setBounds(10,270,200,50);
        price.setFont(new Font("Charcoal CY",Font.PLAIN,20));
        panel.add(price);

        enter_price = new JTextField();
        enter_price.setBounds(170,270,250,40);
        enter_price.setFont(new Font("Charcoal CY",Font.PLAIN,18));
        panel.add(enter_price);

        clearpanel= new JButton("Clear");
        clearpanel.setBounds(20,380,150,50);
        clearpanel.setFont(new Font("Mongolian Baiti",Font.BOLD,16));
        clearpanel.setBackground(dodgerBlue);
        clearpanel.setForeground(Color.WHITE);
        clearpanel.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(ActionEvent evt)
            {
                clearpanel_Button_Action(evt);
            }
        });
        panel.add(clearpanel);

        done= new JButton("Add");
        done.setBounds(250,380,150,50);
        done.setFont(new Font("Mongolian Baiti",Font.BOLD,16));
        done.setBackground(dodgerBlue);
        done.setForeground(Color.WHITE);
        done.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(ActionEvent evt)
            {
                done_Button_Action(evt);
                btnButtonActionPerformed(evt);
            }
        });
        panel.add(done);

        intotextarea = new JButton("Exit");
        intotextarea.setBounds(100,640,300,50);
        intotextarea.setFont(new Font("Mongolian Baiti",Font.BOLD,16));
        intotextarea.setBackground(dodgerBlue);
        intotextarea.setForeground(Color.WHITE);
        intotextarea.addActionListener(this::Exit_Button_Action);
        add(intotextarea);


        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }








    private void clearpanel_Button_Action(ActionEvent evt)
    {
        enter_price.setText(null);
        service.setText(null);
        transaction.setText(null);

    }

    private void Exit_Button_Action(ActionEvent evt)
    {
        System.exit(0);
    }


    private void done_Button_Action(ActionEvent evt)
    {
        if (service.getText().isEmpty() && transaction.getText().isEmpty() &&  enter_price.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(null,  "Please Enter  Details","Getting Error", JOptionPane.ERROR_MESSAGE);
        }
        else {
            String services = String.format(service.getText());
            String trans = String.format(transaction.getText());
            String price = String.format(enter_price.getText());

            amount.add(Double.parseDouble(enter_price.getText()));
            textArea.append("\n" + services + "   " + trans + "\t  " + price);
            done_TextArea_Action(evt);
        }
    }

    private void done_TextArea_Action(ActionEvent evt)
    {
        Double totalamount=0.0;
        for (Double i : amount) {
            totalamount += i;
        }
        textArea.append("\n============================================" +
                "\n\t Total Amount:-"+totalamount+
                "\n=============================================");

    }

    private void submit_Button_Action(ActionEvent evt)
    {
        if (entername.getText().isEmpty() && enterphone.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(null,  "Please Enter Customer Details","Getting Error", JOptionPane.ERROR_MESSAGE);
        }
        else
        {

            String name = String.format(entername.getText());
            String phone = String.format(enterphone.getText());

            textArea.append("************************************************" +
                    "\n\tWSMS\t" +
                    "\n************************************************" +
                    "\n Date & Time :- " + dtf.format(now) +
                    "\n Name:- " + name +
                    "\n Phone Number:- " + phone +
                    "\n************************************************" +
                    "\nservice   transaction\tPrice");

        }
    }


    private void download_pdf_Button_Action(ActionEvent evt)
    {
        try {
            textArea.print();
        } catch (PrinterException e) {
            e.printStackTrace();
        }
    }
    private void btnButtonActionPerformed(java.awt.event.ActionEvent evt) {
        f.setVisible(true);
        panel1.setVisible(true);
    }

    public static void main(String[] args)
    {
        new BillingSystem();


    }

}