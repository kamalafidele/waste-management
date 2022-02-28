package Repositories;

import Config.DatabaseConnection;
import Models.Citizen;

public class CitizenRepo {
    DatabaseConnection database;
    public CitizenRepo() { database = new DatabaseConnection(); }

    public void createCitizen(Citizen citizen) {
        database.insert("INSERT INTO users(fullName,national_id, phone_number, email, district,sector,cell,village) VALUES ("+citizen.getFullName()+", "+citizen.getNational_id()+","+citizen.getPhone_number()+","+citizen.getEmail()+","+citizen.getDistrict()+","+citizen.getSector()+", "+citizen.getCell()+", "+citizen.getVillage()+")");
    }
}
