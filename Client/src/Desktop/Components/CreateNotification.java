package Desktop.Components;

import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;
import net.miginfocom.swing.MigLayout;


import javax.swing.*;
import java.awt.*;
import java.time.DayOfWeek;

public class CreateNotification extends JFrame {
    public static void main(String[] args) {
        new CreateNotification();
    }
    public CreateNotification(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Create Notifications");
        this.setSize(800, 800);
        this.setResizable(false);

        JPanel mainPanel = new JPanel(new MigLayout("center, wrap, fill, insets 20", "20:push[]20:push", "20:push[]20[]20[]20:push"));
        JLabel notification = new JLabel("Create Notifications");
        notification.setFont(new Font(null, Font.BOLD, 20));
        notification.setForeground(new Color(0, 66, 101));
        JPanel insidePanel = new JPanel(new MigLayout("wrap", "[]10:push[]10:push[]10:push[]", "[]10[]"));

        JLabel description = new JLabel("Description");
        description.setFont(new Font(null, Font.BOLD, 15));
        description.setForeground(new Color(0, 66, 101));
        JLabel notificationName = new JLabel("Notification Name");
        JTextField notificationTextField = new JTextField();
        JLabel processName = new JLabel("Process Name");
        JTextField processTextField = new JTextField();
        JLabel status = new JLabel("Status");
        String[] statusChoices = {
                "Active",
                "Inactive"
        };
        JComboBox statusChoice = new JComboBox(statusChoices);
        JLabel assignedGroup = new JLabel("Assigned Group");
        String[] groupChoices = {
                "Citizens",
                "Companies",
                "Districts"
        };
        JComboBox groupChoice = new JComboBox(groupChoices);
        JLabel date = new JLabel("Notify On");

        DatePickerSettings dateSettings = new DatePickerSettings();
        dateSettings.setFirstDayOfWeek(DayOfWeek.MONDAY);
        DatePicker datePicker = new DatePicker(dateSettings);

        JLabel renotify = new JLabel("Renotify");
        String[] periodChoices = {
                "Every Week",
                "Every Month",
                "Every Year",
                "Every Five Years"
        };
        JComboBox periodChoice = new JComboBox(periodChoices);
        JLabel content = new JLabel("Content");
        content.setFont(new Font(null, Font.BOLD, 15));
        content.setForeground(new Color(0, 66, 101));
        JLabel message = new JLabel("Message");
        JTextArea textArea = new JTextArea("Write your message here");

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

        JPanel buttonPanel = new JPanel();
        JButton saveButton = new JButton("Save");
        JButton closeButton = new JButton("Close");

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
