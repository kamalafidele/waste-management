package Desktop.Components.District;

import Desktop.Components.Registration;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DistrictsView extends JPanel {
    Registration registerDistrict = new Registration(true, false, false);
    JPanel container = new JPanel();

    public DistrictsView(){
        setVisible(false);
        setBounds(200,0,1166,768);
        setBorder(new EmptyBorder(new Insets(20,30,20,30)));
        Object [][] data = {{"Kicukiro","kicukiro@gmail.com","500000", "5", "4000"},{"Nyarugenge","nyarugenge@gmail.com","800000", "10", "3000"},{"Gasabo","gasabo@gmail.com","200000", "3", "7000"}};
        Object [] columns = {"Name", "Email", "Wallet (RWF)", "Companies", "Citizens", "Actions"};
        DistrictsTable("All Districts", data, columns);

        //panels
        add(registerDistrict);
        registerDistrict.setVisible(false);
    }

    public void DistrictsTable(String title, Object[][] data, Object[] columns){
        JLabel label = new JLabel(title);
        label.setFont(new Font("Arial", Font.BOLD, 15));
//        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        label.setBorder(new EmptyBorder(new Insets(10, 0, 10, 10)));

        container.setBorder(new EmptyBorder(new Insets(0, 0, 0, 0)));
        container.setPreferredSize(new Dimension(1166,700));
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));

        Color color = new Color(37, 149, 234);
        JButton addButton = new JButton("Create District");
        addButton.setBounds(0, 0, 400, 30);
        addButton.setBackground(color);
        addButton.setForeground(Color.WHITE);
        addButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        addButton.setBorder(new EmptyBorder(new Insets(10, 10, 10, 10)));

        addButton.setActionCommand("createdistrict");
        addButton.addActionListener(new DistrictsView.ButtonClickEventHandler());

        container.add(addButton);
        container.add(label);

        DefaultTableModel model = new DefaultTableModel(data,columns);
        model.setColumnIdentifiers(columns);
        JTable table = new JTable(model) {
            //            @Serial
//            private static final long serialVersionUID = 1L;
            public boolean isCellEditable(int row, int column) {
                return false;
            };
        };
        table.setRowHeight(40);

        //increase table size
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table.getColumnModel().getColumn(0).setPreferredWidth(250);
        table.getColumnModel().getColumn(1).setPreferredWidth(250);
        table.getColumnModel().getColumn(2).setPreferredWidth(150);
        table.getColumnModel().getColumn(3).setPreferredWidth(150);
        table.getColumnModel().getColumn(4).setPreferredWidth(150);
        table.getColumnModel().getColumn(5).setPreferredWidth(200);

        table.getTableHeader().setOpaque(false);
        table.setShowGrid(false);
        table.getTableHeader().setReorderingAllowed(false);
//        Color color = new Color(37, 149, 234);
        table.getTableHeader().setBackground(color);
        table.getTableHeader().setForeground(Color.white);

        table.setDefaultRenderer(Object.class, new TableCellRenderer(){
            private final DefaultTableCellRenderer DEFAULT_RENDERER =  new DefaultTableCellRenderer();
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = DEFAULT_RENDERER.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                if (row%2 == 0){
                    c.setBackground(new Color(240,240,240));
                }
                else {
                    c.setBackground(new Color(255, 255, 255));
                }
                return c;
            }
        });

        ((DefaultTableCellRenderer)table.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);

        JScrollPane jScrollPane = new JScrollPane();
        jScrollPane.setViewportView(table);
        table.getTableHeader().setPreferredSize(new Dimension(jScrollPane.getWidth(),40));
        jScrollPane.getViewport().setBackground(Color.WHITE);
        jScrollPane.setBorder(BorderFactory.createLineBorder(Color.white));

        container.add(jScrollPane);
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        add(container);
    }

    public class ButtonClickEventHandler implements ActionListener {
        public void actionPerformed(ActionEvent ae){
            if(ae.getActionCommand().equals("createdistrict")){
                container.setVisible(false);
                registerDistrict.setVisible(true);
            }
        }
    }

}
