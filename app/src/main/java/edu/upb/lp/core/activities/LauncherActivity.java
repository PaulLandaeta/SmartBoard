package edu.upb.lp.core.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LauncherActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences prefs = getSharedPreferences("menu_prefs", MODE_PRIVATE);
        boolean isMenuEnabled = prefs.getBoolean("menu_enabled", true); // El switch decide esto

        if (isMenuEnabled) {
            // Configurar menú usando la API fácil
            MenuConfigurator.with(this)
                    .addButton("Perfil", () -> showToast("Abriendo Perfil"))
                    .addButton("Configuración", () -> showToast("Abriendo Configuración"))
                    .addButton("Ayuda", () -> showToast("Abriendo Ayuda"))
                    .addButton("Salir", () -> finish())
                    .setBackgroundColor(Color.CYAN)
                    .apply();

            // Ir al menú
            startActivity(new Intent(this, MenuActivity.class));
        } else {
            // Si el menú está deshabilitado, ir directo al juego
            startActivity(new Intent(this, AndroidGameActivity.class));
        }

        finish();
    }

    private void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
