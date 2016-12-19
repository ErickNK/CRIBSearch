package com.flycode.PadSearch;
import java.sql.*;

/**
 * Created by erikn on 12/5/2016.
 * <p>
 * Class establishes connection with  database and contains various CRUD methods.
 * However this methods are not exhaustive of all possible CRUD methods that are
 * available.
 * </p>
 *
 *
 */
public class MySqlHelper {
    private String Username;
    private String Pass;
    private Constants constants;
    private Statement stmt = null;
    private DatFeeder data;
//    private String baseUrl = "jdbc:mysql://localhost:3306/";
//    private String url = baseUrl + constants.DATABASE_NAME;
    
    public MySqlHelper(String username, String pass){
        this.Username = username;
        this.Pass = pass;
    }

    public boolean connectDB(){
        //getConnection
        try{
            //TODO: find a way to get hostname and port automatically.
            Class.forName("com.mysql.jdbc.Driver").newInstance();
//            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+constants.DATABASE_NAME+"/",Username,Pass);
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/padsearch",Username,Pass);
            stmt = con.createStatement();
            return true;
        }catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    //CRUD
    /**
     * <p>Function copied and implemented from DbUtil</p>
     * */
    public ResultSet SelectTable(int tableNo){
        //TODO: make selecting between tables possible
        if(tableNo == 1)
        try {
            ResultSet resultSet = stmt.executeQuery("SELECT * FROM client_test");
            return resultSet;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error in SelectTable");
        }
        return null;
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

    /**
     * Enter a new record at the end of the Client_table
     * */
    public void createRecord(DatFeeder data){
        this.data = data;
        try {
            stmt.executeQuery("INSERT INTO "+constants.TABLE_NAME+" ("
                    +constants.First_Column+","
                    +constants.Second_Column+","
                    +constants.Surname_Column+","
                    +constants.Tell_Column+","
                    +constants.ID_Column+","
                    +constants.Bio_Column+") VALUES ("+data.getFirst()+","
                                                      +data.getSecond()+","
                                                      +data.getSurname()+","
                                                      +data.getTell()+","
                                                      +data.getSocialSecurityNo()+","
                                                      +data.getBio()+");");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Updates Client_table records.
     * NOTE: the where clause must be given in string format. If omitted then
     * all the recodes will be updated, some may be updated to empty values.
     *
     * @param data Of DatFeeder class type used to parse data to table
     * @param where sql where clause to specify the record to update.
     *
     * * */
    public void UpdateRecord(DatFeeder data, String where){
        this.data = data;
        try {
            stmt.executeQuery("UPDATE "+constants.TABLE_NAME
                    +" SET "
                    +constants.First_Column+"="+data.getFirst()+", "
                    +constants.Second_Column+"="+data.getSecond()+", "
                    +constants.Surname_Column+"="+data.getSurname()+", "
                    +constants.Tell_Column+"="+data.getTell()+", "
                    +constants.ID_Column+"="+data.getSocialSecurityNo()+", "
                    +constants.Bio_Column+"="+data.getBio()
                    +" WHERE " + where + ";");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * <p>Sample!</p>
     * <p>Creates a new table in the current connected database</p>
     * */
    public void createTable(String Table_name){
        try{
            stmt.executeQuery("CREATE TABLE "+Table_name+" (" +
                    " PersonID int"          + "," +
                    " LastName varchar(255)" + "," +
                    " FirstName varchar(255)"+ "," +
                    " Address varchar(255)"  + "," +
                    " City varchar(255)" + " );");
        }catch (Exception e){
            e.printStackTrace();
        }

    }


    public void clossConnection(){
        try {
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
