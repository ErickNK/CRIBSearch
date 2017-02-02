package com.flycode.CRIBSearch.Dialogs;

import com.flycode.CRIBSearch.Entities.Building;
import com.flycode.CRIBSearch.PadSql.PadSqlUtil;
import com.flycode.CRIBSearch.SearchEngine.IndexDB;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.ResourceBundle;

public class buildingDialogController{
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
    private Stage stage;
    private ResourceBundle bundle;
    private PadSqlUtil padsql;
    private String id;
    private int MODE;


    public void initialize(ObservableList l, ResourceBundle resources, Stage s,PadSqlUtil p,int mode) {
        bundle = resources;
        stage = s;
        padsql = p;
        MODE = mode;
        building = newBuilding(l);
        fillTextFields();
    }

    private Building newBuilding(Object string){
        Building building = new Building();
        try {
            id = (String) ((ObservableList) string).get(0);
            building.setId(Integer.parseInt((String) ((ObservableList) string).get(0)));
            building.setRegistration_id(Integer.parseInt((String) ((ObservableList) string).get(1)));
            building.setName((String) ((ObservableList) string).get(2));
            building.setOwner_Name((String) ((ObservableList) string).get(3));
            building.setLicense((String) ((ObservableList) string).get(4));
            building.setLocation((String) ((ObservableList) string).get(5));
            building.setNo_of_rooms(Integer.parseInt((String) ((ObservableList) string).get(6)));
        }catch (Exception e){
            if (MODE != 1){
                MODE = 1;
            }
        }
        return building;
    }

    private void fillTextFields(){
        try {
            tf_registrationID.setText(String.valueOf(building.getRegistration_id()));
            tf_Name.setText(building.getName());
            tf_OwnerName.setText(building.getOwner_Name());
            tf_License.setText(String.valueOf(building.getLicense()));
            tf_Location.setText(String.valueOf(building.getLocation()));
            tf_NosOfRooms.setText(String.valueOf(building.getNo_of_rooms()));
        }catch (Exception e){
            tf_registrationID.setText("");
            tf_Name.setText("");
            tf_OwnerName.setText("");
            tf_License.setText("");
            tf_Location.setText("");
            tf_NosOfRooms.setText("");
        }
    }

    private void commitChanges(){
        if (MODE == 1){
            padsql.addBuilding(building);
            IndexDB.addBuildingDoc(building);
        }else if (MODE == 2){
            padsql.UpdateBuilding(building, id);
            IndexDB.updateBuildingDoc(building);
        }else {
            myDialog.showError("ERROR","The MODE for dialog entries is either wrong or NULL");
        }
    }

    public void OnClickOkButton(){
        building.setRegistration_id(Integer.parseInt(tf_registrationID.getText()));
        building.setName(tf_Name.getText());
        building.setOwner_Name(tf_OwnerName.getText());
        building.setLicense(tf_License.getText());
        building.setLocation(tf_Location.getText());
        building.setNo_of_rooms(Integer.parseInt(tf_NosOfRooms.getText()));
        commitChanges();
        stage.close();
    }

    public void OnClickCancelButton(){
        stage.close();
    }

}
