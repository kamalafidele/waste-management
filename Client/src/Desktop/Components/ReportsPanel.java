package Desktop.Components;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class ReportsPanel extends JPanel {
    public ReportsPanel(){
        setBorder(new EmptyBorder(new Insets(10, 10, 10, 10)));
        setPreferredSize(new Dimension(1025,670));
        setLayout(new BoxLayout( this, BoxLayout.Y_AXIS));
        setBackground(Color.white);
        setBounds(40, 50,1025, 670);
        setVisible(true);

        String invoicesParagraph = "<html><h1>Reports</h1><br/><br/>";
        JLabel addReportPLabel = new JLabel(invoicesParagraph);
        add(addReportPLabel);
    }
}
