package Desktop.Screens.Citizen;

import DataHandlers.CompanyHandler;
import DataHandlers.DebtHandler;
import Desktop.Shared.RoundBtn;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import javax.management.ObjectName;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class Debts extends JPanel {
    private DataOutputStream toServer;
    private DataInputStream fromServer;
    private ObjectMapper mapper;
    JPanel panel = new JPanel();
    public Debts(DataOutputStream toServer, DataInputStream fromServer) throws IOException {
        setStreams(toServer,fromServer);
        mapper = new ObjectMapper();
        setVisible(false);
        setBounds(200, 0, 1166, 768);
        setBackground(Color.WHITE);
        setBorder(new EmptyBorder(new Insets(150, 30, 20, 30)));
        setDebtContainer();
    }
    public void setDebtContainer() throws IOException {
        panel.setBackground(new Color(234, 237, 243));
//        panel.setBackground(Color.red);
//        panel.setForeground(Color.white);
        panel.setPreferredSize(new Dimension(300,300));
        panel.setLayout(new GridBagLayout());
        panel.setBorder(new RoundBtn(10));
        String debt = getDebt(1);
        Label debtValue = new Label(debt);
        debtValue.setFont(new Font("Montserrat", Font.BOLD, 18));
        panel.add(debtValue);
//        getDebt(1);
        this.add(panel);
    }
    public void setPaymentMethod(){
        JButton payButton = new JButton("Recharge Balance");
        add(payButton);
    }

    public String getDebt(Integer userId) throws IOException {
        sendRequest("debt/getAll/" + userId);
        String response = fromServer.readUTF();
        String responseData = "";
        ArrayList<DebtHandler> debts = mapper.readValue(response,new TypeReference<ArrayList<DebtHandler>>(){});
        Iterator<DebtHandler> debtsIterator = debts.iterator();
        while (debtsIterator.hasNext()) {
            DebtHandler handler = debtsIterator.next();
            responseData += handler.getServiceName() + ": " + handler.getAmount();
        }
        return responseData;
    }

    public void setStreams(DataOutputStream toServer, DataInputStream fromServer) {
        this.toServer = toServer;
        this.fromServer = fromServer;
    }

    public void sendRequest( String request ){
        try{
            toServer.writeUTF( request );
        }catch ( IOException exception ){
            exception.printStackTrace();
        }
    }
}
