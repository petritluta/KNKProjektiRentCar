package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
//import components.ErrorPopupComponent;
//import components.PaginationComponent;
//import components.UserCardComponent;
import models.User;
//import repositories.UserRepository;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class CarsListController extends ChildController {
    private final int PAGE_SIZE = 10;

//    private PaginationComponent paginationComponent;

    @FXML
    private FlowPane usersPane;
    @FXML
    private HBox paginationPane;
    @FXML
    private Button showAllButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
//            super.initialize(location, resources);
//            paginationComponent = new PaginationComponent(userCount(), PAGE_SIZE);
//            paginationComponent.render(paginationPane, (page) -> {
//                try {
//                    showUsers(page);
//                } catch (Exception e) {
//                    ErrorPopupComponent.show(e);
//                }
//            });
//
//            showUsers(0);
        } catch (Exception e) {
//            ErrorPopupComponent.show(e);
        }
    }

//    private int userCount() throws Exception {
//        return UserRepository.count();
//    }
//
//    private void showUsers(int page) throws Exception {
//        usersPane.getChildren().clear();
//        List<User> users = UserRepository.getAll(PAGE_SIZE, page);
//        UserCardComponent userCard = new UserCardComponent();
//        for (User user : users) {
//            usersPane.getChildren()
//                    .add(userCard.getContent(user, e -> showUser(user), e -> removeUser(user), e -> changeUserState(user)));
//        }
//    }
//
//    private void removeUser(User user) {
//        try {
//            UserRepository.remove(user.getId());
//            showUsers(paginationComponent.getCursor());
//        } catch (Exception e) {
//            ErrorPopupComponent.show(e);
//        }
//    }
//
//    private void showUser(User user) {
//        try {
//            FXMLLoader loader = new FXMLLoader();
//            loader.setLocation(getClass().getResource("../views/" + MainScreenController.BUYERS_DETAILS_VIEW + ".fxml"));
//
//            Pane pane = loader.load();
//            BuyersDetailsController controller = loader.getController();
//            controller.setModel(user);
//
//            parentController.setView(MainScreenController.BUYERS_DETAILS_VIEW, pane, controller);
//        } catch (Exception e) {
//            ErrorPopupComponent.show(e);
//        }
//    }
//
//    private void changeUserState(User user) {
//        try {
//            user.setActive(!user.getActive());
//            UserRepository.update(user);
//        } catch (Exception e) {
//            ErrorPopupComponent.show(e);
//        }
//    }

    @Override
    public void loadLangTexts(ResourceBundle langBundle) {
        super.loadLangTexts(langBundle);
        this.showAllButton.setText(langBundle.getString("cars_list_show_all_button"));
    }
}
