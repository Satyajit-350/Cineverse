package com.example.amazonprimeclone.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.amazonprimeclone.fragments.home.more.see_all_series.SeeAllSeriesFragmentDirections;
import com.example.amazonprimeclone.modal.SeriesResponseResults;
import com.example.amazonprimeclone.R;

import java.util.List;

public class SeriesSearchAdapter extends RecyclerView.Adapter<SeriesSearchAdapter.SearchSeriesViewHolder>{

    private Context context;
    private List<SeriesResponseResults> mList;

    public SeriesSearchAdapter(Context context, List<SeriesResponseResults> mList) {
        this.context = context;
        this.mList = mList;
    }

    @NonNull
    @Override
    public SearchSeriesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_list,parent,false);
        return new SearchSeriesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchSeriesViewHolder holder, int position) {
        SeriesResponseResults responseResults = mList.get(position);
        Glide.with(holder.imageView).load(responseResults.getPoster_path()).into(holder.imageView);
        holder.title.setText(responseResults.getName());
        holder.description.setText(responseResults.getOverview());
        holder.popularity.setText(String.valueOf(responseResults.getVote_average()));
        holder.category.setText(responseResults.getFirst_air_date());

        holder.itemView.setOnClickListener(v -> {
            SeeAllSeriesFragmentDirections.ActionSeeAllSeriesFragmentToSeriesDetailFragment seeAllFragmentToNavSeriesDetail =
                    SeeAllSeriesFragmentDirections.actionSeeAllSeriesFragmentToSeriesDetailFragment(String.valueOf(responseResults.getId()));
            Navigation.findNavController(holder.itemView).navigate(seeAllFragmentToNavSeriesDetail);
        });

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public static class SearchSeriesViewHolder extends RecyclerView.ViewHolder{

        private final ImageView imageView;
        private final TextView title,description,category,popularity;

        public SearchSeriesViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.movie_img);
            title = itemView.findViewById(R.id.movie_name);
            description = itemView.findViewById(R.id.movie_desc);
            category = itemView.findViewById(R.id.movie_category);
            popularity = itemView.findViewById(R.id.movie_popularity);

        }
    }
}
