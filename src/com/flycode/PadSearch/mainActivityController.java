package com.flycode.PadSearch;

import com.flycode.PadSearch.Dialogs.PadDialog;
import com.flycode.PadSearch.Dialogs.myDialog;
import com.flycode.PadSearch.Entities.Building;
import com.flycode.PadSearch.Entities.Owner;
import com.flycode.PadSearch.Entities.Tenant;
import com.flycode.PadSearch.PadSql.PadSqlUtil;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.util.Callback;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Locale;
import java.util.ResourceBundle;
public class mainActivityController implements Initializable {
    @FXML
    ComboBox<String> comboBox;
    @FXML
    TableView tableView;
    @FXML
    PasswordField password_field;
    @FXML
    TextField login_field;
    @FXML
    private Label info_label;
    @FXML
    private Tab tab_sheets;
    @FXML
    private Button loadButton;
    @FXML
    private Button deleteButton;

    private ResultSet resultSet;
    private ObservableList<String> tableNames = FXCollections.observableArrayList();
    private ObservableList data = FXCollections.observableArrayList();
    private String SelectedTable;
    private PadSqlUtil padsql;
    private ResourceBundle dialogResources = ResourceBundle.getBundle("com.flycode.PadSearch.resources.dialog", Locale.getDefault());


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void onClickLoginButton() {
        info_label.setVisible(true);
        info_label.setText("logging in...");
        info_label.setTextFill(Color.BLACK);
        padsql = new PadSqlUtil(login_field.getText(),password_field.getText());
        if (padsql.CONNECTION_STATUS) {
            tab_sheets.setDisable(false);
            info_label.setText("login Successful");
            info_label.setTextFill(Color.GREEN);
            fillComboBox();
            myDialog.showInfo(dialogResources.getString("LoginSuccessTitle"),dialogResources.getString("LoginSuccessMessage"));
        } else {
            tab_sheets.setDisable(true);
            info_label.setText("Login Not Successful");
            info_label.setTextFill(Color.RED);
            myDialog.showError(dialogResources.getString("LoginErrorTitle"),dialogResources.getString("LoginErrorMessage"));
        }
    }

    private void fillComboBox() {
        tableNames.addAll("client_test","tenant","owner","building");
        comboBox.setItems(tableNames);
    }

    public void onClickLoadButton() {
        data.clear();
        tableView.getColumns().clear();
        try {
            buildData(SelectedTable);
        } catch (SQLException e) {
            myDialog.showThrowable("Error","at onClickLoadButton()",e);
        }
    }

    public void onClickAddButton() {
        try{
            /*if (comboBox.getValue().equals("tenant")){
                Tenant tenant = newTenant();
                padsql.addTenant(tenant);
            }else if (comboBox.getValue().equals("owner")) {
                Owner owner = newOwner();
                padsql.addOwner(owner);
            }else if(comboBox.getValue().equals("building")){
                Building building = newBuilding();
                padsql.addBuilding(building);
            }*/
        }catch (Exception e){
            myDialog.showThrowable("Error","at onClickAddButton()",e);
        }
        onClickLoadButton();
    }

    public void onClickDeleteButton() {
        Object string = data.get(tableView.getSelectionModel().getSelectedIndex());
        String id = (String) ((ObservableList) string).get(0);
        try{
            if (comboBox.getValue().equals("tenant")){
                padsql.DeleteTenant(id);
            }else if(comboBox.getValue().equals("owner")){
                padsql.DeleteOwner(id);
            }else if(comboBox.getValue().equals("building")){
                padsql.DeleteBuilding(id);
            }
        }catch (Exception e){
            myDialog.showThrowable("Error","at onClickDeleteButton()",e);
        }
        onClickLoadButton();
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

    public void onClickUpdateButton() {
        try {
            Object string = data.get(tableView.getSelectionModel().getSelectedIndex());
            String id = (String) ((ObservableList) string).get(0);
            if (comboBox.getValue().equals("tenant")) {
                Tenant tenant = newTenant(string);
                PadDialog.tenantDialog("Update Tenant",tenant);
                padsql.UpdateTenant(tenant, id);
            } else if (comboBox.getValue().equals("owner")) {
                Owner owner = newOwner(string);
                PadDialog.ownerDialog("Update Owner",owner);
                padsql.UpdateOwner(owner, id);
            } else if (comboBox.getValue().equals("building")) {
                Building building = newBuilding(string);
                PadDialog.buildingDialog("Update Building",building);
                padsql.UpdateBuilding(building, id);
            }
        }catch (Exception e){
            myDialog.showThrowable("Error","at onClickUpdateButton()",e);
        }
        onClickLoadButton();
    }

    public void onSelectComboBoxTableName() {
        SelectedTable = comboBox.getValue();
        loadButton.setDisable(false);
        deleteButton.setDisable(false);
        onClickLoadButton();
    }

    private void buildData(String table) throws SQLException {
        try{
        resultSet = padsql.selectTable(table); //SELECT ALL THE COLUMNS
        } catch (Exception e){
            myDialog.showThrowable("Error","Error in loading table.",e);
        }

        /**********************************
         * TABLE COLUMN ADDED DYNAMICALLY *
         **********************************/
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

        /********************************
         * Data added to ObservableList *
         ********************************/
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

    private Tenant newTenant(Object string){
        Tenant tenant = new Tenant();
        tenant.setFirst((String) ((ObservableList) string).get(1));
        tenant.setSecond((String) ((ObservableList) string).get(2));
        tenant.setSurname((String) ((ObservableList) string).get(3));
        tenant.setTell(Integer.parseInt((String) ((ObservableList) string).get(4)));
        tenant.setNational_ID(Integer.parseInt((String) ((ObservableList) string).get(5)));
        tenant.setBio((String) ((ObservableList) string).get(6));
        return tenant;
    }
    private Owner newOwner(Object string){
        Owner owner = new Owner();
        owner.setFirst((String) ((ObservableList) string).get(1));
        owner.setSecond((String) ((ObservableList) string).get(2));
        owner.setSurname((String) ((ObservableList) string).get(3));
        owner.setNational_id(Integer.parseInt((String) ((ObservableList) string).get(4)));
        owner.setBio((String) ((ObservableList) string).get(5));
        owner.setTell(Integer.parseInt((String) ((ObservableList) string).get(6)));
        owner.setOwner_id(Integer.parseInt((String) ((ObservableList) string).get(7)));
        return owner;
    }
    private Building newBuilding(Object string){
        Building building = new Building();
        building.setRegistration_id(Integer.parseInt((String) ((ObservableList) string).get(1)));
        building.setName((String) ((ObservableList) string).get(2));
        building.setOwner_Name((String) ((ObservableList) string).get(3));
        building.setLicense((String) ((ObservableList) string).get(4));
        building.setLocation((String) ((ObservableList) string).get(5));
        building.setNo_of_rooms(Integer.parseInt((String) ((ObservableList) string).get(6)));
        return building;
    }


}
