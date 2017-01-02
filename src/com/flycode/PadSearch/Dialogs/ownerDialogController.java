package com.flycode.PadSearch.Dialogs;


import com.flycode.PadSearch.Entities.Owner;
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

    public void initialize(Owner owner, ResourceBundle resources,Stage s) {
        fillTextFields(owner);
        this.stage = s;
        this.bundle = resources;
    }

    private void fillTextFields(Owner o){
        this.owner= o;
        tf_FirstName.setText(owner.getFirst());
        tf_SecondName.setText(owner.getSecond());
        tf_Surname.setText(owner.getSurname());
        tf_tell.setText(String.valueOf(owner.getTell()));
        tf_nationalID.setText(String.valueOf(owner.getNational_id()));
        ta_bio.setText(owner.getBio());
        tf_ownerID.setText(String.valueOf(owner.getOwner_id()));
    }

    public void OnClickOkButton(){
        owner.setFirst(tf_FirstName.getText());
        owner.setSecond(tf_SecondName.getText());
        owner.setSurname(tf_SecondName.getText());
        owner.setTell(Integer.parseInt(tf_tell.getText()));
        owner.setNational_id(Integer.parseInt(tf_nationalID.getText()));
        owner.setBio(ta_bio.getText());
        owner.setOwner_id(Integer.parseInt(tf_ownerID.getText()));
        stage.close();
    }

    public void OnClickCancelButton(){
        stage.close();
    }

    public Owner result(){
        return owner;
    }
}
