package com.flycode.PadSearch;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class MainWindow extends Application {


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        //Creation of a button and register an event when clicked
        Button btn1 = new Button("Say hello");
        btn1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Hello World");
            }
        });

        StackPane root = new StackPane();
        root.getChildren().add(btn1);

        Scene scene = new Scene(root, 300,300);
        primaryStage.setTitle("PadSearch Management Center");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    @Override
    public void init(){

    }
}
