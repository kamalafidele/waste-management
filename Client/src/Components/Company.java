package Components;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

// THIS COMPANY COMPONENT
public class Company {
    DataOutputStream outToServer;
    DataInputStream fromToServer;

    public Company() {
    }

    public void displayCompanies(DataOutputStream outToServer, DataInputStream fromToServer){
        this.outToServer=outToServer;
        this.fromToServer=fromToServer;

        String request="company/getAll/none";
        try{
            outToServer.writeUTF(request);
            String response=fromToServer.readUTF();
            System.out.println(response);

        }catch (IOException ex){}
    }
}
