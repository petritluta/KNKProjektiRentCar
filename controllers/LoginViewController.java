package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import Utils.Security;
import Utils.SessionManager;
import components.ErrorPopupComponent;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import models.User;
import repositories.UserRepo;

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
            try {
                User user = null;
                String emailF = usernanme.getText();
                String passwordF = password.getText();

                if(hasUsers()) {
                    user = login(emailF, passwordF);
                }
                else {
                    //me qit exception qe me dal te regjistrimi
                }

                if (user == null) throw new Exception("Invalid credentials");
                SessionManager.employer = user;
                SessionManager.lastLogin = new Date();


                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("../views/main-screen.fxml"));

                Parent parent = loader.load();
                MainScreenController controller = loader.getController();
//                controller.setView(MainScreenController.CARS_LIST_VIEW);

                Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Scene scene = new Scene(parent);
                primaryStage.setScene(scene);
                primaryStage.setResizable(false);

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Username and password fields are requaired!");
            alert.showAndWait();
        }


    }

    private boolean hasUsers() throws Exception {
        return UserRepo.count() > 0;
    }

    private User login(String email, String password) throws Exception {
        User user = UserRepo.find(email);

        if (user == null)
        {
            return user; //qetu na me qit ni tekst mi than regjistroju}
        }

        String hashedPassword = Security.hashPassword(password, user.getSalt());
        if (!user.getPassword().equals(hashedPassword)) return null;

        return user;
    }

    @FXML
    private void cancelclicked(ActionEvent event) throws Exception {
        Alert alert =new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("Exit");
        alert.setContentText("Are you sure you want to leave ?");
        if(alert.showAndWait().get()==ButtonType.OK){
        Stage stage=(Stage) cancel.getScene().getWindow();
        stage.close();
        }
    }

    @FXML
    private void createaccountclicked(MouseEvent event) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("../views/Register.fxml"));
        Scene scene = new Scene(parent);
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    @Override
    public void loadLangTexts(ResourceBundle langBundle) {
        usernanme.setPromptText(langBundle.getString("login_username"));
        password.setPromptText(langBundle.getString("login_password"));
        try {
            String buttonText = langBundle.getString(hasUsers() ?
                    "login_button_login" : "login_button_register");
        } catch (Exception ex) {
            ErrorPopupComponent.show(ex);
        }
    }
}