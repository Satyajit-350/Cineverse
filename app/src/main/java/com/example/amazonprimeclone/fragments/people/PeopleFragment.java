package com.example.amazonprimeclone.fragments.people;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.amazonprimeclone.adapters.people.PeopleSearchAdapter;
import com.example.amazonprimeclone.databinding.FragmentPeopleBinding;

public class PeopleFragment extends Fragment {

    private FragmentPeopleBinding binding;
    private PeopleSearchAdapter searchAdapter;
    private PeopleFragmentViewModel peopleFragmentViewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentPeopleBinding.inflate(getLayoutInflater());
        peopleFragmentViewModel = new ViewModelProvider(this).get(PeopleFragmentViewModel.class);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.peopleSearchRv.setLayoutManager(new GridLayoutManager(getContext(),2));
        binding.peopleSearchRv.setHasFixedSize(true);

        peopleFragmentViewModel.getPopularPersonList().observe(getViewLifecycleOwner(), personResponseResults -> {
            searchAdapter = new PeopleSearchAdapter(getContext(),personResponseResults);
            binding.peopleSearchRv.setAdapter(searchAdapter);
            searchAdapter.notifyDataSetChanged();
        });

        binding.searchPeople.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                peopleFragmentViewModel.getPerformPersonListSearch(String.valueOf(s)).observe(getViewLifecycleOwner(), personResponseResults -> {
                    searchAdapter = new PeopleSearchAdapter(getContext(),personResponseResults);
                    binding.peopleSearchRv.setAdapter(searchAdapter);
                    searchAdapter.notifyDataSetChanged();
                });
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }
}