package com.flycode.PadSearch.PadSql;
import java.sql.*;

/**
 * Created by erikn on 12/5/2016
 * <p>
 * Class establishes connection with  database and contains various CRUD methods.
 * However this methods are not exhaustive of all possible CRUD methods that are
 * available.
 * </p>
 *<p>This class uses the DriverManager class instead of the DataSource class to establish a connection.
 *</p>
 *<p>The class first loads JDBC 4.0 drivers found within the class path.</p>
 *
 *
 */
public class MySqlHelper {
    private String Username;
    private String Pass;
    private Statement stmt = null;
    private Object data;
    private Connection con;
//    private String baseUrl = "jdbc:mysql://localhost:3306/";
//    private String url = baseUrl + constants.DATABASE_NAME;


    public MySqlHelper(String username, String pass){
        this.Username = username;
        this.Pass = pass;
    }

    public boolean connectDB(String db){
        //getConnection
        try{
            //TODO: find a way to get hostname and port automatically.
            Class.forName("com.mysql.jdbc.Driver").newInstance();
//            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/padsearch",Username,Pass);
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + db,Username,Pass);
            stmt = con.createStatement();
            return true;
        }catch (Exception ex) {
            System.out.println("Error in getting Mysql Connection");
            ex.printStackTrace();
            return false;
        }
    }

    public Connection getCon(){
        return con;
    }

    /**
     * <p>Creates a new table in the current connected database</p>
     * */
    public void createTable(String sql){
        try{
            stmt.executeQuery(sql);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    /**
     *
     * */
    public ResultSet selectTable(String table){
        ResultSet resultSet = null;
            try {
                resultSet = stmt.executeQuery("SELECT * FROM " + table);
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("Error in SelectTable");
            }
        return resultSet;
    }

    /**
     * <p>Function copied and implemented from DbUtil</p>
     * */
    public void doUpdate(String query){
        try{
            stmt.executeUpdate(query);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }


    public void closeConnection(){
        try {
            if(stmt != null)
                stmt.close();
            if(con != null)
                con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
