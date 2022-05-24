package Desktop.Components;

import DataHandlers.CompanyHandler;
import DataHandlers.DistrictHandler;
import DataHandlers.UserHandler;
import Desktop.EventHandlers.PlaceHolderHandler;
import Desktop.Shared.RoundBtn;
import org.codehaus.jackson.map.ObjectMapper;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class Registration extends JPanel {
     private boolean isDistrict;
     private boolean isCompany;
     private boolean isUser;
     ObjectMapper mapper = new ObjectMapper();

     DataOutputStream toServer;
     DataInputStream fromServer;

     JTextField name = new JTextField("Name");
     JTextField email = new JTextField("Email");
     JTextField phone = new JTextField("Phone");
     JTextField tin = new JTextField("TIN");
     JLabel responseLabel = new JLabel("");
     JLabel titleLabel = new JLabel();

     JPanel inputsPanel = new JPanel();
     JPanel titlePanel = new JPanel();
     JPanel buttonsPanel = new JPanel();

     JButton addBtn = new JButton("Add");
     Color dodgerBlue = new Color(52,143,235);

     CompanyHandler companyHandler = null;
     DistrictHandler districtHandler = null;

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
          responseLabel.setForeground(Color.GREEN);

          addBtn.setBackground(dodgerBlue);
          addBtn.setSize(200,100);
          addBtn.setBounds(20,20,200,100);
          addBtn.addActionListener(new SubmitHandler());

          inputsPanel.setBackground(Color.WHITE);
          titlePanel.setBackground(Color.WHITE);
          buttonsPanel.setBackground(Color.WHITE);
          titlePanel.setLayout(new GridLayout(2,1));
          titlePanel.setBorder(new EmptyBorder(new Insets(0,50,0,10)));

          inputsPanel.setBorder(new EmptyBorder(new Insets(10,10,10,50)));
          buttonsPanel.setBorder(new EmptyBorder(new Insets(40,10,40,0)));
     }

     public void setTitlePanelContent () {
          titleLabel.setFont(new Font("Inter", Font.PLAIN, 20));
          titleLabel.setForeground(dodgerBlue);
          titlePanel.add(titleLabel);
          titlePanel.add(responseLabel);
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
               titleLabel.setText("ADD DISTRICT");
          }
          else if (isCompany) {
               inputsPanel.add(name);
               inputsPanel.add(email);
               inputsPanel.add(tin);
               titleLabel.setText("ADD COMPANY");

          }else if (isUser) {
               inputsPanel.add(name);
               inputsPanel.add(email);
               inputsPanel.add(phone);
               titleLabel.setText("ADD USER");
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

     public void setStreams(DataOutputStream toServer, DataInputStream fromServer) {
           this.toServer = toServer;
           this.fromServer = fromServer;
     }

     public void setDistrictHandler(DistrictHandler districtHandler) {
          this.districtHandler = districtHandler;
     }

     public void setCompanyHandler(CompanyHandler companyHandler) {
          this.companyHandler =  companyHandler;
     }

     class SubmitHandler implements ActionListener {
          @Override
          public void actionPerformed(ActionEvent e) {
               if(isCompany) {
                    CompanyHandler companyHandler = new CompanyHandler();
                    if(!email.getText().equals("Email") && !name.getText().equals("Name") && !tin.getText().equals("TIN")){
                         companyHandler.setEmail(email.getText());
                         companyHandler.setName(name.getText());
                         companyHandler.setTin(Long.valueOf(tin.getText()));
                         try {
                              sendRequest("registration/register_company/"+mapper.writeValueAsString(companyHandler) + "/" + districtHandler.getId());
                              String response = fromServer.readUTF();
                              setInputsToDefault();
                              responseLabel.setText(response);
                              System.out.println(response);

                         }catch (Exception exception) {}
                    }
               }
               else if (isDistrict) {
                    DistrictHandler districtHandler = new DistrictHandler();
                    if(!name.getText().equals("Name") && !email.getText().equals("Email")){
                         districtHandler.setEmail(email.getText());
                         districtHandler.setName(name.getText());

                         try {
                              sendRequest("registration/register_district/"+mapper.writeValueAsString(districtHandler));
                              String response = fromServer.readUTF();
                              System.out.println(response);
                              setInputsToDefault();
                              responseLabel.setText(response);
                         } catch (Exception exception) {}
                    }

               } else if(isUser) {
                    UserHandler userHandler = new UserHandler();
                    if(!name.getText().equals("Name") && !email.getText().equals("Email") && !phone.getText().equals("Phone")) {
                       userHandler.setEmail(email.getText());
                       userHandler.setName(name.getText());
                       userHandler.setPhone(phone.getText());

                       if(companyHandler != null){
                            userHandler.setRole(Long.valueOf(4));
                            userHandler.setWork_at(companyHandler.getId());
                            userHandler.setPassword(companyHandler.getName() + userHandler.getName());
                       }
                       else if(districtHandler != null) {
                            userHandler.setRole(Long.valueOf(5));
                            userHandler.setWork_at(districtHandler.getId());
                            userHandler.setPassword(districtHandler.getName()+userHandler.getName());
                       }

                       try {
                            sendRequest("registration/register_user/"+mapper.writeValueAsString(userHandler));
                            String response = fromServer.readUTF();
                            setInputsToDefault();
                            responseLabel.setText(response);
                            System.out.println(response);
                       } catch (Exception exception){}
                    }
               }
          }
     }

     public void sendRequest( String request ){
          try{
               toServer.writeUTF( request );
          }catch ( IOException exception ){}
     }

     public void setInputsToDefault(){
          name.setText("Name");
          email.setText("Email");
          tin.setText("TIN");
          phone.setText("Phone");
     }
}
