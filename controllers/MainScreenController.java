package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
//import components.AboutComponent;
//import components.ErrorPopupComponent;
import models.LangEnum;
//import models.Product;
import models.User;
//import models.UserRole;
//import utils.AppConfig;
//import utils.DateHelper;
//import utils.SessionManager;

import java.net.URL;
import java.util.ResourceBundle;

public class MainScreenController extends BaseController {
    public static final String CARS_DETAILS_VIEW = "cars-details";
    public static final String CARS_LIST_VIEW = "cars-list";
    public static final String BUYERS_DETAILS_VIEW = "buyers-details";
    public static final String BUYERS_LIST_VIEW = "buyers-list";
    private static final String VIEW_PATH = "../views";

    private BaseController childController;
    private String activeView = "";

    @FXML
    private Label navLabel;

    @FXML
    private Button carsButton;

    @FXML
    private Button navUsersButton;
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.initialize(location, resources);

//        boolean enSelected = AppConfig.get().getLanguage() == LangEnum.EN;
//        enCheckMenuItem.setSelected(enSelected);
//        alCheckMenuItem.setSelected(!enSelected);
//
//        // check role access
//        if (SessionManager.user.getRole() == UserRole.Employee) {
//            ((Pane) navUsersButton.getParent()).getChildren().remove(navUsersButton);
//            userMenuItem.getParentMenu().getItems().remove(userMenuItem);
//            userMenuItem.setOnAction(null);
//        }
    }

    public void setView(String view) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(this.viewPath(view)));
        Pane pane = loader.load();
        ChildController controller = loader.getController();
        setView(view, pane, controller);
    }


    public void setView(String view, Parent node, ChildController controller) throws Exception {
        childController = controller;
        controller.setParentController(this);
        contentPage.getChildren().clear();
        contentPage.getChildren().add(node);
        VBox.setVgrow(node, Priority.ALWAYS);

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
            case BUYERS_LIST_VIEW:
                contentPage.setAlignment(Pos.TOP_CENTER);
                break;
            default:
                throw new Exception("ERR_VIEW_NOT_FOUND");
        }

        activeView = view;
//        loadLangTexts(getLangBundle());
    }

    private String viewPath(String view) {
        return VIEW_PATH + "/" + view + ".fxml";
    }

    @FXML
    public void onCarsBtnClick(ActionEvent ev) {
        try {
            this.setView(CARS_LIST_VIEW);
        } catch (Exception ex) {
//            ErrorPopupComponent.show(ex);
            ex.printStackTrace();
        }
    }
