package controllers;

import Utils.SessionManager;
import javafx.fxml.Initializable;
import java.util.Locale;
import java.net.URL;
import java.util.ResourceBundle;

public abstract class BaseController implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadLangTexts(getLangBundle());
    }

    public ResourceBundle getLangBundle() {
        Locale locale = SessionManager.getLocale();
        return ResourceBundle.getBundle("resources.bundles.LangBundle", locale);
    }

    public abstract void loadLangTexts(ResourceBundle langBundle);
}
