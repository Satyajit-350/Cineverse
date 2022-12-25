package com.example.amazonprimeclone.adapters.Trailers;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.codewaves.youtubethumbnailview.ThumbnailLoader;
import com.codewaves.youtubethumbnailview.ThumbnailLoadingListener;
import com.codewaves.youtubethumbnailview.ThumbnailView;
import com.example.amazonprimeclone.data.remote.Constants;
import com.example.amazonprimeclone.fragments.home.details.movie_detail.MovieDetailFragmentDirections;
import com.example.amazonprimeclone.modal.Trailers.TrailerResponse;
import com.example.amazonprimeclone.R;

import java.util.ArrayList;
import java.util.List;

public class MovieTrailerAdapter extends RecyclerView.Adapter<MovieTrailerAdapter.MovieTrailerViewHolder> {

    private final Context context;
    private final List<TrailerResponse> trailerResponseList;

    public MovieTrailerAdapter(Context context, List<TrailerResponse> trailerResponseList) {
        this.context = context;
        this.trailerResponseList = trailerResponseList;
    }

    @NonNull
    @Override
    public MovieTrailerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.trailer_item,parent,false);
        return new MovieTrailerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieTrailerViewHolder holder, int position) {
        TrailerResponse response = trailerResponseList.get(position);

        String videoUrl = Constants.YOUTUBE_BASE_URL + response.getKey();

        ThumbnailLoader.initialize(Constants.YOUTUBE_API_KEY);

        holder.trailerThumbnail.loadThumbnail(videoUrl, new ThumbnailLoadingListener() {
            @Override
            public void onLoadingStarted(@NonNull String url, @NonNull View view) {

            }

            @Override
            public void onLoadingComplete(@NonNull String url, @NonNull View view) {

            }

            @Override
            public void onLoadingCanceled(@NonNull String url, @NonNull View view) {

            }

            @Override
            public void onLoadingFailed(@NonNull String url, @NonNull View view, Throwable error) {
                Toast.makeText(context, error.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        holder.trailerThumbnail.setOnClickListener(v -> {

            ArrayList<TrailerResponse> movieThumbnailList = new ArrayList<>(trailerResponseList);
            MovieDetailFragmentDirections.ActionNavMovieDetailToYoutubeVideoFragment navMovieDetailToYoutubeVideoFragment =
                    MovieDetailFragmentDirections.actionNavMovieDetailToYoutubeVideoFragment(movieThumbnailList,holder.getAdapterPosition());
            Navigation.findNavController(holder.itemView).navigate(navMovieDetailToYoutubeVideoFragment);

        });
    }

    @Override
    public int getItemCount() {
        return trailerResponseList.size();
    }

    public static class MovieTrailerViewHolder extends RecyclerView.ViewHolder{

        private final ThumbnailView trailerThumbnail;

        public MovieTrailerViewHolder(@NonNull View itemView) {
            super(itemView);

            trailerThumbnail = itemView.findViewById(R.id.trailer_img);
        }
    }
}
