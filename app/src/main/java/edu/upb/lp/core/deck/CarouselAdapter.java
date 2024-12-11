package edu.upb.lp.core.deck;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;



import java.util.List;

import edu.upb.lp.genericgame.R;

public class CarouselAdapter extends RecyclerView.Adapter<CarouselAdapter.ViewHolder> {

    private final Context context;
    private final List<Card> cards;

    public CarouselAdapter(Context context, List<Card> cards) {
        this.context = context;
        this.cards = cards;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.screen, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Card card = cards.get(position);

        if (card instanceof CardScreen) {
            CardScreen cardScreen = (CardScreen) card;
            holder.title.setText(cardScreen.getTitle());
            holder.description.setText(cardScreen.getDescription());
            holder.image.setImageResource(cardScreen.getImageResourceId());
            holder.button.setVisibility(View.GONE);
        } else if (card instanceof CardStart) {
            CardStart cardStart = (CardStart) card;
            holder.description.setText("");
            holder.button.setText(cardStart.getButtonText());
            holder.button.setVisibility(View.VISIBLE);
            holder.button.setOnClickListener(v -> cardStart.getOnStart().run());
        }
    }

    @Override
    public int getItemCount() {
        return cards.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView title, description;
        ImageView image;
        Button button;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.screenTitle);
            description = itemView.findViewById(R.id.screenDescription);
            image = itemView.findViewById(R.id.screenImage);
            button = itemView.findViewById(R.id.screenButton);
        }
    }
}
