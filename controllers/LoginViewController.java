package controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class LoginViewController extends BaseController{
    @FXML
    private TextField usernanme;
    @FXML
    private PasswordField password;

    @FXML
    private Button login;

    @FXML
    private Button cancel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    @FXML
    private void loginclicked (ActionEvent event) throws Exception {
        if (usernanme.getText().isBlank()==false && password.getText().isBlank()==false) {
            Parent parent = FXMLLoader.load(getClass().getResource("try.fxml"));
            Scene scene = new Scene(parent);
            Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            primaryStage.setScene(scene);
            primaryStage.show();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Username and password fields are requaried!");
            alert.showAndWait();
        }
    }
    @FXML
    private void cancelclicked(ActionEvent event) throws Exception {
        Stage stage=(Stage) cancel.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void createaccountclicked(MouseEvent event) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("../views/Register.fxml"));
        Scene scene = new Scene(parent);
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @Override
    public void loadLangTexts(ResourceBundle langBundle) {
        usernanme.setPromptText(langBundle.getString("login_email"));
        password.setPromptText(langBundle.getString("login_password"));
//        try {
//            String buttonText = langBundle.getString(hasUsers() ?
//                    "login_button_login" : "login_button_register");
//        } catch (Exception ex) {
//            ErrorPopupComponent.show(ex);
//        }
    }

}