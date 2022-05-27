package Config;

import java.sql.*;

public class DatabaseConnection {
    String driver = "com.mysql.jdbc.Driver";
    String url = "jdbc:mysql://remotemysql.com:3306/LGMxUJ3u44?characterEncoding=latin1";
    String username = "LGMxUJ3u44";
    String password = "gAzBLwXOq8";
    Connection connection = null;
    Statement statement = null;
    ResultSet data = null;

    public DatabaseConnection() {
        try{
            Class.forName(driver);
            connection = DriverManager.getConnection(url,username,password);

            if(connection != null)
                statement=connection.createStatement();

        }catch(Exception exception){
            System.out.println("CONNECTION TO DATABASE FAILED");
            exception.printStackTrace();
        }
    }

    public boolean createTable( String createStatement ) {
        try{
            statement.execute(createStatement);
            return true;
        }catch(SQLException exception) {
            return false;
        }
    }

    public boolean insert( String insertStatement ) {
        try{
            statement.execute( insertStatement );
            return true;
        }catch(SQLException exception){
            exception.printStackTrace();
            return false;
        }
    }

    public ResultSet select( String selectStatement ) {
        try{
            data=statement.executeQuery( selectStatement );
            return data;
        }catch (SQLException exception){
            return data;
        }
    }

    public boolean update( String updateStatement ) {
        try{
            statement.execute( updateStatement );
            return true;
        }catch (SQLException exception){
            return false;
        }
    }

    public boolean delete( String deleteStatement){
        try{
            statement.execute( deleteStatement );
            return true;
        }catch ( SQLException exception){
            return false;
        }
    }

    public ResultSet getById( String updateStatement ) {
        try{
            data = statement.executeQuery( updateStatement );
            return data;
        }catch (SQLException exception){
            return data;
        }
    }
}