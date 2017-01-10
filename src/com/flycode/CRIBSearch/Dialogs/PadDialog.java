package com.flycode.CRIBSearch.Dialogs;

import com.flycode.CRIBSearch.PadSql.PadSqlUtil;
import javafx.collections.ObservableList;
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
    private Parent root;
    private ResourceBundle bundle = ResourceBundle.getBundle("com.flycode.CRIBSearch.resources.dialog");

    public  class Builder {
        private PadDialog stage;
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
            root = fxmlLoader.load();
            stage.scene = new Scene(root);
            stage.setScene(stage.scene);
            return this;
        }

        private Builder setType(String t){
            this.type = t;
            return this;
        }

        public Builder setWindowOwner(Window owner) {

            if (owner != null) {
                stage.initOwner(owner);
                stage.root.maxHeight(owner.getHeight());
                stage.root.maxWidth(owner.getWidth());
            }
            return this;
        }

        private Builder setTitle(String title) {
            stage.setTitle(title);
            return this;
        }

        public Builder setTenant(ObservableList list,PadSqlUtil p,int mode) {
            tenantDialogController controller;
            try {
                controller = fxmlLoader.getController();
                controller.initialize(list,bundle,stage,p,mode);
            }catch (Exception e){
                myDialog.showThrowable("Error!!!!","Error while initializing dialog",e);
            }
            return this;
        }

        public Builder setOwner(ObservableList list,PadSqlUtil p,int mode) {
            ownerDialogController controller;
            try {
                controller = fxmlLoader.getController();
                controller.initialize(list,bundle,stage,p,mode);
            }catch (Exception e){
                myDialog.showThrowable("Error!!!!","Error while filling TextFields",e);
            }
            return this;
        }

        public Builder setBuilding(ObservableList list,PadSqlUtil p,int mode) {
            buildingDialogController controller;
            try {
                controller = fxmlLoader.getController();
                controller.initialize(list,bundle,stage,p,mode);
            }catch (Exception e){
                myDialog.showThrowable("Error!!!!","Error while filling TextFields",e);
            }
            return this;
        }

        public PadDialog build() {
            root.requestFocus();
            return stage;
        }
    }

    public void tenantDialog(String title,ObservableList list,PadSqlUtil p,int mode,Window owner){
        try {
            new Builder()
                    .create()
                    .setType("tenantDialog.fxml")
                    .createParent()
                    .setTenant(list,p,mode)
                    .setWindowOwner(owner)
                    .setTitle(title)
                    .build()
                    .show();
        } catch (Exception e) {
            myDialog.showThrowable("Error","Error creating dialog",e);
        }
    }

    public void tenantDialog(String title,ObservableList list,PadSqlUtil p,int mode){
        tenantDialog(title,list,p,mode,null);
    }

    public void ownerDialog(String title,ObservableList list,PadSqlUtil p,int mode,Window owner){
        try {
            new Builder()
                    .create()
                    .setType("ownerDialog.fxml")
                    .createParent()
                    .setOwner(list,p,mode)
                    .setWindowOwner(owner)
                    .setTitle(title)
                    .build()
                    .show();
        } catch (Exception e) {
            myDialog.showThrowable("Error","Error creating dialog",e);
        }
    }

    public void ownerDialog(String title,ObservableList list,PadSqlUtil p,int mode){
        ownerDialog(title,list,p,mode,null);
    }

    public void buildingDialog(String title,ObservableList list,PadSqlUtil p,int mode,Window owner){
        try {
            new Builder()
                    .create()
                    .setType("buildingDialog.fxml")
                    .createParent()
                    .setBuilding(list,p,mode)
                    .setWindowOwner(owner)
                    .setTitle(title)
                    .build()
                    .show();
        } catch (Exception e) {
            myDialog.showThrowable("Error","Error creating dialog",e);
        }
    }

    public void buildingDialog(String title,ObservableList list,PadSqlUtil p,int mode){
        buildingDialog(title,list,p,mode,null);
    }

}
