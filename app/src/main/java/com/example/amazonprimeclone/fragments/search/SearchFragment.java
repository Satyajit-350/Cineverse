package com.example.amazonprimeclone.fragments.search;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import com.example.amazonprimeclone.adapters.search.MovieSearchAdapter;
import com.example.amazonprimeclone.databinding.FragmentSearchBinding;
import java.util.Objects;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class SearchFragment extends Fragment {

    private FragmentSearchBinding binding;
    private MovieSearchAdapter searchAdapter;
    private SearchFragmentViewModel searchFragmentViewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSearchBinding.inflate(getLayoutInflater());
        searchFragmentViewModel = new ViewModelProvider(this).get(SearchFragmentViewModel.class);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.searchMovies.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(count==0){
                    binding.textView.setVisibility(View.VISIBLE);
                    binding.searchRv.setVisibility(View.GONE);
                }else {
                    searchFragmentViewModel.getMovieResults(String.valueOf(s)).observe(getViewLifecycleOwner(), movieResponseResults -> {
                        binding.textView.setVisibility(View.GONE);
                        binding.searchRv.setVisibility(View.VISIBLE);
                        searchAdapter = new MovieSearchAdapter(getContext(),movieResponseResults);
                        binding.searchRv.setAdapter(searchAdapter);
                    });
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void hideKeyBoard(EditText editText) {
        InputMethodManager manager = (InputMethodManager) Objects.requireNonNull(requireContext()).getSystemService(Context.INPUT_METHOD_SERVICE);
        manager.hideSoftInputFromWindow(editText.getWindowToken(),0);
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Checks whether a hardware keyboard is available
        if (newConfig.hardKeyboardHidden == Configuration.HARDKEYBOARDHIDDEN_NO) {
            Toast.makeText(getContext(), "keyboard visible", Toast.LENGTH_SHORT).show();
        } else if (newConfig.hardKeyboardHidden == Configuration.HARDKEYBOARDHIDDEN_YES) {
            Toast.makeText(getContext(), "keyboard hidden", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}