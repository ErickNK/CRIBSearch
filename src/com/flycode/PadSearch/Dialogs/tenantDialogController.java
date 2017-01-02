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
   private Tenant tenant;

    public void fillTextFields(Tenant t){
        this.tenant = t;
        tf_FirstName.setText(tenant.getFirst());
        tf_SecondName.setText(tenant.getSecond());
        tf_Surname.setText(tenant.getSurname());
        tf_tell.setText(String.valueOf(tenant.getTell()));
        tf_nationalID.setText(String.valueOf(tenant.getNational_ID()));
        ta_bio.setText(tenant.getBio());
    }

    public void OnClickOkButton(){
        tenant.setFirst(tf_FirstName.getText());
        tenant.setSecond(tf_FirstName.getText());
        tenant.setSurname(tf_FirstName.getText());
        tenant.setTell(Integer.parseInt(tf_FirstName.getText()));
        tenant.setNational_ID(Integer.parseInt(tf_FirstName.getText()));
        tenant.setBio(tf_FirstName.getText());
    }

    public Tenant result(){
        return tenant;
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {}
}
