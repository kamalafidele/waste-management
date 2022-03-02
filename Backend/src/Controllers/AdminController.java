package Controllers;
import Models.Admin;
import Repositories.AdminRepo;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.DataOutputStream;
import java.io.IOException;

public class AdminController {

    private DataOutputStream toClient;
    private ObjectMapper mapper;
    private AdminRepo adminRepo;

    public AdminController(){
        this.mapper = new ObjectMapper();
        this.adminRepo = new AdminRepo();
    };

    public void handleRequest(String request, DataOutputStream toClient){
        this.toClient = toClient;

        switch (request.split("/")[1]){
            case "login":
                login(request.split("/")[2]);
                break;
        }
    }

    public void login(String data){
        try {
            Admin admin = mapper.readValue(data, Admin.class);

            if(adminRepo.login(admin.getUsername(), admin.getPassword())){
                sendResponse("admin loggedIn");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendResponse(String response){
        try {
            toClient.writeUTF(response);
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

}
