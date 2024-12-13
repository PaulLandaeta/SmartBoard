package edu.upb.lp.core.deck;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import edu.upb.lp.genericgame.R;

public class CarouselViewHolderFactory {
    public static RecyclerView.ViewHolder create(ViewGroup parent, int viewType, Context context) {
        LayoutInflater inflater = LayoutInflater.from(context);

        if (viewType == CarouselAdapter.TYPE_CARD_SCREEN) {
            View view = inflater.inflate(R.layout.card_screen, parent, false);
            return new CarouselAdapter.CardScreenCardViewHolder(view);
        } else if (viewType == CarouselAdapter.TYPE_CARD_START) {
            View view = inflater.inflate(R.layout.card_start, parent, false);
            return new CarouselAdapter.CardStartCardViewHolder(view);
        } else {
            throw new IllegalArgumentException("Unknown view type: " + viewType);
        }
    }
}
