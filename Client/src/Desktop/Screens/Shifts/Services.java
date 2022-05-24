
package Desktop.Screens.Shifts;

import DataHandlers.CompanyHandler;
import DataHandlers.ServiceConfirmationHandler;
import Desktop.App;
import Desktop.Screens.RoundBtn;
import org.codehaus.jackson.map.ObjectMapper;


import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.EventHandler;
import java.io.DataInputStream;
import java.io.DataOutputStream;

public class Services  extends JPanel{

    ObjectMapper mapper = new ObjectMapper();

    DataOutputStream toServer;
    DataInputStream fromServer;

    JButton wastebtn = new JButton("Waste Collection");
    JButton securitybtn = new JButton("Security");

    Color dodgerBlue = new Color(52,143,235);
    JPanel titlePanel = new JPanel();
    JPanel dataPanel = new JPanel();

    ServiceConfirmationHandler serviceHandler = null;

    public Services(){

        setVisible(false);
        setSize(300,400);

        GridLayout layout = new GridLayout(2,1);
        layout.setVgap(0);
        layout.setHgap(0);
        setLayout(layout);
        setBounds(200,10,1180,500);
        add(titlePanel);
        add(dataPanel);
        setTitlePanel();
        setDataPanel();
    }

    public void setTitlePanel() {
        JLabel titleLabel = new JLabel("Select service");
        titleLabel.setFont(new Font("Inter",Font.PLAIN,20));
        titlePanel.add(titleLabel);
    }
    public void setDataPanel(){
        dataPanel.add(wastebtn);
        dataPanel.setBackground(Color.WHITE);
        dataPanel.add(securitybtn);
        GridLayout layout = new GridLayout(2,1);
        layout.setHgap(10);
        layout.setVgap(10);
        dataPanel.setLayout(layout);
        wastebtn.setBackground(dodgerBlue);
        wastebtn.setSize(200,100);
        wastebtn.setBorder(new RoundBtn(10));
        securitybtn.setBorder(new RoundBtn(10));
        wastebtn.setBounds(20,20,200,100);
        securitybtn.setBackground(dodgerBlue);
        securitybtn.setSize(200,100);
        securitybtn.setBounds(20,20,200,100);
        wastebtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JPanel newPanel = new JPanel();
                newPanel.setVisible(true);
                dataPanel.setVisible(false);
                JLabel titlelabel = new JLabel("Select company");
                titlePanel.setVisible(false);
                JPanel companyTitlePanel = new JPanel();
                companyTitlePanel.setVisible(true);
                add(companyTitlePanel);
                titlelabel.setFont(new Font("Inter",Font.PLAIN,20));
                companyTitlePanel.add(titlelabel);
                JPanel companyForm = new JPanel();
                ServiceConfirmationHandler service = new ServiceConfirmationHandler();
            }
        });


        securitybtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dataPanel.setVisible(false);
                titlePanel.setVisible(false);

                JPanel smallpanel = new JPanel();
                JPanel title = new JPanel();
                JLabel label = new JLabel("Not available");
                label.setFont(new Font("Inter",Font.PLAIN,20));
                title.add(label);
                smallpanel.setBounds(200,10,1180,500);
                smallpanel.setBackground(Color.WHITE);
                JPanel mainPanel = new JPanel();
                mainPanel.setBounds(200,10,1180,500);
                mainPanel.setLayout(new GridLayout(2,1));
                mainPanel.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
                mainPanel.add(title);
                mainPanel.add(smallpanel);
//                GridLayout layout = new GridLayout(2,1);
//                layout.setVgap(0);
//                layout.setHgap(0);
//                smallpanel.setLayout(layout);
                smallpanel.setBorder(new EmptyBorder(new Insets(0,20,0,0)));
                smallpanel.setVisible(true);
                JLabel textlabel = new JLabel("Feature not available yet");
                textlabel.setFont(new Font("Inter",Font.PLAIN,20));
                smallpanel.add(textlabel);
                textlabel.setVisible(true);
//                add(title);
//                add(smallpanel);
                add(mainPanel);

            }
        });


    }
}
