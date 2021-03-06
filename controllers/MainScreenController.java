package controllers;

import Utils.AppConfig;
import Utils.DateHelper;
import Utils.SessionManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
//import components.AboutComponent;
import components.ErrorPopupComponent;
import javafx.stage.StageStyle;
import models.Car;
import models.LangEnum;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainScreenController extends BaseController {
    public static final String CARS_DETAILS_VIEW = "cars-details";
    public static final String CARS_LIST_VIEW = "cars-list";
    public static final String BUYERS_DETAILS_VIEW = "buyers-details";
    public static final String EXPIRED_LIST = "expired-list";
    public static final String VIEW_PATH = "../views";
    private ChildController childController = null;
    private String activeView = "";

    @FXML
    private Button navCarsButton;

    @FXML
    private Button navLogoutButton;
    @FXML
    private VBox contentPage;
    @FXML
    private Label statusLabel;
    @FXML
    private Label sectionLabel;
    @FXML
    private CheckMenuItem enCheckMenuItem;
    @FXML
    private CheckMenuItem alCheckMenuItem;
    @FXML
    private MenuItem userMenuItem;

    @FXML
    private CheckMenuItem enMenuItem;
    @FXML
    private CheckMenuItem alMenuItem;
    @FXML
    private MenuItem exitmenu;



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.initialize(location, resources);

        boolean enSelected = AppConfig.get().getLanguage() == LangEnum.EN;
        enCheckMenuItem.setSelected(enSelected);
        alCheckMenuItem.setSelected(!enSelected);


        String statusText = "User %s logged in at %s";
        String userr = SessionManager.employer.getFirst_name();
        String lastLoginn = DateHelper.toSqlFormat(SessionManager.lastLogin);
        statusLabel.setText(String.format(statusText, userr, lastLoginn));

    }


    public void setView(String view) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(this.viewPath(view)));
        Pane pane = loader.load();
        ChildController controller = loader.getController();
        setView(view, pane, controller);
    }

    public void setView(String view, Pane pane, ChildController controller) throws Exception {
        switch (view) {
            case CARS_DETAILS_VIEW:
                contentPage.setAlignment(Pos.TOP_CENTER);
                break;
            case CARS_LIST_VIEW:
                contentPage.setAlignment(Pos.TOP_LEFT);
                break;
            case BUYERS_DETAILS_VIEW:
                contentPage.setAlignment(Pos.TOP_CENTER);
                break;
            case EXPIRED_LIST:
                contentPage.setAlignment(Pos.TOP_CENTER);
                break;
            default:
                throw new Exception("ERR_VIEW_NOT_FOUND");
        }

        this.childController = controller;
        this.childController.setParentController(this);
        contentPage.getChildren().clear();
        contentPage.getChildren().add(pane);
        VBox.setVgrow(pane, Priority.ALWAYS);
    }

    private String viewPath(String view) {
        return VIEW_PATH + "/" + view + ".fxml";
    }

    @FXML
    public void onCarsBtnClick(ActionEvent ev) {
        try {
            this.setView(CARS_LIST_VIEW);
        } catch (Exception ex) {
            ErrorPopupComponent.show(ex);
            ex.printStackTrace();
        }
    }

    @FXML
    public void onExpiredBtnClick(ActionEvent ev) {
        try {
            this.setView(EXPIRED_LIST);
        } catch (Exception ex) {
            ErrorPopupComponent.show(ex);
            ex.printStackTrace();
        }
    }

    @FXML
    public void onInsertCarClick(ActionEvent ev) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../views/cars-details.fxml"));
            Pane pane = loader.load();
            CarsDetailsController controller = loader.getController();
            controller.setModel(new Car());
            controller.setEditable(true);

            this.setView(CARS_DETAILS_VIEW, pane, controller);
        } catch (Exception ex) {
            ErrorPopupComponent.show(ex);
            ex.printStackTrace();
        }
    }

    @FXML
    public void onAlMenuItemClick(ActionEvent ev) {
        enCheckMenuItem.setSelected(false);
        alCheckMenuItem.setSelected(true);

        updateLanguage();
    }

    @FXML
    public void onEnMenuItemClick(ActionEvent ev) {
        enCheckMenuItem.setSelected(true);
        alCheckMenuItem.setSelected(false);
        updateLanguage();
    }

    @FXML
    public void help(ActionEvent ev) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../views/partials/help.fxml"));

        Parent parent = loader.load();
        Scene scene = new Scene(parent);

        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("HELP");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initStyle(StageStyle.UTILITY);
        stage.showAndWait();
    }

    private void updateLanguage() {
        try {
            LangEnum lang = enCheckMenuItem.isSelected() ? LangEnum.EN : LangEnum.AL;
            AppConfig conf = AppConfig.get();
            conf.setLanguage(lang);
            ResourceBundle bundle = getLangBundle();
            loadLangTexts(bundle);
        } catch (Exception ex) {
            ErrorPopupComponent.show(ex);
        }
    }
    @Override
    public void loadLangTexts(ResourceBundle langBundle) {
        String navCarsTxt = langBundle.getString("main_nav_cars");
        String navEmployersTxt = langBundle.getString("main_nav_employers");
        String navLogoutTxt = langBundle.getString("main_nav_logout");
        String statusLabelTxt = langBundle.getString("main_status_label");

        String employer = SessionManager.employer.getEmail();
        String loginTime = DateHelper.toSqlFormat(SessionManager.lastLogin);
        statusLabel.setText(String.format(statusLabelTxt, employer, loginTime));
        navCarsButton.setText(navCarsTxt);
//        navEmployersButton.setText(navEmployersTxt);
        navLogoutButton.setText(navLogoutTxt);

        if (childController != null)
            childController.loadLangTexts(langBundle);

        switch (activeView) {
            case CARS_DETAILS_VIEW:
                sectionLabel.setText(langBundle.getString("main_nav_section_cars_details"));
                break;
            case CARS_LIST_VIEW:
                sectionLabel.setText(langBundle.getString("main_nav_section_cars_list"));
                break;
            case BUYERS_DETAILS_VIEW:
                sectionLabel.setText(langBundle.getString("main_nav_section_employers_details"));
                break;
            case EXPIRED_LIST:
                sectionLabel.setText(langBundle.getString("main_nav_section_employers_list"));
                break;
        }
    }
    @FXML
    public void Logout(ActionEvent event) throws IOException {
        Alert alert =new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Logout");
        alert.setHeaderText("You're about to logout");
        if(alert.showAndWait().get()==ButtonType.OK){
            Parent parent = FXMLLoader.load(getClass().getResource("../views/LoginView.fxml"));
            Scene scene = new Scene(parent);
            Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            primaryStage.show();
        }
    }
    @FXML
    public void exitfield(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("Exit");
        alert.setContentText("Are you sure you want to leave ?");
        if (alert.showAndWait().get() == ButtonType.OK) {
            System.exit(0);
        }
    }}
