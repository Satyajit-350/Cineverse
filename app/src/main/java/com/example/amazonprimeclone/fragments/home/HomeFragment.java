package com.example.amazonprimeclone.fragments.home;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.amazonprimeclone.databinding.FragmentHomeBinding;
import com.example.amazonprimeclone.fragments.home.movie.MovieFragment;
import com.example.amazonprimeclone.fragments.home.mylist.MyListFragment;
import com.example.amazonprimeclone.fragments.home.series.SeriesFragment;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    private SelectionPageAdapter selectionPageAdapter;
    private FragmentHomeBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        selectionPageAdapter = new SelectionPageAdapter(getChildFragmentManager(),requireActivity().getLifecycle());
        selectionPageAdapter.addFragment(new MovieFragment(),"Movies");
        selectionPageAdapter.addFragment(new SeriesFragment(),"Series");
        selectionPageAdapter.addFragment(new MyListFragment(),"MyList");
        binding.viewpager.setAdapter(selectionPageAdapter);
        binding.viewpager.setUserInputEnabled(false);
        binding.viewpager.setOffscreenPageLimit(3);


        new TabLayoutMediator(binding.tabLayout, binding.viewpager, true,(tab, position) -> tab.setText(selectionPageAdapter.fragmentTitleList.get(position))).attach();
        for (int i = 0; i < binding.tabLayout.getTabCount(); i++) {
            binding.tabLayout.getTabAt(i);
        }
    }

    static class SelectionPageAdapter extends FragmentStateAdapter {

        private final List<Fragment> fragmentList = new ArrayList<>();
        private final List<String> fragmentTitleList = new ArrayList<>();

        public SelectionPageAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
            super(fragmentManager, lifecycle);
        }

        public void addFragment(Fragment fragment, String title) {
            fragmentList.add(fragment);
            fragmentTitleList.add(title);
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getItemCount() {
            return fragmentList.size();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}