package controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Utils.Authenticate;
import Utils.DbHelper;
import Utils.Notification;
import Utils.Security;
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
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import models.User;
import repositories.UserRepo;

public class RegisterViewController implements Initializable {
    @FXML
    private TextField firstName;
    @FXML
    private TextField lastName;
    @FXML
    private PasswordField passwordfield;
    @FXML
    private PasswordField confirmpassword;
    @FXML
    private TextField emailfield;
    @FXML
    private Button create;
    @FXML
    private Label created;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    private void openLogin(MouseEvent event) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("../views/LoginView.fxml"));
        Scene scene = new Scene(parent);
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    @FXML
    private void createclicked(ActionEvent event) throws Exception {

        if (firstName.getText().isBlank() == false && passwordfield.getText().isBlank() == false
                && confirmpassword.getText().isBlank() == false && emailfield.getText().isBlank() == false) {
            if ((passwordfield.getText().equals(confirmpassword.getText())) == false
            ) {
                created.setTextFill(Color.RED);
                created.setText("     Please confrim the password!");
            } else if (is_Valid_Password(passwordfield.getText()) == false) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Pasword must contain at least 8 characters,one letter and one digit");
                alert.showAndWait();
            } else if (is_Valid_Email(emailfield.getText()) == false) {
                created.setTextFill(Color.RED);
                created.setText("               Type a valid email");
            }
            else {
                try {
                    String emailF = emailfield.getText();
                    String passwordF = passwordfield.getText();
                    String firstNamee = firstName.getText();
                    String lastNamee = lastName.getText();
                    User user = new User(firstNamee, lastNamee,emailF,passwordF);


                    if (Authenticate.register(user)!=null) {

                        created.setTextFill(Color.GREEN);
                        created.setText("Your account has been created successfully");

                    } else {
                        String errStr="";
                        ArrayList<Notification> errors=Authenticate.getNotifications();
                        for (int i = 0; i < errors.size(); i++) {
                            errStr+="* "+errors.get(i).getMsg();
                        }
                        Authenticate.clearNotificaitons();
                        created.setTextFill(Color.RED);
                        created.setText(errStr);
                    }

                } catch (Exception ex) {
                    ex.printStackTrace();
                }

            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Invalid credentials!");
            alert.showAndWait();
        }


    }

    public static boolean is_Valid_Password(String password) {

        if (password.length() < 8) return false;

        int charCount = 0;
        int numCount = 0;
        for (int i = 0; i < password.length(); i++) {

            char ch = password.charAt(i);

            if (is_Numeric(ch)) numCount++;
            else if (is_Letter(ch)) charCount++;
            else return false;
        }


        return (charCount >= 1 && numCount >= 1);
    }

    public static boolean is_Letter(char ch) {
        ch = Character.toUpperCase(ch);
        return (ch >= 'A' && ch <= 'Z');
    }


    public static boolean is_Numeric(char ch) {

        return (ch >= '0' && ch <= '9');
    }

    public static boolean is_Valid_Email(String email) {
        boolean rez;
        String regex = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
        //Compile regular expression to get the pattern
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        if (matcher.matches() == true) {
            rez = true;
        } else
            rez = false;
        return rez;
    }

}