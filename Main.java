
import Utils.AppConfig;
import Utils.DbHelper;
import models.LangEnum;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import models.User;
import repositories.UserRepo;

import java.util.Properties;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

//        primaryStage.setTitle("Hello World");
//        primaryStage.setScene(new Scene(new Button("OK")));
//        primaryStage.show();  
    }


    public static void main(String[] args) throws Exception {
        try {
            DbHelper.getConnection();
            System.out.println("Koneksioni me DB u krye me sukses");
            User u=new User(2,"Petrit","Luta","petrit.luta@gmail.com","rias","12asd",false);
            UserRepo.create(u);
        }catch(Exception ex){
            System.out.println("Deshtoi koneksioni me DB");

        }
//        launch(args);
    }
}
