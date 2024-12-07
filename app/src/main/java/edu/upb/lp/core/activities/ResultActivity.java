package edu.upb.lp.core.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import edu.upb.lp.genericgame.R;

public class ResultActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_result);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.scoreScreen), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        setupButtons();
    }

    private void setupButtons() {
        Button exitGameButton = findViewById(R.id.exitGame);
        exitGameButton.setOnClickListener(v -> {
            finishAffinity(); //TODO: connect to Menu Activity
        });
    }

    public void setupText(String texto) {
        TextView textView = findViewById(R.id.textView);
        textView.setText(texto);
    }

    public void setupImage(String image) {
        int resId = getResources().getIdentifier(image, "drawable", getPackageName());
        ImageView logoScore = findViewById(R.id.logoScore);
        logoScore.setImageResource(resId);
    }

    public void setupGameOver() {
        Button yesButton = findViewById(R.id.yesButton);
        Button noButton = findViewById(R.id.noButton);

        yesButton.setVisibility(View.VISIBLE);
        yesButton.setEnabled(true);

        noButton.setVisibility(View.VISIBLE);
        noButton.setEnabled(true);

        yesButton.setOnClickListener(v -> {
            Intent intent = new Intent(this, AndroidGameActivity.class);
            startActivity(intent);
            finish();
        });
        noButton.setOnClickListener(v -> {
            finishAffinity(); //TODO: connect to Menu Activity
        });
    }
}
