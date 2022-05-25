package Desktop.Components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.print.PrinterException;

public class InvoiceView extends JFrame {
    public JTextArea textArea;
    public JButton download_pdf;
    Color dodgerBlue = new Color(52,143,235);

    public InvoiceView(Object [] data){
        Object inv_date = data[4];
        Object usr_name = data[1];
        Object service = data[2];
        Object price = data[3];
        setBounds(200, 200, 500, 600);
        setLayout(null);
        setVisible(true);

        JPanel panel1 = new JPanel();
        panel1.setBounds(20,170,460,450);
        panel1.setBorder(BorderFactory.createBevelBorder(1));
        panel1.setLayout(null);
        add(panel1);

        panel1.setVisible(true);
        panel1.setBounds(300,100,1020,740);
        getContentPane().setBackground(Color.LIGHT_GRAY);

        textArea = new JTextArea();
        textArea.setBounds(590,170,360,450);
        textArea.setFont(new Font("Charcoal CY",Font.PLAIN,18));
        textArea.setBorder(BorderFactory.createBevelBorder(1));
        panel1.add(textArea);

        textArea.append("************************************************" +
                "\n\tWSMS\t" +
                "\n************************************************" +
                "\n Date & Time :- " + inv_date +
                "\n Name:- " + usr_name +
                "\n************************************************" +
                "\nservice   transaction\tPrice");

//        textArea.append("\n" + service + "   " + trans + "\t  " + price);
        textArea.append("\n" + service + "   " + "\t  " + price);

        textArea.append("\n============================================" +
                "\n\t Total Amount:-"+price+
                "\n=============================================");

        download_pdf = new JButton("Print Bill");
        download_pdf.setBounds(750,640,200,50);
        download_pdf.setFocusable(false);
        download_pdf.setFont(new Font("Mongolian Baiti",Font.BOLD,16));
        download_pdf.setBackground(dodgerBlue);
        download_pdf.setForeground(Color.WHITE);
        download_pdf.addActionListener(this::download_pdf_Button_Action);
        panel1.add(download_pdf);
    }

    private void download_pdf_Button_Action(ActionEvent evt)
    {
        try {
            textArea.print();
        } catch (PrinterException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Object [] data = {"0", "Colombe", "Security", "5000", "24-05-2022"};
        new InvoiceView(data);
    }
}
