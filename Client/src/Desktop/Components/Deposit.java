package Desktop.Components;

import DataHandlers.CompanyHandler;
import DataHandlers.DistrictHandler;
import Desktop.EventHandlers.PlaceHolderHandler;
import Desktop.Screens.RoundBtn;
import org.codehaus.jackson.map.ObjectMapper;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class Deposit extends JFrame {

    private boolean isMomo;
    private boolean isBank;
    ObjectMapper mapper = new ObjectMapper();

    DataOutputStream toServer;
    DataInputStream fromServer;
    private  static  JTextField bankacc = new JTextField();
    private  static  JTextField momo = new JTextField();
    private  static  JTextField amount = new JTextField();

    JLabel bankLabel = new JLabel("Bank Account");
    JLabel phoneLabel = new JLabel("Phone Number");
    JLabel heading = new JLabel();
    JLabel amountLabel = new JLabel("Amount");


    JButton Finish = new JButton("Finish");

    JPanel inputsPanel = new JPanel();
    JPanel titlePanel = new JPanel();
    JPanel buttonsPanel = new JPanel();

    Color dodgerBlue = new Color(52, 143, 235);

    CompanyHandler companyHandler = null;
    DistrictHandler districtHandler = null;

    public Deposit(boolean isMomo, boolean isBank, DataOutputStream toServer, DataInputStream fromServer) {
        System.out.println(toServer + "IN DEPOSITI");
        this.toServer = toServer;
        this.fromServer = fromServer;
        this.isMomo = isMomo;
        this.isBank = isBank;

        setVisible(false);
        setSize(300,600);
        GridLayout layout = new GridLayout(3, 1);
        layout.setVgap(0);
        layout.setHgap(0);

        setLayout(layout);
        setBounds(400,0,960,720);

        add(titlePanel);
        add(inputsPanel);
        add(buttonsPanel);

        setTitlePanelContent();
        setInputsPanelContent();
        setButtonsPanelContent();

        styleComponents();
    }

    public void styleComponents() {

        heading.setBounds(310,60,350,40);
        heading.setFont(new Font("verdana", Font.PLAIN, 30));
        heading.setForeground(Color.decode("#3674D0"));
        bankacc.setBorder(new RoundBtn(8));
        momo.setBorder(new RoundBtn(8));
        amount.setBorder(new RoundBtn(8));
        Finish.setBorder(new RoundBtn(10));
        Finish.setBackground(dodgerBlue);


        Finish.setActionCommand("submit");

        inputsPanel.setBackground(Color.WHITE);
        titlePanel.setBackground(Color.WHITE);
        buttonsPanel.setBackground(Color.WHITE);
        titlePanel.setLayout(new BorderLayout());

        inputsPanel.setBorder(new EmptyBorder(new Insets(10, 10, 10, 50)));
        buttonsPanel.setBorder(new EmptyBorder(new Insets(40, 10, 40, 0)));
    }

    public void setTitlePanelContent() {
        titlePanel.add(heading);


    }

    public void setButtonsPanelContent() {
        buttonsPanel.add(Finish);
        buttonsPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        buttonsPanel.setSize(1180, 400);
    }

    public void setInputsPanelContent() {
        if (isBank) {
            inputsPanel.add(bankLabel);
            inputsPanel.add(bankacc);

            inputsPanel.add(amountLabel);
            inputsPanel.add(amount);
            heading.setText("Deposit By Bank");
            Finish.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String bankAccount = bankacc.getText();
                    String amt = amount.getText();
                    System.out.println(toServer);
                    String Request = "payment/bankpayment/" + bankAccount +"/"+amt +"/"+5678;
                    sendRequest(Request);
                    if (bankAccount.equalsIgnoreCase("1234567")) {
                        JOptionPane.showMessageDialog(Finish, "Deposit Recorded ");
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(Finish, "Invalid Bank Account");
                    }
                }
            });

        } else if (isMomo) {
            inputsPanel.add(phoneLabel);
            inputsPanel.add(momo);
            inputsPanel.add(amountLabel);
            inputsPanel.add(amount);
            heading.setText("Deposit By Momo");
            Finish.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String momoAccount = momo.getText();
                    String amt = amount.getText();
                    System.out.println(toServer);
                    String Request = "payment/momopayment/" + momoAccount +"/"+amt +"/"+1234;
                    sendRequest(Request);

                    if (momoAccount.equalsIgnoreCase("0790880013")) {
                        JOptionPane.showMessageDialog(Finish, "Deposit Recorded ");
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(Finish, "Invalid Number");
                    }
                }

            });

        }
        GridLayout layout = new GridLayout(2, 2);
        layout.setHgap(10);
        layout.setVgap(10);
        inputsPanel.setLayout(layout);

    }

    public void setStreams(DataOutputStream toServer, DataInputStream fromServer) {
        this.toServer = toServer;
        this.fromServer = fromServer;
    }
    public void sendRequest( String request ){
        try{
            toServer.writeUTF( request );
        }catch ( IOException exception ){}
    }

    public void setInputsToDefault(){
        bankacc.setText("bank account");
        amount.setText("amount");
        momo.setText("Momo number");

    }
}
