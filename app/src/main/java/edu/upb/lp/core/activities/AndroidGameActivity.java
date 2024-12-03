package edu.upb.lp.core.activities;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.text.InputType;
import android.util.SparseArray;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;

import edu.upb.lp.core.adapter.AndroidLibrary;
import edu.upb.lp.core.adapter.AppConnector;
import edu.upb.lp.feature.ui.GameFactory;
import edu.upb.lp.core.adapter.TextListener;
import edu.upb.lp.core.views.MyTextView;
import edu.upb.lp.genericgame.R;


public class AndroidGameActivity extends AppCompatActivity implements AndroidLibrary,
        OnClickListener {
    private TableLayout table;
    private LinearLayout buttons;
    private LinearLayout messages;
    private LinearLayout bottomSection;

    private final Map<String, Integer> viewIds = new HashMap<>();
    private final SparseArray<String> viewNames = new SparseArray<>();

    private static final AtomicInteger sNextGeneratedId = new AtomicInteger(1);

    private final String domain = "edu.upb.game";
    private SharedPreferences prefs;
    private final Handler handler = new Handler();

    private AppConnector userUI;

    private final MediaPlayer[] mp = new MediaPlayer[10];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        prefs = this.getSharedPreferences(domain, Context.MODE_PRIVATE);
        userUI = GameFactory.selectGame(this);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("UPB Game");

        // table = (TableLayout) findViewById(R.id.maingrid);

        table = findViewById(R.id.maingrid);

// Asegúrate de usar FrameLayout.LayoutParams si cambias dinámicamente los LayoutParams
        if (table != null) {
            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                    FrameLayout.LayoutParams.MATCH_PARENT,
                    FrameLayout.LayoutParams.MATCH_PARENT
            );
            table.setLayoutParams(params); // Asegura el tipo correcto de LayoutParams
        }

        buttons = (LinearLayout) findViewById(R.id.buttons);
        messages = (LinearLayout) findViewById(R.id.messages);
        bottomSection = (LinearLayout) findViewById(R.id.bottomSection);

        for (int i = 0; i < mp.length; ++i) {
            mp[i] = new MediaPlayer();
        }

        userUI.initialiseInterface();
    }

    @Override
    public void addButton(String name, int textSize, int buttonSize) {
        Integer id = viewIds.get(name);
        Button button;
        if (id == null) {
            id = generateViewId();
            button = new Button(this);
            button.setId(id);
            viewIds.put(name, id);
            viewNames.put(id, name);
            button.setOnClickListener(this);
            button.setGravity(Gravity.CENTER_VERTICAL
                    | Gravity.CENTER_HORIZONTAL);
            button.setSoundEffectsEnabled(false);
            buttons.addView(button);
        } else {
            button = (Button) findViewById(id);
        }

        LinearLayout.LayoutParams buttonParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, dpToPixel(buttonSize));
        button.setLayoutParams(buttonParams);
        button.setText(name);
        button.setTextSize(textSize);
    }

    @Override
    public void removeButton(String name) {
        Integer id = viewIds.get(name);
        if (id != null) {
            View view = findViewById(id);
            buttons.removeView(view);
            viewIds.remove(name);
            viewNames.remove(id);
        }
    }

    public void removeAllButtons() {
        while (buttons.getChildCount() > 0) {
            View view = buttons.getChildAt(0);
            int id = view.getId();
            String name = viewNames.get(id);
            removeButton(name);
        }
    }

    @Override
    public void addTextField(String name, String message, int textSize,
                             int textFieldSize) {
        TextView view;
        Integer id = viewIds.get(name);
        if (id == null) {
            view = new TextView(this);
            LinearLayout.LayoutParams textParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    dpToPixel(textFieldSize));
            view.setLayoutParams(textParams);
            view.setClickable(false);
            id = generateViewId();
            view.setId(id);
            viewIds.put(name, id);
            viewNames.put(id, name);
            view.setText(message);
            view.setTextSize(textSize);
            view.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
            messages.addView(view);
        } else {
            updateTextField(name, message);
        }

    }

    @Override
    public void updateTextField(String name, String message) {
        int id = viewIds.get(name);
        TextView view = (TextView) findViewById(id);
        view.setText(message);
    }

    @Override
    public void removeTextField(String name) {
        Integer id = viewIds.get(name);
        if (id != null) {
            View view = findViewById(id);
            messages.removeView(view);
            viewIds.remove(name);
            viewNames.remove(id);
        }
    }

    public void removeAllTextFields() {
        while (messages.getChildCount() > 0) {
            View view = messages.getChildAt(0);
            int id = view.getId();
            String name = viewNames.get(id);
            removeTextField(name);
        }
    }

    @Override
    public void setTextOnCell(int vertical, int horizontal, String text) {
        TableRow row = (TableRow) table.getChildAt(vertical);
        TextView view = (TextView) row.getChildAt(horizontal);
        view.setText(text);
    }

    @Override
    public void setTextSizeOnCell(int vertical, int horizontal, int size) {
        TableRow row = (TableRow) table.getChildAt(vertical);
        TextView view = (TextView) row.getChildAt(horizontal);
        view.setTextSize(size);
    }

    @Override
    public void setImageOnCell(int vertical, int horizontal, String image) {
        TableRow row = (TableRow) table.getChildAt(vertical);
        if (row == null) {
            throw new IndexOutOfBoundsException("Wrong vertical coordinate " + vertical + ", actual range is (0,"+table.getChildCount()+")");
        }
        TextView view = (TextView) row.getChildAt(horizontal);
        if (view == null) {
            throw new IndexOutOfBoundsException("Wrong horizontal coordinate " + horizontal + ", actual range is (0,"+row.getChildCount()+")");
        }
        int id = getResources().getIdentifier(image, "drawable",
                getPackageName());
        view.setBackgroundResource(id);
    }

    /**
     * Generate a value suitable for use in {setId(int)}. This value will
     * not collide with ID values generated at build time by aapt for R.id.
     *
     * @return a generated ID value
     */
    private static int generateViewId() {
        for (;;) {
            final int result = sNextGeneratedId.get();
            // aapt-generated IDs have the high byte nonzero; clamp to the range
            // under that.
            int newValue = result + 1;
            if (newValue > 0x00FFFFFF)
                newValue = 1; // Roll over to 1, not 0.
            if (sNextGeneratedId.compareAndSet(result, newValue)) {
                return result;
            }
        }
    }

    @Override
    public void onClick(View v) {
        if (v instanceof Button) {
            String name = viewNames.get(v.getId());
            userUI.onButtonPressed(name);
        } else if (v instanceof MyTextView) {
            MyTextView mv = (MyTextView) v;
            userUI.onCellPressed(mv.getVertical(), mv.getHorizontal());
        }
    }

    private Float scale;

    private int dpToPixel(int dp) {
        if (scale == null)
            scale = getResources().getDisplayMetrics().density;
        return (int) ((float) dp * scale);
    }

    @Override
    public void showTemporaryMessage(String message) {
        Toast toast = Toast.makeText(this, message, Toast.LENGTH_LONG);
        toast.show();

    }

    @Override
    public void configureScreen(int numberOfRows, int numberOfColumns,
                                int verticalSpacing, int horizontalSpacing, boolean vertical, double proportion) {
        table.removeAllViews();

        // Configurar los LayoutParams para las celdas
        TableRow.LayoutParams normalCellParams = new TableRow.LayoutParams();
        normalCellParams.rightMargin = dpToPixel(horizontalSpacing);
        normalCellParams.height = TableRow.LayoutParams.MATCH_PARENT;
        normalCellParams.width = 0;
        normalCellParams.weight = 1;

        TableRow.LayoutParams rightmostCellParams = new TableRow.LayoutParams();
        rightmostCellParams.height = TableRow.LayoutParams.MATCH_PARENT;
        rightmostCellParams.width = 0;
        rightmostCellParams.weight = 1;

        TableLayout.LayoutParams rowParams = new TableLayout.LayoutParams();
        rowParams.height = 0;
        rowParams.weight = 1;

        // Crear las filas y celdas
        for (int j = 0; j < numberOfRows; j++) {
            TableRow row = new TableRow(this);
            row.setPadding(0, verticalSpacing, 0, verticalSpacing);
            row.setGravity(Gravity.CENTER);
            row.setLayoutParams(rowParams);
            for (int i = 0; i < numberOfColumns; i++) {
                MyTextView view = new MyTextView(this);
                view.setText("");
                view.setTextSize(20);
                view.setOnClickListener(this);
                view.setCoords(i, j);
                view.setGravity(Gravity.CENTER);
                view.setSoundEffectsEnabled(false);
                if (i < numberOfColumns - 1) {
                    view.setLayoutParams(normalCellParams);
                } else {
                    view.setLayoutParams(rightmostCellParams);
                }
                row.addView(view);
            }
            table.addView(row);
        }

        // Ajustar orientación
        if (vertical) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        } else {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }

        // Obtener altura total de la pantalla, excluyendo decoraciones si es posible
        int screenHeight = getResources().getDisplayMetrics().heightPixels;

        // Validar proporción para asegurarse de que esté entre 0 y 1
        proportion = Math.max(0, Math.min(1, proportion));

        // Calcula las alturas basadas en la proporción
        int tableHeight = (int) (screenHeight * 0.75);
        int bottomSectionHeight = (int) (screenHeight * 0.25);

        // Aplicar las alturas calculadas
        FrameLayout.LayoutParams tableParams = new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT, tableHeight);
        table.setLayoutParams(tableParams);

        LinearLayout.LayoutParams bottomParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, bottomSectionHeight);
        bottomSection.setLayoutParams(bottomParams);
    }
    @Override
    public void setDynamicTitle(String title) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(title);
        }
    }

    @Override
    public void setToolbarColors(int backgroundColorResId, int titleColorResId) {
        Toolbar toolbar = findViewById(R.id.toolbar);
        if (toolbar != null) {
            int backgroundColor = backgroundColorResId != 0
                    ? getResources().getColor(backgroundColorResId)
                    : getResources().getColor(R.color.default_toolbar_color);

            int titleColor = titleColorResId != 0
                    ? getResources().getColor(titleColorResId)
                    : getResources().getColor(R.color.default_title_color);

            toolbar.setBackgroundColor(backgroundColor);
            toolbar.setTitleTextColor(titleColor);
        }
    }

    @Override
    public void reproduceSound(String s) {
        Uri uri = Uri.parse("android.resource://" + getPackageName() + "/raw/"
                + s);
        for (int i = 0; i < mp.length; ++i) {
            if (!mp[i].isPlaying()) {
                mp[i] = MediaPlayer.create(AndroidGameActivity.this, uri);
                mp[i].start();
                break;
            }
        }

    }

    @Override
    public void stopSounds() {
        for (MediaPlayer mediaPlayer : mp) {
            if (mediaPlayer.isPlaying())
                mediaPlayer.stop();
        }
    }

    // @see
    // http://stackoverflow.com/questions/10903754/input-text-dialog-android
    @Override
    public void askUserText(String title, final TextListener listener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title);

        // Set up the input
        final EditText input = new EditText(this);
        // Specify the type of input expected
        input.setInputType(InputType.TYPE_CLASS_TEXT
                | InputType.TYPE_TEXT_VARIATION_NORMAL);
        builder.setView(input);

        // Set up the buttons
        builder.setPositiveButton("OK", (dialog, which) -> listener.receiveText(input.getText().toString()));
        builder.show();
    }

    @Override
    public void storeString(String key, String value) {
        prefs.edit().putString(domain + key, value).apply();

    }

    @Override
    public void storeInt(String key, int value) {
        prefs.edit().putInt(domain + key, value).apply();
    }

    @Override
    public void storeFloat(String key, float value) {
        prefs.edit().putFloat(domain + key, value).apply();
    }

    @Override
    public void storeBoolean(String key, boolean value) {
        prefs.edit().putBoolean(domain + key, value).apply();
    }

    @Override
    public String retrieveString(String key) {
        return prefs.getString(domain + key, "");
    }

    @Override
    public int retrieveInt(String key) {
        return prefs.getInt(domain + key, 0);
    }

    @Override
    public float retrieveFloat(String key) {
        return prefs.getFloat(domain + key, 0);
    }

    @Override
    public boolean retrieveBoolean(String key) {
        return prefs.getBoolean(domain + key, false);
    }

    @Override
    public void executeLater(Runnable r, int ms) {
        handler.postDelayed(r,ms);

    }

}


