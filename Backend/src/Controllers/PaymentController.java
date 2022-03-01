package Controllers;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.List;

public class PaymentController {

    private DataOutputStream toClient;

    public void momoPayment(){
        // Sending response to the client
        sendResponse("Server listened to your request");
    };

    public void filterRequest( String request, DataOutputStream toClient ) {
        this.toClient=toClient;
        switch (request.split("/")[1]) {
            case "momopayment":
                this.momoPayment();
                break;
            case "checkSecurityDebt":
                checkSecurityDebt();

        }
    }
    public void checkSecurityDebt(){

    }
    public void sendResponse( String response ) {
        try {
            toClient.writeUTF(response);
        } catch ( IOException exception ) {
            exception.printStackTrace();
        }
    }

}
