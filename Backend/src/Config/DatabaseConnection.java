package Config;

import java.sql.*;

public class DatabaseConnection {
    String driver="com.mysql.jdbc.Driver";
    String url="jdbc:mysql://localhost:3306/waste_management?characterEncoding=latin1";
    String username="root";
<<<<<<< HEAD
    String password="teta2005";
=======
    String password="nyabugogo";
>>>>>>> a63e6427dc8341b8b62aa6b184c0834aaf1c636d
    Connection connection=null;
    Statement statement=null;
    ResultSet data=null;

    public DatabaseConnection(){
        try{
          Class.forName(driver);
          connection= DriverManager.getConnection(url,username,password);

          if(connection != null)
              statement=connection.createStatement();

        }catch(Exception exception){
            System.out.println("CONNECTION TO DATABASE FAILED");
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
            statement.execute(insertStatement);
            return true;
        }catch(SQLException exception){
            return false;
        }
    }

    public ResultSet select( String selectStatement ) {
       try{
           data=statement.executeQuery(selectStatement);
           return data;
       }catch (SQLException exception){
            return data;
       }
    }

    public boolean update( String updateStatement ) {
        try{
            statement.execute(updateStatement);
            return true;
        }catch (SQLException exception){
            return false;
        }
    }
}
