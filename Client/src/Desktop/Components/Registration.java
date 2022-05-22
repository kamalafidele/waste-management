package Desktop.Components;

import Desktop.Shared.RoundBtn;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;

public class Registration extends JPanel {
     private boolean isDistrict;
     private boolean isCompany;
     private boolean isUser;

     JTextField name = new JTextField();
     JTextField email = new JTextField();
     JTextField phone = new JTextField();
     JTextField tin = new JTextField();

     private DataOutputStream toServer;
     private DataInputStream fromServer;

     public Registration(boolean isDistrict, boolean isCompany, boolean isUser) {
          this.isDistrict = isDistrict;
          this.isCompany = isCompany;
          this.isUser = isUser;

          setComponents();
     }

     public void initialize() {
          setVisible(true);
          if(isDistrict) {
               setLayout(new GridLayout(2,1));
          }
          else if (isCompany) {
               setLayout(new GridLayout(3,1));
          }else if (isUser) {
               setLayout(new GridLayout(3,1));
          }
          setBorder(new EmptyBorder(new Insets(30,20,30,20)));
     }

     public void setStreams(DataInputStream fromServer, DataOutputStream toServer) {
          this.fromServer = fromServer;
          this.toServer = toServer;
     }

     public void setComponents() {
          add(name);
          add(email);
          add(phone);
          add(tin);
     }

     public void styleComponents(){
          name.setBorder(new RoundBtn(10));
          email.setBorder(new RoundBtn(10));
          phone.setBorder(new RoundBtn(10));
          tin.setBorder(new RoundBtn(10));
     }
}
