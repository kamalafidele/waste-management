package Desktop.Components;

import Desktop.Shared.RoundBtn;

import javax.swing.*;
import java.awt.*;
import java.io.*;

public class Wallet extends JPanel {
    public Wallet(DataOutputStream toServer, DataInputStream fromServer) throws IOException {

        BufferedReader reader = new BufferedReader(new FileReader("loggedIn.txt"));
        String userId = reader.readLine();
        reader.close();
        toServer.writeUTF("wallet/" + userId);
        String response= fromServer.readUTF();

        Font  boldFont  = new Font(Font.SANS_SERIF,  Font.BOLD, 12);
        setVisible(true);
        setBounds(970,40,150,50);
        setBackground(new Color(52,143,235));
        setAlignmentX(CENTER_ALIGNMENT);
        setBorder(new RoundBtn(10));

        JLabel wallet = new JLabel("Wallet: ");
        wallet.setForeground(Color.white);

        JLabel amountLabel = new JLabel(response);
        amountLabel.setForeground(Color.white);
        amountLabel.setFont(boldFont);

        add(wallet);
        add(amountLabel);
        setLayout(new GridLayout(1,2));
    }
    public void initialize(){
    }

}
