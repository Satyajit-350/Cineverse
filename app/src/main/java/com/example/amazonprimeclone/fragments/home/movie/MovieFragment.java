package com.example.amazonprimeclone.fragments.home.movie;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.amazonprimeclone.adapters.home.HomeMovieAdapter;
import com.example.amazonprimeclone.adapters.home.RecommendationAdapter;
import com.example.amazonprimeclone.adapters.home.slider.MovieSliderAdapter;
import com.example.amazonprimeclone.databinding.FragmentMovieBinding;
import com.example.amazonprimeclone.fragments.home.HomeFragmentDirections;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MovieFragment extends Fragment {

    private FragmentMovieBinding binding;
    private MovieFragmentViewModel movieFragmentViewModel;
    private MovieSliderAdapter movieSliderAdapter;
    private RecommendationAdapter recommendationAdapter;
    private HomeMovieAdapter adapter;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentMovieBinding.inflate(getLayoutInflater());
        movieFragmentViewModel = new ViewModelProvider(this).get(MovieFragmentViewModel.class);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.movieShimmerLayout.startShimmer();
        /*
          Movies RecyclerView Data
         */
        movieFragmentViewModel.getResultsList().observe(getViewLifecycleOwner(), movieCategoryResults -> {
            adapter = new HomeMovieAdapter(movieCategoryResults, (title, id) -> {
                HomeFragmentDirections.ActionNavHomeToSeeAllFragment actionNavHomeToSeeAllFragment = HomeFragmentDirections.actionNavHomeToSeeAllFragment(title,id);
                Navigation.findNavController(binding.getRoot()).navigate(actionNavHomeToSeeAllFragment);
            });
            binding.rvMain.setAdapter(adapter);
        });

        handleRecommendations();

        handleSlider();

    }

    private void handleRecommendations() {
        movieFragmentViewModel.getRecommendationList().observe(getViewLifecycleOwner(), recommendedMovies ->{
            if(recommendedMovies.isEmpty()){
                binding.pickedTv.setVisibility(View.GONE);
                binding.recommendationRvHorizontal.setVisibility(View.GONE);
            }else{
                binding.pickedTv.setVisibility(View.VISIBLE);
                binding.recommendationRvHorizontal.setVisibility(View.VISIBLE);
            }
            recommendationAdapter = new RecommendationAdapter(recommendedMovies, id -> {
                HomeFragmentDirections.ActionNavHomeToNavMovieDetail actionNavHomeToNavMovieDetail = HomeFragmentDirections.actionNavHomeToNavMovieDetail(id);
                Navigation.findNavController(binding.getRoot()).navigate(actionNavHomeToNavMovieDetail);
            });
            binding.recommendationRvHorizontal.setAdapter(recommendationAdapter);
        });
    }

    private void handleSlider() {
        movieFragmentViewModel.getSlideList().observe(getViewLifecycleOwner(), slideMovieResults -> {
            movieSliderAdapter = new MovieSliderAdapter(slideMovieResults);
            binding.sliderPager.setSliderAdapter(movieSliderAdapter);
            binding.sliderPager.setScrollTimeInSec(3);
            binding.sliderPager.setAutoCycle(true);
            binding.sliderPager.startAutoCycle();
            binding.mainLayout.setVisibility(View.VISIBLE);
            binding.movieShimmerLayout.setVisibility(View.GONE);
            binding.movieShimmerLayout.stopShimmer();
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}