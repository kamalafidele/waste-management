package Desktop.Components;

import Desktop.Shared.RoundBtn;

import javax.swing.*;
import java.awt.*;

public class Wallet extends JPanel {
    public Wallet(int amount){
        Font  boldFont  = new Font(Font.SANS_SERIF,  Font.BOLD, 12);
        setVisible(true);
        setBounds(970,40,150,50);
        setBackground(new Color(52,143,235));
        setAlignmentX(CENTER_ALIGNMENT);
        setBorder(new RoundBtn(10));

        JLabel wallet = new JLabel("Wallet: ");
        wallet.setForeground(Color.white);

        JLabel amountLabel = new JLabel(String.valueOf(amount));
        amountLabel.setForeground(Color.white);
        amountLabel.setFont(boldFont);

        add(wallet);
        add(amountLabel);
        setLayout(new GridLayout(1,2));
    }
    public void initialize(){
    }

}
