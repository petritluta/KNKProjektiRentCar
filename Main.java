
import Utils.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import models.Car;
import models.LangEnum;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import models.User;

import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Date;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent parent = FXMLLoader.load(getClass().getResource("views/main-screen.fxml"));
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) throws Exception {
         String[] brands = {"Audi","BMW","Mercedes"};
         String[] classification = {"Sedan","SUV","Cabriolet","Sports Car"};
        Connection conn = DbHelper.getConnection();
        String query = "INSERT INTO car (publisher,manufacture,model,price_per_day,avg_fuel_km,transmission,speed_limit,type,seat_num,door_num,inserted_at,updated_at, car_img) VALUES (?, ?, ?, ?, ?,?, ?, ?, ?, ?, ?, ?, ?)";

        for (int i = 0; i < brands.length; i++) {
            for (int j = 0; j < classification.length; j++) {
                for (int k = 1; k <= 6 ; k++) {
                    if(k==6 && brands[i] != "BMW") continue;
                    PreparedStatement stmt = conn.prepareStatement(query);
                    stmt.setInt(1, 1);
                    stmt.setInt(2, i+1);
                    stmt.setString(3, model.getModel());
                    stmt.setDouble(4, model.getPrice_per_day());
                    stmt.setDouble(5, model.getAvg_fuel_km());
                    stmt.setInt(6, model.getTransmission().getValue());
                    stmt.setDouble(7, model.getSpeed_limit());
                    stmt.setInt(8, model.getType().getValue());
                    stmt.setInt(9, model.getSeat_num());
                    stmt.setInt(10, model.getDoor_num());
                    stmt.setTimestamp(11, java.sql.Timestamp.valueOf(java.time.LocalDateTime.now()));
                    stmt.setTimestamp(12, java.sql.Timestamp.valueOf(DateHelper.toSqlFormat(model.getUpdated_at())));
                    stmt.setString(13,model.getCar_img());
                }
            }
        }
         DbHelper.getConnection();
         launch(args);
    }
}
