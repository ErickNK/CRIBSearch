package com.flycode.PadSearch.PadSql;
import com.flycode.PadSearch.Constants.BuildingTable;
import com.flycode.PadSearch.Constants.Constants;
import com.flycode.PadSearch.Constants.OwnerTable;
import com.flycode.PadSearch.Constants.TenantTable;
import com.flycode.PadSearch.Entities.Building;
import com.flycode.PadSearch.Entities.Owner;
import com.flycode.PadSearch.Entities.PadEntity;
import com.flycode.PadSearch.Entities.Tenant;

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
    private Constants constants;
    private Statement stmt = null;
    private PadEntity data;
    private Connection con;
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
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/padsearch",Username,Pass);
            stmt = con.createStatement();
            return true;
        }catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    // ***************************/
    // *          C*R*U*D        */
    // ***************************/


    //TODO: Make able to have only one method to create records for all entities.
    //CREATE RECORDS
    public void addTenant(Tenant data){
        this.data = data;
        TenantTable tenant_table = new TenantTable();
        try {
            stmt.executeQuery("INSERT INTO "+ tenant_table.TABLE_NAME+" ("
                    +tenant_table.Second_Column+","
                    +tenant_table.Third_Column+","
                    +tenant_table.Fourth_Column+","
                    +tenant_table.Fifth_Column+","
                    +tenant_table.Sixth_Column+","
                    +tenant_table.Seventh_Column+") VALUES ("
                    +data.getFirst()+","
                    +data.getSecond()+","
                    +data.getSurname()+","
                    +data.getTell()+","
                    +data.getNational_ID()+","
                    +data.getBio()+");");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void addBuilding(Building data){
        this.data = data;
        BuildingTable buildingTable = new BuildingTable();
        try {
            stmt.executeQuery("INSERT INTO "+buildingTable.TABLE_NAME+" ("
                    +buildingTable.Second_Column+","
                    +buildingTable.Third_Column+","
                    +buildingTable.Fourth_Column+","
                    +buildingTable.Fifth_Column+","
                    +buildingTable.Sixth_Column+","
                    +buildingTable.Seventh_Column+") VALUES ("
                    +data.getRegistration_id()+","
                    +data.getName()+","
                    +data.getOwner_Name()+","
                    +data.getLicense()+","
                    +data.getLocation()+","
                    +data.getNo_of_rooms()+");");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void addOwner(Owner data){
        this.data = data;
        OwnerTable ownerTable = new OwnerTable();
        try {
            stmt.executeQuery("INSERT INTO "+ownerTable.TABLE_NAME+" ("
                    +ownerTable.Second_Column+","
                    +ownerTable.Third_Column+","
                    +ownerTable.Fourth_Column+","
                    +ownerTable.Fifth_Column+","
                    +ownerTable.Sixth_Column+","
                    +ownerTable.Seventh_Column+","
                    +ownerTable.Eight_Column + ") VALUES ("
                    +data.getFirst()+","
                    +data.getSecond()+","
                    +data.getSurname()+","
                    +data.getNational_id()+","
                    +data.getTell()+","
                    +data.getBio()+","
                    +data.getOwner_id()+");");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    //TODO: Make delete method for all entities.
    //DELETE RECORDS
    public void DeleteTenant(String id){
        TenantTable tenantTable = new TenantTable();
        try{
            stmt.executeQuery("DELETE FROM " + tenantTable.TABLE_NAME
                    + " WHERE  id=" + id);
        }catch (Exception ex){
            System.out.println("An error occured when deleting record");
            ex.printStackTrace();
        }

    }
    public void DeleteOwner(String id){
        OwnerTable table = new OwnerTable();
        try{
            stmt.executeQuery("DELETE FROM " + table.TABLE_NAME
                    + " WHERE  id=" + id);
        }catch (Exception ex){
            System.out.println("An error occured when deleting record");
            ex.printStackTrace();
        }

    }
    public void DeleteBuilding(String id){
        BuildingTable table = new BuildingTable();
        try{
            stmt.executeQuery("DELETE FROM " + table.TABLE_NAME
                    + " WHERE  id=" + id);
        }catch (Exception ex){
            System.out.println("An error occured when deleting record");
            ex.printStackTrace();
        }

    }

    //TODO: Make update method for all entities.
    //UPDATE RECORDS
    /**
     * Updates Tenant records.
     * NOTE: the where clause must be given in string format. If omitted then
     * all the recodes will be updated, some may be updated to empty values.
     *  @param data Of PadEntity class type used to parse data to table
     * @param where sql where clause to specify the record to update.
     * */
    public void UpdateTenant(Tenant data, String where){
        this.data = data;
        TenantTable tenant_table = new TenantTable();
        try {
            stmt.executeQuery("UPDATE "+tenant_table.TABLE_NAME
                    +" SET "
                    +tenant_table.Second_Column+"="+data.getFirst()+", "
                    +tenant_table.Third_Column+"="+data.getSecond()+", "
                    +tenant_table.Fourth_Column+"="+data.getSurname()+", "
                    +tenant_table.Fifth_Column+"="+data.getTell()+", "
                    +tenant_table.Sixth_Column+"="+data.getNational_ID()+", "
                    +tenant_table.Seventh_Column+"="+data.getBio()
                    +" WHERE " + where + ";");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    /**
     * <p>Beta!</p>
     * <p>Creates a new table in the current connected database</p>
     * */
    public void createTable(String sql/*Table_name*/){
        try{
            stmt.executeQuery(sql);
            /*stmt.executeQuery("CREATE TABLE "+Table_name+" (" +
                    " PersonID int"          + "," +
                    " LastName varchar(255)" + "," +
                    " FirstName varchar(255)"+ "," +
                    " Address varchar(255)"  + "," +
                    " City varchar(255)" + " );");*/
        }catch (Exception e){
            e.printStackTrace();
        }

    }


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
