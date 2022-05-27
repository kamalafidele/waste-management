package Desktop.Screens.Shifts;

import DataHandlers.ShiftsHandler;
import Desktop.Components.ConfirmationProcess;
import org.codehaus.jackson.type.TypeReference;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.Serial;
import java.util.ArrayList;
import java.util.Iterator;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

public class ViewShifts extends JPanel {

    private DataOutputStream toServer;
    private DataInputStream fromServer;
    Object [][] data;
    ObjectMapper mapper;

    public ViewShifts(DataOutputStream toServer,DataInputStream fromServer) throws IOException {
        this.toServer=toServer;
        this.fromServer=fromServer;
        setVisible(false);
        setBounds(200,0,1166,768);
        setBackground(Color.WHITE);
        setBorder(new EmptyBorder(new Insets(20,30,20,30)));

        mapper=new ObjectMapper();
        sendRequest("serviceconfirmation/getShifts");
        String response = this.fromServer.readUTF();
//        System.out.println(response);
//        ArrayList<ShiftsHandler> shifts = mapper.readValue(response,new TypeReference<ArrayList<ShiftsHandler>>(){});
//        Iterator<ShiftsHandler> shiftsIterator = shifts.iterator();

        Object[][] shifts = mapper.readValue(response, Object[][].class);

//        while(shiftsIterator.hasNext()){
//            ShiftsHandler handler = shiftsIterator.next();
////            System.out.println(" | "+ handler.getId()+" | "+"      | "+handler.getCompany_id()+" | "+"     | "+handler.getDate()+" | "+"       | "+handler.getConfirmerId()+" | ");
//            data = new Object[][]{{handler.getCompany_name(), handler.getDate(), handler.getConfirmerId(),"Waste collection"}};
//         }

        Object [] columns = {"Company", "Date", "Confirmed people", "Service"};
        shiftsTable("All created shifts", shifts, columns);
    }
    public void shiftsTable(String title, Object[][] data, Object[] columns){
        JLabel label = new JLabel(title);
        label.setFont(new Font("Arial", Font.BOLD, 15));
//        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        label.setBorder(new EmptyBorder(new Insets(10, 10, 10, 10)));

        JPanel container = new JPanel();
        container.setBorder(new EmptyBorder(new Insets(10, 10, 10, 10)));
        container.setPreferredSize(new Dimension(1105,610));
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));

        Color color = new Color(37, 149, 234);
        JButton addButton = new JButton("Create shift");
        addButton.setBounds(1000, 30, 400, 30);
        addButton.setBackground(color);
        addButton.setForeground(Color.WHITE);
//        addButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        addButton.setBorder(new EmptyBorder(new Insets(10, 10, 10, 10)));

        addButton.setActionCommand("CreateShift");
        addButton.addActionListener(new ButtonClickEventHandler());

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
        buttonPanel.setBorder(new EmptyBorder(new Insets(10, 10, 10, 10)));
        buttonPanel.add(addButton);
        buttonPanel.setBackground(Color.WHITE);
        add(buttonPanel);

//        container.add(addButton);
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
        table.getColumnModel().getColumn(0).setPreferredWidth(270);
        table.getColumnModel().getColumn(1).setPreferredWidth(270);
        table.getColumnModel().getColumn(2).setPreferredWidth(270);
        table.getColumnModel().getColumn(3).setPreferredWidth(270);

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
        @Override
        public void actionPerformed(ActionEvent ae) {
            if(ae.getActionCommand().equals("CreateShift")){
                try{
                    sendRequest("serviceconfirmation/initShift");
//                    String response = fromServer.readUTF();
//                    System.out.println(response);
                }catch (Exception e){
                    System.out.println(e.getMessage());
                }
                JFrame frame = new JFrame("Shift activated");
                frame.setSize(700,700);
                frame.addWindowListener(
                        new WindowAdapter() {
//                            @Override
                            public void windowClosing(WindowEvent e) {
//                        super.windowClosing(e);
                                System.out.println("you have closed window,shift is not activated");
                                try {
//                                    Thread.sleep(1000);
                                    sendRequest("serviceconfirmation/closeShift");
                                } catch (IOException ex) {
                                    ex.printStackTrace();
                                }
                                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                            }
                        }
                );
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
                frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
                add(new ConfirmationProcess());
            }

        }
    }

    public void sendRequest(String request) throws IOException {
        try{
            toServer.writeUTF(request);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void setStreams(DataOutputStream outToServer,DataInputStream fromServer){
        this.fromServer=fromServer;
        this.toServer=outToServer;
    }
}
