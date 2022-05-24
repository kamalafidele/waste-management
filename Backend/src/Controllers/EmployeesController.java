package Controllers;

import Models.EmployeesRepo;

import java.io.DataOutputStream;
import java.sql.ResultSet;
public class EmployeesController {
    private EmployeesRepo employeesRepo;

   public void filterRequest(String request,DataOutputStream toClient){
       String path=request.split("/")[0];
       switch(request.split("/")[2]){
           case "allEmployees":
               getAllEmployees();
       }
   }
   public ResultSet getAllEmployees(){
       return employeesRepo.employees();
   }
}
