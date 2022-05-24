package Desktop.Screens.Citizen;

import Desktop.Shared.RoundBtn;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class Debts extends JPanel {
    JPanel panel = new JPanel();
    public Debts() {
        setVisible(false);
        setBounds(200, 0, 1166, 768);
        setBackground(Color.WHITE);
        setBorder(new EmptyBorder(new Insets(150, 30, 20, 30)));
        setDebtContainer();
    }
    public void setDebtContainer(){
        panel.setBackground(new Color(234, 237, 243));
//        panel.setBackground(Color.red);
//        panel.setForeground(Color.white);
        panel.setPreferredSize(new Dimension(300,300));
        panel.setLayout(new GridBagLayout());
        panel.setBorder(new RoundBtn(10));

        Label debtValue = new Label("Debt: 1000");
        debtValue.setFont(new Font("Montserrat", Font.BOLD, 18));
        panel.add(debtValue);
        this.add(panel);
    }
    public void setPaymentMethod(){
        JButton payButton = new JButton("Recharge Balance");
        add(payButton);
    }
}
