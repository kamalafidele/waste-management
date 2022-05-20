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

import Desktop.Application;

public class Company extends JFrame {

        private Application application = new Application();

        public void initialize() {
            application.close();

            setVisible(true);
            setTitle("LOG IN AS COMPANY");
            setSize(1366,760);
            setBackground(Color.YELLOW);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }
       Company() {
          viewWorkAreas();
       }

     public void viewWorkAreas(){

         String data[][] = {{"1", "Kamonyi", "Runda", "390"},
                 {"2", "Kamonyi","Runda", "780"},
                 {"3", "Kamonyi", "Rugazi","23"},
                 {"4", "Kigali", "Nyagacaca","535"},
                 {"5", "Kigali", "Bumbogo","724"},
                 {"6", "Muhanga", "Nyamabuye","567"},
                 {"7", "Muhanga", "Ruli","895"},
                 {"8", "Rubavu", "Urumuri","734"},
                 {"9", "Rubavu", "Nyanza","700000"},
                 {"10", "Rubavu", "Gacurabwenge","700000"}
         };

         String columns[] = {"id", "District", "Sector", "No. of houses"};


         JButton addButton = new JButton("Add");
         JPanel container = new JPanel();
         container.setBorder(new EmptyBorder(new Insets(5, 10, 10, 10)));
         DefaultTableModel model = new DefaultTableModel(data,columns);
         model.setColumnIdentifiers(columns);

         JTable table = new JTable(model);

         table.setRowHeight(40);
         table.getTableHeader().setOpaque(false);
        table.setShowGrid(false);
         table.getTableHeader().setReorderingAllowed(false);
         Color color = new Color(98, 193, 178);
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
         container.add(jScrollPane);
         container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));

         this.setSize(800, 900);
         this.setVisible(true);
         this.add(container);
     }
       public static void main(String[] args){
           new Company();
       }

}


