package controllers;

import javafx.fxml.Initializable;
import java.util.ResourceBundle;

public abstract class ChildController extends BaseController {
    public MainScreenController parentController;

    public void setParentController(MainScreenController parentController) {
        this.parentController = parentController;
    }

    @Override
    public void loadLangTexts(ResourceBundle langBundle) {

    }
}
