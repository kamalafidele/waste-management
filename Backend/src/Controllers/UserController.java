package Controllers;

import Models.User;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.DataOutputStream;
import java.io.IOException;

public class UserController {
    private ObjectMapper mapper = new ObjectMapper();
    private DataOutputStream toClient;

    public void filterRequest( String request, DataOutputStream toClient ) throws Exception {
        this.toClient = toClient;

        switch (request.split("/")[1]) {
            case "login":
                login(request.split("/")[2]);
                break;
            default:
                sendResponse("Please specify your request");
                break;
        }
    }

    public void login(String data) {
        try {
            User user = mapper.readValue(data, User.class);

        } catch (Exception exception) { }
    }

    public void sendResponse( String response ) {
        try {
            toClient.writeUTF(response);
        } catch ( IOException exception ) {
            exception.printStackTrace();
        }
    }
}
