package Desktop.Components;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.io.Serial;
import java.util.Locale;

public class Employees extends JPanel {

    public Employees(){
        setVisible(false);
        setBounds(200,0,1166,768);
        setBackground(Color.WHITE);
        setBorder(new EmptyBorder(new Insets(20,30,20,30)));
        Object [][] data = {{"Karera Marvin","Karera@gmail.com","0781234568","Mukamira"},{"Kayitare Audax","audax@gmail.com","0786783420","Nyamabuye"},{"Nick Singizwa","nick@gmail.com","0784893734","Niboye"}};
        Object [] columns = {"Company", "Date", "Confirmed people", "No of shifts"};
        shiftsTable("All created shifts", data, columns);
    }
    public void shiftsTable(String title, Object[][] data, Object[] columns){
        JLabel label = new JLabel(title);
        label.setFont(new Font("Arial", Font.BOLD, 15));
//        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        label.setBorder(new EmptyBorder(new Insets(10, 10, 10, 10)));

        JPanel container = new JPanel();
        container.setBorder(new EmptyBorder(new Insets(10, 10, 10, 10)));
        container.setPreferredSize(new Dimension(1025,670));
//        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        JLabel searchLbl=new JLabel("search employee");
        JTextField search=new JTextField();
        search.setBounds(10,10,200,20);
            Color color = new Color(37, 149, 234);
        JButton addButton = new JButton("add new employee");
        addButton.setBounds(1000, 30, 400, 30);
        addButton.setBackground(color);
        addButton.setForeground(Color.WHITE);
        addButton.setBorder(new EmptyBorder(new Insets(10, 10, 10, 10)));

        container.add(addButton);
        container.add(label);
        container.add(searchLbl);
        container.add(search);

        DefaultTableModel model = new DefaultTableModel(data,columns);
        model.setColumnIdentifiers(columns);
        JTable table = new JTable(model) {
            //            @Serial
//            private static final long serialVersionUID = 1L;
            public boolean isCellEditable(int row, int column) {
                return false;
            };
        };
        TableRowSorter sorter = new TableRowSorter<>(model);
        table.setRowSorter(sorter);
        search.getDocument().addDocumentListener(new DocumentListener() {

            public void insertUpdate(DocumentEvent e) {
                search(search.getText());
            }

            public void removeUpdate(DocumentEvent e) {
                search(search.getText());
            }

            public void changedUpdate(DocumentEvent e) {
                search(search.getText());
            }
            public void search(String str) {
                if (str.length() == 0) {
                    sorter.setRowFilter(null);
                } else {
                    sorter.setRowFilter(RowFilter.regexFilter(str.toLowerCase(Locale.ROOT)));
                }
            }
        });
        table.setRowHeight(40);
        //increase table size
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table.getColumnModel().getColumn(0).setPreferredWidth(250);
        table.getColumnModel().getColumn(1).setPreferredWidth(250);
        table.getColumnModel().getColumn(2).setPreferredWidth(250);
        table.getColumnModel().getColumn(3).setPreferredWidth(250);

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

}