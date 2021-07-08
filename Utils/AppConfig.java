package Utils;

import models.LangEnum;
import java.io.File;
import java.io.FileOutputStream;
import java.net.URI;
import java.util.Properties;

public class AppConfig {
    private static AppConfig instance;
    private Properties props;

    private AppConfig() {
        try {
            props = new Properties();
            props.load(getClass().getResourceAsStream("../resources/config.properties"));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static AppConfig get() {
        if (instance == null) {
            instance = new AppConfig();
        }
        return instance;
    }

    public String getAppName() {
        return props.getProperty("appname", "PWM");
    }

    public String getVersion() {
        return props.getProperty("version", "1.0,0");
    }

    public String getReleased() {
        return props.getProperty("released", "2021-05-25");
    }

    public String getConnectionString() {
        return props.getProperty("connectionString");
    }

    public String getDriverType() {
        return props.getProperty("driverType");
    }

    public LangEnum getLanguage() {
        LangEnum lang = props.getProperty("lang", "en").equals("en") ? LangEnum.EN : LangEnum.AL;
        return lang;
    }

    public void setLanguage(LangEnum lang) throws Exception {
        URI confPath = getClass().getResource("../resources/config.properties").toURI();
        String langStr = lang == LangEnum.EN ? "en" : "al";
        props.setProperty("lang", langStr);
        props.store(new FileOutputStream(new File(confPath)), "");
    }

//    public LangEnum getLanguage() {
//        return props.getProperty("lang", "en").equals("en") ? LangEnum.EN : LangEnum.AL;
//    }
// qeto Vigani i ka pase te resources a pjesa nalt osht si ne video
//    public void setLanguage(LangEnum lang) throws Exception {
//        URI confPath = getClass().getResource("../resources/config.properties").toURI();
//        props.setProperty("lang", lang == LangEnum.EN ? "en" : "al");
//        props.store(new FileOutputStream(new File(confPath)), "");
//    }
}
