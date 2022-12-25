package com.example.amazonprimeclone.fragments.people.person_details;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.amazonprimeclone.adapters.home.MovieAdapter;
import com.example.amazonprimeclone.adapters.SeriesCreditAdapter;
import com.example.amazonprimeclone.databinding.FragmentPersonDetailBinding;
import com.example.amazonprimeclone.domain.listeners.DetailClickListeners;

public class PersonDetailFragment extends Fragment {
    private FragmentPersonDetailBinding binding;
    private MovieAdapter moviesKnownForAdapter;
    private SeriesCreditAdapter seriesKnownForAdapter;
    private PersonDetailsViewModel personDetailsViewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentPersonDetailBinding.inflate(getLayoutInflater());

        personDetailsViewModel = new ViewModelProvider(this).get(PersonDetailsViewModel.class);

        return binding.getRoot();
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        personDetailsViewModel.getPersonDetailsData().observe(getViewLifecycleOwner(), personDetailsModel -> {
            if(personDetailsModel!=null){
                Glide.with(binding.actorImg).load(personDetailsModel.getProfile_path()).into(binding.actorImg);
                binding.actorAbout.setText(personDetailsModel.getBiography());
                binding.actorBirthDate.setText(personDetailsModel.getBirthday());
                binding.actorTitle.setText(personDetailsModel.getName());
                binding.actorLocation.setText(personDetailsModel.getPlace_of_birth());
            }
        });

        binding.moviesKnownForRv.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        binding.moviesKnownForRv.setHasFixedSize(true);

        binding.seriesKnownForRv.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        binding.moviesKnownForRv.setHasFixedSize(true);

        personDetailsViewModel.getMoviesKnownFor().observe(getViewLifecycleOwner(), moviesKnownForList -> {
            moviesKnownForAdapter = new MovieAdapter(moviesKnownForList, id -> {
                PersonDetailFragmentDirections.ActionPersonDetailFragmentToNavMovieDetail personDetailFragmentToNavMovieDetail =
                        PersonDetailFragmentDirections.actionPersonDetailFragmentToNavMovieDetail(id);
                Navigation.findNavController(binding.getRoot()).navigate(personDetailFragmentToNavMovieDetail);
            });
            binding.moviesKnownForRv.setAdapter(moviesKnownForAdapter);
        });

        personDetailsViewModel.getSeriesKnownFor().observe(getViewLifecycleOwner(), seriesKnownForList -> {
            seriesKnownForAdapter = new SeriesCreditAdapter(getContext(), seriesKnownForList, new DetailClickListeners() {
                @Override
                public void onDetailsClickListeners(String id) {
                    PersonDetailFragmentDirections.ActionPersonDetailFragmentToSeriesDetailFragment personDetailFragmentToSeriesDetailFragment =
                            PersonDetailFragmentDirections.actionPersonDetailFragmentToSeriesDetailFragment(id);
                    Navigation.findNavController(binding.getRoot()).navigate(personDetailFragmentToSeriesDetailFragment);
                }
            });
            binding.seriesKnownForRv.setAdapter(seriesKnownForAdapter);
            seriesKnownForAdapter.notifyDataSetChanged();
        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}