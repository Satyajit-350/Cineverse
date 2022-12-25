package com.example.amazonprimeclone.fragments.home.series;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.amazonprimeclone.adapters.home.HomeSeriesAdapter;
import com.example.amazonprimeclone.adapters.home.slider.SeriesSliderAdapter;
import com.example.amazonprimeclone.databinding.FragmentSeriesBinding;

public class SeriesFragment extends Fragment {

    private FragmentSeriesBinding binding;
    private SeriesFragmentViewModel seriesFragmentViewModel;
    private SeriesSliderAdapter sliderAdapter;
    private HomeSeriesAdapter seriesAdapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSeriesBinding.inflate(getLayoutInflater());
        seriesFragmentViewModel = new ViewModelProvider(this).get(SeriesFragmentViewModel.class);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        seriesFragmentViewModel.getResultsList().observe(getViewLifecycleOwner(), seriesCategoryResults -> {
            seriesAdapter = new HomeSeriesAdapter(getContext(),seriesCategoryResults);
            binding.seriesRvMain.setAdapter(seriesAdapter);
        });
        handleSlider();
    }

    private void handleSlider() {
        seriesFragmentViewModel.getSlideList().observe(getViewLifecycleOwner(), seriesSlideResults -> {
            sliderAdapter = new SeriesSliderAdapter(seriesSlideResults);
            binding.seriesSliderPager.setSliderAdapter(sliderAdapter);
            binding.seriesSliderPager.setScrollTimeInSec(3);
            binding.seriesSliderPager.setAutoCycle(true);
            binding.seriesSliderPager.startAutoCycle();
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}