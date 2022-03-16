package Components;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class Analytics {
    DataOutputStream toServer;
    DataInputStream fromServer;
    Scanner keyboard;

    public Analytics(DataOutputStream toServer, DataInputStream fromServer, Scanner keyboard){
        this.toServer = toServer;
        this.fromServer = fromServer;
        this.keyboard = keyboard;
    }

    public void getAnalytics() throws IOException {
        Integer choice = 0;
        System.out.println("---------- Analytics ----------");
        System.out.println("1. Get weekly analytics\n2. Get Monthly analytics\n3. Get yearly analytics");
        System.out.print("choose: ");
        choice = keyboard.nextInt();
        switch (choice){
            case 1:
                toServer.writeUTF("company/analytics/getWeekly/2");
                String response = fromServer.readUTF();
                System.out.println(response);
                break;
            case 2:
                toServer.writeUTF("company/analytics/getMonthly/2");
                System.out.println(fromServer.readUTF());
                break;
            case 3:
                toServer.writeUTF("company/analytics/getAnnual/2");
                System.out.println(fromServer.readUTF());
                break;
            default:
                System.out.println("Please enter a valid choice");
        }
    }
    public void downloadAnalytics() throws IOException {
        Integer choice = 0;
        System.out.println("---------- Download Analytics ----------");
        System.out.println("1. Download weekly analytics\n2. Download Monthly analytics\n" +
                "3. Download yearly analytics");
        System.out.print("choose: ");
        choice = keyboard.nextInt();
        switch (choice){
            case 1:
                toServer.writeUTF("company/analytics/download/2/week");
                String response = fromServer.readUTF();
                System.out.println(response);
                break;
            case 2:
                toServer.writeUTF("company/analytics/download/2/month");
                System.out.println(fromServer.readUTF());
                break;
            case 3:
                toServer.writeUTF("company/analytics/download/2/year");
                System.out.println(fromServer.readUTF());
                break;
            default:
                System.out.println("Please enter a valid choice");
        }
    }
}
