package com.flycode.CRIBSearch.Dialogs;


import com.flycode.CRIBSearch.Entities.Owner;
import com.flycode.CRIBSearch.PadSql.PadSqlUtil;
import com.flycode.CRIBSearch.SearchEngine.IndexDB;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.ResourceBundle;

public class ownerDialogController{
    @FXML public Label l_FirstName;
    @FXML public Label l_SecondName;
    @FXML public Label l_Surname;
    @FXML public Label l_Tell;
    @FXML public Label l_NationalID;
    @FXML public Label l_Bio;
    @FXML public Label l_ownerID;
    @FXML private TextField tf_FirstName;
    @FXML private TextField tf_SecondName;
    @FXML private TextField tf_Surname;
    @FXML private TextField tf_nationalID;
    @FXML private TextField tf_tell;
    @FXML private TextArea ta_bio;
    @FXML private TextField tf_ownerID;

    private Owner owner;
    private Stage stage;
    private ResourceBundle bundle;
    private PadSqlUtil padSql;
    private String id;
    private int MODE;

    public void initialize(ObservableList l, ResourceBundle resources,Stage s,PadSqlUtil p,int mode) {
        stage = s;
        bundle = resources;
        padSql = p;
        MODE = mode;
        owner = newOwner(l);
        fillTextFields();
    }

    private Owner newOwner(Object string){
        Owner owner = new Owner();
        try {
            id = (String) ((ObservableList) string).get(0);
            owner.setId(Integer.parseInt((String) ((ObservableList) string).get(0)));
            owner.setFirst((String) ((ObservableList) string).get(1));
            owner.setSecond((String) ((ObservableList) string).get(2));
            owner.setSurname((String) ((ObservableList) string).get(3));
            owner.setNational_id(Integer.parseInt((String) ((ObservableList) string).get(4)));
            owner.setBio((String) ((ObservableList) string).get(5));
            owner.setTell(Integer.parseInt((String) ((ObservableList) string).get(6)));
            owner.setOwner_id(Integer.parseInt((String) ((ObservableList) string).get(7)));
        }catch (Exception e){
            if (MODE != 1){
                MODE = 1;
            }
        }
        return owner;
    }

    private void fillTextFields(){
        try {
            tf_FirstName.setText(owner.getFirst());
            tf_SecondName.setText(owner.getSecond());
            tf_Surname.setText(owner.getSurname());
            tf_tell.setText(String.valueOf(owner.getTell()));
            tf_nationalID.setText(String.valueOf(owner.getNational_id()));
            ta_bio.setText(owner.getBio());
            tf_ownerID.setText(String.valueOf(owner.getOwner_id()));
        }catch (Exception e){
            tf_FirstName.setText("");
            tf_SecondName.setText("");
            tf_Surname.setText("");
            tf_tell.setText("");
            tf_nationalID.setText("");
            ta_bio.setText("");
            tf_ownerID.setText("");
        }
    }

    private void commitChanges(){
        if (MODE == 1){
            padSql.addOwner(owner);
            IndexDB.addOwnerDoc(owner);
        }else if (MODE == 2){
            padSql.UpdateOwner(owner, id);
            IndexDB.updateOwnerDoc(owner);
        }else {
            myDialog.showError("ERROR","The MODE for dialog entries is either wrong or NULL");
        }
    }

    public void OnClickOkButton(){
        owner.setFirst(tf_FirstName.getText());
        owner.setSecond(tf_SecondName.getText());
        owner.setSurname(tf_SecondName.getText());
        owner.setTell(Integer.parseInt(tf_tell.getText()));
        owner.setNational_id(Integer.parseInt(tf_nationalID.getText()));
        owner.setBio(ta_bio.getText());
        owner.setOwner_id(Integer.parseInt(tf_ownerID.getText()));
        commitChanges();
        stage.close();
    }

    public void OnClickCancelButton(){
        stage.close();
    }
}
