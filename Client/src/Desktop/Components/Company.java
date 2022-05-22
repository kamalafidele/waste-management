package Desktop.Components;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serial;
import java.sql.*;

import Desktop.Application;
import Desktop.Shared.RoundBtn;

public class Company extends JFrame {
    Connection connection;
    Statement statement;
    int flag=0;
    private Application application = new Application();
    private  JPanel leftPanel = new JPanel();
    private  JPanel rightPanel = new JPanel();

        public void initialize() {
            application.close();

            setVisible(true);
            setTitle("LOG IN AS COMPANY");
            setSize(1366,760);
            setBackground(Color.YELLOW);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }
       Company() {

           leftPanel.setVisible(true);
           leftPanel.setSize(500,600);
           BoxLayout boxLayout = new BoxLayout(leftPanel, BoxLayout.Y_AXIS);
           leftPanel.setLayout(boxLayout);
           leftPanel.setBorder(new EmptyBorder(new Insets(100, 50, 150, 50)));
           rightPanel.setVisible(true);
           rightPanel.setSize(900,600);
           rightPanel.setBackground(Color.WHITE);
           GridLayout layout = new GridLayout(5,1);
           layout.setVgap(10);
           rightPanel.setLayout(layout);
           rightPanel.setBorder(new EmptyBorder(100, 50, 100, 50));
           add(leftPanel);
           add(rightPanel);


           setLayout(new GridLayout(1,2));
           setVisible(true);
           setSize(800,700);

           setLeftPanelTexts();
           setRightPanelContent();
}

    public void setLeftPanelTexts() {
    SidebarDesign();
    }

    public void setRightPanelContent() {
    viewWorkAreas();
    }
    public void SidebarDesign(){
        JMenuBar menuBar = new JMenuBar();
        menuBar.setLayout(new GridLayout(0,1));
        menuBar.setPreferredSize(new Dimension(180,400));
        menuBar.setBackground(Color.decode("#EAEDF3"));
        JMenu Dashboard = new JMenu("Dashboard");
        Dashboard.setFont(new Font("Inter", Font.BOLD, 16));
        menuBar.add(Dashboard);
        JMenu Districts = new JMenu("Districts");
        Districts.setFont(new Font("Inter", Font.PLAIN, 16));
        menuBar.add(Districts);
        JMenu Analytics = new JMenu("Analytics");
        Analytics.setFont(new Font("Inter", Font.PLAIN, 16));
        menuBar.add(Analytics);
        JMenu RegisterAdmin = new JMenu("RegisterAdmin");
        RegisterAdmin.setFont(new Font("Inter", Font.PLAIN, 16));
        menuBar.add(RegisterAdmin);
        JMenu Transactions = new JMenu("Transactions");
        Transactions.setFont(new Font("Inter", Font.PLAIN, 16));
        menuBar.add(Transactions);

        JMenu notification = new JMenu("Notifications");
        notification.setFont(new Font("Inter", Font.PLAIN, 16));
        menuBar.add(notification);
        menuBar.revalidate();
        leftPanel.add(menuBar);
    }
     public void viewWorkAreas(){

         String data[][] = {{ "Kamonyi", "Runda", "390"},
                 {"Kamonyi","Runda", "780"},
                 { "Kamonyi", "Rugazi","23"},
                 { "Kigali", "Nyagacaca","535"},
                 { "Kigali", "Bumbogo","724"},
                 { "Muhanga", "Nyamabuye","567"},
                 {"Muhanga", "Ruli","895"},
                 { "Rubavu", "Urumuri","734"},
                 { "Rubavu", "Nyanza","700000"},
                 { "Rubavu", "Gacurabwenge","700000"}
         };

         String columns[] = {"District", "Sector", "No. of houses"};

         Color color = new Color(98, 193, 178);
         JButton addButton = new JButton("Add");

         addButton.setBounds(10, 300, 400, 30);
         addButton.setBackground(color);
         addButton.setForeground(Color.WHITE);

         rightPanel.setBorder(new EmptyBorder(new Insets(5, 10, 10, 10)));
         DefaultTableModel model = new DefaultTableModel(data,columns);
         model.setColumnIdentifiers(columns);

         JTable table = new JTable(model);

         table.setRowHeight(40);
         table.getTableHeader().setOpaque(false);
         table.setShowGrid(false);
         table.getTableHeader().setReorderingAllowed(false);

         table.getTableHeader().setBackground(color);
         table.getTableHeader().setFont(new Font("roboto", Font.BOLD, 14));
         table.getTableHeader().setForeground(Color.WHITE);
         table.setFont(new Font("roboto", Font.ITALIC, 14));
         table.setDefaultRenderer(Object.class, new TableCellRenderer(){
             private final DefaultTableCellRenderer DEFAULT_RENDERER =  new DefaultTableCellRenderer();
             @Override
             public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                 Component c = DEFAULT_RENDERER.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                 if (row%2 == 0){
                     c.setBackground(new Color(255, 255, 255));
                 }
                 else {
                     c.setBackground(new Color(0, 253, 233, 24));
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
        rightPanel.add(jScrollPane);
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));

         this.setSize(800, 900);
         this.setVisible(true);
         this.add(rightPanel);
         rightPanel.add(addButton);

     }

       public static void main(String[] args){
            new SideMenuPanel();
           new Company();

       }

}


