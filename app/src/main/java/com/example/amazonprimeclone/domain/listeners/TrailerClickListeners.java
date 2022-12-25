package com.example.amazonprimeclone.domain.listeners;

import com.example.amazonprimeclone.modal.Trailers.TrailerResponse;

import java.util.ArrayList;

public interface TrailerClickListeners {

    void onClickListeners(ArrayList<TrailerResponse> videos_list,int position);
}
