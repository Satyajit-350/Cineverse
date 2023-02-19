package com.example.amazonprimeclone.fragments.home.details.series_detail;

import android.annotation.SuppressLint;
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
import com.example.amazonprimeclone.adapters.Cast.SeriesCastAdapter;
import com.example.amazonprimeclone.adapters.Trailers.SeriesTrailerAdapter;
import com.example.amazonprimeclone.adapters.home.SeriesAdapter;
import com.example.amazonprimeclone.databinding.FragmentSeriesDetailBinding;
import com.example.amazonprimeclone.modal.SeriesDetail.Genre;

import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class SeriesDetailFragment extends Fragment {

    private FragmentSeriesDetailBinding binding;
    private SeriesDetailsViewModel seriesDetailViewModel;
    private SeriesTrailerAdapter trailerAdapter;
    private SeriesAdapter recommendAdapter;
    private SeriesCastAdapter castAdapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSeriesDetailBinding.inflate(inflater,container,false);
        seriesDetailViewModel = new ViewModelProvider(this).get(SeriesDetailsViewModel.class);
        return binding.getRoot();
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        seriesDetailViewModel.getResult().observe(getViewLifecycleOwner(), exists -> {
            if(exists){
                binding.favoriteBtn.setImageDrawable(getResources().getDrawable(R.drawable.ic_like_red));
            }else{
                binding.favoriteBtn.setImageDrawable(getResources().getDrawable(R.drawable.ic_like_svgrepo_com));
            }
        });

        seriesDetailViewModel.getSeriesDetailsList().observe(getViewLifecycleOwner(), seriesDetailsModel -> {
            if (seriesDetailsModel != null) {
                List<Genre> genres = seriesDetailsModel.getGenres();
                binding.movieDesc.setText(seriesDetailsModel.getOverview());
                Glide.with(binding.moviePoster).load(seriesDetailsModel.getPosterUrl()).into(binding.moviePoster);
                binding.movieTitleTv.setText(seriesDetailsModel.getName());
                binding.timeTv.setText(String.valueOf(seriesDetailsModel.getEpisode_run_time()));
                binding.ratingTv.setText(String.format("%.1f", seriesDetailsModel.getVote_average()));
                binding.categoryTv.setText(genres.get(0).getName());
            }
        });

        seriesDetailViewModel.getSeriesTrailerResponse().observe(getViewLifecycleOwner(), seriesTrailerResponses -> {
            trailerAdapter = new SeriesTrailerAdapter(getContext(),seriesTrailerResponses);
            binding.trailerRv.setAdapter(trailerAdapter);
        });

        seriesDetailViewModel.getRecommendationSeriesData().observe(getViewLifecycleOwner(), seriesResponseResults -> {
            recommendAdapter = new SeriesAdapter(seriesResponseResults, id -> {
                SeriesDetailFragmentDirections.ActionSeriesDetailFragmentSelf actionNavSeriesDetailSelf =
                        SeriesDetailFragmentDirections.actionSeriesDetailFragmentSelf(id);
                Navigation.findNavController(binding.getRoot()).navigate((NavDirections) actionNavSeriesDetailSelf);
            });
            binding.recommendationRv.setAdapter(recommendAdapter);
        });

        seriesDetailViewModel.getSeriesCastResponse().observe(getViewLifecycleOwner(), seriesCasts -> {
            castAdapter = new SeriesCastAdapter(getContext(),seriesCasts);
            binding.castRv.setAdapter(castAdapter);
        });

        binding.favoriteBtn.setOnClickListener(v -> {
            seriesDetailViewModel.getResult().observe(getViewLifecycleOwner(), exits -> {
                if(exits){
                    seriesDetailViewModel.deleteMovie();
                    binding.favoriteBtn.setImageDrawable(getResources().getDrawable(R.drawable.ic_like_svgrepo_com));
                }else{
                    seriesDetailViewModel.insertMovie();
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