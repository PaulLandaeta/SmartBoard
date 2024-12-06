package edu.upb.lp.core.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import edu.upb.lp.genericgame.R;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_menu);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.menu), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        setupButtons();
    }

    private void setupButtons() {
        ImageButton buttonClose = findViewById(R.id.button_close);
        buttonClose.setOnClickListener(v -> finish());

        Button buttonTutorial = findViewById(R.id.button_tutorial);
//        if (isTutorialAvailable()) {
//            buttonTutorial.setVisibility(View.VISIBLE);
//            buttonTutorial.setOnClickListener(v -> redirectTo(TutorialActivity.class));
//        } else {
//            buttonTutorial.setVisibility(View.GONE);
//        }

        Button buttonExit = findViewById(R.id.button_exit);
        buttonExit.setOnClickListener(v -> finish());

        Button buttonScore = findViewById(R.id.button_score);
//        if (isScoreAvailable()) {
//            buttonScore.setVisibility(View.VISIBLE);
//            buttonScore.setOnClickListener(v -> redirectTo(ScoreActivity.class));
//        } else {
//            buttonScore.setVisibility(View.GONE);
//        }

        Button buttonPlay = findViewById(R.id.button_play);
        buttonPlay.setOnClickListener(v -> redirectTo(AndroidGameActivity.class));

        Button buttonMoreAboutUs = findViewById(R.id.button_more_about_us);
//        buttonMoreAboutUs.setOnClickListener(v -> redirectTo(AboutUsActivity.class));
    }

//    private boolean isTutorialAvailable() {
//        return getResources().getBoolean(R.bool.is_tutorial_available);
//    }
//
//    private boolean isScoreAvailable() {
//        return getResources().getBoolean(R.bool.is_score_available);
//    }

    private void redirectTo(Class<?> targetActivity) {
        Intent intent = new Intent(MenuActivity.this, targetActivity);
        startActivity(intent);
    }
}
