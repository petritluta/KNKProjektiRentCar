package models;

import java.util.HashMap;
import java.util.Map;

public enum Type {
    Sedan(1),SUV(2),Cabriolet(3),SportsCar(4);
    private int value;
    private static Map map = new HashMap<>();

    private Type(int value) {
        this.value = value;
    }

    static {
        for (Type pageType : Type.values()) {
            map.put(pageType.value, pageType);
        }
    }

    public static Type valueOf(int pageType) {
        return (Type) map.get(pageType);
    }

    public int getValue() {
        return value;
    }
}
