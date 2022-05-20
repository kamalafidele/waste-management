package Desktop.Components;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

public class View_Districts extends JFrame {
    public static void main(String[] args) {
        new View_Districts();
    }
    private JPanel leftPanel = new JPanel();
    private JPanel rightPanel = new JPanel();

    public View_Districts(){
        JFrame frame = new JFrame("WSMS_Y2_C");
        frame.setSize(1300,750);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(1,2));


        leftPanel.setVisible(true);
        leftPanel.setSize(300,700);
        leftPanel.setBackground(new Color(234, 237, 243));


        rightPanel.setVisible(true);
        rightPanel.setSize(700,600);
        rightPanel.setBackground(Color.WHITE);


        frame.add(leftPanel);
        frame.add(rightPanel);

        Button newShift = new Button("+ new Shift");
        newShift.setBackground(Color.GREEN);
        newShift.setBounds(200,100,50,20);

        Color exportButtonColor = new Color(76, 138, 199);
        Button exportData =new Button("Export");
        Label exportDataLabel =new Label();
        exportDataLabel.setBackground(Color.WHITE);
        exportData.setBackground(exportButtonColor);
        exportDataLabel.setText(String.valueOf(exportDataLabel));
        exportData.setBounds(250,200,50,20);

        setLeftPanelTexts();
        String[][] tableData = {{"", "", "", "", ""},
                {"", "", "", "", ""},
                {"", " ", "", "", ""},
                {"", "", "", "", ""},
                {"", "", "", "", ""}};
        String[] tableColumn = {"#","service","Company-in-charge","Citizen","Date"};
        addTable("Manage Districts",tableData, tableColumn);

        JTextField search =new JTextField();
        search.setBounds(50, 50, 150, 20);
        search.setText("search");

        Button btn1 = new Button("1");
        btn1.setBackground(Color.GREEN);
        btn1.setBounds(200,100,50,20);
        Button btn2 = new Button("2");
        btn2.setBackground(Color.GREEN);
        btn2.setBounds(200,100,50,20);
        Button btn3 = new Button("3");
        btn3.setBackground(Color.GREEN);
        btn3.setBounds(200,100,50,20);

        JPanel mb =new JPanel();
        JPanel pagination =new JPanel();

        rightPanel.setLayout(new GridLayout(3,1));
        mb.add(exportData);
        mb.add(newShift);
        mb.setBounds(0,20, 200, 100);
        pagination.add(btn1);
        pagination.add(btn2);
        pagination.add(btn3);
        pagination.setBounds(0,20, 200, 100);

        rightPanel.add(pagination);
        rightPanel.setLayout(new BorderLayout());
        rightPanel.add(mb, BorderLayout.NORTH);
        rightPanel.add(pagination, BorderLayout.SOUTH);
        rightPanel.add(addTable("Manage Districts",tableData, tableColumn), BorderLayout.CENTER);

    }

    public void setLeftPanelTexts(){
        JLabel label = new JLabel("Sidebar");
        leftPanel.add(label);
    }

    public Component addTable(String title, String[][] data, String[] columns) throws HeadlessException {

        JLabel label = new JLabel(title, JLabel.LEFT);
        label.setAlignmentX(Component.LEFT_ALIGNMENT);
        label.setFont(new Font("Arial", Font.BOLD, 18));
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
        table.setShowVerticalLines(false);
        table.getTableHeader().setBackground(color);
        table.getTableHeader().setForeground(Color.white);
        table.setDefaultRenderer(Object.class, new TableCellRenderer(){
            private final DefaultTableCellRenderer DEFAULT_RENDERER =  new DefaultTableCellRenderer();
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = DEFAULT_RENDERER.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                c.setBackground(new Color(215,215,215));
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
        rightPanel.add(container);

        return null;
    }



}