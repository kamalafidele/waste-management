package Desktop.Components;

import javax.swing.*;
import java.awt.*;

public class Role_Selection extends JPanel {
    private  JPanel leftPanel = new JPanel();
    private  JPanel rightPanel = new JPanel();

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
        //leftPanel.setLayout(new GridLayout(1,1));
        leftPanel.add(label);
    }

    public void setRightPanelContent(){
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

        rightPanel.add(citizen);
        rightPanel.add(admin);
        rightPanel.add(district);
        rightPanel.add(company);
        rightPanel.add(confirmer);
    }
}
