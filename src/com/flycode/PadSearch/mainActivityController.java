package com.flycode.PadSearch;

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
import java.util.ResourceBundle;
public class mainActivityController extends DbUtil implements Initializable {
    @FXML
    ComboBox<String> comboBox;
    @FXML
    TableView tableView;
    @FXML
    PasswordField password_field;
    @FXML
    TextField login_field;
    @FXML
    private TextField textField1;
    @FXML
    private TextField textField2;
    @FXML
    private TextField textField3;
    @FXML
    private TextField textField4;
    @FXML
    private TextField textField5;
    @FXML
    private Label info_label;
    @FXML
    private Label label1;
    @FXML
    private Label label2;
    @FXML
    private Label label3;
    @FXML
    private Label label4;
    @FXML
    private Label label5;
    @FXML
    private Label label6;
    @FXML
    private Tab tab_sheets;
    @FXML
    private Button loadButton;
    @FXML
    private Button deleteButton;

    private ResultSet resultSet;
    private ObservableList<String> tableNames = FXCollections.observableArrayList();
    private ObservableList data = FXCollections.observableArrayList();
    private String tableName;
    private Constants constants;
    private MySqlHelper sqlhelp;


    public void onClickLoginButton() {
        info_label.setVisible(true);
        info_label.setText("logging in...");
        info_label.setTextFill(Color.BLACK);
        sqlhelp = new MySqlHelper(login_field.getText(),password_field.getText());
        if (sqlhelp.connectDB()) {
            tab_sheets.setDisable(false);
            info_label.setText("login Successful");
            info_label.setTextFill(Color.GREEN);
            fillComboBox();
        } else {
            tab_sheets.setDisable(true);
            info_label.setText("Login Not Successful");
            info_label.setTextFill(Color.RED);
        }
    }

    private void fillComboBox() {
        try{
            resultSet = sqlhelp.SelectTable(1);
        } catch (Exception e){
            System.out.println("Problem with constants class");
            e.printStackTrace();
        }
        if (resultSet != null) try {
            while (resultSet.next()) {
                String name = "client_test";//resultSet.getString(1);
                tableNames.add(name);
            }
        } catch (SQLException e) {
            System.out.println("Error in fillCombo");
            e.printStackTrace();
        }
        if (conn != null)
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        comboBox.setItems(tableNames);

    }

    public void onClickLoadButton() {
        data.clear();
        tableView.getColumns().clear();
        try {
            buildData();
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
    }

    public void onClickAddButton() {
        String query = "insert into " + constants.TABLE_NAME + " values (null,\"" + textField1.getText() + "\",\"" + textField2.getText() + "\",\"" + textField3.getText() + "\")";
        sqlhelp.doUpdate(query);
        onClickLoadButton();
    }

    public void onClickDeleteButton() {
        Object string = data.get(tableView.getSelectionModel().getSelectedIndex());
        String id = (String) ((ObservableList) string).get(0);

        sqlhelp.doUpdate("delete from " + constants.TABLE_NAME + " where id=" + id);
        onClickLoadButton();

    }

    public void onMouseClickOnTable() {
        int index = tableView.getSelectionModel().getSelectedIndex();
        if (index >= 0) {
            Object string = data.get(index);
            textField1.setText(String.valueOf(((ObservableList) string).get(1)));
            textField2.setText(String.valueOf(((ObservableList) string).get(2)));
            textField3.setText(String.valueOf(((ObservableList) string).get(3)));
            textField4.setText(String.valueOf(((ObservableList) string).get(3)));
            textField5.setText(String.valueOf(((ObservableList) string).get(3)));
        } else {
            textField1.setText("");
            textField2.setText("");
            textField3.setText("");
            textField4.setText("");
            textField5.setText("");
        }
    }

    public void onClickUpdateButton() {
        Object string = data.get(tableView.getSelectionModel().getSelectedIndex());
        String id = (String) ((ObservableList) string).get(0);
        String query = "update " + constants.TABLE_NAME + " set " + label1.getText() + "=\"" + textField1.getText() + "\"," + label2.getText() + "=\"" + textField2.getText()
                + "\"," + label3.getText() + "=\"" + textField3.getText() + "\"" + " where id=" + id;
        sqlhelp.doUpdate(query);
        onClickLoadButton();
    }

    public void onSelectComboBoxTableName() {
        tableName = comboBox.getValue();
        loadButton.setDisable(false);
        deleteButton.setDisable(false);
        onClickLoadButton();
    }


    //TODO: fix buildData() to inflate TableView correctly
    private void buildData() throws SQLException {
        try{
        resultSet = sqlhelp.SelectTable(1); //SELECT ALL THE COLUMNS
        } catch (Exception e){

            System.out.println("Cannot retrieve data from database");
            e.printStackTrace();
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
        label1.setText(resultSet.getMetaData().getColumnName(2));
        label2.setText(resultSet.getMetaData().getColumnName(3));
        label3.setText(resultSet.getMetaData().getColumnName(4));

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

        if (conn != null)
            conn.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }


}
