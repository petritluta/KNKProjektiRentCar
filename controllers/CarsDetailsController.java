package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import components.ErrorPopupComponent;
import models.Transmission;
import models.Type;
import models.Car;
import models.views.CarViewModel;
import repositories.CarRepo;
import Utils.DateHelper;
import Utils.FileHelper;
import Utils.Utils;
import java.io.File;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

public class CarsDetailsController extends ChildController {

    @FXML
    private TextField idField;

    @FXML
    private TextField publisherField;

    @FXML
    private TextField manufactureField;

    @FXML
    private TextField modelField;

    @FXML
    private ImageView imgField;

    @FXML
    private TextField priceField;

    @FXML
    private TextField transmissionField;

    @FXML
    private TextField dateInsertedField;

    @FXML
    private TextField dateUpdatedField;
    @FXML
    private TextField seatnumField;

    @FXML
    private TextField doornumField;

    @FXML
    private TextField typeField;
    @FXML
    private TextField speedlimitField;
    @FXML
    private TextField avgfuelField;

    private boolean isEditable = false;
    private CarViewModel viewModel;
    private FileChooser fileChooser;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.initialize(location, resources);
        fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("image", "*.png", "*.jpg", "*.jpeg"));
    }

    @FXML
    private void onCancelButtonClick(ActionEvent event) {
        try {
            parentController.setView(MainScreenController.CARS_LIST_VIEW);
        } catch (Exception e) {
            ErrorPopupComponent.show(e);
        }
    }

    @FXML
    private void onSaveButtonClick(ActionEvent event) {
        try {
            if (viewModel.getId() > 0) {
                CarRepo.update(viewModel.getModel());
            } else {
                CarRepo.create(viewModel.getModel());
            }
            parentController.setView(MainScreenController.CARS_LIST_VIEW);
        } catch (Exception e) {
            ErrorPopupComponent.show(e);
        }
    }

    public void setEditable(boolean editable) {
        this.isEditable = editable;

        publisherField.setDisable(!isEditable);
        manufactureField.setDisable(!isEditable);
        modelField.setDisable(!isEditable);
        priceField.setDisable(!isEditable);
        transmissionField.setDisable(!isEditable);
        seatnumField.setDisable(!isEditable);
        doornumField.setDisable(!isEditable);
        typeField.setDisable(!isEditable);
        speedlimitField.setDisable(!isEditable);
        avgfuelField.setDisable(!isEditable);

        if (isEditable) {
            imgField.setOnMouseClicked(e -> this.onImageClick());
        } else {
            imgField.setOnMouseClicked(null);
        }
    }

    public void setModel(Car model) {
        viewModel = new CarViewModel(model);

        idField.setText(Integer.toString(viewModel.getId()));
        publisherField.setText(Integer.toString(viewModel.getPublisher()));
        manufactureField.setText(Integer.toString(viewModel.getManufacture()));
        modelField.setText(viewModel.getmodel());
        if (!Utils.isEmpty(viewModel.getCar_img())) {
            File src = new File(FileHelper.get().getImageDir() + "/" + viewModel.getCar_img());
            Image image = new Image(src.toURI().toString());
            imgField.setImage(image);
        }
        priceField.setText(Double.toString(viewModel.getPrice_per_day()));
        transmissionField.setText(viewModel.getTransmission());
        dateInsertedField.setText(DateHelper.toSqlFormat(viewModel.getInserted_at()));
        dateUpdatedField.setText(DateHelper.toSqlFormat(viewModel.getUpdated_at()));
        seatnumField.setText(Integer.toString(viewModel.getSeat_num()));
        doornumField.setText(Integer.toString(viewModel.getDoor_num()));
        typeField.setText(viewModel.getType());
        speedlimitField.setText(Double.toString(viewModel.getSpeed_limit()));
        avgfuelField.setText(Double.toString(viewModel.getAvg_fuel_km()));


        publisherField.textProperty().addListener((ov, oldVal, newVal) -> {
            if (!Utils.isEmpty(newVal)) {
                try {
                    viewModel.setPublisher(Integer.parseInt(newVal));
                } catch (Exception e) {
                }
            }
        });
        manufactureField.textProperty().addListener((ov, oldVal, newVal) -> {
            if (!Utils.isEmpty(newVal)) {
                try {
                    viewModel.setManufacture(Integer.parseInt(newVal));
                } catch (Exception e) {
                }
            }
        });
        modelField.textProperty().bindBidirectional(viewModel.modelProperty());
        priceField.textProperty().addListener((ov, oldVal, newVal) -> {
            if (!Utils.isEmpty(newVal)) {
                try {
                    viewModel.setPrice_per_day(Double.parseDouble(newVal));
                } catch (Exception e) {
                }
            }
        });
        transmissionField.textProperty().bindBidirectional(viewModel.transmissionProperty());
        seatnumField.textProperty().addListener((ov, oldVal, newVal) -> {
            if (!Utils.isEmpty(newVal)) {
                try {
                    viewModel.setSeat_num(Integer.parseInt(newVal));
                } catch (Exception e) {
                }
            }
        });
        doornumField.textProperty().addListener((ov, oldVal, newVal) -> {
            if (!Utils.isEmpty(newVal)) {
                try {
                    viewModel.setDoor_num(Integer.parseInt(newVal));
                } catch (Exception e) {
                }
            }
        });
        typeField.textProperty().bindBidirectional(viewModel.typeProperty());
        speedlimitField.textProperty().addListener((ov, oldVal, newVal) -> {
            if (!Utils.isEmpty(newVal)) {
                try {
                    viewModel.setSpeed_limit(Double.parseDouble(newVal));
                } catch (Exception e) {
                }
            }
        });
        avgfuelField.textProperty().addListener((ov, oldVal, newVal) -> {
            if (!Utils.isEmpty(newVal)) {
                try {
                    viewModel.setAvg_fuel_km(Double.parseDouble(newVal));
                } catch (Exception e) {
                }
            }
        });
    }

//    private void onImageClick(MouseEvent event) {
//        try {
//            Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//            File srcFile = fileChooser.showOpenDialog(primaryStage);
//            if (srcFile != null) {
//                FileHelper fh = FileHelper.get();
//                String filename = new Date().getTime() + (int) (Math.random() * 100) + "." + fh.fileExt(srcFile);
//                File destFile = new File(fh.getImageDir() + "/" + filename);
//                fh.copyFile(srcFile, destFile);
//
//                Image image = new Image(destFile.toURI().toString());
//                imgField.setImage(image);
//                viewModel.setCar_img(filename);
//            }
//        } catch (Exception e) {
//            ErrorPopupComponent.show(e);
//        }
//    }
private void onImageClick() {
    try {
        Stage primaryStage = (Stage) idField.getScene().getWindow();
        File srcFile = fileChooser.showOpenDialog(primaryStage);
        if (srcFile != null) {
            FileHelper fh = FileHelper.get();
            String filename = new Date().getTime() + (int) (Math.random() * 100) + "." + fh.fileExt(srcFile);
            File destFile = new File(fh.getImageDir() + "/" + filename);
            fh.copyFile(srcFile, destFile);

            Image image = new Image(destFile.toURI().toString());
            imgField.setImage(image);
            viewModel.setCar_img(filename);
        }
    } catch (Exception e) {
        ErrorPopupComponent.show(e);
    }
}
}
