package Desktop.Components;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.io.Serial;

import Desktop.Application;

public class District extends JFrame {
    private Application application = new Application();
    SideMenuPanel sideMenuPanel = new SideMenuPanel();
//    private ViewCitizens viewCitizens = new ViewCitizens();

    public void initialize() {
        application.close();

        setVisible(true);
        setTitle("District Dashboard");
        setSize(1366,760);
        setBackground(Color.YELLOW);

        JPanel leftPanel = new JPanel();
        JPanel rightPanel = new JPanel();
        leftPanel.setVisible(true);
        leftPanel.setSize(200,730);
//        rightPanel.setVisible(true);
//        rightPanel.setSize(800,730);

        add(leftPanel);
//        add(rightPanel);

        leftPanel.add(sideMenuPanel);
//        rightPanel.add()

        Object [][] data = {{"Karera Marvin","Karera@gmail.com","0781234568","Mukamira"},{"Kayitare Audax","audax@gmail.com","0786783420","Nyamabuye"},{"Nick Singizwa","nick@gmail.com","0784893734","Niboye"}};
        Object [] columns = {"Names", "Email", "Phone no", "Location"};
        viewCitizens("All citizens in your district", data, columns);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void viewCitizens(String title, Object[][] data, Object[] columns){
        JLabel label = new JLabel(title, JLabel.CENTER);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        label.setFont(new Font("Arial", Font.BOLD, 15));
        label.setBorder(new EmptyBorder(new Insets(10, 10, 10, 10)));

        JPanel container = new JPanel();
        container.setOpaque(false);
//        container.setBackground(Color.RED);
        container.setBorder(new EmptyBorder(new Insets(10, 10, 10, 10)));
        container.add(label);
//        container.setLayout(new FlowLayout());
        DefaultTableModel model = new DefaultTableModel(data,columns);
        model.setColumnIdentifiers(columns);

        JTable table = new JTable(model) {
            @Serial
            private static final long serialVersionUID = 1L;

            public boolean isCellEditable(int row, int column) {
                return false;
            };
        };
        table.setRowHeight(40);
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
        this.add(container);
    }
}
