package com.example.amazonprimeclone.adapters.home;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.amazonprimeclone.fragments.home.HomeFragmentDirections;
import com.example.amazonprimeclone.modal.SearchSeries;
import com.example.amazonprimeclone.modal.SeriesCategoryResults;
import com.example.amazonprimeclone.modal.SeriesResponseResults;
import com.example.amazonprimeclone.R;
import com.example.amazonprimeclone.retrofit.RetrofitClient;
import com.example.amazonprimeclone.retrofit.RetrofitService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeSeriesAdapter extends RecyclerView.Adapter<HomeSeriesAdapter.SeriesViewHolder> {

    private final Context context;
    private SeriesAdapter movieAdapter;
    private final List<SeriesCategoryResults> seriesList;
    private List<SeriesResponseResults> list;
    private final RecyclerView.RecycledViewPool recycledViewPool;
    private final RetrofitService retrofitService = RetrofitClient.getClient().create(RetrofitService .class);

    public HomeSeriesAdapter(Context context, List<SeriesCategoryResults> seriesList) {
        this.context = context;
        this.seriesList = seriesList;
        recycledViewPool = new RecyclerView.RecycledViewPool();
    }


    @NonNull
    @Override
    public SeriesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_row_layout,parent,false);
        return new SeriesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SeriesViewHolder holder, int position) {

        SeriesCategoryResults results = seriesList.get(position);

        holder.categoryText.setText(results.getName());

        holder.recyclerViewHorizontal.setAdapter(movieAdapter);

        holder.recyclerViewHorizontal.setRecycledViewPool(recycledViewPool);

        Call<SearchSeries> movieResponseCall = retrofitService.getCategoriesResultsSeries(String.valueOf(results.getId()));
        movieResponseCall.enqueue(new Callback<SearchSeries>() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onResponse(@NonNull Call<SearchSeries> call, @NonNull Response<SearchSeries> response) {
                SearchSeries searchMovie = response.body();
                //add to list
                if(searchMovie!=null){
                    list = searchMovie.getResults();
                    movieAdapter = new SeriesAdapter(list, id -> {
                        HomeFragmentDirections.ActionNavHomeToSeriesDetailFragment navHomeToSeriesDetailFragment =
                                HomeFragmentDirections.actionNavHomeToSeriesDetailFragment(id);
                        Navigation.findNavController(holder.itemView).navigate((NavDirections) navHomeToSeriesDetailFragment);
                    });
                    holder.recyclerViewHorizontal.setAdapter(movieAdapter);
                    movieAdapter.notifyDataSetChanged();
                }

            }

            @Override
            public void onFailure(@NonNull Call<SearchSeries> call, @NonNull Throwable t) {

            }
        });

        holder.seeAll.setOnClickListener(v -> {
            HomeFragmentDirections.ActionNavHomeToSeeAllSeriesFragment navHomeToSeeAllSeriesFragment =
                    HomeFragmentDirections.actionNavHomeToSeeAllSeriesFragment(holder.categoryText.getText().toString(),String.valueOf(results.getId()));
            Navigation.findNavController(holder.itemView).navigate(navHomeToSeeAllSeriesFragment);
        });

    }

    @Override
    public int getItemCount() {
        return seriesList.size();
    }

    public class SeriesViewHolder extends RecyclerView.ViewHolder{

        private final RecyclerView recyclerViewHorizontal;
        private final TextView categoryText,seeAll;

        private final LinearLayoutManager horizontalManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);

        public SeriesViewHolder(@NonNull View itemView) {
            super(itemView);

            recyclerViewHorizontal = itemView.findViewById(R.id.home_recycler_view_horizontal);
            recyclerViewHorizontal.setHasFixedSize(true);
            recyclerViewHorizontal.setNestedScrollingEnabled(false);
            recyclerViewHorizontal.setLayoutManager(horizontalManager);
            recyclerViewHorizontal.setItemAnimator(new DefaultItemAnimator());

            categoryText = itemView.findViewById(R.id.tv_movie_category);
            seeAll = itemView.findViewById(R.id.see_all_tv);

        }
    }
}
