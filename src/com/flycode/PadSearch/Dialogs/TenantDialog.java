package com.flycode.PadSearch.Dialogs;


import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class TenantDialog {
    private static DialogPane root = new DialogPane();

    public static Parent setDialog(){
        root.setPrefHeight(271.0);
        root.setPrefWidth(549.0);
        setHeader();
        setContent();
        return root;
    }

    public static void setContent(){
        //TextFields
        TextField tf_FirstName = new TextField("First Name");
        tf_FirstName.setLayoutX(0.0);
        tf_FirstName.setLayoutY(0.0);

        TextField tf_SecondName = new TextField("Second Name");
        tf_FirstName.setLayoutX(109.0);
        tf_FirstName.setLayoutY(41.0);

        TextField tf_Surname = new TextField("Surname");
        tf_FirstName.setLayoutX(109.0);
        tf_FirstName.setLayoutY(76.0);

        TextField tf_nationalID = new TextField("National ID");
        tf_FirstName.setLayoutX(109.0);
        tf_FirstName.setLayoutY(140.0);

        TextField tf_tell = new TextField("Telephone Number");
        tf_FirstName.setLayoutX(109.0);
        tf_FirstName.setLayoutY(108.0);

        TextArea ta_bio = new TextArea("Something about the tenant");
        tf_FirstName.setLayoutX(383.0);
        tf_FirstName.setLayoutY(14.0);


        //Labels
        Label l_FirstName = new Label("First Name");
        l_FirstName.setLayoutX(10.0);
        l_FirstName.setLayoutY(14.0);
        l_FirstName.setLabelFor(tf_FirstName);

        Label l_SecondName = new Label("Second Name");
        l_SecondName.setLayoutX(10.0);
        l_SecondName.setLayoutY(45.0);
//        l_SecondName.setLabelFor(tf_SecondName);

        Label l_Surname = new Label("Surname");
        l_Surname.setLayoutX(10.0);
        l_Surname.setLayoutY(80.0);
//        l_Surname.setLabelFor(tf_Surname);

        Label l_Tell = new Label("Tell");
        l_Tell.setLayoutX(10.0);
        l_Tell.setLayoutY(112.0);
//        l_Tell.setLabelFor(tf_tell);

        Label l_NationalID = new Label("National ID");
        l_NationalID.setLayoutX(10.0);
        l_NationalID.setLayoutY(114.0);
 //       l_FirstName.setLabelFor(tf_nationalID);

        Label l_Bio = new Label("Bio");
        l_Bio.setLayoutX(355.0);
        l_Bio.setLayoutY(18.0);
//        l_FirstName.setLabelFor(ta_bio);

        HBox firstName = new HBox(4);

        VBox vBox = new VBox(8);
        vBox.prefHeight(200.0);
        vBox.prefWidth(286.0);
        vBox.getChildren().addAll(l_FirstName,tf_FirstName);


        AnchorPane pane = new AnchorPane();
 //       pane.getChildren().addAll(l_FirstName,l_SecondName/*,l_Surname,l_Tell,l_NationalID,l_Bio*/
 //                                 ,tf_FirstName/*,tf_SecondName,tf_Surname,tf_tell,tf_nationalID,ta_bio*/);
        pane.getChildren().add(vBox);
        pane.prefHeight(200.0);
        pane.prefWidth(320.0);
        root.setContent(pane);
    }

    public static void setHeader(){
        Label label1 = new Label("Add or modify tenant's record appropriately");
            label1.setLayoutX(10.0);
            label1.setLayoutY(8.0);
        AnchorPane pane = new AnchorPane();
        pane.prefHeight(30.0);
        pane.prefWidth(549.0);
        pane.getChildren().add(label1);
        root.setHeader(pane);
    }

    public static void setExpandableContent(){
        Label label1 = new Label("SQL:");
            label1.setLayoutX(10.0);
        TextArea textArea1 = new TextArea(null);

        AnchorPane pane = new AnchorPane();
            pane.prefHeight(154.0);
            pane.prefWidth(549.0);
        pane.getChildren().addAll(label1,textArea1);

        root.setExpandableContent(pane);
    }

}
