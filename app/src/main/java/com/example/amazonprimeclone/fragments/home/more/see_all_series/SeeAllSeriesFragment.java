package com.example.amazonprimeclone.fragments.home.more.see_all_series;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.amazonprimeclone.adapters.SeriesSearchAdapter;
import com.example.amazonprimeclone.databinding.FragmentSeeAllSeriesBinding;
import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class SeeAllSeriesFragment extends Fragment {
    private FragmentSeeAllSeriesBinding binding;
    private SeeAllSeriesViewModel seeAllSeriesViewModel;
    private SeriesSearchAdapter searchAdapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSeeAllSeriesBinding.inflate(getLayoutInflater());
        seeAllSeriesViewModel = new ViewModelProvider(this).get(SeeAllSeriesViewModel.class);
        return binding.getRoot();
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.titleTv.setText(seeAllSeriesViewModel.getTitle());
        seeAllSeriesViewModel.getAllSeriesList().observe(getViewLifecycleOwner(), seriesResponseResults -> {
            searchAdapter = new SeriesSearchAdapter(getContext(),seriesResponseResults);
            binding.searchSeriesRv.setAdapter(searchAdapter);
            searchAdapter.notifyDataSetChanged();
        });
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}