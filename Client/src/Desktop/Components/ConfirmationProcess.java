package Desktop.Components;

import Desktop.Shared.RoundBtn;
//import Desktop.Shared.RoundBtn;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ConfirmationProcess extends JPanel{

//    DataOutputStream toServer;
//    DataInputStream fromServer;
//    ObjectMapper mapper =new ObjectMapper();

    JPanel confirmationLevelPanel=new JPanel();
    JPanel confirmationLevelPanel2=new JPanel();
    JPanel inputsPanel =new JPanel();

    JButton villageLevel =new JButton("Village Level");
    JButton userLevel =new JButton("Individual Level");

    JTextField input =new JTextField();
    JTextField individualInput =new JTextField();

    JLabel label =new JLabel("Confirm on Village Level");
    JLabel individualLabel =new JLabel("Confirm on Individual Level");
    JButton submit =new JButton("Confirm");
    JButton individualSubmit =new JButton("Confirm");
    Color lightGray = new Color(199, 216, 227);
    public ConfirmationProcess() {
        setVisible(true);
        setBounds(200,0,1166,768);
        setBackground(Color.WHITE);
        GridLayout layout = new GridLayout(3,1);
        layout.setVgap(0);
        layout.setHgap(1);

        setLayout(layout);
        setBorder(new EmptyBorder(new Insets(20,30,20,30)));
        styleComponents();
        handleEvents();
    }

    public void styleComponents() {

        villageLevel.setBackground(Color.decode("#557DF8"));
        villageLevel.setBorder(new EmptyBorder(new Insets(12,40,12,40)));
        villageLevel.setFont(new Font("Inter", Font.PLAIN, 18));
        villageLevel.setForeground(Color.WHITE);
        villageLevel.setFocusPainted(false);

        userLevel.setBackground(Color.decode("#557DF8"));
        userLevel.setBorder(new EmptyBorder(new Insets(12,40,12,40)));
        userLevel.setFont(new Font("Inter", Font.PLAIN, 18));
        userLevel.setForeground(Color.WHITE);
        userLevel.setFocusPainted(false);

        input.setBorder(new RoundBtn(10));
        individualInput.setBorder(new RoundBtn(15));
        label.setFont(new Font("Inter", Font.PLAIN, 18));
        individualLabel.setFont(new Font("Inter",Font.PLAIN,17));

        submit.setBackground(Color.decode("#000"));
        submit.setForeground(Color.WHITE);
        submit.setBorder(new EmptyBorder(new Insets(15,40,12,40)));
        submit.setFocusPainted(false);
        submit.setFont(new Font("Inter", Font.PLAIN, 18));
        submit.setSize(200,200);

        individualSubmit.setBackground(Color.decode("#000"));
        individualSubmit.setForeground(Color.WHITE);
        individualSubmit.setBorder(new EmptyBorder(new Insets(15,40,12,40)));
        individualSubmit.setFocusPainted(false);
        individualSubmit.setFont(new Font("Inter", Font.PLAIN, 18));
        individualSubmit.setSize(200,200);

        confirmationLevelPanel.setBackground(lightGray);
        confirmationLevelPanel.setBounds(200,20,166,768);
        confirmationLevelPanel.setVisible(false);
        confirmationLevelPanel.add(label);
        confirmationLevelPanel.add(input);
        confirmationLevelPanel.add(submit);
        confirmationLevelPanel.setLayout(new GridLayout(3,1));
        confirmationLevelPanel.setBorder(new EmptyBorder(new Insets(20,30,20,30)));


        confirmationLevelPanel2.setBackground(lightGray);
        confirmationLevelPanel2.setBounds(200,2,166,768);
        confirmationLevelPanel2.setVisible(false);
        confirmationLevelPanel2.add(individualLabel);
        confirmationLevelPanel2.add(individualInput);
        confirmationLevelPanel2.add(individualSubmit);
        confirmationLevelPanel2.setLayout(new GridLayout(3,1));
        confirmationLevelPanel2.setBorder(new EmptyBorder(new Insets(2,30,20,30)));


//        userLevel.setActionCommand("individualLevel");
//        villageLevel.setActionCommand("villageLevel");

//        villageLevel.addActionListener(this);
//        villageLevel.addActionListener(this);

        inputsPanel.add(villageLevel);
        inputsPanel.add(userLevel);

        add(inputsPanel);
        add(confirmationLevelPanel);
        add(confirmationLevelPanel2);

    }


    public void handleEvents() {
        villageLevel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            confirmationLevelPanel2.setVisible(false);
            villageLevel.setBackground(Color.decode("#0000"));
            userLevel.setBackground(Color.decode("#557DF8"));
            confirmationLevelPanel.setVisible(true);
            }
        });
        userLevel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            confirmationLevelPanel.setVisible(false);
            userLevel.setBackground(Color.decode("#0000"));
            villageLevel.setBackground(Color.decode("#557DF8"));
            confirmationLevelPanel2.setVisible(true);
            }
        });
    }
}
