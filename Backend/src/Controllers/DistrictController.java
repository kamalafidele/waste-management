package Controllers;
import Config.DatabaseConnection;
import Models.District;
import Repositories.DistrictRepo;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.DataOutputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DistrictController {

    private DataOutputStream toClient;
    private final ObjectMapper mapper;
    private final DistrictRepo districtRepo;
    private DatabaseConnection connection;

    public DistrictController(DatabaseConnection connection) {
        this.connection = connection;
        this.mapper = new ObjectMapper();
        this.districtRepo = new DistrictRepo(connection);
    };

    public void handleRequest(String request, DataOutputStream toClient){
        this.toClient = toClient;

        switch (request.split("/")[1]){
            case "getDistricts":
                getDistricts();
                break;
            default:
                sendResponse("request not specified");
                break;
        }
    }

    private int getRowCount(ResultSet rs) {

        try {

            if(rs != null) {

                rs.last();

                return rs.getRow();
            }

        } catch (SQLException e) {

            System.out.println(e.getMessage());
            e.printStackTrace();
        }

        return 0;
    }

    private int getColumnCount(ResultSet rs) {

        try {

            if(rs != null)
                return rs.getMetaData().getColumnCount();

        } catch (SQLException e) {

            System.out.println(e.getMessage());
            e.printStackTrace();
        }

        return 0;
    }


    public void getDistricts() {
        List<District> districts = new ArrayList<>();
        ResultSet resultSet = districtRepo.findAll();
        try{

            while(resultSet.next()){
                District district= new District(resultSet.getLong(1),resultSet.getString(2),resultSet.getString(3)
                        ,resultSet.getLong(4));
                districts.add(district);
            }

            int rowCount = getRowCount(resultSet);
            int columnCount = getColumnCount(resultSet);

            Object data[][] = new Object[rowCount][columnCount];

            resultSet.beforeFirst();

            int i = 0;
            while(resultSet.next()){
                int j = 0;
                data[i][j++] = resultSet.getLong(1);
                data[i][j++] = resultSet.getString(2);
                data[i][j++] = resultSet.getString(3);
                data[i][j++] = resultSet.getLong(4);

                i++;
            }

            sendResponse(mapper.writeValueAsString(data));

        }catch( IOException | SQLException exception ){}
    }

    public void sendResponse(String response){
        try {
            toClient.writeUTF(response);
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

}












