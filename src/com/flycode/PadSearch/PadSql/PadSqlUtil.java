package com.flycode.PadSearch.PadSql;


import com.flycode.PadSearch.Constants.BuildingTable;
import com.flycode.PadSearch.Constants.Constants;
import com.flycode.PadSearch.Constants.OwnerTable;
import com.flycode.PadSearch.Constants.TenantTable;
import com.flycode.PadSearch.Entities.Building;
import com.flycode.PadSearch.Entities.Owner;
import com.flycode.PadSearch.Entities.Tenant;
import com.sun.xml.internal.bind.v2.runtime.reflect.opt.Const;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


//TODO: make PadSqlUtil independent form the MySqlHelper class to be able to connect to any other DBMS
public class PadSqlUtil extends MySqlHelper{
    private Statement stmt = null;
    public boolean CONNECTION_STATUS = false;
    private Connection con;
    private Object data;
    private Constants constants =  new Constants();

    public PadSqlUtil(String Username,String Pass){
        super(Username,Pass);
        ConnectDB();


    }

    private void ConnectDB(){
        if(super.connectDB(constants.DATABASE_NAME)) {
            con = super.getCon();
            try {
                stmt = con.createStatement();
            } catch (Exception ex) {
                System.out.println("Problem Creating Statement");
                ex.printStackTrace();
            }
            CONNECTION_STATUS = true;
        }
        else{
            CONNECTION_STATUS = false;
            System.out.println("Error in getting connection");
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
     * <p>Function copied and implemented from DbUtil</p>
     * */
    public ResultSet SelectTable(int tableNo){
        //TODO: make code better and shorter.
        String table;
        switch (tableNo){
            case 1://client_test table
                table = "client_test";
                break;
            case 2://Tenant table
                table = "tenant";
                break;
            case 3://Owner table
                table = "owner";
                break;
            case 4://Building table
                table = "building";
                break;
            default:
                table = "client_test";
        }
        return super.selectTable(table);
    }
}
