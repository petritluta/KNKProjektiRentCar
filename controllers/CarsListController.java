package controllers;

import components.CarCardComponent;
import components.PageBtnComponent;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;

import javafx.scene.control.ComboBox;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.*;

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
    private ComboBox<String> brands ;
    @FXML
    private ComboBox<String> types ;
    @FXML
    private RadioButton rented;
    @FXML
    private RadioButton forRent;
    @FXML
    private RadioButton showAll;
    private ToggleGroup group = new ToggleGroup();
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
//            super.initialize(location, resources);
              setComboBoxesAndRadio();

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
    private void setComboBoxesAndRadio(){
        brands.getItems().addAll("Audi", "BMW",
                "Mercedes");
        brands.setValue("BMW");
        types.getItems().addAll("Sedan", "SUV","Cabriolet",
                "Sports Car");
        types.setValue("Sedan");
        rented.setToggleGroup(group);
        forRent.setToggleGroup(group);
        showAll.setToggleGroup(group);
        showAll.setSelected(true);
    }

    private int carCount() throws Exception {
        return CarRepo.count();
    }

    private void showCars(int page) throws Exception {
        carsPane.getChildren().clear();
        List<Car> cars = CarRepo.getAll(PAGE_SIZE, page);
        for (Car car : cars) {
            Node carCard = new CarCardComponent().getContent(car,e->showCar(car),e->removeCar(car));
            carsPane.getChildren().add(carCard);
        }
    }
    private void showCars(String queryString) throws Exception {
        carsPane.getChildren().clear();
        List<Car> cars = CarRepo.getSelectedCars(queryString);
        for (Car car : cars) {
            Node carCard = new CarCardComponent().getContent(car,e->showCar(car),e->removeCar(car));
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


    private void showCar(Car car) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../views/cars-details.fxml"));

            Pane pane = loader.load();
            CarsDetailsController controller = loader.getController();
            controller.setModel(car);
            controller.setEditable(true);

            parentController.setView(MainScreenController.CARS_DETAILS_VIEW, pane, controller);
        } catch (Exception e) {
            ErrorPopupComponent.show(e);
        }
    }
    @FXML
    private void onSelectBrandSetAcion(ActionEvent ev) throws Exception{
        String brand = brands.getValue();
        String type = types.getValue();
        String radBtnString = rented.isSelected() ? "rented" : (forRent.isSelected() ? "forRent" : "showAll" );
        String queryString = CarRepo.getQuery(brand,type,radBtnString);
        List<Car> cars = CarRepo.getSelectedCars(queryString);

        paginationComponent = new PageBtnComponent(CarRepo.getAffectedRows(queryString), PAGE_SIZE);
        paginationComponent.render(btnPane, qs -> {
            try {
                showCars(qs);
            }catch (Exception ex){
                ErrorPopupComponent.show(ex);
            }
        });
//            System.out.println("erdhh");
        showCars(queryString);
    }
    @FXML
    private void onSelectTypeSetAcion(ActionEvent ev) throws Exception{
        String brand = brands.getValue();
        String type = types.getValue();
        String radBtnString = rented.isSelected() ? "rented" : (forRent.isSelected() ? "forRent" : "showAll" );
        String queryString = CarRepo.getQuery(brand,type,radBtnString);
        List<Car> cars = CarRepo.getSelectedCars(queryString);

        paginationComponent = new PageBtnComponent(CarRepo.getAffectedRows(queryString), PAGE_SIZE);
        paginationComponent.render(btnPane, qs -> {
            try {
                showCars(qs);
            }catch (Exception ex){
                ErrorPopupComponent.show(ex);
            }
        });
//            System.out.println("erdhh");
        showCars(queryString);
    }
    @FXML
    private void onSelectForRentSetAcion(ActionEvent ev) throws Exception{
        String brand = brands.getValue();
        String type = types.getValue();
        String radBtnString = rented.isSelected() ? "rented" : (forRent.isSelected() ? "forRent" : "showAll" );
        String queryString = CarRepo.getQuery(brand,type,radBtnString);
        List<Car> cars = CarRepo.getSelectedCars(queryString);

        paginationComponent = new PageBtnComponent(CarRepo.getAffectedRows(queryString), PAGE_SIZE);
        paginationComponent.render(btnPane, qs -> {
            try {
                showCars(qs);
            }catch (Exception ex){
                ErrorPopupComponent.show(ex);
            }
        });
//            System.out.println("erdhh");
        showCars(queryString);
    }
    @FXML
    private void onSelectRentedSetAcion(ActionEvent ev) throws Exception{
        String brand = brands.getValue();
        String type = types.getValue();
        String radBtnString = rented.isSelected() ? "rented" : (forRent.isSelected() ? "forRent" : "showAll" );
        String queryString = CarRepo.getQuery(brand,type,radBtnString);
        List<Car> cars = CarRepo.getSelectedCars(queryString);

        paginationComponent = new PageBtnComponent(CarRepo.getAffectedRows(queryString), PAGE_SIZE);
        paginationComponent.render(btnPane, qs -> {
            try {
                showCars(qs);
            }catch (Exception ex){
                ErrorPopupComponent.show(ex);
            }
        });
//            System.out.println("erdhh");
        showCars(queryString);
    }
    @FXML
    private void onSelectShowAllSetAcion(ActionEvent ev) throws Exception{
        String brand = brands.getValue();
        String type = types.getValue();
        String radBtnString = rented.isSelected() ? "rented" : (forRent.isSelected() ? "forRent" : "showAll" );
        String queryString = CarRepo.getQuery(brand,type,radBtnString);
        List<Car> cars = CarRepo.getSelectedCars(queryString);

        paginationComponent = new PageBtnComponent(CarRepo.getAffectedRows(queryString), PAGE_SIZE);
        paginationComponent.render(btnPane, qs -> {
            try {
                showCars(qs);
            }catch (Exception ex){
                ErrorPopupComponent.show(ex);
            }
        });
//            System.out.println("erdhh");
        showCars(queryString);
    }



    @Override
    public void loadLangTexts(ResourceBundle langBundle) {
//        super.loadLangTexts(langBundle);
//        this.showAllButton.setText(langBundle.getString("cars_list_show_all_button"));
    }
}
