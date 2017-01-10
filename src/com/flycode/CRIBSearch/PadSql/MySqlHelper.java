package com.flycode.CRIBSearch.PadSql;
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
    private Connection con;

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

    // ***************************/
    // *          C*R*U*D        */
    // ***************************/

    public void createTable(String sql){
        try{
            stmt.executeQuery(sql);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public ResultSet selectTable(String table){
        ResultSet resultSet = null;
        String sql = "SELECT * FROM " + table;
            try {
                resultSet = stmt.executeQuery(sql);
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("Error in selectTable()");
            }
        return resultSet;
    }

    public void updateTable(String sql){
        try{
            stmt.executeUpdate(sql);
        }catch (Exception ex){
            ex.printStackTrace();
            System.out.println("Error in updateTable()");
        }
    }

    public void insertRecord(String sql){
        try{
            stmt.executeUpdate(sql);
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Error in insertRecord()");
        }
    }

    public void deleteRecord(String sql){
        try{
            stmt.executeUpdate(sql);
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Error in deleteRecord()");
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

    public void setUsername(String username) {
        Username = username;
    }

    public void setPass(String pass) {
        Pass = pass;
    }
}