//
//    @FXML
//    public void onBuyersNavClick(ActionEvent ev) {
//        try {
//            this.setView(BUYERS_LIST_VIEW);
//        } catch (Exception ex) {
//            ErrorPopupComponent.show(ex);
//        }
//    }
//
//    @FXML
//    public void onLogoutNavClick(ActionEvent ev) {
//        try {
//            Parent parent = FXMLLoader.load(getClass().getResource(viewPath("LoginView")));
//            Scene scene = new Scene(parent);
//
//            Stage primaryStage = (Stage) ((Node) ev.getSource()).getScene().getWindow();
//            primaryStage.setScene(scene);
//
//            SessionManager.user = null;
//            SessionManager.lastLogin = null;
//        } catch (Exception ex) {
//            ErrorPopupComponent.show(ex);
//        }
//    }
//
//    @FXML
//    public void onLogoutMenuClick(ActionEvent ev) {
//        try {
//            Parent parent = FXMLLoader.load(getClass().getResource(viewPath("LoginView")));
//            Scene scene = new Scene(parent);
//
//            Stage primaryStage = (Stage) statusLabel.getScene().getWindow();
//            primaryStage.setScene(scene);
//
//            SessionManager.user = null;
//            SessionManager.lastLogin = null;
//        } catch (Exception ex) {
//            ErrorPopupComponent.show(ex);
//        }
//    }
//
//    @FXML
//    public void onExitMenuClick(ActionEvent ev) {
//        try {
//            Stage primaryStage = (Stage) statusLabel.getScene().getWindow();
//            primaryStage.close();
//        } catch (Exception ex) {
//            ErrorPopupComponent.show(ex);
//        }
//    }
//
//    @FXML
//    public void onInsertCarClick(ActionEvent ev) {
//        try {
//            FXMLLoader loader = new FXMLLoader();
//            loader.setLocation(getClass().getResource(viewPath(CARS_DETAILS_VIEW)));
//            Parent node = loader.load();
//
//            CarsDetailsController controller = loader.getController();
//            controller.setModel(new Product());
//            controller.setEditable(true);
//
//            this.setView(CARS_DETAILS_VIEW, node, controller);
//        } catch (Exception ex) {
//            ErrorPopupComponent.show(ex);
//        }
//    }
//
//    @FXML
//    public void onInsertBuyerClick(ActionEvent ev) {
//        try {
//            FXMLLoader loader = new FXMLLoader();
//            loader.setLocation(getClass().getResource(viewPath(BUYERS_DETAILS_VIEW)));
//            Parent node = loader.load();
//
//            BuyersDetailsController controller = loader.getController();
//            controller.setModel(new User());
//
//            this.setView(BUYERS_DETAILS_VIEW, node, controller);
//        } catch (Exception ex) {
//            ErrorPopupComponent.show(ex);
//        }
//    }
//
//    @FXML
//    public void onHelpClick(ActionEvent ev) {
//        try { // qekjo duhet me kon HelpComponent
//            new AboutComponent().showDialog();
//        } catch (Exception ex) {
//            ErrorPopupComponent.show(ex);
//        }
//    }
//
//    @FXML
//    private void onChangeLangMenuItemEnClick(ActionEvent event) {
//        enCheckMenuItem.setSelected(true);
//        alCheckMenuItem.setSelected(false);
//        changeUILanguage();
//    }
//
//    @FXML
//    private void onChangeLangMenuItemAlClick(ActionEvent event) {
//        enCheckMenuItem.setSelected(false);
//        alCheckMenuItem.setSelected(true);
//        changeUILanguage();
//    }
//
//    private void changeUILanguage() {
//        try {
//            LangEnum lang = enCheckMenuItem.isSelected() ? LangEnum.EN : LangEnum.AL;
//            AppConfig.get().setLanguage(lang);
//
//            ResourceBundle langBundle = getLangBundle();
//            loadLangTexts(langBundle);
//            if (childController != null) childController.loadLangTexts(langBundle);
//        } catch (Exception e) {
//            ErrorPopupComponent.show(e);
//        }
//    }
//
//    @Override
//    public void loadLangTexts(ResourceBundle langBundle) {
//        String navLabelTxt = langBundle.getString("main_nav_label");
//        String navProductsTxt = langBundle.getString("main_nav_products");
//        String navUsersTxt = langBundle.getString("main_nav_users");
//        String navLogoutTxt = langBundle.getString("main_nav_logout");
//        String statusLabelTxt = langBundle.getString("main_status_label");
//
//        String user = SessionManager.user.getEmail();
//        String loginTime = DateHelper.toSqlFormat(SessionManager.lastLogin);
//
//        statusLabel.setText(String.format(statusLabelTxt, user, loginTime));
//        navLabel.setText(navLabelTxt);
//        navProductsButton.setText(navProductsTxt);
//        navUsersButton.setText(navUsersTxt);
//        navLogoutButton.setText(navLogoutTxt);
//
//        switch (activeView) {
//            case CARS_DETAILS_VIEW:
//                sectionLabel.setText(langBundle.getString("main_nav_section_product_details"));
//                break;
//            case CARS_LIST_VIEW:
//                sectionLabel.setText(langBundle.getString("main_nav_section_product_list"));
//                break;
//            case BUYERS_DETAILS_VIEW:
//                sectionLabel.setText(langBundle.getString("main_nav_section_user_details"));
//                break;
//            case BUYERS_LIST_VIEW:
//                sectionLabel.setText(langBundle.getString("main_nav_section_user_list"));
//                break;
//        }
//
//        if (childController != null) childController.loadLangTexts(langBundle);
//    }
        public void loadLangTexts(ResourceBundle langBundle){

        }
}
