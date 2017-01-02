package com.flycode.PadSearch.Dialogs;

import com.flycode.PadSearch.Entities.Building;
import com.flycode.PadSearch.Entities.Owner;
import com.flycode.PadSearch.Entities.Tenant;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;

import java.util.ResourceBundle;

public class PadDialog extends Stage{
    private Scene scene;
    private static Parent root;


    public static class Builder {
        private PadDialog stage;
        private Parent dialog;
        private String type;
        private FXMLLoader fxmlLoader;


        private Builder create() {
            //Use resource bundles to allow different locale
            stage = new PadDialog();
            stage.setResizable(false);
            stage.initStyle(StageStyle.DECORATED);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setIconified(false);
            stage.centerOnScreen();
            return this;
        }

        private Builder createParent() throws Exception{
            //Parent node
            fxmlLoader = new FXMLLoader(getClass().getResource(type));
            root = /*dialog;*/fxmlLoader.load();
            stage.scene = new Scene(root);
            stage.setScene(stage.scene);
            return this;
        }

        private Builder setType(String t){
            this.type = t;
            return this;
        }

        public Builder setOwner(Window owner) {
            //TODO; set max height and width according to owner
            if (owner != null) {
                stage.initOwner(owner);
            }
            return this;
        }

        private Builder setTitle(String title) {
            stage.setTitle(title);
            return this;
        }

        public PadDialog build() {
            root.requestFocus();
            return stage;
        }

        public Builder setTenant(Tenant tenant) {
            tenantDialogController controller;
            try {
                controller = fxmlLoader.getController();
                controller.fillTextFields(tenant);
            }catch (Exception e){
                myDialog.showThrowable("Error!!!!","Error while filling TextFields",e);
            }
            return this;
        }

        public Builder setOwner2(Owner owner) {
            ownerDialogController controller;
            try {
                controller = fxmlLoader.getController();
                controller.fillTextFields(owner);
            }catch (Exception e){
                myDialog.showThrowable("Error!!!!","Error while filling TextFields",e);
            }
            return this;
        }

        public Builder setBuilding(Building building) {
            buildingDialogController controller;
            try {
                controller = fxmlLoader.getController();
                controller.fillTextFields(building);
            }catch (Exception e){
                myDialog.showThrowable("Error!!!!","Error while filling TextFields",e);
            }
            return this;
        }
    }

    public static void tenantDialog(String title,Tenant tenant,Window owner){
        try {
            new Builder()
                    .create()
                    .setType("tenantDialog.fxml")
                    .createParent()
                    .setTenant(tenant)
                    .setOwner(owner)
                    .setTitle(title)
                    .build()
                    .show();
        } catch (Exception e) {
            myDialog.showThrowable("Error","Error creating dialog",e);
        }
    }

    public static void tenantDialog(String title,Tenant tenant){
        tenantDialog(title,tenant,null);
    }

    //TODO; finish off the other dialogs.
    public static void ownerDialog(String title,Owner o,Window owner){
        try {
            new Builder()
                    .create()
                    .setType("ownerDialog.fxml")
                    .createParent()
                    .setOwner2(o)
                    .setOwner(owner)
                    .setTitle(title)
                    .build()
                    .show();
        } catch (Exception e) {
            myDialog.showThrowable("Error","Error creating dialog",e);
        }
    }

    public static void ownerDialog(String title,Owner o){
        ownerDialog(title,o,null);
    }

    public static void buildingDialog(String title,Building building,Window owner){
        try {
            new Builder()
                    .create()
                    .setType("buildingDialog.fxml")
                    .createParent()
                    .setBuilding(building)
                    .setOwner(owner)
                    .setTitle(title)
                    .build()
                    .show();
        } catch (Exception e) {
            myDialog.showThrowable("Error","Error creating dialog",e);
        }
    }

    public static void buildingDialog(String title,Building building){
        buildingDialog(title,building,null);
    }

}
