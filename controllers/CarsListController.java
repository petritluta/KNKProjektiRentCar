package controllers;

import components.CarCardComponent;
import components.PageBtnComponent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import components.ErrorPopupComponent;
//import components.PaginationComponent;
//import components.UserCardComponent;
import javafx.scene.layout.VBox;
import models.Car;
import models.User;
import repositories.CarRepo;
//import repositories.UserRepository;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class CarsListController extends ChildController {
    private final int PAGE_SIZE = 6;

    private PageBtnComponent paginationComponent;

    @FXML
    private VBox carsPane;
    @FXML
    private HBox btnPane;
    @FXML
    ComboBox<String> Brands ;
    @FXML
    ComboBox<String> types ;
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
            paginationComponent = new PageBtnComponent(carCount(), PAGE_SIZE);
            paginationComponent.render(btnPane, page -> {
                try {
                    showCars(page);
                }catch (Exception ex){
                    ErrorPopupComponent.show(ex);
                }
            });
//            System.out.println("erdhh");
            showCars(0);
        } catch (Exception e) {
            ErrorPopupComponent.show(e);
        }
    }

    private int carCount() throws Exception {
        return CarRepo.count();
    }

    private void showCars(int page) throws Exception {
        carsPane.getChildren().clear();
        List<Car> cars = CarRepo.getAll(PAGE_SIZE, page);
        for (Car car : cars) {
            Node carCard = new CarCardComponent().getContent(car,null,null);
            carsPane.getChildren().add(carCard);
        }

    }

    private void removeCar(Car car) {
        try {
            CarRepo.remove(car.getId());
            System.out.println("aa");
            showCars(paginationComponent.getCursor());
        } catch (Exception e) {
            ErrorPopupComponent.show(e);
        }
    }
//private void showCar(Car car) {
      //try {
         //   FXMLLoader loader = new FXMLLoader();
        //    loader.setLocation(getClass().getResource("../views/cars-details.fxml"));
//
      //      Pane pane = loader.load();
    //        CarsDetailsController controller = loader.getController();
  //          controller.setModel(car);
//
   //         parentController.setView(MainScreenController.CARS_DETAILS_VIEW, pane, controller);
 //       } catch (Exception e) {
     //       ErrorPopupComponent.show(e);
   //     }
 //   }



    @Override
    public void loadLangTexts(ResourceBundle langBundle) {
//        super.loadLangTexts(langBundle);
//        this.showAllButton.setText(langBundle.getString("cars_list_show_all_button"));
    }
}
