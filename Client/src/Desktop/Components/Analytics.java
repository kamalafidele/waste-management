package Desktop.Components;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Analytics extends JPanel {
    public Analytics(JPanel invoicesPanel, JPanel reportsPanel, JPanel addReportPanel,JPanel mainPanel){
        invoicesPanel.setVisible(false);
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        String analyticsParagraph = "<html><h1>Analytics</h1><h4>Your analytics includes invoices, " +
                "reports and <br/> wallet information</h4></html>";
        String invoicesParagraph = "<html><br/><br/><br/><h4>Recent invoices issued to you</h4><br/></html>";
        String reportsParagraph = "<html><br/><br/><br/><h4>Your recent reports</h4><br/></html>";

        JButton backBtn = new JButton("< Back");
        backBtn.setBackground(Color.decode("#557DF8"));
        backBtn.setBounds(50, 20,150, 40);
        backBtn.setBorder(new EmptyBorder(new Insets(12,40,12,40)));
        backBtn.setFont(new Font("Inter", Font.BOLD, 13));
        backBtn.setForeground(Color.WHITE);
        backBtn.setFocusPainted(false);
        backBtn.setVisible(false);

        backBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                invoicesPanel.setVisible(false);
                backBtn.setVisible(false);
                setVisible(true);
            }
        });

        JLabel analyticsLabel = new JLabel(analyticsParagraph);
        JLabel invoicesParagraphLabel = new JLabel(invoicesParagraph);
        JLabel reportPLabel = new JLabel(reportsParagraph);

        JButton invoices = new JButton("Invoices");
        invoices.setBackground(Color.decode("#557DF8"));
        invoices.setPreferredSize(new Dimension(140, 40));
        invoices.setBorder(new EmptyBorder(new Insets(12,40,12,40)));
        invoices.setFont(new Font("Inter", Font.BOLD, 13));
        invoices.setForeground(Color.WHITE);
        invoices.setFocusPainted(false);

        invoices.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                backBtn.setVisible(true);
                invoicesPanel.setVisible(true);
                setVisible(false);
            }
        });

        JPanel reportsButtons = new JPanel();
        reportsButtons.setSize(300, 20);
        reportsButtons.setLayout(new BoxLayout(reportsButtons, BoxLayout.X_AXIS));
        reportsButtons.setAlignmentX(Component.LEFT_ALIGNMENT);
        reportsButtons.setBackground(Color.white);


        JButton marginButton = new JButton("");
        marginButton.setBackground(Color.decode("#ffffff"));
        marginButton.setPreferredSize(new Dimension(0, 0));
        marginButton.setMaximumSize(new Dimension(20, 0));
        marginButton.setBorder(new EmptyBorder(new Insets(12,40,12,40)));
        marginButton.setFont(new Font("Inter", Font.BOLD, 13));
        marginButton.setForeground(Color.WHITE);
        marginButton.setFocusPainted(false);

        JButton reports = new JButton("Reports");
        reports.setBackground(Color.decode("#557DF8"));
        reports.setPreferredSize(new Dimension(140, 20));
        reports.setBorder(new EmptyBorder(new Insets(12,40,12,40)));
        reports.setFont(new Font("Inter", Font.BOLD, 13));
        reports.setForeground(Color.WHITE);
        reports.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                backBtn.setVisible(true);
                invoicesPanel.setVisible(false);
                reportsPanel.setVisible(true);
                setVisible(false);
            }
        });

        JButton newReport = new JButton("+ New Report");
        newReport.setBackground(Color.decode("#557DF8"));
        newReport.setPreferredSize(new Dimension(140, 20));
        newReport.setBorder(new EmptyBorder(new Insets(12,40,12,40)));
        newReport.setFont(new Font("Inter", Font.BOLD, 13));
        newReport.setForeground(Color.WHITE);
        newReport.setFocusPainted(false);
        newReport.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                backBtn.setVisible(true);
                invoicesPanel.setVisible(false);
                addReportPanel.setVisible(true);
                setVisible(false);
            }
        });

        reportsButtons.add(reports);
        reportsButtons.add(marginButton);
        reportsButtons.add(newReport);

        mainPanel.add(backBtn);
        add(analyticsLabel, LEFT_ALIGNMENT);
        add(invoicesParagraphLabel);
        add(invoices);
        add(reportPLabel);
        add(reportsButtons);

        setBackground(Color.white);
        setBounds(50, 50,1025, 670);
    }
    void initialize(){}
}
