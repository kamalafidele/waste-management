package Config;

import java.sql.*;

public class DatabaseConnection {
    String driver="com.mysql.jdbc.Driver";
    String url="jdbc:mysql://localhost:3306/waste_management";
    String username="root";
<<<<<<< HEAD
    String password="kayitare@123";
=======
    String password="teta2005";
>>>>>>> 9bf76e65478996b44cae5960b8618d780957a805
    Connection connection=null;
    Statement statement=null;
    ResultSet data=null;

    public DatabaseConnection(){
        try{
          connection= DriverManager.getConnection(url,username,password);

          if(connection != null)
              statement=connection.createStatement();

        }catch(Exception exception){
            System.out.println("Error: "+exception);
            System.out.println("CONNECTION TO DATABASE FAILED");
            exception.printStackTrace();
        }
    }

    public boolean createTable( String createStatement ) {
        try{
            statement.execute(createStatement);
            return true;
        }catch(SQLException exception){
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
