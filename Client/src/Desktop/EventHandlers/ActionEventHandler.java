package Desktop.EventHandlers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ActionEventHandler implements ActionListener {
    private String actionCommand;
    @Override
    public void actionPerformed(ActionEvent e) {
        actionCommand = e.getActionCommand();
    }
}
