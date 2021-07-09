package controllers;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class RentedCarController implements Initializable {
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
    private ComboBox payment;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        payment.getItems().removeAll(payment.getItems());
        payment.getItems().addAll("Cach","Bank");
    }
}