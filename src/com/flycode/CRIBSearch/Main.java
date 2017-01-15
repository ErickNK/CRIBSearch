package com.flycode.CRIBSearch;

import com.flycode.CRIBSearch.PadSql.MySqlHelper;
import com.flycode.CRIBSearch.PadSql.PadSqlUtil;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    private MySqlHelper sqlHelper = new MySqlHelper(null,null);
    private PadSqlUtil padSql = new PadSqlUtil(sqlHelper);

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("fxml/mainActivity.fxml"));
        Parent root = fxmlLoader.load();
        mainActivityController controller = fxmlLoader.getController();
        controller.initialize(padSql);

        primaryStage.setTitle("CRIBSearch Database");
        primaryStage.setScene(new Scene(root, 861, 572));
        primaryStage.show();
    }

    @Override
    public void stop() throws Exception{
        sqlHelper.closeConnection();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
