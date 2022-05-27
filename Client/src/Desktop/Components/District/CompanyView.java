package Desktop.Components.District;

import Desktop.Components.Registration;
import org.codehaus.jackson.map.ObjectMapper;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class CompanyView extends JPanel {
    Registration registerDistrict = new Registration(true, false, false);
    DataInputStream fromServer;
    DataOutputStream toServer;
    JPanel container = new JPanel();
    ObjectMapper mapper = new ObjectMapper();

    public CompanyView(DataOutputStream toServer, DataInputStream fromServer) throws IOException {
        this.toServer = toServer;
        this.fromServer = fromServer;

        // getting districts
        sendRequest("company/getAll");
        String response = fromServer.readUTF();
        Object[][] districts = mapper.readValue(response, Object[][].class);

        System.out.println(districts);

        setVisible(false);
        setBounds(200, 0, 1166, 768);
        setBorder(new EmptyBorder(new Insets(20, 30, 20, 30)));
        Object[] columns = { "id", "name", "tin", "email" };

        DistrictsTable("All Districts", districts, columns);

        System.out.println(districts);

        // panels
        add(registerDistrict);
        registerDistrict.setVisible(false);

    }

    public void DistrictsTable(String title, Object[][] data, Object[] columns) throws IOException {
        JLabel label = new JLabel(title);
        Color color = new Color(37, 149, 234);
        JButton addButton = new JButton("Create District");
        DefaultTableModel model = new DefaultTableModel(data, columns);
        JTable table = new JTable(model) {
            public boolean isCellEditable(int row, int column) {
                return false;
            };
        };
        JScrollPane jScrollPane = new JScrollPane();

        label.setFont(new Font("Arial", Font.BOLD, 15));
        label.setBorder(new EmptyBorder(new Insets(10, 0, 10, 10)));

        container.setBorder(new EmptyBorder(new Insets(0, 0, 0, 0)));
        container.setPreferredSize(new Dimension(1166, 700));
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));

        addButton.setBounds(0, 0, 400, 30);
        addButton.setBackground(color);
        addButton.setForeground(Color.WHITE);
        addButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        addButton.setBorder(new EmptyBorder(new Insets(10, 10, 10, 10)));

        addButton.setActionCommand("createdistrict");
        addButton.addActionListener(new CompanyView.ButtonClickEventHandler());

        container.add(addButton);
        container.add(label);

        model.setColumnIdentifiers(columns);

        table.setRowHeight(40);

        // increase table size
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table.getColumnModel().getColumn(0).setPreferredWidth(350);
        table.getColumnModel().getColumn(1).setPreferredWidth(350);
        table.getColumnModel().getColumn(2).setPreferredWidth(350);
        table.getColumnModel().getColumn(3).setPreferredWidth(350);
        // table.getColumnModel().getColumn(4).setPreferredWidth(200);
        // table.getColumnModel().getColumn(5).setCellRenderer(new ButtonRenderer());
        // table.getColumnModel().getColumn(5).setCellEditor( new ButtonEditor(new
        // JCheckBox()));

        table.getTableHeader().setOpaque(false);
        table.setShowGrid(false);
        table.getTableHeader().setReorderingAllowed(false);
        table.getTableHeader().setBackground(color);
        table.getTableHeader().setForeground(Color.white);

        table.setDefaultRenderer(Object.class, new TableCellRenderer() {
            private final DefaultTableCellRenderer DEFAULT_RENDERER = new DefaultTableCellRenderer();

            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                                                           boolean hasFocus, int row, int column) {
                Component c = DEFAULT_RENDERER.getTableCellRendererComponent(table, value, isSelected, hasFocus, row,
                        column);
                if (row % 2 == 0) {
                    c.setBackground(new Color(240, 240, 240));
                } else {
                    c.setBackground(new Color(255, 255, 255));
                }
                return c;
            }
        });

        ((DefaultTableCellRenderer) table.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);

        jScrollPane.setViewportView(table);
        table.getTableHeader().setPreferredSize(new Dimension(jScrollPane.getWidth(), 40));
        jScrollPane.getViewport().setBackground(Color.WHITE);
        jScrollPane.setBorder(BorderFactory.createLineBorder(Color.white));

        container.add(jScrollPane);
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        add(container);
    }

    public class ButtonClickEventHandler implements ActionListener {
        public void actionPerformed(ActionEvent ae) {
            if (ae.getActionCommand().equals("createdistrict")) {
                setVisible(false);
                registerDistrict.setVisible(true);
            }
        }
    }

    public class ButtonRenderer extends JButton implements TableCellRenderer {
        public ButtonRenderer() {
            setOpaque(true);
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
                                                       int row, int column) {
            setText("View");
            return this;
        }
    }

    public class ButtonEditor extends DefaultCellEditor {

        private String label;
        public JButton button = new JButton();

        public ButtonEditor(JCheckBox checkBox) {
            super(checkBox);
        }

        public Component getTableCellEditorComponent(JTable table, Object value, Boolean isSelected, int row,
                                                     int column) {
            label = "View";
            button.setText(label);
            return button;
        }

        public Object getCellEditorValue() {
            return new String(label);
        }
    }

    public void sendRequest(String request) {
        try {
            this.toServer.writeUTF(request);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
