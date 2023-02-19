package com.example.amazonprimeclone.fragments.home.mylist;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.amazonprimeclone.databinding.FragmentMyListBinding;
import com.example.amazonprimeclone.fragments.home.mylist.adapter.MyListAdapter;


import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MyListFragment extends Fragment {
    private FragmentMyListBinding binding;
    private MyListViewModel viewModel;
    private MyListAdapter myListAdapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentMyListBinding.inflate(inflater,container,false);
        viewModel = new ViewModelProvider(this).get(MyListViewModel.class);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel.getGetMovieData().observe(getViewLifecycleOwner(), movieAndSeriesData -> {
            if(movieAndSeriesData.size()>0){
                binding.emptyAnimation.setVisibility(View.GONE);
            }else{
                binding.emptyAnimation.setVisibility(View.VISIBLE);
            }
            myListAdapter = new MyListAdapter(movieAndSeriesData);
            binding.allMoviesRv.setAdapter(myListAdapter);
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}