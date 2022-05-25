package Desktop.Components;

import javax.swing.*;

public class ChooseServiceToPay extends JFrame {
    ChooseServiceToPay(){
        setVisible(true);
        setSize(1366,760);
        new ChooseServiceToPayComp();

    }

    public static void main(String[] args) {
        new ChooseServiceToPay();
    }





}
