package com.flycode.PadSearch.Dialogs;

import com.flycode.PadSearch.Entities.Tenant;
import com.flycode.PadSearch.PadSql.PadSqlUtil;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class tenantDialogController implements Initializable{
    @FXML public Label l_FirstName;
    @FXML public Label l_SecondName;
    @FXML public Label l_Surname;
    @FXML public Label l_Tell;
    @FXML public Label l_NationalID;
    @FXML public Label l_Bio;
    @FXML private TextField tf_FirstName;
    @FXML private TextField tf_SecondName;
    @FXML private TextField tf_Surname;
    @FXML private TextField tf_nationalID;
    @FXML private TextField tf_tell;
    @FXML private TextArea ta_bio;
    @FXML Button bt_ok;
    @FXML Button bt_cancel;

    private PadSqlUtil padsql;

    private Tenant newTenant(){
        Tenant tenant = new Tenant();
        tenant.setFirst(tf_FirstName.getText());
        tenant.setSecond(tf_SecondName.getText());
        tenant.setSurname(tf_Surname.getText());
        tenant.setTell(Integer.parseInt(tf_tell.getText()));
        tenant.setNational_ID(Integer.parseInt(tf_nationalID.getText()));
        tenant.setBio(ta_bio.getText());
        return tenant;
    }

    public void fillTextFields(Tenant tenant){
        tf_FirstName.setText(tenant.getFirst());
        tf_SecondName.setText(tenant.getSecond());
        tf_Surname.setText(tenant.getSurname());
        tf_tell.setText(String.valueOf(tenant.getTell()));
        tf_nationalID.setText(String.valueOf(tenant.getNational_ID()));
        ta_bio.setText(tenant.getBio());
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {}
}
