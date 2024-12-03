package edu.upb.lp.core.adapter;

import android.content.Context;
import android.content.Intent;
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
import edu.upb.lp.core.activities.AndroidGameActivity;
import edu.upb.lp.core.model.ScreenData;

public class CarouselAdapter extends RecyclerView.Adapter<CarouselAdapter.ViewHolder> {

    private final Context context;
    private final List<ScreenData> screens;

    public CarouselAdapter(Context context, List<ScreenData> screens) {
        this.context = context;
        this.screens = screens;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.screen, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ScreenData screenData = screens.get(position);

        holder.itemView.setBackgroundColor(context.getResources().getColor(screenData.getBackgroundColor()));
        holder.title.setText(screenData.getTitle());
        holder.description.setText(screenData.getDescription());
        holder.image.setImageResource(screenData.getImageResourceId());

        if (screenData.isShowButton()) {
            holder.button.setVisibility(View.VISIBLE);
            if (screenData.isLastScreen()) {
                holder.button.setText("Start");
                holder.button.setOnClickListener(v -> {
                    Intent intent = new Intent(context, AndroidGameActivity.class);
                    context.startActivity(intent);
                });
            } else {
                holder.button.setText("Next");
                holder.button.setOnClickListener(v -> {
                    ((RecyclerView) holder.itemView.getParent()).smoothScrollToPosition(position + 1);
                });
            }
        } else {
            holder.button.setVisibility(View.GONE);
        }
    }



    @Override
    public int getItemCount() {
        return screens.size();
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
