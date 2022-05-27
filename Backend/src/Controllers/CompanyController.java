package Controllers;

import Config.DatabaseConnection;
import Models.Company;
import Repositories.CompanyRepo;
import org.codehaus.jackson.map.ObjectMapper;
import Repositories.CustomerInvoicesRepo;

import java.io.DataOutputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CompanyController {
    private DataOutputStream toClient;
    private CompanyRepo companyRepo;
    private ObjectMapper mapper;
    AnalyticsController analyticsController;
    CustomerInvoicesRepo customerInvoice;
    private DatabaseConnection connection;

    public CompanyController(DatabaseConnection connection){
        this.connection = connection;
        companyRepo=new CompanyRepo(connection);
        analyticsController=new AnalyticsController(connection);
        customerInvoice = new CustomerInvoicesRepo(connection);
        mapper = new ObjectMapper();
    }

    // THIS METHOD DETERMINES WHAT OPERATION REQUESTED BY CLIENT
    public void filterRequest( String request, DataOutputStream toClient ) throws Exception {
        this.toClient=toClient;

        switch (request.split("/")[1]) {
            case "getAll":
                getCompanies();
              break;
            case "getSingle":
                getCompany(Long.valueOf(request.split("/")[2]));
              break;
            case "createContract":
                createContract(request.split("/")[2]);
            case "getInvoices":
                customerInvoice.getInvoices(Integer.parseInt(request.split("/")[2]), toClient);
                break;
            case "downloadInvoice":
                customerInvoice.downloadInvoice(Integer.parseInt(request.split("/")[2]), toClient);
                break;
            case "analytics":
                analyticsController.filterRequest(request, toClient);
                break;
            case "getCompaniesByDistrict":
                getCompaniesByDistrict(Long.valueOf(request.split("/")[2]));
            default:
                sendResponse("Please specify your request");
              break;
        }
    }

    public void getCompany(long companyId) {
       ResultSet resultSet = companyRepo.findById(companyId);
       Company company = extractCompany(resultSet);
       try {
           sendResponse(mapper.writeValueAsString(company));
       } catch (Exception exception){}
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

    public void getCompanies() {
        List<Company> companies = new ArrayList<>();
        ResultSet resultSet = companyRepo.findAll();
        try{

            while(resultSet.next()) {
                Company company = new Company(resultSet.getLong(1),resultSet.getString(2),resultSet.getString(3),
                resultSet.getLong(4),resultSet.getLong(5));
                companies.add(company);
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
                data[i][j++] = resultSet.getLong(5);

                i++;
            }

            sendResponse(mapper.writeValueAsString(data));

        }catch( IOException | SQLException exception ){}
    }

    public void createContract (String request) {
        System.out.println(request.split("-")[0]+" "+request.split("-")[1]);
        Long districtId = Long.valueOf(request.split("-")[0]);
        Long companyId = Long.valueOf(request.split("-")[1]);
        if(companyRepo.createContract(districtId,companyId))
          sendResponse("Contract created successfully");
        else
          sendResponse("Creating contract failed! Try again");
    }

    public void getCompaniesByDistrict(Long districtId) {
        ResultSet resultSet = companyRepo.findCompanyByDistrict(districtId);
        List<Company> companies = new ArrayList<>();

        try{
            while(resultSet.next()) {
                Company company = new Company(resultSet.getLong(4),resultSet.getString(5),resultSet.getString(7),
                        resultSet.getLong(6),resultSet.getLong(8));
                companies.add(company);
            }

            sendResponse(mapper.writeValueAsString(companies));

        }catch( IOException | SQLException exception ) {}

    }

    public void sendResponse( String response ) {
        try {
            toClient.writeUTF(response);
        } catch ( IOException exception ) {
            exception.printStackTrace();
        }
    }

    public Company extractCompany(ResultSet resultSet) {
        Company company = new Company();
        try{
            while(resultSet.next()) {
                company.setId(resultSet.getLong(1));
                company.setName(resultSet.getString(2));
                company.setEmail(resultSet.getString(3));
                company.setTin(resultSet.getLong(4));
                company.setWalletId(resultSet.getLong(5));
            }
        } catch (Exception exception){}

        return company;
    }
}
