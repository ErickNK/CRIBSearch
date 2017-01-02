package com.flycode.PadSearch.Dialogs;

import com.flycode.PadSearch.Entities.Building;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class buildingDialogController implements Initializable{
    @FXML public Label l_RegistrationID;
    @FXML public Label l_Name;
    @FXML public Label l_OwnerName;
    @FXML public Label l_License;
    @FXML public Label l_Location;
    @FXML public Label l_NosOfRooms;
    @FXML private TextField tf_registrationID;
    @FXML private TextField tf_Name;
    @FXML private TextField tf_OwnerName;
    @FXML private TextField tf_Location;
    @FXML private TextField tf_License;
    @FXML private TextField tf_NosOfRooms;
    private Building building;

    public void fillTextFields(Building b){
        this.building = b;
        tf_registrationID.setText(String.valueOf(building.getRegistration_id()));
        tf_Name.setText(building.getName());
        tf_OwnerName.setText(building.getOwner_Name());
        tf_License.setText(String.valueOf(building.getLicense()));
        tf_Location.setText(String.valueOf(building.getLocation()));
        tf_NosOfRooms.setText(String.valueOf(building.getNo_of_rooms()));
    }

    public void OnClickOkButton(){
        building.setRegistration_id(Integer.parseInt(tf_registrationID.getText()));
        building.setName(tf_Name.getText());
        building.setOwner_Name(tf_OwnerName.getText());
        building.setLicense(tf_License.getText());
        building.setLocation(tf_Location.getText());
        building.setNo_of_rooms(Integer.parseInt(tf_NosOfRooms.getText()));
    }

    public Building result(){
        return building;
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {}
}
