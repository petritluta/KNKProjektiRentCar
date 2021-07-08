package controllers;

import javafx.fxml.Initializable;
import Utils.Session;

import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

public abstract class BaseController implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        loadLangTexts(getLangBundle());
    }

//    public ResourceBundle getLangBundle() {
//        Locale locale = Session.getLocale();
//        return ResourceBundle.getBundle("resources.bundles.LangBundle", locale);
//    }

    public abstract void loadLangTexts(ResourceBundle langBundle);
}
