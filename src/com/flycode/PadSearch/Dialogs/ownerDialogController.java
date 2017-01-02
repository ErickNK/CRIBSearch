package com.flycode.PadSearch.Dialogs;


import com.flycode.PadSearch.Entities.Owner;
import com.flycode.PadSearch.Entities.Tenant;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class ownerDialogController implements Initializable{
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

    public void fillTextFields(Owner t){
        this.owner= t;
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
        owner.setSecond(tf_FirstName.getText());
        owner.setSurname(tf_FirstName.getText());
        owner.setTell(Integer.parseInt(tf_FirstName.getText()));
        owner.setNational_id(Integer.parseInt(tf_FirstName.getText()));
        owner.setBio(tf_FirstName.getText());
        owner.setOwner_id(Integer.parseInt(tf_ownerID.getText()));
    }

    public Owner result(){
        return owner;
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {}
}
