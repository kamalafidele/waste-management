package Desktop.Components;

import Desktop.EventHandlers.PlaceHolderHandler;
import Desktop.Shared.RoundBtn;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class Registration extends JPanel {
     private boolean isDistrict;
     private boolean isCompany;
     private boolean isUser;

     JTextField name = new JTextField("Name");
     JTextField email = new JTextField("Email");
     JTextField phone = new JTextField("Phone");
     JTextField tin = new JTextField("TIN");
     JLabel titleLabel = new JLabel();

     JPanel inputsPanel = new JPanel();
     JPanel titlePanel = new JPanel();
     JPanel buttonsPanel = new JPanel();

     JButton addBtn = new JButton("Add");

     Color dodgerBlue = new Color(52,143,235);

     public Registration(boolean isDistrict, boolean isCompany, boolean isUser) {
          this.isDistrict = isDistrict;
          this.isCompany = isCompany;
          this.isUser = isUser;

          setVisible(false);
          setSize(300,400);
          GridLayout layout = new GridLayout(3,1);
          layout.setVgap(0);
          layout.setHgap(0);

          setLayout(layout);
          setBounds(200,10,1180,500);

          add(titlePanel);
          add(inputsPanel);
          add(buttonsPanel);

          setTitlePanelContent();
          setInputsPanelContent();
          setButtonsPanelContent();

          styleComponents();
     }

     public void styleComponents() {
          name.setBorder(new RoundBtn(10));
          email.setBorder(new RoundBtn(10));
          phone.setBorder(new RoundBtn(10));
          tin.setBorder(new RoundBtn(10));
          addBtn.setBorder(new RoundBtn(10));

          addBtn.setBackground(dodgerBlue);
          addBtn.setSize(200,100);
          addBtn.setBounds(20,20,200,100);

          inputsPanel.setBackground(Color.WHITE);
          titlePanel.setBackground(Color.WHITE);
          buttonsPanel.setBackground(Color.WHITE);

          inputsPanel.setBorder(new EmptyBorder(new Insets(10,10,10,50)));
          buttonsPanel.setBorder(new EmptyBorder(new Insets(40,10,40,0)));
     }

     public void setTitlePanelContent () {
          titleLabel.setFont(new Font("Inter", Font.PLAIN, 20));
          titlePanel.add(titleLabel);
     }

     public void setButtonsPanelContent() {
           buttonsPanel.add(addBtn);

           buttonsPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
           buttonsPanel.setSize(1180,400);
     }

     public void setInputsPanelContent(){
          if(isDistrict) {
               inputsPanel.add(name);
               inputsPanel.add(email);
               titleLabel.setText("Add District");
          }
          else if (isCompany) {
               inputsPanel.add(name);
               inputsPanel.add(email);
               inputsPanel.add(tin);
               titleLabel.setText("Add Company");

          }else if (isUser) {
               inputsPanel.add(name);
               inputsPanel.add(email);
               inputsPanel.add(phone);
               inputsPanel.add(tin);
               titleLabel.setText("Add User");
          }

          GridLayout layout = new GridLayout(2,2);
          layout.setHgap(10);
          layout.setVgap(10);
          inputsPanel.setLayout(layout);

          name.addFocusListener(new PlaceHolderHandler(name,"Name"));
          email.addFocusListener(new PlaceHolderHandler(email,"Email"));
          phone.addFocusListener(new PlaceHolderHandler(phone,"Phone"));
          tin.addFocusListener(new PlaceHolderHandler(tin,"TIN"));
     }
}
