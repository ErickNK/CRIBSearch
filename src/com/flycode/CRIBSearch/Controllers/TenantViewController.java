package com.flycode.CRIBSearch.Controllers;


import com.flycode.CRIBSearch.Dialogs.myDialog;
import com.flycode.CRIBSearch.PadSql.PadSqlUtil;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.util.Callback;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TenantViewController {
    @FXML TableView tableView;
    @FXML private Label l_firstname;
    @FXML private Label l_secondname;
    @FXML private Label l_surname;
    @FXML private Label l_tell;
    @FXML private Label l_nationalID;
    @FXML private Label l_bio;
    private PadSqlUtil padsql;
    private ResultSet resultSet;
    private ObservableList data = FXCollections.observableArrayList();

    public void initialize(PadSqlUtil p){
        padsql = p;
        fillTable();
    }

    private void fillTable(){
        try {
            buildData("tenant");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void onRefreshTable() {
        data.clear();
        tableView.getColumns().clear();
        try {
            buildData("tenant");
        } catch (SQLException e) {
            myDialog.showThrowable("Error","at onRefreshTable()",e);
        }
    }

    public void onMouseClickOnTable() {
        /*int index = tableView.getSelectionModel().getSelectedIndex();
        if (index >= 0) {
            Object string = data.get(index);
            textField1.setText(String.valueOf(((ObservableList) string).get(1)));
            textField2.setText(String.valueOf(((ObservableList) string).get(2)));
            textField3.setText(String.valueOf(((ObservableList) string).get(3)));
            textField4.setText(String.valueOf(((ObservableList) string).get(4)));
            textField5.setText(String.valueOf(((ObservableList) string).get(5)));
            textField6.setText(String.valueOf(((ObservableList) string).get(6)));
        } else {
            textField1.setText("");
            textField2.setText("");
            textField3.setText("");
            textField4.setText("");
            textField5.setText("");
            textField6.setText("");
        }*/
    }

    private void buildData(String table) throws SQLException {
        try{
            resultSet = padsql.selectTable(table); //SELECT ALL THE COLUMNS
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
