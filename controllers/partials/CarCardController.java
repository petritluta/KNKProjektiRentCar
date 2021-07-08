package controllers.partials;

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
        id.setText("Identifier: " + car.getId());
        model.setText(car.getModel());
        transmission.setText(car.getTransmission().toString());
        type.setText(car.getType().toString());
        seatnum_doornum.setText("door num:"+car.getDoor_num()+"/seat num:"+car.getSeat_num());
        manufacture.setText("manufacture id:"+car.getManufacture());
        speedlimit.setText("speed limit:: " + car.getSpeed_limit());
        avg_fuel_km.setText("Average fuel consumption:"+car.getAvg_fuel_km());
        price_per_day.setText("Price per day:"+car.getPrice_per_day());
        Image image=new Image(car.getCar_img());
        img.setImage(image);
    }
    public void setOnEditAction(EventHandler<ActionEvent> handler) {
        this.editButton.setOnAction(handler);
    }

    public void setOnDeleteAction(EventHandler<ActionEvent> handler) {
        this.deleteButton.setOnAction(handler);
    }
}

