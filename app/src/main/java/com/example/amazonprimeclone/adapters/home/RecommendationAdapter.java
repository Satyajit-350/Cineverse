package com.example.amazonprimeclone.adapters.home;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.amazonprimeclone.data.local.modals.RecommendedMovies;
import com.example.amazonprimeclone.databinding.InnerRowLayoutBinding;
import com.example.amazonprimeclone.domain.listeners.DetailClickListeners;

import java.util.List;

public class RecommendationAdapter extends RecyclerView.Adapter<RecommendationAdapter.RecommendationViewHolder> {

    private final List<RecommendedMovies> recommendedMoviesList;
    private final DetailClickListeners movieDetailClickListeners;

    public RecommendationAdapter(List<RecommendedMovies> recommendedMoviesList, DetailClickListeners movieDetailClickListeners) {
        this.recommendedMoviesList = recommendedMoviesList;
        this.movieDetailClickListeners = movieDetailClickListeners;
    }

    @NonNull
    @Override
    public RecommendationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        InnerRowLayoutBinding binding = InnerRowLayoutBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new RecommendationViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecommendationViewHolder holder, int position) {

        RecommendedMovies recommendedMovies = recommendedMoviesList.get(position);
        Glide.with(holder.binding.imageViewMovie).load(recommendedMovies.getPoster()).into(holder.binding.imageViewMovie);

        holder.itemView.setOnClickListener(v -> movieDetailClickListeners.onDetailsClickListeners(recommendedMovies.getMovieId()));
    }

    @Override
    public int getItemCount() {
        return recommendedMoviesList.size();
    }

    public static class RecommendationViewHolder extends RecyclerView.ViewHolder{
        private final InnerRowLayoutBinding binding;
        public RecommendationViewHolder(@NonNull InnerRowLayoutBinding itemView) {
            super(itemView.getRoot());
            this.binding = itemView;
        }
    }
}
