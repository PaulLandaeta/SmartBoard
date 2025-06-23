package edu.upb.lp.core.activities;

import android.content.Context;
import android.content.SharedPreferences;
import java.util.HashMap;
import java.util.Map;

public class MenuConfigurator {

    private final Context context;
    private final SharedPreferences prefs;
    private final StringBuilder botones;
    private final Map<String, Runnable> acciones;

    private MenuConfigurator(Context context) {
        this.context = context;
        this.prefs = context.getSharedPreferences("menu_prefs", Context.MODE_PRIVATE);
        this.botones = new StringBuilder();
        this.acciones = new HashMap<>();
    }

    public static MenuConfigurator with(Context context) {
        return new MenuConfigurator(context);
    }

    public MenuConfigurator addButton(String name, Runnable action) {
        botones.append(name).append(",");
        acciones.put(name, action);
        return this;
    }

    public MenuConfigurator setBackgroundColor(int color) {
        prefs.edit()
                .putString("menu_background_type", "color")
                .putInt("menu_background_color", color)
                .apply();
        return this;
    }

    public MenuConfigurator setBackgroundImage(String imageUri) {
        prefs.edit()
                .putString("menu_background_type", "image")
                .putString("menu_background_image", imageUri)
                .apply();
        return this;
    }

    public MenuConfigurator enableMenu(boolean enabled) {
        prefs.edit()
                .putBoolean("menu_enabled", enabled)
                .apply();
        return this;
    }

    public void apply() {
        prefs.edit()
                .putString("custom_buttons", botones.toString())
                .apply();

        MenuButtonActions.saveActions(acciones);
    }
}
