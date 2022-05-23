package Desktop.Components;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

public class Employees extends JPanel {
    JLabel searchLbl=new JLabel("Search employee");
    JTextField search=new JTextField();
    JButton addNew=new JButton("Add new Employee");
    public Employees(){
        setVisible(false);
        setBounds(200,0,1166,768);
        setBackground(Color.WHITE);
        setBorder(new EmptyBorder(new Insets(20,30,20,30)));
        Object [][] data = {{"Karera Marvin","Karera@gmail.com","0781234568","Mukamira"},{"Kayitare Audax","audax@gmail.com","0786783420","Nyamabuye"},{"Nick Singizwa","nick@gmail.com","0784893734","Niboye"}};
        Object [] columns = {"Names", "Email", "Phone no", "Location"};
        shiftsTable("All created shifts", data, columns);
        add(searchLbl);
        add(search);
        add(addNew);
    }
    public void setStyles(){
        Color dodgerBlue = new Color(52,143,235);
        search.setSize(200,30);
        searchLbl.setFont(new Font("POPPINS",Font.PLAIN,15));
        addNew.setBackground(dodgerBlue);
    }
    public void shiftsTable(String title, Object[][] data, Object[] columns){
        JLabel label = new JLabel(title);
        label.setFont(new Font("Arial", Font.BOLD, 15));
//        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        label.setBorder(new EmptyBorder(new Insets(10, 10, 10, 10)));

        JPanel container = new JPanel();
//        container.setBounds(20, 20, 1000, 200);
//        container.setSize(1000,700);
//        container.setBackground(Color.BLUE);
//        container.setBorder(new EmptyBorder(new Insets(10, 10, 10, 10)));
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
//        table.setSize(700,700);
        table.getTableHeader().setOpaque(false);
        table.setShowGrid(false);
        table.getTableHeader().setReorderingAllowed(false);
        Color color = new Color(37, 149, 234);
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

//        JButton addButton = new JButton("Add Citizen");
//        addButton.setBounds(10, 300, 400, 30);
//        addButton.setBackground(color);
//        addButton.setForeground(Color.WHITE);
    }
}
