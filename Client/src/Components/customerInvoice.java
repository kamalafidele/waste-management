package Components;

import DataHandlers.CompanyHandler;
import DataHandlers.CustomerInvoicesHandler;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.util.JSONPObject;
import org.codehaus.jackson.type.TypeReference;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class customerInvoice  {
    ObjectMapper mapper;

    public void createTable (String response) throws Exception {
        System.out.println("######################### REGISTERED COMPANIES ###################################### ");
        System.out.println("|------------|-------------|-----------|---------|----------------|-------------------|");
        System.out.println("| Invoice Id |   User Name |  Service  |  Amount |  Invoice date  |  Generation Time  |");
        System.out.println("|------------|-------------|-----------|---------|----------------|-------------------|\n");
        System.out.println(response);
    }

    public void mainMethod() throws Exception {

        Socket socket = new Socket("localhost",2500);
        OutputStream req = socket.getOutputStream();
        DataOutputStream toServer = new DataOutputStream(req);

        InputStream res = socket.getInputStream();
        DataInputStream fromServer =  new DataInputStream(res);
        String response;

        System.out.println("\t\t\t --------------------------------------------");
        System.out.println("\t\t\t+             INVOICE MANAGEMENT            + ");
        System.out.println("\t\t\t --------------------------------------------");
        System.out.println("\t\t\t|| 1.  VIEW INVOICES               ||");
        System.out.println("\t\t\t|| 2.  DOWNLOAD INVOICE                 ||");
        System.out.println("\t\t\t|| 0.  BACK                 ||");
        System.out.println("\t\t\t---------------------------------------------");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter choice: ");
        int choice = scanner.nextInt();
       switch (choice){
           case 1 -> {
               System.out.println("Company > Invoices > View all invoices.");
               toServer.writeUTF("company/getInvoices/1");
               response = fromServer.readUTF();
               createTable(response);
               break;
           }
           case 2 -> {
               System.out.println("Company > Invoices > Download invoice.");
               toServer.writeUTF("company/downloadInvoice/1");
               response = fromServer.readUTF();
               System.out.println(response);
               break;
           }
           default ->System.out.println(" \t\t\t\t Invalid input");

       }
    }
}