package com.example.amazonprimeclone.fragments.home.mylist.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.amazonprimeclone.R;
import com.example.amazonprimeclone.data.local.modals.MovieAndSeriesData;
import com.example.amazonprimeclone.fragments.home.HomeFragmentDirections;

import java.util.List;

public class MyListAdapter extends RecyclerView.Adapter<MyListAdapter.MyListViewHolder> {

    private final List<MovieAndSeriesData> mList;

    public MyListAdapter(List<MovieAndSeriesData> mList) {
        this.mList = mList;
    }

    @NonNull
    @Override
    public MyListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_list,parent,false);
        return new MyListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyListViewHolder holder, int position) {
        MovieAndSeriesData responseResults = mList.get(position);
        Glide.with(holder.imageView).load(responseResults.getPoster_path()).into(holder.imageView);
        holder.title.setText(responseResults.getTitle());
        holder.description.setText(responseResults.getOverview());
        holder.popularity.setText(String.valueOf(responseResults.getVote_average()));
        holder.category.setText(responseResults.getRelease_date());

        holder.itemView.setOnClickListener(v -> {

            HomeFragmentDirections.ActionNavHomeToNavMovieDetail toNavMovieDetail =
                    HomeFragmentDirections.actionNavHomeToNavMovieDetail(String.valueOf(responseResults.getId()));
            Navigation.findNavController(holder.itemView).navigate(toNavMovieDetail);
        });

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public static class MyListViewHolder extends RecyclerView.ViewHolder{

        private final ImageView imageView;
        private final TextView title,description,category,popularity;

        public MyListViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.movie_img);
            title = itemView.findViewById(R.id.movie_name);
            description = itemView.findViewById(R.id.movie_desc);
            category = itemView.findViewById(R.id.movie_category);
            popularity = itemView.findViewById(R.id.movie_popularity);
        }
    }


}
