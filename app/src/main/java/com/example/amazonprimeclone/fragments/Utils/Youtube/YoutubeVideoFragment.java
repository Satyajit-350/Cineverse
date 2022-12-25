package com.example.amazonprimeclone.fragments.Utils.Youtube;

import android.content.pm.ActivityInfo;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.codewaves.youtubethumbnailview.ThumbnailLoader;
import com.example.amazonprimeclone.adapters.Trailers.MoreTrailerAdapter;
import com.example.amazonprimeclone.data.remote.Constants;
import com.example.amazonprimeclone.databinding.FragmentYoutubeVideoBinding;
import com.example.amazonprimeclone.fragments.Utils.FullScreenHelper;
import com.example.amazonprimeclone.modal.Trailers.TrailerResponse;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.YouTubePlayerFullScreenListener;

import java.util.ArrayList;
import java.util.List;

public class YoutubeVideoFragment extends Fragment {

    private FragmentYoutubeVideoBinding binding;
    private YoutubeVideoViewModel youtubeVideoViewModel;
    private MoreTrailerAdapter moreTrailerAdapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentYoutubeVideoBinding.inflate(getLayoutInflater());

        youtubeVideoViewModel = new ViewModelProvider(this).get(YoutubeVideoViewModel.class);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ThumbnailLoader.initialize(Constants.YOUTUBE_API_KEY);

        FullScreenHelper fullScreenHelper = new FullScreenHelper(getActivity());

        binding.moreVideoRv.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        binding.moreVideoRv.setHasFixedSize(true);

        if(youtubeVideoViewModel.trailerResponses!=null&&youtubeVideoViewModel.trailerResponses.size()>0){
            String videoId = youtubeVideoViewModel.trailerResponses.get(youtubeVideoViewModel.getPosition()).getKey();
            String title = youtubeVideoViewModel.trailerResponses.get(youtubeVideoViewModel.getPosition()).getName();
            if(title!=null){
                binding.videoTitle.setText(title);
            }
            if(videoId!=null){
                binding.thumbnailView.loadThumbnail(Constants.YOUTUBE_BASE_URL+videoId);
                binding.videoPlayerView.initialize(new AbstractYouTubePlayerListener() {
                    @Override
                    public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                        binding.thumbnailView.setVisibility(View.GONE);
                        binding.videoProgress.setVisibility(View.GONE);
                        binding.videoPlayerView.setVisibility(View.VISIBLE);

                        if(getLifecycle().getCurrentState() == Lifecycle.State.RESUMED){
                            youTubePlayer.loadVideo(videoId,0);
                        }else{
                            youTubePlayer.cueVideo(videoId,0);
                        }
                    }
                });
                //full screen
                binding.videoPlayerView.addFullScreenListener(new YouTubePlayerFullScreenListener() {
                    @Override
                    public void onYouTubePlayerEnterFullScreen() {
                        requireActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                        fullScreenHelper.enterFullScreen();
                    }

                    @Override
                    public void onYouTubePlayerExitFullScreen() {
                        requireActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                        fullScreenHelper.exitFullScreen();
                    }
                });
            }
        }

        youtubeVideoViewModel.getTrailerList().observe(getViewLifecycleOwner(), trailerResponses -> {
            try {
                trailerResponses.remove((int) youtubeVideoViewModel.getPosition());
            }catch (NullPointerException e){
                e.printStackTrace();
            }
            if(trailerResponses.size()>0){
                binding.noVideos.setVisibility(View.GONE);

                moreTrailerAdapter = new MoreTrailerAdapter(getContext(),trailerResponses);
                binding.moreVideoRv.setAdapter(moreTrailerAdapter);

                binding.moreVideoRv.setVisibility(View.VISIBLE);

            }else{
                binding.noVideos.setVisibility(View.VISIBLE);
                binding.moreVideoRv.setVisibility(View.GONE);
            }
        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding.videoPlayerView.release();
        binding = null;
    }
}