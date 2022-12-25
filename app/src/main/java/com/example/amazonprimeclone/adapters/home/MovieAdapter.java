package com.example.amazonprimeclone.adapters.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.amazonprimeclone.domain.listeners.DetailClickListeners;
import com.example.amazonprimeclone.modal.MovieResponseResults;
import com.example.amazonprimeclone.R;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {

    private final List<MovieResponseResults> mList;
    private final DetailClickListeners movieDetailClickListeners;

    public MovieAdapter(List<MovieResponseResults> mList,DetailClickListeners movieDetailClickListeners) {
        this.movieDetailClickListeners = movieDetailClickListeners;
        this.mList = mList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.inner_row_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MovieResponseResults responseResults = mList.get(position);
        Glide.with(holder.movieImg).load(responseResults.getPoster_path()).into(holder.movieImg);
        holder.movieImg.setOnClickListener(v -> movieDetailClickListeners.onDetailsClickListeners(String.valueOf(responseResults.getId())));
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
