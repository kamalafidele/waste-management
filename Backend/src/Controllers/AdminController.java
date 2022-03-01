package Controllers;
import java.io.DataOutputStream;

public class AdminController {

    private DataOutputStream toClient;

    public AdminController(){};

    public void handleRequest(String request, DataOutputStream toClient){
        this.toClient = toClient;

        switch (request.split("/")[1]){
            case "login":
                
                break;
        }
    }

    public void login(String request, DataOutputStream toClient){

    }


}
