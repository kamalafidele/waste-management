package Desktop.Components;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

public class Transactions extends JFrame {
    public static void main(String[] args) {
        new Transactions();
    }
    private JPanel leftPanel = new JPanel();
    private JPanel rightPanel = new JPanel();


    public Transactions(){
        SideMenuPanel sideMenuPanel = new SideMenuPanel();
        JFrame frame = new JFrame("Transactions");
        frame.setSize(1000,600);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        leftPanel.setVisible(true);
        leftPanel.setSize(300,600);
//        Color rightPanelBg = new Color()
        leftPanel.setBackground(new Color(234, 237, 243));
        rightPanel.setVisible(true);
        rightPanel.setSize(700,600);
        rightPanel.setBackground(Color.WHITE);

        leftPanel.add(sideMenuPanel);


        frame.add(leftPanel);
        frame.add(rightPanel);

        String[][] tableData = {{"01", "Rusizi District", "Me", "10000", "2021-12-30"},
                {"02", "Me", "Confirmer", "5000", "2022-01-30"},
                {"03", "Rusizi District", "Me", "10000", "2022-01-30"},
                {"04", "Me", "Confirmer", "5000", "2022-02-28"},
                {"03", "Karongi District", "Me", "10000", "2022-02-28"}};
        String[] tableColumn = {"ID", "FROM", "TO", "AMOUNT", "DATE"};
        addTable("All Transactions",tableData, tableColumn);


    }

    public void setLeftPanelTexts(){
        JLabel label = new JLabel("Sidebar");
        leftPanel.add(label);
    }


    public  void addTable(String title, String[][] data, String[] columns) throws HeadlessException {

        JLabel label = new JLabel(title, JLabel.LEFT);
        label.setAlignmentX(Component.LEFT_ALIGNMENT);
        label.setFont(new Font("Arial", Font.BOLD, 16));
        label.setBackground(Color.red);
        label.setAlignmentX(Component.LEFT_ALIGNMENT);
        JPanel container = new JPanel();
        container.setOpaque(false);
        container.add(label);
        container.setLayout(new FlowLayout());
        DefaultTableModel model = new DefaultTableModel(data, columns);
        model.setColumnIdentifiers(columns);

        JTable table = new JTable(model) {
            private static final long serialVersionUID = 1L;

            public boolean isCellEditable(int row, int column) {
                return false;
            };
        };
        table.setRowHeight(40);

        table.getTableHeader().setOpaque(false);
        table.setShowGrid(false);

        table.getTableHeader().setReorderingAllowed(false);

        Color color = new Color(85, 125, 248);
        table.getTableHeader().setBackground(color);
        table.getTableHeader().setForeground(Color.white);
        table.setDefaultRenderer(Object.class, new TableCellRenderer(){
            private final DefaultTableCellRenderer DEFAULT_RENDERER =  new DefaultTableCellRenderer();
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = DEFAULT_RENDERER.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                if (row%2 == 0){
                    c.setBackground(new Color(209, 219, 244));
                }
                else {
                    c.setBackground(new Color(234, 237, 243));
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
        JButton saveToFile = new JButton("Save To File");
        saveToFile.setBackground(new Color(122, 153, 247));
        saveToFile.setForeground(Color.WHITE);
        saveToFile.setBounds(100,400,100,40);
        container.add(saveToFile);

        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        rightPanel.add(container);

//        JTable jTable = new JTable(tableData, tableColumn);
//        jTable.setBounds(0, 0, 50, 280);
//        JScrollPane jScrollPane = new JScrollPane(jTable);
//        rightPanel.add(jScrollPane);
    }

    public void setSaveButton(){
        JButton saveToFile = new JButton("Save To File");
        saveToFile.setBackground(new Color(122, 153, 247));
        saveToFile.setForeground(Color.WHITE);
        saveToFile.setBounds(0,0,100,40);
        rightPanel.add(saveToFile);
    }

}
