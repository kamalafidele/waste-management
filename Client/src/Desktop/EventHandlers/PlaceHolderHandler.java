package Desktop.EventHandlers;

import javax.swing.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class PlaceHolderHandler implements FocusListener {
    JTextField textField;
    String text;

    public PlaceHolderHandler(JTextField textField, String text) {
        this.textField = textField;
        this.text = text;
    }

    @Override
    public void focusGained(FocusEvent e) {
        if(textField.getText().equals(text)){
            textField.setText("");
        }
    }

    @Override
    public void focusLost(FocusEvent e) {
        if(textField.getText().isEmpty()){
            textField.setText(text);
        }
    }
}
