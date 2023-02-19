package com.example.amazonprimeclone.adapters.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.RecyclerView;

import com.example.amazonprimeclone.data.remote.Constants;
import com.example.amazonprimeclone.domain.listeners.SectionClickListeners;
import com.example.amazonprimeclone.fragments.home.HomeFragmentDirections;
import com.example.amazonprimeclone.modal.MovieCategoryResults;
import com.example.amazonprimeclone.modal.MovieResponseResults;
import com.example.amazonprimeclone.modal.SearchMovie;
import com.example.amazonprimeclone.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeMovieAdapter extends RecyclerView.Adapter<HomeMovieAdapter.HomeViewHolder> {

    private MovieAdapter movieAdapter;
    private final List<MovieCategoryResults> movieList;
    private List<MovieResponseResults> list;
    private final RecyclerView.RecycledViewPool recycledViewPool;
    private final SectionClickListeners sectionMovieClickListener;

    public HomeMovieAdapter(List<MovieCategoryResults> movieList,SectionClickListeners sectionMovieClickListener) {
        this.movieList = movieList;
        this.sectionMovieClickListener = sectionMovieClickListener;
        recycledViewPool = new RecyclerView.RecycledViewPool();
    }

    @NonNull
    @Override
    public HomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_row_layout,parent,false);
        return new HomeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeViewHolder holder, int position) {

        MovieCategoryResults results = movieList.get(position);
        holder.categoryText.setText(results.getName());
        holder.recyclerViewHorizontal.setAdapter(movieAdapter);
        holder.recyclerViewHorizontal.setRecycledViewPool(recycledViewPool);

        Call<SearchMovie> movieResponseCall = Constants.retrofitService.getCategoriesResults(String.valueOf(results.getId()));
        movieResponseCall.enqueue(new Callback<SearchMovie>() {
            @Override
            public void onResponse(@NonNull Call<SearchMovie> call, @NonNull Response<SearchMovie> response) {
                SearchMovie searchMovie = response.body();
                //add to list
                if(searchMovie!=null){
                    list = searchMovie.getResults();
                    movieAdapter = new MovieAdapter(list, id -> {
                        HomeFragmentDirections.ActionNavHomeToNavMovieDetail actionNavHomeToNavMovieDetail = HomeFragmentDirections.actionNavHomeToNavMovieDetail(id);
                        Navigation.findNavController(holder.itemView).navigate((NavDirections) actionNavHomeToNavMovieDetail);
                    });
                    holder.recyclerViewHorizontal.setAdapter(movieAdapter);
                }
            }
            @Override
            public void onFailure(@NonNull Call<SearchMovie> call, @NonNull Throwable t) {
            }
        });

        holder.seeAllTv.setOnClickListener(v ->
                sectionMovieClickListener.onSectionMovieClicked(
                        holder.categoryText.getText().toString()
                        ,String.valueOf(results.getId())
                )
        );

    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    public static class HomeViewHolder extends RecyclerView.ViewHolder{
        private final RecyclerView recyclerViewHorizontal;
        private final TextView categoryText;
        private final TextView seeAllTv;
        public HomeViewHolder(@NonNull View itemView) {
            super(itemView);
            recyclerViewHorizontal = itemView.findViewById(R.id.home_recycler_view_horizontal);
            recyclerViewHorizontal.setHasFixedSize(true);
            recyclerViewHorizontal.setNestedScrollingEnabled(false);
            recyclerViewHorizontal.setItemAnimator(new DefaultItemAnimator());
            categoryText = itemView.findViewById(R.id.tv_movie_category);
            seeAllTv = itemView.findViewById(R.id.see_all_tv);
        }
    }
}
