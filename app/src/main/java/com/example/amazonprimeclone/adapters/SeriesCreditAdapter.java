package com.example.amazonprimeclone.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.amazonprimeclone.R;
import com.example.amazonprimeclone.domain.listeners.DetailClickListeners;
import com.example.amazonprimeclone.modal.CastandCredits.Series.SeriesCast;
import com.example.amazonprimeclone.modal.CastandCredits.SeriesKnownFor.Cast;

import java.util.List;

public class SeriesCreditAdapter extends RecyclerView.Adapter<SeriesCreditAdapter.ViewHolder> {

    private final Context context;
    private final List<Cast> castList;
    private final DetailClickListeners seriesDetailClickListeners;

    public SeriesCreditAdapter(Context context, List<Cast> castList, DetailClickListeners seriesDetailClickListeners) {
        this.context = context;
        this.castList = castList;
        this.seriesDetailClickListeners = seriesDetailClickListeners;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.inner_row_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Cast cast = castList.get(position);
        Glide.with(holder.movieImg).load(cast.getPosterImage()).into(holder.movieImg);
        holder.itemView.setOnClickListener(v -> seriesDetailClickListeners.onDetailsClickListeners(String.valueOf(cast.getId())));
    }

    @Override
    public int getItemCount() {
        return castList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        private final ImageView movieImg;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            movieImg = itemView.findViewById(R.id.image_view_movie);

        }
    }
}
