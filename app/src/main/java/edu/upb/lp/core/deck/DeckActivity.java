package edu.upb.lp.core.deck;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import edu.upb.lp.core.activities.AndroidGameActivity;
import edu.upb.lp.genericgame.R;

public class DeckActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deck);

        Deck deck = new Deck(this);

        deck.addCardScreen(
                "Features",
                "Here are some cool features.",
                R.drawable.bugs_old_bug
        );
        deck.setCardStart("Let's Start", () -> {
            Intent intent = new Intent(this, AndroidGameActivity.class);
            startActivity(intent);
        });

        ViewPager2 viewPager = findViewById(R.id.viewPager);
        deck.setupViewPager(viewPager);
    }
}

