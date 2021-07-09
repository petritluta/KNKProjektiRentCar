////package controllers;
////
////import javafx.collections.FXCollections;
////import javafx.event.ActionEvent;
////import javafx.fxml.FXML;
////import javafx.fxml.FXMLLoader;
////import javafx.scene.control.*;
////import javafx.scene.control.cell.PropertyValueFactory;
////import javafx.scene.input.KeyCode;
////import javafx.scene.input.KeyCodeCombination;
////import javafx.scene.input.KeyCombination;
////import javafx.scene.input.KeyEvent;
////import javafx.scene.layout.HBox;
////import javafx.scene.layout.Pane;
////import javafx.stage.WindowEvent;
//import components.ErrorPopupComponent;
//import components.FindProductComponent;
//import components.PaginationComponent;
//import models.Product;
//import repositories.ProductRepository;
//import java.net.URL;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.ResourceBundle;
//
//public class BuyersListController extends ChildController {
//    final KeyCombination keyCtrlF = new KeyCodeCombination(KeyCode.F, KeyCombination.CONTROL_ANY);
//    private final int PAGE_SIZE = 10;
//
//    @FXML
//    private TableView<Product> tableView;
//    @FXML
//    private TableColumn<Product, Integer> idColumn;
//    @FXML
//    private TableColumn<Product, String> titleColumn;
//    @FXML
//    private TableColumn<Product, Double> priceColumn;
//    @FXML
//    private TableColumn<Product, Double> qtyColumn;
//    @FXML
//    private HBox paginationPane;
//    @FXML
//    private ToggleButton multipleButton;
//    @FXML
//    private Button showAllButton;
//    @FXML
//    private MenuItem viewMenuItem;
//    @FXML
//    private MenuItem editMenuItem;
//    @FXML
//    private MenuItem removeMenuItem;
//
//    private PaginationComponent paginationComponent;
//
//    @Override
//    public void initialize(URL location, ResourceBundle resources) {
//        try {
//            super.initialize(location, resources);
//            idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
//            titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
//            priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
//            qtyColumn.setCellValueFactory(new PropertyValueFactory<>("qty"));
//            tableView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
//
//            showProducts(0);
//            paginationComponent = new PaginationComponent(productCount(), PAGE_SIZE);
//            paginationComponent.render(paginationPane, page -> {
//                try {
//                    showProducts(page);
//                } catch (Exception ex) {
//                    ErrorPopupComponent.show(ex);
//                }
//            });
//        } catch (Exception ex) {
//            ErrorPopupComponent.show(ex);
//        }
//    }
//
//    @FXML
//    private void onMultipleButtonClick(ActionEvent ev) {
//        tableView.getSelectionModel().setSelectionMode(
//                multipleButton.isSelected() ? SelectionMode.MULTIPLE : SelectionMode.SINGLE
//        );
//    }
//
//    @FXML
//    private void onContextMenuShowing(WindowEvent ev) {
//        int selected = tableView.getSelectionModel().getSelectedItems().size();
//        if (selected == 1) {
//            editMenuItem.setDisable(false);
//            removeMenuItem.setDisable(false);
//            viewMenuItem.setDisable(false);
//        } else if (selected > 1) {
//            editMenuItem.setDisable(true);
//            removeMenuItem.setDisable(false);
//            viewMenuItem.setDisable(true);
//        } else {
//            editMenuItem.setDisable(true);
//            removeMenuItem.setDisable(true);
//            viewMenuItem.setDisable(true);
//        }
//    }
//
//    @FXML
//    private void onShowAllButtonClick(ActionEvent ev) {
//        try {
//            showProducts(paginationComponent.getCursor());
//            paginationPane.setVisible(true);
//        } catch (Exception ex) {
//            ErrorPopupComponent.show(ex);
//        }
//    }
//
//    @FXML
//    private void onFindClick(ActionEvent ev) {
//        try {
//            List<Product> products = findProducts();
//            tableView.getItems().clear();
//            tableView.setItems(FXCollections.observableList(products));
//            paginationPane.setVisible(false);
//        } catch (Exception ex) {
//            ErrorPopupComponent.show(ex);
//        }
//    }
//
//    @FXML
//    private void onViewMenuItemClick(ActionEvent ev) {
//        Product selected = tableView.getSelectionModel().getSelectedItem();
//        if (selected == null)
//            return;
//
//        try {
//            FXMLLoader loader = new FXMLLoader();
//            loader.setLocation(getClass().getResource("../views/" + MainScreenController.CARS_DETAILS_VIEW + ".fxml"));
//
//            Pane pane = loader.load();
//            CarsDetailsController controller = loader.getController();
//            controller.setModel(selected);
//            controller.setEditable(false);
//
//            parentController.setView(MainScreenController.CARS_DETAILS_VIEW, pane, controller);
//        } catch (Exception e) {
//            ErrorPopupComponent.show(e);
//        }
//    }
//
//    @FXML
//    private void onEditMenuItemClick(ActionEvent ev) {
//        Product selected = tableView.getSelectionModel().getSelectedItem();
//        if (selected == null)
//            return;
//
//        try {
//            FXMLLoader loader = new FXMLLoader();
//            loader.setLocation(getClass().getResource("../views/" + MainScreenController.CARS_DETAILS_VIEW + ".fxml"));
//
//            Pane pane = loader.load();
//            CarsDetailsController controller = loader.getController();
//            controller.setModel(selected);
//            controller.setEditable(true);
//
//            parentController.setView(MainScreenController.CARS_DETAILS_VIEW, pane, controller);
//        } catch (Exception e) {
//            ErrorPopupComponent.show(e);
//        }
//
//    }
//
//    @FXML
//    private void onRemoveMenuItemClick(ActionEvent ev) {
//        try {
//            for (Product p : tableView.getSelectionModel().getSelectedItems()) {
//                ProductRepository.remove(p.getId());
//            }
//            showProducts(paginationComponent.getCursor());
//        } catch (Exception ex) {
//            ErrorPopupComponent.show(ex);
//        }
//    }
//
//    @FXML
//    private void onScreenKeyPressed(KeyEvent event) {
//        if (keyCtrlF.match(event)) {
//            try {
//                List<Product> products = this.findProducts();
//                tableView.getItems().clear();
//                tableView.setItems(FXCollections.observableArrayList(products));
//                paginationPane.setVisible(false);
//            } catch (Exception e) {
//                ErrorPopupComponent.show(e);
//            }
//        }
//    }
//
//    private void showProducts(int page) throws Exception {
//        List<Product> products = ProductRepository.getAll(PAGE_SIZE, page);
//        tableView.getItems().clear();
//        tableView.setItems(FXCollections.observableList(products));
//    }
//
//    private List<Product> findProducts() throws Exception {
//        String text = new FindProductComponent().showDialog();
//        if (text.length() == 0) return new ArrayList<>();
//        return ProductRepository.find(text);
//    }
//
//    private int productCount() throws Exception {
//        return ProductRepository.count();
//    }
//
//    @Override
//    public void loadLangTexts(ResourceBundle langBundle) {
//        super.loadLangTexts(langBundle);
//
//        String showAllTxt = langBundle.getString("product_list_show_all_button");
//        showAllButton.setText(showAllTxt);
//    }
//}