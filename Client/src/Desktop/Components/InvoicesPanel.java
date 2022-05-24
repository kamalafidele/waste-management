package Desktop.Components;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class InvoicesPanel extends JPanel {
    public InvoicesPanel(){
        Object [][] data = {{"0","Security","5000","24-05-2022"},{"0","Security","5000","24-05-2022"},{"0","Security","5000","24-05-2022"}, {"0","Security","5000","24-05-2022"}};
        Object [] columns = {"ID", "Service", "Amount (Rwf)", "Date"};

        setBorder(new EmptyBorder(new Insets(10, 10, 10, 10)));
        setPreferredSize(new Dimension(1025,670));
        setLayout(new BoxLayout( this, BoxLayout.Y_AXIS));
        setBackground(Color.white);
        setBounds(40, 50,1025, 670);
        setVisible(true);

        String invoicesParagraph = "<html><h1>Invoices</h1><br/><br/><h3> "+ data.length + " invoices</h3><br/></html>";
        JLabel invoicesPLabel = new JLabel(invoicesParagraph);

        Table newInvoicesTable = new Table(data, columns);
        newInvoicesTable.setAlignmentX(Component.LEFT_ALIGNMENT);
        add(invoicesPLabel);
        add(newInvoicesTable);
    }

    void initialize() {

    }
}
