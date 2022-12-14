package kowale.database;

import kowale.event.Event;

public class GlobalVariables {
    public static volatile Event SELECTED_EVENT;
    public static volatile int SELECTED_INDEX = 0;

    public static volatile String USER_LOGIN;
    public static volatile String USER_TYPE;

    public static volatile double CHILDREN_COEF = 0.7;
    public static volatile double VIP_COEF = 1.5;

    public static String[] COUNTRIES = {
        "Belgia",
        "Czechy",
        "Dania",
        "Francja",
        "Hiszpania",
        "Niemcy",
        "Polska",
        "Rosja",
        "Sosnowiec",
        "Szwecja",
        "Wielka Brytania"
    };
}
