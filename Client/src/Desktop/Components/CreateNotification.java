package Desktop.Components;

import DataHandlers.NotificationHandler;
import Desktop.EventHandlers.PlaceHolderHandler;
import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;
import net.miginfocom.swing.MigLayout;
import org.codehaus.jackson.map.ObjectMapper;


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

/*
 * @authors: Fiat Bruno, Ineza Naillah
 *
 */

public class CreateNotification extends JFrame {
    public static void main(String[] args) {
        new CreateNotification();
    }
    ObjectMapper mapper = new ObjectMapper();

    DataOutputStream toServer;
    DataInputStream fromServer;
    NotificationHandler notificationHandler = null;
    Color dodgerBlue = new Color(52,143,235);
    JPanel mainPanel;
    JPanel insidePanel;
    JLabel notification;
    JLabel description;
    JLabel notificationName;
    JLabel processName;
    JLabel status;
    JLabel assignedGroup;
    JLabel renotify;
    JLabel date;
    JLabel content;
    JLabel message;
    JTextField notificationTextField;
    JTextField processTextField;
    JComboBox statusChoice;
    JComboBox groupChoice;
    JComboBox periodChoice;
    JTextArea textArea;
    JPanel buttonPanel;
    JButton saveButton;
    JButton closeButton;
    DatePicker datePicker;
    public CreateNotification(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Create Notifications");
        this.setSize(800, 800);
        this.setResizable(false);

        mainPanel = new JPanel(new MigLayout("center, wrap, fill, insets 20", "20:push[]20:push", "20:push[]20[]20[]20:push"));
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
        closeButton = new JButton("Close");

        buttonPanel.add(saveButton);
        buttonPanel.add(closeButton);

        mainPanel.add(notification);
        mainPanel.add(insidePanel);
        mainPanel.add(buttonPanel);
        this.add(mainPanel);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
    public void setStreams(DataOutputStream toServer, DataInputStream fromServer) {
        this.toServer = toServer;
        this.fromServer = fromServer;
    }
    public void setNotificationHandler(NotificationHandler notificationHandler) {
        this.notificationHandler = notificationHandler;
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
//                        System.out.println("notification/create/" + notificationHandler.getReceiver() + "/" +
//                                notificationHandler.getType());
                        String response = fromServer.readUTF();
                        System.out.println(response);

                    }catch (Exception exception) {
                        exception.printStackTrace();
                    }
                }
            }
        }
    }
    public void sendRequest( String request ){
        try{
            toServer.writeUTF( request );
        }catch ( IOException exception ){
            exception.printStackTrace();
        }
    }
}

