package com.example.amazonprimeclone.adapters.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.amazonprimeclone.domain.listeners.DetailClickListeners;
import com.example.amazonprimeclone.modal.SeriesResponseResults;
import com.example.amazonprimeclone.R;

import java.util.List;

public class SeriesAdapter extends RecyclerView.Adapter<SeriesAdapter.ViewHolder> {

    private final List<SeriesResponseResults> mList;
    private final DetailClickListeners seriesDetailClickListeners;

    public SeriesAdapter(List<SeriesResponseResults> mList, DetailClickListeners seriesDetailClickListeners) {
        this.mList = mList;
        this.seriesDetailClickListeners = seriesDetailClickListeners;
    }

    @NonNull
    @Override
    public SeriesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.inner_row_layout,parent,false);
        return new SeriesAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SeriesAdapter.ViewHolder holder, int position) {
        SeriesResponseResults responseResults = mList.get(position);
        Glide.with(holder.movieImg).load(responseResults.getPoster_path()).into(holder.movieImg);
        holder.movieImg.setOnClickListener(v -> seriesDetailClickListeners.onDetailsClickListeners(String.valueOf(responseResults.getId())));
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        private final ImageView movieImg;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            movieImg = itemView.findViewById(R.id.image_view_movie);
        }
    }
}
