package ClientServer;

import Models.RequestBody;
import Models.ResponseBody;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;


public class Application {
    public ResponseBody ConnectToServer(RequestBody requestBody) throws Exception {
        //Establishing a connection by providing host and port number
        try (Socket socket = new Socket("localhost", 2500)) {
            //writing to the server
            ObjectOutputStream toServer = new ObjectOutputStream(socket.getOutputStream());

            //reading from the server
            ObjectInputStream fromServer = new ObjectInputStream(socket.getInputStream());

            //SENDING THE USER INPUT TO SERVER
            toServer.writeObject(requestBody);
            toServer.flush();

            //DISPLAYING THE SERVER REPLY
            List<Object> returnedData = (List<Object>) fromServer.readObject();

            ResponseBody responseBody = new ResponseBody(returnedData);
            return responseBody;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}


