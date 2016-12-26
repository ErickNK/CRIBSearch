package com.flycode.PadSearch.Dialogs;

import com.flycode.PadSearch.Entities.Tenant;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;

import java.util.Observable;
import java.util.ResourceBundle;

public class PadDialog extends Stage{
    private Scene scene;
    private static Parent root;


    public static class Builder {
        private PadDialog stage;
        private String dialog;
        private FXMLLoader fxmlLoader;


        private Builder create() throws Exception {
            //Use resource bundles to allow different locale
            stage = new PadDialog();
            stage.setResizable(false);
            stage.initStyle(StageStyle.DECORATED);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setIconified(false);
            stage.centerOnScreen();

            //Parent node
            fxmlLoader = new FXMLLoader(getClass().getResource(dialog));
            root = fxmlLoader.load();
            stage.scene = new Scene(root);
            stage.setScene(stage.scene);
            return this;
        }

        public Builder setOwner(Window owner) {
            if (owner != null) {
                stage.initOwner(owner);
                /*stage.borderPanel.setMaxWidth(owner.getWidth());
                stage.borderPanel.setMaxHeight(owner.getHeight());*/
            }
            return this;
        }

        private Builder setType(String dialog) {
            this.dialog = dialog;
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
            ResourceBundle bundle = ResourceBundle.getBundle("com.flycode.PadSearch.resources.dialog");
            tenantDialogController controller = new tenantDialogController();
            controller.initialize(getClass().getResource(dialog),bundle);
            fxmlLoader.setController(controller);
            controller.fillTextFields(tenant);
            return this;
        }
    }

    public static void tenantDialog(String title,Tenant tenant,Window owner){
        try {
            new Builder()
                    .setType("tenantDialog.fxml")
                    .create()
                    .setOwner(owner)
                    .setTitle(title)
                    .setTenant(tenant)
                    .build()
                    .show();
        } catch (Exception e) {
            myDialog.showThrowable("Error","Error creating dialog",e);
        }
    }

    public static void tenantDialog(String title,Tenant tenant){
        tenantDialog(title,tenant,null);
    }

    public static void ownerDialog(String title,Window owner){
        try {
            new Builder()
                    .setType("ownerDialog.fxml")
                    .create()
                    .setOwner(owner)
                    .setTitle(title)
                    .build()
                    .show();
        } catch (Exception e) {
            myDialog.showThrowable("Error","Error creating dialog",e);
        }
    }

    public static void ownerDialog(String title){
        ownerDialog(title,null);
    }

    public static void buildingDialog(String title,Window owner){
        try {
            new Builder()
                    .setType("buildingDialog.fxml")
                    .create()
                    .setOwner(owner)
                    .setTitle(title)
                    .build()
                    .show();
        } catch (Exception e) {
            myDialog.showThrowable("Error","Error creating dialog",e);
        }
    }

    public static void buildingDialog(String title){
        buildingDialog(title,null);
    }

}
