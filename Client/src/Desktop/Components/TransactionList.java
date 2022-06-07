package Desktop.Components;

import DataHandlers.DistrictHandler;
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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.*;
import java.util.ArrayList;

public class TransactionList extends JPanel {
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

    public TransactionList() throws IOException, SQLException {

        setVisible(false);
        setBounds(200,0,1166,768);
        setBackground(Color.white);
        setBorder(new EmptyBorder(new Insets(20,30,20,30)));
        JButton btn1 = new JButton("Recent Transactions");
        JButton btn2 = new JButton("Past Transactions");
        JButton btn3 = new JButton("Your Debts");


        Connection connection =  DriverManager.getConnection("jdbc:mysql://localhost:3306/LGMxUJ3u44",
                "root", "marv1nk@rs");

        PreparedStatement st1 = connection
                .prepareStatement("SELECT t.id, type, type_name, amount, user_id, date from transactions t inner join transaction_type ty on t.type = ty.id where user_id = 4 AND type = 1;");
        ResultSet rs1 = st1.executeQuery();

        PreparedStatement st2 = connection
                .prepareStatement("SELECT t.id, type, type_name, amount, user_id, date from transactions t inner join transaction_type ty on t.type = ty.id where user_id = 4 AND type = 2;");
        ResultSet rs2 = st2.executeQuery();
        int deposit = 0, pay =0;
        while(rs1.next()){
            deposit += rs1.getInt("amount");
        }
        while(rs2.next()){
            pay += rs2.getInt("amount");
        }

        Label money = new Label("Frw "+(deposit-pay));
        money.setFont(new Font("Inter", Font.BOLD, 30));
        money.setForeground(Color.decode("#557DF8"));
        money.setBounds(20, 10, 200, 50);

        Label summary = new Label("Total Balanced based on your wallet");
        summary.setBounds(30, 10, 200, 20);
        summary.setForeground(Color.black);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = GridBagConstraints.RELATIVE;

        JLabel contLabel = new JLabel("<html><ul><h3>You can proceed with the following:</h3><li>View your transactions</li><li>Deposit your money</li><li>View your debts</li></ul></html>");
        contLabel.setForeground(Color.black);

        contLabel.setFont(new Font("Inter", Font.LAYOUT_LEFT_TO_RIGHT, 17));

        JPanel pn1 = new JPanel();
        pn1.setBounds(20, 20, 200, 100);
        pn1.setPreferredSize(new Dimension(400, 500));
        pn1.setLayout(new GridBagLayout());

        JButton momo = new JButton("Choose Service");
        momo.setBackground(Color.decode("#557DF8"));
//        momo.setBounds(30, 10, 300, 100);
        momo.setBorder(new EmptyBorder(new Insets(10,25,10,25)));
//        momo.setFont(new Font("Inter", Font.SANS_SERIF, 16));
        momo.setForeground(Color.WHITE);
        momo.setFocusPainted(false);

        gbc.gridy = 0;
        pn1.add(money, gbc);
        gbc.gridy = 1;
        pn1.add(summary, gbc);
        gbc.gridy = 2;
        pn1.add(contLabel, gbc);
        gbc.gridy = 3;
        pn1.add(momo, gbc);
        add(pn1);

        JPanel pn2 = new JPanel();
        pn2.setPreferredSize(new Dimension(600, 500));
        pn2.setBorder(new EmptyBorder(50, 10, 20, 20));

        gbc.gridy = 0;
        pn2.add(btn1, gbc);
        pn2.add(btn2, gbc);
        pn2.add(btn3, gbc);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(5,1,25, 15));

        PreparedStatement st = connection
                .prepareStatement("SELECT t.id, type, type_name, amount, user_id, date from transactions t inner join transaction_type ty on t.type = ty.id where user_id = 4;");
        ResultSet rs = st.executeQuery();

        if(!rs.next()){
            JLabel notFoound = new JLabel("No transactin yet!");
            JPanel not = new JPanel();
            not.setLayout(new GridLayout(5,1,25, 15));
            not.add(notFoound);
            gbc.gridy = 1;
            pn2.add(not, gbc);
            pn2.add(not);
        }else{
            createAlertNotification(mainPanel, rs.getString("type_name"), rs.getInt("amount"), rs.getDate("date"));
            while (rs.next()) {
                createAlertNotification(mainPanel, rs.getString("type_name"), rs.getInt("amount"), rs.getDate("date"));
                pn2.add(mainPanel);
            }
        }

        add(pn2);

    }

    private static void createAlertNotification(JPanel panel, String serv, int amou, Date dat) {

        JPanel trans = new JPanel();
        trans.setBackground(Color.white);
        trans.setBorder(new EmptyBorder(10, 5, 10, 5));

        ImageIcon icon = new ImageIcon("C:\\Users\\RCA student\\OneDrive\\Documents\\Projects\\waste_management_front\\Client\\src\\Desktop\\Images\\tick.png");
        Image statIcon = icon.getImage().getScaledInstance(25, 20, 1);
        JLabel statusIcon = new JLabel();
        statusIcon.setIcon(new ImageIcon(statIcon));
        statusIcon.setBorder(new EmptyBorder(0, 15, 0, 20));
        String name = new String();

//        if(serv=="PAYMENT"){
//            name = "Payment"
//        }
        JLabel service = new JLabel(serv);
        service.setFont(new Font("Inter", Font.BOLD, 17));
        service.setSize(200,10);
        service.setBounds(0 , 0, 200, 10);
        service.setBorder(new EmptyBorder(0, 0, 0, 20));
        JLabel amount = new JLabel(amou+" Frw");
        amount.setForeground(Color.lightGray);
        amount.setSize(200, 10);
        amount.setBorder(new EmptyBorder(0, 0, 0, 20));
        JLabel date = new JLabel(String.valueOf(dat));
        date.setBorder(new EmptyBorder(0, 0, 0, 20));

        trans.add(statusIcon);
        trans.add(service);
        trans.add(amount);
        trans.add(date);

        panel.add(trans);

//        deleteBtn.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                if (deleteBtn.getText().equals("delete")){
//                    notificationsPanel.setVisible(false);
//                    panel.remove(notificationsPanel);
//
//                }
//            }
//        });


        //++++++++++++++++++ CHECK BUTTON +++++++++++++

//        checkBtn.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                if (checkBtn.getText().equals("check"))
//                    panel.add(fullNotification);
//                    fullNotification1.setVisible(true);
//
//
//                //logic for the back btn
//                    backbtn.addActionListener(new ActionListener() {
//                        @Override
//                        public void actionPerformed(ActionEvent e) {
//                            if (backbtn.getText().equals("Back"))
//                                //logic to go back to the original page
//                                fullNotification.setVisible(false);
//                        }
//                    });
//            }
//        });
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