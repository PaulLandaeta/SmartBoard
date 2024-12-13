package edu.upb.lp.core.deck;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.util.List;

import edu.upb.lp.genericgame.R;

public class CarouselAdapter extends RecyclerView.Adapter<CarouselAdapter.CardViewHolder> {
    public static final int TYPE_CARD_SCREEN = 0;
    public static final int TYPE_CARD_START = 1;

    private final Context context;
    private List<Card> cards;

    public CarouselAdapter(Context context, List<Card> cards) {
        this.context = context;
        this.cards = cards;
    }

    @Override
    public int getItemViewType(int position) {
        if (position != cards.size() - 1) {
            return TYPE_CARD_SCREEN;
        } else {
            return TYPE_CARD_START;
        }
    }

    @NonNull
    @Override
    public CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return (CardViewHolder) CarouselViewHolderFactory.create(parent, viewType, context);
    }

    // TODO: Util o Helper?
    private int getSourceId(Activity activity, String sourceName) {
        @SuppressLint("DiscouragedApi") int id = activity.getResources().getIdentifier(sourceName, "drawable", activity.getPackageName());
        return id;
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewHolder holder, int position) {
        if (getItemViewType(position) == TYPE_CARD_SCREEN) {
            CardScreenCardViewHolder cardHolder = (CardScreenCardViewHolder) holder;
            CardScreen cardScreen = (CardScreen) cards.get(position);

            cardHolder.title.setText(cardScreen.getTitle());
            cardHolder.description.setText(cardScreen.getDescription());
            int idImageSource = getSourceId((Activity) cardHolder.itemView.getContext(), cardScreen.getImage());
            cardHolder.image.setImageResource(idImageSource);
        } else {
            CardStartCardViewHolder startHolder = (CardStartCardViewHolder) holder;
            CardStart cardStart = (CardStart) cards.get(position);

            startHolder.button.setText(cardStart.getButtonText());
            startHolder.button.setOnClickListener(v -> {
                ((Activity) startHolder.itemView.getContext()).finish();
            });
        }
    }

    @Override
    public int getItemCount() {
        return cards.size();
    }

    public static class CardViewHolder extends RecyclerView.ViewHolder {
        public CardViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    public static class CardScreenCardViewHolder extends CardViewHolder {
        TextView title, description;
        ImageView image;

        public CardScreenCardViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title_text);
            description = itemView.findViewById(R.id.description_text);
            image = itemView.findViewById(R.id.center_image);
        }
    }

    public static class CardStartCardViewHolder extends CardViewHolder {
        Button button;

        public CardStartCardViewHolder(@NonNull View itemView) {
            super(itemView);
            button = itemView.findViewById(R.id.btn_start);
        }
    }
}
