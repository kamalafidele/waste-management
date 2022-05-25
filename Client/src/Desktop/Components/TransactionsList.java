package Desktop.Components;

import org.codehaus.jackson.map.ObjectMapper;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class TransactionsList extends JPanel {
    ObjectMapper mapper = new ObjectMapper();

    DataOutputStream toServer;
    DataInputStream fromServer;

    JTextField name = new JTextField("Name");
    JTextField email = new JTextField("Email");
    JTextField phone = new JTextField("Phone");

    JLabel titleLabel = new JLabel("Transactions");
    JLabel lastTransactions = new JLabel("Last Transactions");
    JPanel lastTransactionss = new JPanel();
    JLabel transName = new JLabel("Paying security money");
    JLabel amount = new JLabel("2000");
    JLabel date = new JLabel("10 April, 2022");
    JPanel inputsPanel = new JPanel();

    ImageIcon trans = new ImageIcon("C:\\Users\\RCA student\\OneDrive\\Documents\\Projects\\waste_management_front\\Client\\src\\Desktop\\Images\\trans.webp");

//    public TransactionsList(){
//        setVisible(false);
//        setBounds(200,0,1166,768);
//        setBackground(Color.white);
//        setBorder(new EmptyBorder(new Insets(20,30,20,30)));
//        add(titleLabel);
//    }
//    public void initialize(){
//    }

    public TransactionsList() throws IOException {
//        TransactionHandler transactionHandler = new TransactionHandler();
//        transactionHandler.setMoney("2000");
//        sendRequest("registration/register_district/");
//        String response = fromServer.readUTF();
//        System.out.println(response);

        setVisible(false);
        setBounds(200,0,1166,768);
        setBackground(Color.white);
        setBorder(new EmptyBorder(new Insets(20,30,20,30)));
//        Image transIcon =trans.getImage().getScaledInstance(30, 30, 1);
//        lastTransactionss.setBackground(Color.gray);
//        GridLayout layout = new GridLayout(2,2);
//        layout.setHgap(10);
//        layout.setVgap(10);
//        add(inputsPanel);
//        setBorder(new EmptyBorder(new Insets(20,30,20,30)));
//
//        inputsPanel.setBackground(Color.lightGray);
//        inputsPanel.setBorder(new EmptyBorder(new Insets(10,10,10,50)));
//        inputsPanel.add(titleLabel);
//        inputsPanel.add(lastTransactions);
//        inputsPanel.add(date);
//        inputsPanel.add(amount);
//        lastTransactionss.setLayout(layout);
//        inputsPanel.setLayout(layout);

        JButton btn1 = new JButton("Recent Transactions");
        JButton btn2 = new JButton("Past Transactions");
        JButton btn3 = new JButton("Your Debts");

        Label money = new Label("Frw 250,600");
        money.setFont(new Font("Inter", Font.BOLD, 25));
        money.setBounds(20, 10, 200, 50);

        Label summary = new Label("Total Balanced based on your wallet");
        summary.setBounds(30, 10, 200, 20);
        summary.setForeground(Color.blue);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = GridBagConstraints.RELATIVE;

        JLabel contLabel = new JLabel("Procceed if you want to Depose your money");
        contLabel.setForeground(Color.white);
        contLabel.setFont(new Font("Inter", Font.LAYOUT_LEFT_TO_RIGHT, 10));

        JPanel form = new JPanel();
        form.setBackground(Color.darkGray);
        form.setPreferredSize(new Dimension(400, 500));
        gbc.gridy = 0;
        form.add(contLabel);
        gbc.gridy = 1;
        form.add(new JButton("Deposse"), gbc);

        JPanel trans = new JPanel();
        trans.setBackground(Color.white);
        trans.setPreferredSize(new Dimension(400, 50));

//        DropShadowBorder shadow = new DropShadowBorder();
//        shadow.setShadowColor(Color.BLACK);
//        shadow.setShowLeftShadow(true);
//        shadow.setShowRightShadow(true);
//        shadow.setShowBottomShadow(true);
//        shadow.setShowTopShadow(true);

        ImageIcon icon = new ImageIcon("C:\\Users\\RCA student\\OneDrive\\Documents\\Projects\\waste_management_front\\Client\\src\\Desktop\\Images\\tick.png");
        Image statIcon =icon.getImage().getScaledInstance(25, 20, 1);
        JLabel statusIcon = new JLabel();
        statusIcon.setIcon(new ImageIcon(statIcon));

        JLabel service = new JLabel("Paying Wastes");
        service.setFont(new Font("Inter", Font.BOLD, 17));
        service.setSize(200,10);
        service.setBounds(0 , 0, 200, 10);

        JLabel amount = new JLabel("2000"+" Frw");
        amount.setForeground(Color.lightGray);
        amount.setSize(200, 10);

        JLabel date = new JLabel("20 Apri, 2022");

        trans.add(statusIcon);
        trans.add(service);
        trans.add(amount);
        trans.add(date);

        JPanel pn1 = new JPanel();
        pn1.setBounds(20, 20, 200, 100);
        pn1.setPreferredSize(new Dimension(400, 500));
        pn1.setLayout(new GridBagLayout());

        gbc.gridy = 0;
        pn1.add(money, gbc);
        gbc.gridy = 1;
        pn1.add(summary, gbc);
        gbc.gridy = 2;
        pn1.add(form, gbc);
        add(pn1);

        JPanel pn2 = new JPanel();
        pn2.setPreferredSize(new Dimension(600, 500));
        pn2.setBorder(new EmptyBorder(20, 20, 20, 20));
        pn2.add(btn1);
        pn2.add(btn2);
        pn2.add(btn3);
        gbc.gridy = 2;
        pn2.add(trans, gbc);
        pn2.add(trans, gbc);
        add(pn2);

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
}
