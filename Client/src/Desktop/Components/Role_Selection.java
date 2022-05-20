package Desktop.Components;

import Desktop.Shared.RoundBtn;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.font.TextAttribute;

public class Role_Selection extends JPanel {
    //SIDEBARS
    private  JPanel leftPanel = new JPanel();
    private  JPanel rightPanel = new JPanel();

    //BUTTONS
    JButton citizen = new JButton("Citizen");
    JButton admin = new JButton("Admin");
    JButton district = new JButton("District Manager");
    JButton company = new JButton("Company");
    JButton confirmer = new JButton("Confirmer");

    //CUSTOM COLORS
    Color dodgerBlue = new Color(52,143,235);
    Color lightGray = new Color(225, 227, 225);
    public static final String BLUE = "\033[0;34m";
    public static final String RESET = "\033[0m";

    //STANDALONE COMPONENTS
    private Company companyComponent = new Company();

    public Role_Selection() {

        leftPanel.setVisible(true);
        leftPanel.setSize(500,600);
        leftPanel.setBackground(lightGray);
        BoxLayout boxLayout = new BoxLayout(leftPanel, BoxLayout.Y_AXIS);
        leftPanel.setLayout(boxLayout);
        leftPanel.setBorder(new EmptyBorder(new Insets(150, 200, 150, 200)));

        rightPanel.setVisible(true);
        rightPanel.setSize(500,600);
        rightPanel.setBackground(Color.WHITE);
        GridLayout layout = new GridLayout(5,1);
        layout.setVgap(10);
        rightPanel.setLayout(layout);
        rightPanel.setBorder(new EmptyBorder(100, 50, 100, 50));

        add(leftPanel);
        add(rightPanel);


        setLayout(new GridLayout(1,2));
        setVisible(true);
        setSize(1000,700);

        setLeftPanelTexts();
        setRightPanelContent();
    }

    public void setLeftPanelTexts() {
        JLabel label = new JLabel("Welcome back to WSMS");
        JLabel label1 = new JLabel("The best online waste and security");
        JLabel label2 = new JLabel("management system in Rwanda");
        JLabel label3 = new JLabel("Choose role");

        label.setBorder(new EmptyBorder(new Insets(30,0,30,0)));
        label.setFont(new Font(label.getFont().getName(), label.getFont().getStyle(), 20));
        label3.setBorder(new EmptyBorder(new Insets(30,20,30,20)));
        label3.setFont(new Font(label.getFont().getName(), label.getFont().getStyle(), 22));
        label3.setForeground(dodgerBlue);

        leftPanel.add(label);
        leftPanel.add(label1);
        leftPanel.add(label2);
        leftPanel.add(label3);
    }

    public void setRightPanelContent() {

        citizen.setSize(60,40);
        citizen.setBorder(new RoundBtn(15));
        citizen.setBackground(lightGray);
        admin.setSize(100,50);
        admin.setBackground(lightGray);
        admin.setBorder(new RoundBtn(15));
        district.setSize(100,50);
        district.setBorder(new RoundBtn(15));
        district.setBackground(lightGray);
        company.setSize(100,50);
        company.setBorder(new RoundBtn(15));
        company.setBackground(lightGray);
        confirmer.setSize(100,50);
        confirmer.setBorder(new RoundBtn(15));
        confirmer.setBackground(lightGray);


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

            switch (actionCommand) {
                case "Citizen":
                    citizen.setBackground(dodgerBlue);
                    //Here you will initialize citizen component;
                    break;
                case "Admin":
                    admin.setBackground(dodgerBlue);
                    //Here you will initialize Admin component
                    break;
                case "Company":
                    company.setBackground(dodgerBlue);
                    companyComponent.initialize();
                    break;
                case "District Manager":
                    district.setBackground(dodgerBlue);
                    //Here you will initialize District component
                    break;
                case "Confirmer":
                    confirmer.setBackground(dodgerBlue);
                    //Here you will initialize Confirmer component
                    break;
                default:
                    System.out.println("Impossible");
                    break;
            }
        }
    }
}
