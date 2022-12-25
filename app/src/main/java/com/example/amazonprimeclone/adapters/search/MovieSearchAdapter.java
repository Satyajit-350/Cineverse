package com.example.amazonprimeclone.adapters.search;

import android.content.Context;
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
import com.example.amazonprimeclone.fragments.search.SearchFragmentDirections;
import com.example.amazonprimeclone.modal.MovieResponseResults;
import com.example.amazonprimeclone.R;

import java.util.List;

public class MovieSearchAdapter extends RecyclerView.Adapter<MovieSearchAdapter.SearchMovieViewHolder> {

    private final Context context;
    private final List<MovieResponseResults> mList;

    public MovieSearchAdapter(Context context, List<MovieResponseResults> mList) {
        this.context = context;
        this.mList = mList;
    }

    @NonNull
    @Override
    public SearchMovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_list,parent,false);
        return new SearchMovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchMovieViewHolder holder, int position) {
        MovieResponseResults responseResults = mList.get(position);
        Glide.with(holder.imageView).load(responseResults.getPoster_path()).into(holder.imageView);
        holder.title.setText(responseResults.getTitle());
        holder.description.setText(responseResults.getOverview());
        holder.popularity.setText(String.valueOf(responseResults.getVote_average()));
        holder.category.setText(responseResults.getRelease_date());

        holder.itemView.setOnClickListener(v -> {

            SearchFragmentDirections.ActionNavSearchToNavMovieDetail navSearchToNavMovieDetail =
                    SearchFragmentDirections.actionNavSearchToNavMovieDetail(String.valueOf(responseResults.getId()));
            Navigation.findNavController(holder.itemView).navigate((NavDirections) navSearchToNavMovieDetail);
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public static class SearchMovieViewHolder extends RecyclerView.ViewHolder{

        private final ImageView imageView;
        private final TextView title,description,category,popularity;

        public SearchMovieViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.movie_img);
            title = itemView.findViewById(R.id.movie_name);
            description = itemView.findViewById(R.id.movie_desc);
            category = itemView.findViewById(R.id.movie_category);
            popularity = itemView.findViewById(R.id.movie_popularity);
        }
    }

}
