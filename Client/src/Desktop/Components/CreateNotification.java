package Desktop.Components;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;

public class CreateNotification extends JFrame {
    public static void main(String[] args) {
        new CreateNotification();
    }
    public CreateNotification(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800, 500);

        JPanel mainPanel = new JPanel(new MigLayout("wrap, debug, fill", "[]", "[][][]"));
        JLabel notification = new JLabel("Notification");
        JPanel insidePanel = new JPanel(new MigLayout("wrap", "[][][][]", "[][]"));
        JPanel formPanel = new JPanel();

        JLabel description = new JLabel("Description");
        description.setFont(new Font(null, Font.BOLD, 15));
        description.setForeground(new Color(0,101,51));
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
        String[] dateChoices = {
                "12/05/2022",
                "13/05/2022",
                "14/05/2022",
                "15/05/2022",
                "16/05/2022",
                "17/05/2022",
                "18/05/2022",
                "19/05/2022"
        };
        JComboBox dateChoice = new JComboBox(dateChoices);
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
        content.setForeground(new Color(0,101,51));
        JLabel message = new JLabel("Message");
        JTextArea textArea = new JTextArea("Write your message here");

        insidePanel.add(description, "span");
        insidePanel.add(notificationName);
        insidePanel.add(notificationTextField);
        insidePanel.add(assignedGroup);
        insidePanel.add(groupChoice);
        insidePanel.add(processName);
        insidePanel.add(processTextField);
        insidePanel.add(date);
        insidePanel.add(dateChoice);
        insidePanel.add(status);
        insidePanel.add(statusChoice);
        insidePanel.add(renotify);
        insidePanel.add(periodChoice);
        insidePanel.add(content, "span");
        insidePanel.add(message);
        insidePanel.add(textArea, "span, grow");

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
        this.setVisible(true);
    }
}
