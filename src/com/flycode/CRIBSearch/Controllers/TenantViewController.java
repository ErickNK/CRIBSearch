package com.flycode.CRIBSearch.Controllers;


import com.flycode.CRIBSearch.Dialogs.PadDialog;
import com.flycode.CRIBSearch.Dialogs.myDialog;
import com.flycode.CRIBSearch.PadSql.PadSqlUtil;
import com.flycode.CRIBSearch.interfaces.Updatable;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.util.Callback;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TenantViewController implements Updatable{
    @FXML private TableView tableView;
    @FXML private Label l_firstname;
    @FXML private Label l_secondname;
    @FXML private Label l_surname;
    @FXML private Label l_tell;
    @FXML private Label l_nationalID;
    @FXML private Label l_bio;
    @FXML private Button btn_update;
    @FXML private Button btn_delete;

    private PadSqlUtil padsql;
    private ResultSet resultSet;
    private ObservableList data = FXCollections.observableArrayList();
    private PadDialog padDialog = new PadDialog();

    public void initialize(PadSqlUtil p){
        padsql = p;
        fillTable();
        btn_update.setDisable(true);
        btn_delete.setDisable(true);
//         data.addListener();
    }

    private void fillTable(){
        try {
            buildData();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void onRefreshTable() {
        data.clear();
        tableView.getColumns().clear();
        try {
            buildData();
        } catch (SQLException e) {
            myDialog.showThrowable("Error","at onRefreshTable()",e);
        }
    }

    public void onMouseClickOnTable() {
        int index = tableView.getSelectionModel().getSelectedIndex();
        btn_update.setDisable(false);
        btn_delete.setDisable(false);
        if (index >= 0) {
            Object string = data.get(index);
            l_firstname.setText(String.valueOf(((ObservableList) string).get(1)));
            l_secondname.setText(String.valueOf(((ObservableList) string).get(2)));
            l_surname.setText(String.valueOf(((ObservableList) string).get(3)));
            l_tell.setText(String.valueOf(((ObservableList) string).get(4)));
            l_nationalID.setText(String.valueOf(((ObservableList) string).get(5)));
            l_bio.setText(String.valueOf(((ObservableList) string).get(6)));
        } else {
            l_firstname.setText("");
            l_secondname.setText("");
            l_surname.setText("");
            l_tell.setText("");
            l_nationalID.setText("");
            l_bio.setText("");
        }
    }

    public void onClickDeleteButton() {
        Object string = data.get(tableView.getSelectionModel().getSelectedIndex());
        String id = (String) ((ObservableList) string).get(0);
        try{
            padsql.DeleteTenant(id);
        }catch (Exception e){
            myDialog.showThrowable("Error","at onClickDeleteButton()",e);
        }
        onRefreshTable();
    }

    public void onClickUpdateButton() {
        ObservableList list = (ObservableList) data.get(tableView.getSelectionModel().getSelectedIndex());
        try {
            padDialog.tenantDialog("Update Tenant",list,padsql,2);
        }catch (Exception e) {
            myDialog.showThrowable("Error", "at onClickUpdateButton()", e);
        }

    }

    public void onClickAddButton() {
        try{
            padDialog.tenantDialog("Add a new tenant",null,padsql,1);
        }catch (Exception e){
            myDialog.showThrowable("Error","at onClickAddButton()",e);
            e.printStackTrace();
        }
    }

    private void buildData() throws SQLException {
        try{
            resultSet = padsql.selectTable("tenant"); //SELECT ALL THE COLUMNS
        } catch (Exception e){
            myDialog.showThrowable("Error","Error in loading table.",e);
        }


         //TABLE COLUMN ADDED DYNAMICALLY
        for (int i = 0; i < resultSet.getMetaData().getColumnCount(); i++) {
            //We are using non property style for making dynamic table
            final int j = i;
            TableColumn col = new TableColumn(resultSet.getMetaData().getColumnName(i + 1));
            col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
                public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList, String> param) {
                    return new SimpleStringProperty(param.getValue().get(j).toString());
                }
            });

            tableView.getColumns().addAll(col);

        }

         //Data added to ObservableList
        while (resultSet.next()) {
            //Iterate Row
            ObservableList<String> row = FXCollections.observableArrayList();
            for (int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++) {
                //Iterate Column
                row.add(resultSet.getString(i));
            }
            data.add(row);

        }

        //FINALLY ADDED TO TableView
        tableView.setItems(data);
    }
}
