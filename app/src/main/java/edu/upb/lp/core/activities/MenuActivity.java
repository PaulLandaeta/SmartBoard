package edu.upb.lp.core.activities;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

import edu.upb.lp.genericgame.R;

public class MenuActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_IMAGE = 100;
    private Switch switchEnableMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_menu);


        aplicarFondoGuardado();
        setupButtons();
        setupMenuSwitch();
        setupDynamicButtons();

    }

    private void setupButtons() {
        ImageButton buttonClose = findViewById(R.id.button_close);
        buttonClose.setOnClickListener(v -> finish());

        Button buttonTutorial = findViewById(R.id.button_tutorial);

        Button buttonExit = findViewById(R.id.button_exit);
        buttonExit.setOnClickListener(v -> finish());

        Button buttonScore = findViewById(R.id.button_score);

        Button buttonPlay = findViewById(R.id.button_play);
        buttonPlay.setOnClickListener(v -> {
            Intent intent = new Intent(MenuActivity.this, AndroidGameActivity.class);
            startActivity(intent);
        });

        Button buttonMoreOptions = findViewById(R.id.button_more_options);
        buttonMoreOptions.setOnClickListener(v -> mostrarMenuPersonalizacion());
    }


    private void setupMenuSwitch() {
        switchEnableMenu = findViewById(R.id.switch_enable_menu);

        // Leer estado actual desde SharedPreferences
        SharedPreferences prefs = getSharedPreferences("menu_prefs", MODE_PRIVATE);
        boolean isMenuEnabled = prefs.getBoolean("menu_enabled", false);
        switchEnableMenu.setChecked(isMenuEnabled);

        // Guardar nuevo estado al cambiar el switch
        switchEnableMenu.setOnCheckedChangeListener((buttonView, isChecked) -> {
            prefs.edit().putBoolean("menu_enabled", isChecked).apply();
            Toast.makeText(this, "Menú " + (isChecked ? "Habilitado" : "Deshabilitado"), Toast.LENGTH_SHORT).show();

            // Si el menú se deshabilita, redirigir al juego
            if (!isChecked) {
                Intent intent = new Intent(MenuActivity.this, AndroidGameActivity.class);
                startActivity(intent);
                finish();  // Cierra el menú
            }
        });
    }


    private void mostrarMenuPersonalizacion() {
        String[] opciones = {"Cambiar Color de Fondo", "Elegir Imagen de Fondo"};

        new AlertDialog.Builder(this)
                .setTitle("Personalizar Menú")
                .setItems(opciones, (dialog, which) -> {
                    if (which == 0) {
                        mostrarSelectorColores();
                    } else if (which == 1) {
                        abrirSelectorDeArchivos();
                    }
                })
                .show();
    }

    private void mostrarSelectorColores() {
        String[] colores = {"Rojo", "Verde", "Azul", "Negro", "Amarillo"};

        new AlertDialog.Builder(this)
                .setTitle("Selecciona un Color")
                .setItems(colores, (dialog, which) -> {
                    int color = Color.BLACK;
                    switch (which) {
                        case 0:
                            color = Color.RED;
                            break;
                        case 1:
                            color = Color.GREEN;
                            break;
                        case 2:
                            color = Color.BLUE;
                            break;
                        case 3:
                            color = Color.BLACK;
                            break;
                        case 4:
                            color = Color.YELLOW;
                            break;


                    }
                    guardarColorFondo(color);
                    aplicarColorFondo(color);
                })
                .show();
    }

    private void abrirSelectorDeArchivos() {
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.setType("image/*");
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        startActivityForResult(intent, REQUEST_CODE_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_IMAGE && resultCode == RESULT_OK && data != null) {
            Uri imagenSeleccionada = data.getData();
            guardarImagenFondo(imagenSeleccionada.toString());
            aplicarImagenFondo(imagenSeleccionada);
        }
    }

    private void guardarColorFondo(int color) {
        getSharedPreferences("menu_prefs", MODE_PRIVATE).edit()
                .putString("menu_background_type", "color")
                .putInt("menu_background_color", color)
                .apply();
    }

    private void guardarImagenFondo(String uri) {
        getSharedPreferences("menu_prefs", MODE_PRIVATE).edit()
                .putString("menu_background_type", "image")
                .putString("menu_background_image", uri)
                .apply();
    }

    private void aplicarFondoGuardado() {
        SharedPreferences prefs = getSharedPreferences("menu_prefs", MODE_PRIVATE);
        String tipo = prefs.getString("menu_background_type", "color");

        if ("color".equals(tipo)) {
            int color = prefs.getInt("menu_background_color", Color.BLACK);
            aplicarColorFondo(color);
        } else if ("image".equals(tipo)) {
            String uri = prefs.getString("menu_background_image", null);
            if (uri != null) {
                aplicarImagenFondo(Uri.parse(uri));
            }
        }
    }

    private void aplicarColorFondo(int color) {
        findViewById(R.id.menu).setBackgroundColor(color);
    }

    private void aplicarImagenFondo(Uri uri) {
        try {
            Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
            Drawable drawable = new BitmapDrawable(getResources(), bitmap);
            findViewById(R.id.menu).setBackground(drawable);
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Error al cargar imagen", Toast.LENGTH_SHORT).show();
        }
    }

    private void setupDynamicButtons() {
        Button buttonAddMore = findViewById(R.id.button_add_more);
        Button buttonRemoveLast = findViewById(R.id.button_remove_last);
        FrameLayout container = findViewById(R.id.optional_buttons_container);

        // Cargar botones guardados al iniciar
        cargarBotonesGuardados(container);

        buttonAddMore.setOnClickListener(v -> {
            EditText input = new EditText(this);

            new AlertDialog.Builder(this)
                    .setTitle("Nombre del nuevo botón")
                    .setView(input)
                    .setPositiveButton("Añadir", (dialog, which) -> {
                        String nombreBoton = input.getText().toString().trim();
                        if (nombreBoton.isEmpty()) {
                            Toast.makeText(this, "El nombre no puede estar vacío", Toast.LENGTH_SHORT).show();
                        } else {
                            confirmarGuardarBoton(container, nombreBoton);
                        }
                    })
                    .setNegativeButton("Cancelar", null)
                    .show();
        });

        buttonRemoveLast.setOnClickListener(v -> confirmarEliminarBoton(container));
    }

    // Confirmar guardado
    private void confirmarGuardarBoton(FrameLayout container, String nombreBoton) {
        new AlertDialog.Builder(this)
                .setTitle("Confirmar")
                .setMessage("¿Guardar el botón '" + nombreBoton + "'?")
                .setPositiveButton("Sí", (dialog, which) -> {
                    crearBotonDinamico(container, nombreBoton);
                    guardarBotonEnPrefs(nombreBoton);
                })
                .setNegativeButton("No", null)
                .show();
    }

    // Confirmar eliminación
    private void confirmarEliminarBoton(FrameLayout container) {
        new AlertDialog.Builder(this)
                .setTitle("Confirmar")
                .setMessage("¿Eliminar el último botón?")
                .setPositiveButton("Sí", (dialog, which) -> {
                    eliminarUltimoBoton(container);
                })
                .setNegativeButton("No", null)
                .show();
    }

    // Crear botón dinámico
    private void crearBotonDinamico(FrameLayout container, String nombre) {
        Button button = new Button(this);
        button.setText(nombre);

        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.WRAP_CONTENT
        );
        button.setLayoutParams(params);

        container.addView(button, container.getChildCount() - 1); // Antes del LinearLayout de add/remove
    }

    // Guardar botón en SharedPreferences
    private void guardarBotonEnPrefs(String nombre) {
        SharedPreferences prefs = getSharedPreferences("menu_prefs", MODE_PRIVATE);
        String botonesGuardados = prefs.getString("custom_buttons", "");

        botonesGuardados += nombre + ",";
        prefs.edit().putString("custom_buttons", botonesGuardados).apply();
    }

    // Cargar botones guardados al abrir el menú
    private void cargarBotonesGuardados(FrameLayout container) {
        SharedPreferences prefs = getSharedPreferences("menu_prefs", MODE_PRIVATE);
        String botonesGuardados = prefs.getString("custom_buttons", "");

        if (!botonesGuardados.isEmpty()) {
            String[] nombres = botonesGuardados.split(",");
            for (String nombre : nombres) {
                if (!nombre.trim().isEmpty()) {
                    crearBotonDinamico(container, nombre);
                }
            }
        }
    }

    // Eliminar el último botón dinámico
    private void eliminarUltimoBoton(FrameLayout container) {
        int count = container.getChildCount();
        if (count > 2) { // Mínimo hay 2 hijos (tutorial y score o layout add/remove)
            container.removeViewAt(count - 2); // penúltimo es el último dinámico

            actualizarBotonesEnPrefs(container);
        } else {
            Toast.makeText(this, "No hay botones adicionales para eliminar", Toast.LENGTH_SHORT).show();
        }
    }

    // Actualizar SharedPreferences después de eliminar
    private void actualizarBotonesEnPrefs(FrameLayout container) {
        StringBuilder nuevosBotones = new StringBuilder();

        for (int i = 0; i < container.getChildCount() - 1; i++) {
            if (container.getChildAt(i) instanceof Button) {
                Button button = (Button) container.getChildAt(i);
                String nombre = button.getText().toString();
                if (!nombre.equals("Tutorial") && !nombre.equals("Score")) {
                    nuevosBotones.append(nombre).append(",");
                }
            }
        }

        getSharedPreferences("menu_prefs", MODE_PRIVATE).edit()
                .putString("custom_buttons", nuevosBotones.toString())
                .apply();
    }
}
