package controllers;

import Utils.AppConfig;
import Utils.DateHelper;
import Utils.SessionManager;
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
import components.ErrorPopupComponent;
import models.LangEnum;
import models.User;
import Utils.AppConfig;
import Utils.DateHelper;
import Utils.SessionManager;

import java.net.URL;
import java.util.ResourceBundle;

public class MainScreenController extends BaseController {
    public static final String CARS_DETAILS_VIEW = "cars-details";
    public static final String CARS_LIST_VIEW = "cars-list";
    public static final String BUYERS_DETAILS_VIEW = "buyers-details";
    public static final String BUYERS_LIST_VIEW = "buyers-list";
    public static final String VIEW_PATH = "../views";

    private BaseController childController;
    private String activeView = "";

    @FXML
    private Button navCarsButton;
    @FXML
    private Button navEmployersButton;
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
        ResourceBundle langBundle = getLangBundle();
        this.childController.loadLangTexts(langBundle);
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
        loadLangTexts(getLangBundle());
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
    public void onAlMenuItemClick(ActionEvent ev) {
        enMenuItem.setSelected(false);
        alMenuItem.setSelected(true);
        updateLanguage();
    }

    @FXML
    public void onEnMenuItemClick(ActionEvent ev) {
        enMenuItem.setSelected(true);
        alMenuItem.setSelected(false);
        updateLanguage();
    }

    private void updateLanguage() {
        try {
            LangEnum lang = enMenuItem.isSelected() ? LangEnum.EN : LangEnum.AL;
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
        navEmployersButton.setText(navEmployersTxt);
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
            case BUYERS_LIST_VIEW:
                sectionLabel.setText(langBundle.getString("main_nav_section_employers_list"));
                break;
        }
    }
}
