package com.example.amazonprimeclone.fragments.home.details.movie_detail;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.amazonprimeclone.R;
import com.example.amazonprimeclone.adapters.Cast.MovieCastAdapter;
import com.example.amazonprimeclone.adapters.home.MovieAdapter;
import com.example.amazonprimeclone.adapters.Trailers.MovieTrailerAdapter;
import com.example.amazonprimeclone.databinding.FragmentMovieDetailBinding;
import com.example.amazonprimeclone.modal.MovieDetail.Genres;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;
@AndroidEntryPoint
public class MovieDetailFragment extends Fragment {

    private FragmentMovieDetailBinding binding;
    private MovieDetailsViewModel movieDetailViewModel;
    private MovieCastAdapter castAdapter;
    private MovieTrailerAdapter trailerAdapter;
    private MovieAdapter recommendAdapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentMovieDetailBinding.inflate(getLayoutInflater());

        movieDetailViewModel = new ViewModelProvider(this).get(MovieDetailsViewModel.class);

        return binding.getRoot();
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        movieDetailViewModel.getResult().observe(getViewLifecycleOwner(), exists -> {
            if(exists){
                binding.favoriteBtn.setImageDrawable(getResources().getDrawable(R.drawable.ic_like_red));
            }else{
                binding.favoriteBtn.setImageDrawable(getResources().getDrawable(R.drawable.ic_like_svgrepo_com));
            }
        });

        movieDetailViewModel.getMovieDetails().observe(getViewLifecycleOwner(), movieDetailsModel -> {
            if (movieDetailsModel != null) {
                List<Genres> genres = movieDetailsModel.getGenres();
                binding.movieDesc.setText(movieDetailsModel.getOverview());
                Glide.with(binding.moviePoster).load(movieDetailsModel.getBackdrop_path()).into(binding.moviePoster);
                binding.movieTitleTv.setText(movieDetailsModel.getTitle());
                binding.timeTv.setText(String.valueOf(movieDetailsModel.getRuntime()));
                binding.ratingTv.setText(String.valueOf(movieDetailsModel.getVote_average()));
                binding.categoryTv.setText(genres.get(0).getName());
            }
        });

        movieDetailViewModel.getMovieTrailerResponse().observe(getViewLifecycleOwner(), movieTrailerResponses -> {
            trailerAdapter = new MovieTrailerAdapter(getContext(),movieTrailerResponses);
            binding.trailerRv.setAdapter(trailerAdapter);
        });

        movieDetailViewModel.getMovieCastResponse().observe(getViewLifecycleOwner(), movieCasts -> {
            castAdapter = new MovieCastAdapter(getContext(),movieCasts);
            binding.castRv.setAdapter(castAdapter);
        });

        movieDetailViewModel.getRecommendationMovieData().observe(getViewLifecycleOwner(), movieResponseResults -> {
            recommendAdapter = new MovieAdapter(movieResponseResults, id -> {
                MovieDetailFragmentDirections.ActionNavMovieDetailSelf actionNavMovieDetailSelf = MovieDetailFragmentDirections.actionNavMovieDetailSelf(id);
                Navigation.findNavController(binding.getRoot()).navigate((NavDirections) actionNavMovieDetailSelf);
            });
            binding.recommendationRv.setAdapter(recommendAdapter);
        });

        binding.favoriteBtn.setOnClickListener(v -> {
            movieDetailViewModel.getResult().observe(getViewLifecycleOwner(), exits -> {
                if(exits){
                    movieDetailViewModel.deleteMovie();
                    binding.favoriteBtn.setImageDrawable(getResources().getDrawable(R.drawable.ic_like_svgrepo_com));
                }else{
                    movieDetailViewModel.insertMovie();
                    binding.favoriteBtn.setImageDrawable(getResources().getDrawable(R.drawable.ic_like_red));
                }
            });
        });

        binding.backBtn.setOnClickListener(v -> Navigation.findNavController(v).navigateUp());

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}