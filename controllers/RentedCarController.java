package controllers;
import Utils.DbHelper;
import Utils.Utils;
import components.ErrorPopupComponent;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import models.Car;

import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.time.Duration;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ResourceBundle;

public class RentedCarController extends ChildController {
    @FXML
    private TextField carfield;
    @FXML
    private TextField personfield;
    @FXML
    private DatePicker startdate;
    @FXML
    private DatePicker enddate;
    @FXML
    private TextField totalprice;
    @FXML
    private ComboBox<String> payment;
    private Car car;
    @FXML
    private Button rentButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        payment.getItems().removeAll(payment.getItems());
        payment.getItems().addAll("Cash","Bank Transfer","Checks","Card");
        payment.setValue("Cash");
    }

    public void setId(Car car)
    {
        this.carfield.setText(Integer.toString(car.getId()));
        this.car=car;
        this.carfield.setDisable(true);
        this.totalprice.setDisable(true);
    }
    @FXML
    private void onCancelButtonClick(ActionEvent event) {
        try {
            parentController.setView(MainScreenController.CARS_LIST_VIEW);
        } catch (Exception e) {
            ErrorPopupComponent.show(e);
        }
    }


    @FXML
    public void rentCar() throws Exception {
        rentButton.setDisable(true);
        Connection conn= DbHelper.getConnection();
        int carId=Integer.parseInt(this.carfield.getText());
        String personId=this.personfield.getText();
        Date start_date=Date.valueOf(this.startdate.getValue());
        Date end_date=Date.valueOf(this.enddate.getValue());
        Double total_price=Double.parseDouble(this.totalprice.getText());
        String payment_type=this.payment.getValue();
        PreparedStatement stmt=conn.prepareStatement("INSERT INTO rentedcar(car,person_id,start_date,end_date,total_price,payment_method) VALUES (?,?,?,?,?,?)");
        stmt.setInt(1,carId);
        stmt.setString(2,personId);
        stmt.setDate(3,start_date);
        stmt.setDate(4,end_date);
        stmt.setDouble(5,total_price);
        stmt.setString(6,payment_type);

        stmt.executeUpdate();
        parentController.setView(MainScreenController.CARS_LIST_VIEW);
    }

public void disableButton(){
    String personId=this.personfield.getText();
if(personId.length()!=10 || personId.contains("[a-zA-Z]+"))
{
    rentButton.setDisable(true);
}
else{
    rentButton.setDisable(false);
}
}
    public void insertEndDate()
    {
        String personId=this.personfield.getText();
        if(this.startdate.getValue()!=null  && this.enddate.getValue()!=null && personId.length()==10 && !personId.contains("[a-zA-Z]+"))
        {
            long daysBetween= ChronoUnit.DAYS.between(this.startdate.getValue(),this.enddate.getValue());
            if(daysBetween > 0){
                rentButton.setDisable(false);
                this.totalprice.setText(Double.toString(daysBetween*this.car.getPrice_per_day()));
            }
            else{
                rentButton.setDisable(true);
                this.totalprice.setText(Double.toString(0));
            }
        }
        else{
            rentButton.setDisable(true);
            this.totalprice.setText(Double.toString(0));

        }
    }
}