
import Utils.AppConfig;
import Utils.DbHelper;
import models.LangEnum;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.util.Properties;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

//        primaryStage.setTitle("Hello World");
//        primaryStage.setScene(new Scene(new Button("OK")));
//        primaryStage.show();
    }


    public static void main(String[] args) {
        try {
            DbHelper.getConnection();
            System.out.println("Koneksioni me DB u krye me sukses");
        }catch(Exception ex){
            System.out.println("Deshtoi koneksioni me DB");
        }
//        launch(args);
    }
}
