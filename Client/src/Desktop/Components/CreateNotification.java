package Desktop.Components;

import Desktop.EventHandlers.PlaceHolderHandler;
import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;
import net.miginfocom.swing.MigLayout;
import org.codehaus.jackson.map.ObjectMapper;


import javax.swing.*;
import java.awt.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.time.DayOfWeek;

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
        notificationTextField.addFocusListener(new PlaceHolderHandler(notificationTextField, "Name your notification"));
        processName = new JLabel("Process Name");
        processTextField = new JTextField();
        processTextField.addFocusListener(new PlaceHolderHandler(processTextField, "Name your process"));
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
        DatePicker datePicker = new DatePicker(dateSettings);

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
        textArea = new JTextArea("Write your message here");

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
}
