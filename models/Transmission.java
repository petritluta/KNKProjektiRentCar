package models;

import java.util.HashMap;
import java.util.Map;

public enum Transmission {
    Auto(1) ,
    Manual(2);
    private int value;
    private static Map map = new HashMap<>();

    private Transmission(int value) {
        this.value = value;
    }

    static {
        for (Transmission pageType : Transmission.values()) {
            map.put(pageType.value, pageType);
        }
    }

    public static Transmission valueOf(int pageType) {
        return (Transmission) map.get(pageType);
    }

    public int getValue() {
        return value;
    }
}
