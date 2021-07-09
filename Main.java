
import Utils.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import models.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import repositories.CarRepo;

import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Date;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent parent = FXMLLoader.load(getClass().getResource("views/LoginView.fxml"));
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) throws Exception {
        launch(args);
    }

    private static void insertCarRecords(){
        try {
            String[] brands = {"Audi", "BMW", "Mercedes"};
            String[] classification = {"Sedan", "SUV", "Cabriolet", "Sports Car"};
            String[] model = {"A6", "X5", "S Class"};
            Connection conn = DbHelper.getConnection();
            System.out.println("U konektu");
            String query = "INSERT INTO car (publisher,manufacture,model,price_per_day,avg_fuel_km,transmission,speed_limit,type,seat_num,door_num,inserted_at,updated_at, car_img) VALUES (?, ?, ?, ?, ?,?, ?, ?, ?, ?, ?, ?, ?)";
            String imageLocation = "resources/images/cars/";
            for (int i = 0; i < brands.length; i++) {
                for (int j = 0; j < classification.length; j++) {
                    for (int k = 1; k <= 6; k++) {
                        if (k == 6 && brands[i] != "BMW") continue;
                        PreparedStatement stmt = conn.prepareStatement(query);
                        stmt.setInt(1, 1);
                        stmt.setInt(2, i + 1);
                        stmt.setString(3, model[i]);
                        stmt.setDouble(4, 50 + Math.random() * 20);
                        stmt.setDouble(5, 5 + Math.random() * 5);
                        stmt.setInt(6, Transmission.Auto.getValue());
                        stmt.setDouble(7, 300);
                        stmt.setInt(8, CarRepo.stringToType(classification[j]).getValue());
                        stmt.setInt(9, 5);
                        stmt.setInt(10, 4);
                        stmt.setTimestamp(11, java.sql.Timestamp.valueOf(java.time.LocalDateTime.now()));
                        stmt.setTimestamp(12, java.sql.Timestamp.valueOf(java.time.LocalDateTime.now()));
                        stmt.setString(13, imageLocation + brands[i] + "/" + classification[j] + "/" + k + ".jpg");
                        if (stmt.executeUpdate() != 1)
                            throw new Exception("ERR_NO_ROW_CHANGE");
                    }
                }


            }
        } catch (Exception ex) {
            System.out.println("Deshtoi");
        }
    }
}



