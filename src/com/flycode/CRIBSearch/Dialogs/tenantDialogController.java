package com.flycode.CRIBSearch.Dialogs;

import com.flycode.CRIBSearch.Entities.Tenant;
import com.flycode.CRIBSearch.PadSql.PadSqlUtil;
import javafx.collections.ObservableList;
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
    private PadSqlUtil padsql;
    private String id;
    private int MODE;

    public void initialize(ObservableList l, ResourceBundle resources,Stage s,PadSqlUtil p,int mode) {
        stage = s;
        bundle = resources;
        padsql = p;
        MODE = mode;
        tenant = newTenant(l);
        fillTextFields();
    }

    private Tenant newTenant(Object string){
        Tenant tenant = new Tenant();
        try {
            id = (String) ((ObservableList) string).get(0);
            tenant.setFirst((String) ((ObservableList) string).get(1));
            tenant.setSecond((String) ((ObservableList) string).get(2));
            tenant.setSurname((String) ((ObservableList) string).get(3));
            tenant.setTell(Integer.parseInt((String) ((ObservableList) string).get(4)));
            tenant.setNational_ID(Integer.parseInt((String) ((ObservableList) string).get(5)));
            tenant.setBio((String) ((ObservableList) string).get(6));
        }catch (Exception e){
            if (MODE != 1){
                MODE = 1;
            }
        }
        return tenant;
    }

    private void fillTextFields(){
        try {
            tf_FirstName.setText(tenant.getFirst());
            tf_SecondName.setText(tenant.getSecond());
            tf_Surname.setText(tenant.getSurname());
            tf_tell.setText(String.valueOf(tenant.getTell()));
            tf_nationalID.setText(String.valueOf(tenant.getNational_ID()));
            ta_bio.setText(tenant.getBio());
        }catch (Exception e){
            tf_FirstName.setText("");
            tf_SecondName.setText("");
            tf_Surname.setText("");
            tf_tell.setText("");
            tf_nationalID.setText("");
            ta_bio.setText("");
        }
    }

    private void commitChanges(){
        if (MODE == 1){
            padsql.addTenant(tenant);
        }else if (MODE == 2){
            padsql.UpdateTenant(tenant,id);
        }else {
            myDialog.showError("ERROR","The MODE for dialog entries is either wrong or NULL");
        }
    }

    public void OnClickOkButton(){
        tenant.setFirst(tf_FirstName.getText());
        tenant.setSecond(tf_SecondName.getText());
        tenant.setSurname(tf_Surname.getText());
        tenant.setTell(Integer.parseInt(tf_tell.getText()));
        tenant.setNational_ID(Integer.parseInt(tf_nationalID.getText()));
        tenant.setBio(ta_bio.getText());
        commitChanges();
        stage.close();
    }

    public void OnClickCancelButton(){
        stage.close();
    }
}
