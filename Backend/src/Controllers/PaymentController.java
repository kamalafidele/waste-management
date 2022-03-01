package Controllers;

import java.io.DataOutputStream;
import java.io.IOException;

public class PaymentController {
    public void PaymentContoller(){};

    private DataOutputStream toClient;

    public void momoPayment(){
        // Sending response to the client
        sendResponse("Your momo payment has completed successfully");
    };

    public void filterRequest( String request, DataOutputStream toClient ) {
        this.toClient=toClient;
        switch (request.split("/")[1]) {
            case "momopayment":
                this.momoPayment();
                break;
        }
    }

    public void sendResponse( String response ) {
        try {
            toClient.writeUTF(response);
        } catch ( IOException exception ) {
            exception.printStackTrace();
        }
    }

}
