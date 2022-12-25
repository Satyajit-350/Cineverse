package com.example.amazonprimeclone.fragments.home.more.see_all_movies;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.amazonprimeclone.databinding.FragmentSeeAllBinding;
import com.example.amazonprimeclone.fragments.home.more.see_all_movies.adapters.SeeAllAdapter;


public class SeeAllMoviesFragment extends Fragment {

    private FragmentSeeAllBinding binding;
    private SeeAllMoviesViewModel seeAllMoviesViewModel;
    private SeeAllAdapter seeAllAdapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSeeAllBinding.inflate(getLayoutInflater());
        seeAllMoviesViewModel = new ViewModelProvider(this).get(SeeAllMoviesViewModel.class);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.TitleTv.setText(seeAllMoviesViewModel.title);

        seeAllMoviesViewModel.getAllMoviesList().observe(getViewLifecycleOwner(), allMoviesResults -> {
            seeAllAdapter = new SeeAllAdapter(allMoviesResults);
            binding.allMoviesRv.setAdapter(seeAllAdapter);
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}