package edu.upb.lp.core.activities;

import java.util.HashMap;
import java.util.Map;

public class MenuButtonActions {

    private static final Map<String, Runnable> acciones = new HashMap<>();

    public static void saveActions(Map<String, Runnable> actions) {
        acciones.clear();
        acciones.putAll(actions);
    }

    public static Runnable getAction(String buttonName) {
        return acciones.get(buttonName);
    }
}
