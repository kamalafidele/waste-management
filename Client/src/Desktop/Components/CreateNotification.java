package Desktop.Components;
import DataHandlers.NotificationHandler;
import Desktop.EventHandlers.PlaceHolderHandler;
import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;
import net.miginfocom.swing.MigLayout;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Properties;

/*
 * @authors: Fiat Bruno, Ineza Naillah
 *
 */

public class CreateNotification extends JFrame {
    DataOutputStream toServer;
    DataInputStream fromServer;
    Color dodgerBlue = new Color(52,143,235);
    JPanel mainPanel;
    JPanel mainPanel2;
    JPanel insidePanel;
    JPanel emailPanel;
    JLabel notification;
    JLabel mail;
    JLabel description;
    JLabel notificationName;
    JLabel mailUserName;
    JLabel from;
    JLabel password;
    JLabel cc;
    JLabel bcc;
    JLabel organization;
    JLabel subject;

    JLabel processName;
    JLabel status;
    JLabel signature;
    JLabel assignedGroup;
    JLabel renotify;
    JLabel date;
    JLabel content;
    JLabel message;
    JLabel footer;
    JLabel body;
    JTextField notificationTextField;
    JTextField mailTextField;
    JTextField fromTextField;
    JPasswordField passwordTextField;
    JTextField ccTextField;
    JTextField bccTextField;
    JTextField orgTextField;
    JTextField subjectTextField;

