package Desktop.Components;

import Desktop.Shared.RoundBtn;

import javax.swing.*;
import java.awt.*;
import java.io.*;

public class Wallet extends JPanel {
    static public String response = "";
    BufferedReader reader = new BufferedReader(new FileReader("loggedIn.txt"));
    String userId = reader.readLine();
    static JLabel amountLabel = new JLabel(response);
    public Wallet(DataOutputStream toServer, DataInputStream fromServer) throws IOException {
        reader.close();
        Font  boldFont  = new Font(Font.SANS_SERIF,  Font.BOLD, 12);
        setVisible(true);
        setBounds(970,40,150,50);
        setBackground(new Color(52,143,235));
        setAlignmentX(CENTER_ALIGNMENT);
        setBorder(new RoundBtn(10));

        JLabel wallet = new JLabel("Wallet: ");
        wallet.setForeground(Color.white);


        amountLabel.setForeground(Color.white);
        amountLabel.setFont(boldFont);

        add(wallet);
        add(amountLabel);
        setLayout(new GridLayout(1,2));

        new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){
                    try {
                        toServer.writeUTF("wallet/" + userId);
                        response = fromServer.readUTF();
                        amountLabel.setText(response);
                    }
                    catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
    public void initialize(){
    }


}
