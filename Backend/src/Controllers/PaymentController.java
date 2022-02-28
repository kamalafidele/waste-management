package Controllers;

import Repositories.PaymentRepo;

import java.io.*;

public class PaymentController {
    private DataOutputStream toClient;
    private PaymentRepo paymentRepo;
    public PaymentController() {
        paymentRepo=new PaymentRepo();
    }
    //determine which payment request sent by client(Audax)
    public void checkRequest(String request, DataOutputStream toClient) throws IOException{
        this.toClient=toClient;
        switch (request.split("/")[1]){
            case "checksecurityDebt":
                checkDebt(toClient,"Security");
                break;
            case "checkwasteDebt":
                checkDebt(toClient,"Waste");
        }
    }
    public void checkDebt(DataOutputStream toClient,String type) throws IOException{
        System.out.println("you have zero in security debt");
    }
}
