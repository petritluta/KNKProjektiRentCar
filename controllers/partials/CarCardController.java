package controllers.partials;

import components.ErrorPopupComponent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import models.Car;

import java.net.URL;
import java.util.ResourceBundle;

public class CarCardController implements Initializable {

    @FXML
    private Button deleteButton;
    @FXML
    private Button editButton;
    @FXML
    private Label model;
    @FXML
    private Label type;
    @FXML
    private Label transmission;
    @FXML
    private Label seatnum_doornum;
    @FXML
    private Label manufacture;
    @FXML
    private Label speedlimit;
    @FXML
    private Label avg_fuel_km;
    @FXML
    private Label price_per_day;
    @FXML
    private Label id;
    @FXML
    private ImageView img;

    @FXML
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    public void setCar(Car car) {
        try {
        id.setText("Identifier: " + car.getId());
        model.setText("Model: " +car.getModel());
        transmission.setText("Transmission: "+car.getTransmission());
        type.setText("Type: " + car.getType());
        seatnum_doornum.setText("door num: "+car.getDoor_num()+"/seat num:"+car.getSeat_num());
        manufacture.setText("manufacture id:"+car.getManufacture());
        speedlimit.setText("speed limit: " + car.getSpeed_limit());
        avg_fuel_km.setText("Fuel consumption: "+(int)car.getAvg_fuel_km());
        price_per_day.setText("Price per day: "+(int)car.getPrice_per_day());
        Image image = new Image(getClass().getResource("../../"+car.getCar_img()).toURI().toString());
        img.setImage(image);

        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    public void setOnEditAction(EventHandler<ActionEvent> handler) {
        this.editButton.setOnAction(handler);
    }

    public void setOnDeleteAction(EventHandler<ActionEvent> handler) {
        this.deleteButton.setOnAction(handler);
    }
}

