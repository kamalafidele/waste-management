package Desktop.Components;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Table extends JScrollPane{
    public JButton button = new JButton();
    public Table(Object[][] data, Object[] columns) {

        Color color = new Color(37, 149, 234);
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
        for (int i = 0; i < columns.length; i++){
            table.getColumnModel().getColumn(i).setPreferredWidth(160);
        }

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

        setViewportView(table);
        table.getTableHeader().setPreferredSize(new Dimension(getWidth(),40));
        getViewport().setBackground(Color.WHITE);
        setBorder(BorderFactory.createLineBorder(Color.white));

        table.getColumn("View").setCellRenderer(new ButtonRenderer(data));
//        table.getColumn("View").setCellEditor(new ButtonEditor(new JCheckBox()));

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Cliiiicked");
            }
        });
    }

    class ButtonRenderer extends JButton implements TableCellRenderer
    {
        Object[][] data;
        public ButtonRenderer(Object[][] data) {
            setOpaque(true);
            this.data = data;
        }
        public Component getTableCellRendererComponent(JTable table, Object value,
                                                       boolean isSelected, boolean hasFocus, int row, int column) {
            setText((value == null) ? "View" : value.toString());
            if(hasFocus){
                System.out.println(data[table.getSelectedRow()][1]);
                hasFocus = false;
            }
            return this;
        }
    }
    class ButtonEditor extends DefaultCellEditor
    {
        private String label;
        public ButtonEditor(JCheckBox checkBox)
        {
            super(checkBox);
        }
        public Component getTableCellEditorComponent(JTable table, Object value,
                                                     boolean isSelected, int row, int column)
        {
            label = (value == null) ? "View" : value.toString();
            button.setText(label);
            return button;
        }
        public Object getCellEditorValue()
        {
            return new String(label);
        }
    }
}
