package Utils;

import models.LangEnum;
import models.User;
import java.util.Date;
import java.util.Locale;

public class SessionManager {
    public static User employer = null;
    public static Date lastLogin = null;

    public static Locale getLocale() {
        LangEnum lang = AppConfig.get().getLanguage();
        return lang == LangEnum.EN ? new Locale("en", "US") :
                new Locale("al", "AL");
    }
}
