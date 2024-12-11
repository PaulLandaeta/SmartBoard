package edu.upb.lp.core.deck;

import android.content.Context;

import androidx.viewpager2.widget.ViewPager2;

import java.util.ArrayList;
import java.util.List;

public class Deck {
    List<Card> cards;
    private final Context context;

    public Deck(Context context) {
        this.cards = new ArrayList<>();
        this.context = context;
    }

    public void addCardScreen(String title, String description, int imageResourceId ) {
        cards.add(new CardScreen(title, description, imageResourceId));
    }

    public void setCardStart(String buttonText, Runnable onStart) {
        cards.add(new CardStart(buttonText, onStart));
    }

    public void setupViewPager(ViewPager2 viewPager) {
        CarouselAdapter adapter = new CarouselAdapter(context, cards);
        viewPager.setAdapter(adapter);
    }
    
}
