package Desktop.Components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Role_Selection extends JPanel {
    private  JPanel leftPanel = new JPanel();
    private  JPanel rightPanel = new JPanel();

    //COMPONENTS ALLOWED HERE
    //Company, District, Admin, Citizen, Confirmer
    private Company company = new Company();

    public Role_Selection(){

        leftPanel.setVisible(true);
        leftPanel.setSize(500,600);
        leftPanel.setBackground(Color.LIGHT_GRAY);

        rightPanel.setVisible(true);
        rightPanel.setSize(500,600);
        rightPanel.setBackground(Color.WHITE);

        add(leftPanel);
        add(rightPanel);


        setLayout(new GridLayout(1,2));
        setVisible(true);
        setSize(1000,700);

        setLeftPanelTexts();
        setRightPanelContent();
    }

    public void setLeftPanelTexts(){
        JLabel label = new JLabel("Welcome back to WSMS");
        leftPanel.add(label);
    }

    public void setRightPanelContent() {
        JButton citizen = new JButton("Citizen");
        JButton admin = new JButton("Admin");
        JButton district = new JButton("District Manager");
        JButton company = new JButton("Company");
        JButton confirmer = new JButton("Confirmer");

        citizen.setSize(100,50);
        admin.setSize(100,50);
        district.setSize(100,50);
        company.setSize(100,50);
        confirmer.setSize(100,50);

        //Add action listeners to buttons
        citizen.addActionListener(new ButtonEventHandler());
        admin.addActionListener(new ButtonEventHandler());
        district.addActionListener(new ButtonEventHandler());
        company.addActionListener(new ButtonEventHandler());
        confirmer.addActionListener(new ButtonEventHandler());

        rightPanel.add(citizen);
        rightPanel.add(admin);
        rightPanel.add(district);
        rightPanel.add(company);
        rightPanel.add(confirmer);
    }

    class ButtonEventHandler implements ActionListener {
        private String actionCommand;
        @Override
        public void actionPerformed(ActionEvent e){
            actionCommand = e.getActionCommand();

            switch (actionCommand){
                case "Citizen":
                    //Here you will initialize citizen component;
                    break;
                case "Admin":
                    //Here you will initialize Admin component
                    break;
                case "Company":
                    company.initialize();
                    break;
                case "District Manager":
                    //Here you will initialize District component
                    break;
                case "Confirmer":
                    //Here you will initialize Confirmer component
                    break;
                default:
                    System.out.println("Impossible");
                    break;
            }
        }
    }
}
