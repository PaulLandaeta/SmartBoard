package edu.upb.lp.core.activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import java.util.List;

import edu.upb.lp.core.deck.Card;
import edu.upb.lp.core.deck.CarouselAdapter;
import edu.upb.lp.genericgame.R;

public class DeckActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deck);

        // Catching data from previous activity
        Intent intent = getIntent();
        List<Card> cards = intent.getParcelableArrayListExtra("DECK");

        // Configuring ViewPager2 in order to show correctly data from cards
        ViewPager2 viewPager = findViewById(R.id.viewPager);
        CarouselAdapter adapter = new CarouselAdapter(this, cards);
        viewPager.setAdapter(adapter);
    }
}

