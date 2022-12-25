package com.example.amazonprimeclone.fragments.home.more.see_all_movies.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.amazonprimeclone.R;
import com.example.amazonprimeclone.fragments.home.more.see_all_movies.SeeAllMoviesFragmentDirections;
import com.example.amazonprimeclone.modal.MovieResponseResults;

import java.util.List;

public class SeeAllAdapter extends RecyclerView.Adapter<SeeAllAdapter.SeeAllMoviesViewHolder>{

    private final List<MovieResponseResults> mList;

    public SeeAllAdapter(List<MovieResponseResults> mList) {
        this.mList = mList;
    }

    @NonNull
    @Override
    public SeeAllMoviesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_list,parent,false);
        return new SeeAllMoviesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SeeAllMoviesViewHolder holder, int position) {
        MovieResponseResults responseResults = mList.get(position);
        Glide.with(holder.imageView).load(responseResults.getPoster_path()).into(holder.imageView);
        holder.title.setText(responseResults.getTitle());
        holder.description.setText(responseResults.getOverview());
        holder.popularity.setText(String.valueOf(responseResults.getVote_average()));
        holder.category.setText(responseResults.getRelease_date());

        holder.itemView.setOnClickListener(v -> {
            SeeAllMoviesFragmentDirections.ActionSeeAllFragmentToNavMovieDetail seeAllFragmentToNavMovieDetail =
                    SeeAllMoviesFragmentDirections.actionSeeAllFragmentToNavMovieDetail(String.valueOf(responseResults.getId()));
            Navigation.findNavController(holder.itemView).navigate(seeAllFragmentToNavMovieDetail);
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public static class SeeAllMoviesViewHolder extends RecyclerView.ViewHolder{

        private final ImageView imageView;
        private final TextView title,description,category,popularity;

        public SeeAllMoviesViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.movie_img);
            title = itemView.findViewById(R.id.movie_name);
            description = itemView.findViewById(R.id.movie_desc);
            category = itemView.findViewById(R.id.movie_category);
            popularity = itemView.findViewById(R.id.movie_popularity);
        }
    }
}