    JTextField processTextField;
    JComboBox statusChoice;
    JComboBox signatureChoice;
    JComboBox groupChoice;
    JComboBox periodChoice;
    JTextArea textArea;
    JTextArea footerTextArea;
    JTextArea bodyTextArea;
    JPanel buttonPanel;
    JPanel buttonPanel2;
    JButton saveButton;
    JButton sendButton;
    JButton closeButton;
    JButton switchButton;
    DatePicker datePicker;
    public CreateNotification(int version){
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setTitle("Create Notifications");
        this.setSize(800, 800);
        this.setResizable(false);

        mainPanel = new JPanel(new MigLayout("center, wrap, fill, insets 20", "20:push[]20:push", "20:push[]20[]20[]20:push"));
        mainPanel2 = new JPanel(new MigLayout("center, wrap, fill, insets 20", "20:push[]20:push", "20:push[]20[]20[]20:push"));
        notification = new JLabel("Create Notifications");
        notification.setFont(new Font("Inter", Font.BOLD, 20));
        notification.setForeground(dodgerBlue);
        insidePanel = new JPanel(new MigLayout("wrap", "[]10:push[]10:push[]10:push[]", "[]10[]"));

        description = new JLabel("Description");
        description.setFont(new Font("Inter", Font.BOLD, 15));
        description.setForeground(dodgerBlue);
        notificationName = new JLabel("Notification Name");
        notificationTextField = new JTextField();
        notificationTextField.addFocusListener(new PlaceHolderHandler(notificationTextField, "Notification Name"));
        processName = new JLabel("Process Name");
        processTextField = new JTextField();
        processTextField.addFocusListener(new PlaceHolderHandler(processTextField, "Process Name"));
        status = new JLabel("Status");
        String[] statusChoices = {
                "Active",
                "Inactive"
        };
        statusChoice = new JComboBox(statusChoices);
        assignedGroup = new JLabel("Assigned Group");
        String[] groupChoices = {
                "Citizens",
                "Companies",
                "Districts"
        };
        groupChoice = new JComboBox(groupChoices);
        date = new JLabel("Notify On");

        DatePickerSettings dateSettings = new DatePickerSettings();
        dateSettings.setFirstDayOfWeek(DayOfWeek.MONDAY);
        datePicker = new DatePicker(dateSettings);

        renotify = new JLabel("Renotify");
        String[] periodChoices = {
                "Every Week",
                "Every Month",
                "Every Year",
                "Every Five Years"
        };
        periodChoice = new JComboBox(periodChoices);
        content = new JLabel("Content");
        content.setFont(new Font("Inter", Font.BOLD, 15));
        content.setForeground(dodgerBlue);
        message = new JLabel("Message");
        textArea = new JTextArea("Write Message");

        insidePanel.add(description, "span");
        insidePanel.add(notificationName);
        insidePanel.add(notificationTextField, "width 200");
        insidePanel.add(assignedGroup);
        insidePanel.add(groupChoice, "width 200");
        insidePanel.add(processName);
        insidePanel.add(processTextField, "width 200");
        insidePanel.add(date);
        insidePanel.add(datePicker);
        insidePanel.add(status);
        insidePanel.add(statusChoice, "width 200");
        insidePanel.add(renotify);
        insidePanel.add(periodChoice, "width 200");
        insidePanel.add(content, "span");
        insidePanel.add(message);
        insidePanel.add(textArea, "span, grow, height 200");

        buttonPanel = new JPanel();

        saveButton = new JButton("Save");
        saveButton.addActionListener(new saveNotification());
        closeButton = new JButton("Switch Back");
        closeButton.addActionListener(new switchListener());


        emailPanel = new JPanel(new MigLayout("wrap", "[]10:push[]10:push[]10:push[]", "[]10[]"));
        mail = new JLabel("Create Email Notifications");
        mail.setFont(new Font("Inter", Font.BOLD, 20));
        mail.setForeground(dodgerBlue);
        mailUserName = new JLabel("Email User Name");
        mailTextField = new JTextField();
        mailTextField.addFocusListener(new PlaceHolderHandler(notificationTextField, "Notification Name"));
        from = new JLabel("From");
        fromTextField = new JTextField();
        password = new JLabel("Password");
        passwordTextField = new JPasswordField();
        cc = new JLabel("CC");
        ccTextField = new JTextField();
        bcc = new JLabel("BCC");
        bccTextField = new JTextField();
        organization = new JLabel("Organization");
        orgTextField = new JTextField();
        subject = new JLabel("Subject");
        subjectTextField = new JTextField();
        signature = new JLabel("Use Digital Signature");
        String[] signatureChoices = {
                "YES",
                "NO"
        };
        signatureChoice = new JComboBox(signatureChoices);
        body = new JLabel("Body");
        bodyTextArea = new JTextArea("Write Your Message");
        footer = new JLabel("Footer");
        footerTextArea = new JTextArea("Write Your Footer");

        buttonPanel2 = new JPanel();

        sendButton = new JButton("Send");
        sendButton.addActionListener(new saveNotification());

        emailPanel.add(mail);
        emailPanel.add(mailUserName);
        emailPanel.add(mailTextField, "width 200");
        emailPanel.add(signature);
        emailPanel.add(signatureChoice, "width 200");
        emailPanel.add(from);
        emailPanel.add(fromTextField, "width 200");
        emailPanel.add(organization);
        emailPanel.add(orgTextField, "width 200");
        emailPanel.add(password);
        emailPanel.add(passwordTextField, "width 200");
        emailPanel.add(subject);
        emailPanel.add(subjectTextField, "width 200");
        emailPanel.add(cc);
        emailPanel.add(ccTextField, "width 200");
        emailPanel.add(bcc);
        emailPanel.add(bccTextField, "width 200");
        emailPanel.add(body);
        emailPanel.add(bodyTextArea, "span, grow, height 200");
        emailPanel.add(footer);
        emailPanel.add(footerTextArea, "span, grow, height 100");



        buttonPanel.add(saveButton);

        buttonPanel2.add(sendButton);

        buttonPanel.add(closeButton);
        buttonPanel2.add(closeButton);

        switchButton = new JButton("Switch");
        switchButton.setFocusable(false);
        switchButton.addActionListener(new switchListener());

        mainPanel2.add(switchButton);
        mainPanel.add(switchButton);

        mainPanel.add(notification);
        mainPanel.add(insidePanel);

        mainPanel2.add(mail);
        mainPanel2.add(emailPanel);

        mainPanel.add(buttonPanel);
        mainPanel2.add(buttonPanel2);
        if (version == 1){
            this.add(mainPanel);
        }
        else {
            this.add(mainPanel2);
        }
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
    public void setStreams(DataOutputStream toServer, DataInputStream fromServer) {
        this.toServer = toServer;
        this.fromServer = fromServer;
        System.out.println("SetStreams in CreateNotification " + toServer);
        System.out.println("SetStreams in CreateNotification " + fromServer);
    }
    class saveNotification implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == saveButton){
                NotificationHandler notificationHandler = new NotificationHandler();
                if(!notificationTextField.getText().equals("Notification Name") && !processTextField.getText().equals("Process Name") && !textArea.getText().equals("Write Message")){
                    notificationHandler.setContent(textArea.getText());
                    notificationHandler.setTitle(processTextField.getText());
                    notificationHandler.setType(notificationTextField.getText());
                    notificationHandler.setViewStatus("unRead");
                    LocalDate localDate = datePicker.getDate();
                    Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
                    notificationHandler.setSentDate(date);
                    notificationHandler.setReceiver(1);
                    try {
                        sendRequest("notification/create/" + notificationHandler.getReceiver() + "/" +
                                notificationHandler.getType());
                        String response = fromServer.readUTF();
                        System.out.println(response);
                    }catch (Exception exception) {
                        exception.printStackTrace();
                    }
                }
            }
            else if (e.getSource() == sendButton){
                try {
                    sendEmailNotification();

                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }

        }
    }
    class switchListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == switchButton){
                try {
                    new CreateNotification(2);
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
            else if(e.getSource() == closeButton){
                try {
                    new CreateNotification(1);
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }

        }
    }
    public void sendRequest( String request ){
        try{
            toServer.writeUTF( request );
            System.out.println("SendRequestFun " + toServer);
            System.out.println("SendRequestFun " + fromServer);
        }catch ( IOException exception ){
            exception.printStackTrace();
        }
    }
    public void sendEmailNotification(){
        String recipient = mailTextField.getText();
        String sender = fromTextField.getText();
        Properties properties = new Properties();
        properties.setProperty("mail.transport.protocol", "smtp");
        properties.setProperty("mail.host", "smtp.gmail.com");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.port", "465");
        properties.put("mail.debug", "true");
        properties.put("mail.smtp.socketFactory.port", "465");
        properties.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
        properties.put("mail.smtp.socketFactory.fallback", "false");
        Authenticator authenticator = new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromTextField.getText(), new String(passwordTextField.getPassword()));
            }
        };
        Session session = Session.getDefaultInstance(properties, authenticator);
        try
        {
            Transport transport = session.getTransport();
            MimeMessage message = new MimeMessage(session);

            message.setFrom(new InternetAddress(sender));

            message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));

            message.setSubject(subjectTextField.getText());

            message.setText(bodyTextArea.getText() + footerTextArea.getText());

            transport.connect();
            Transport.send(message);
            transport.close();
            System.out.println("Mail successfully sent");
        }
        catch (MessagingException mex)
        {
            mex.printStackTrace();
        }
    }

}

