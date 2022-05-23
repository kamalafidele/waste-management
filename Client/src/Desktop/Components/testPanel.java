package Desktop.Components;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.io.Serial;

public class testPanel extends JPanel {

    public testPanel(){
        setVisible(false);
        setBounds(200,0,1166,768);
        setBackground(Color.WHITE);
        setBorder(new EmptyBorder(new Insets(20,30,20,30)));
    }

    public static class ViewCitizens extends JFrame {

        private String title;
        private Object[][] data;
        private Object[] columns;

        public void citizens(String title, Object[][] data, Object[] columns) throws HeadlessException {
            this.title = title;
            this.data = data;
            this.columns = columns;
            this.setVisible(true);

            JLabel label = new JLabel(this.title, JLabel.LEFT);
            label.setAlignmentX(Component.LEFT_ALIGNMENT);
            label.setFont(new Font("Arial", Font.BOLD, 15));
            label.setBorder(new EmptyBorder(new Insets(10, 10, 10, 10)));

            JPanel container = new JPanel();
            container.setOpaque(false);
    //        container.setBackground(Color.RED);
            container.setBorder(new EmptyBorder(new Insets(10, 10, 10, 10)));
            container.add(label);
    //        container.setLayout(new FlowLayout());
            DefaultTableModel model = new DefaultTableModel(this.data, this.columns);
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

        public static class viewDistricts {
        }
    }
}
