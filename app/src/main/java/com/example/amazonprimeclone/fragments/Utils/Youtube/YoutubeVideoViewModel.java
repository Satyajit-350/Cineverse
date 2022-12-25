package com.example.amazonprimeclone.fragments.Utils.Youtube;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;

import com.example.amazonprimeclone.modal.Trailers.TrailerResponse;

import java.util.ArrayList;
import java.util.List;

public class YoutubeVideoViewModel extends ViewModel {
    private final MutableLiveData<List<TrailerResponse>> trailerList;
    public final ArrayList<TrailerResponse> trailerResponses;
    public final Integer position;

    public YoutubeVideoViewModel(SavedStateHandle savedStateHandle){
        trailerList = new MutableLiveData<>();
        trailerResponses = savedStateHandle.get("videos_list");
        position = savedStateHandle.get("position");

        trailerList.setValue(trailerResponses);
    }

    public Integer getPosition() {
        return position;
    }

    public MutableLiveData<List<TrailerResponse>> getTrailerList() {
        return trailerList;
    }
}
