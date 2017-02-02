package com.flycode.CRIBSearch;

import com.flycode.CRIBSearch.Controllers.mainActivityController;
import com.flycode.CRIBSearch.PadSql.MySqlHelper;
import com.flycode.CRIBSearch.PadSql.PadSqlUtil;
import com.flycode.CRIBSearch.SearchEngine.Demo.IndexFiles;
import com.flycode.CRIBSearch.SearchEngine.Demo.SearchFiles;
import com.flycode.CRIBSearch.SearchEngine.IndexDB;
import com.flycode.CRIBSearch.SearchEngine.Search;
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
        controller.initialize(padSql,primaryStage);

        primaryStage.setTitle("CRIBSearch Database");
        primaryStage.setScene(new Scene(root, 861, 572));
        primaryStage.show();
    }

    @Override
    public void stop() throws Exception{
        sqlHelper.closeConnection();
    }

    public static void main(String[] args) {
        String usage = "java com.flycode.CRIBSearch.Main"
                + " <options> \n\n"
                + "-test -> test wether the search engine is functional"
                + "-app -> normal app startup"
                + "-searchdemo -> start a small commandline search tool";
        for(int i=0;i<args.length;i++) {
            if ("-test".equals(args[i])) {
               Search s = new Search();
               s.doTest();
            } else if ("-app".equals(args[i])) {
                launch(args);
                i++;
            } else if ("-searchdemo".equals(args[i])){
                try{
                    IndexFiles.main(args);
                    SearchFiles.main(args);
                }catch (Exception e){
                    e.printStackTrace();
                }
            } else{
                System.out.println(usage);
            }
        }

    }
}
