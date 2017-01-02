package com.flycode.PadSearch.Dialogs;

import com.flycode.PadSearch.Entities.Tenant;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.ResourceBundle;

public class tenantDialogController{
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
    private Stage stage;
    private ResourceBundle bundle;

    public void initialize(Tenant tenant, ResourceBundle resources,Stage s) {
        fillTextFields(tenant);
        this.stage = s;
        this.bundle = resources;
    }

    private void fillTextFields(Tenant t){
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
        tenant.setSecond(tf_SecondName.getText());
        tenant.setSurname(tf_Surname.getText());
        tenant.setTell(Integer.parseInt(tf_tell.getText()));
        tenant.setNational_ID(Integer.parseInt(tf_nationalID.getText()));
        tenant.setBio(ta_bio.getText());
        stage.close();
    }

    public void OnClickCancelButton(){
        stage.close();
    }

    /*public Tenant result(){
        return tenant;
    }*/
}
