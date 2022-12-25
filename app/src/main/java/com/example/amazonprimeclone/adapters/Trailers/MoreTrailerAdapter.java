package com.example.amazonprimeclone.adapters.Trailers;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.codewaves.youtubethumbnailview.ThumbnailLoader;
import com.codewaves.youtubethumbnailview.ThumbnailLoadingListener;
import com.codewaves.youtubethumbnailview.ThumbnailView;
import com.example.amazonprimeclone.data.remote.Constants;
import com.example.amazonprimeclone.fragments.Utils.Youtube.YoutubeVideoFragmentDirections;
import com.example.amazonprimeclone.modal.Trailers.TrailerResponse;
import com.example.amazonprimeclone.R;

import java.util.ArrayList;
import java.util.List;

public class MoreTrailerAdapter extends RecyclerView.Adapter<MoreTrailerAdapter.MoreTrailerViewHolder> {

    private final Context context;
    private final List<TrailerResponse> trailerResponseList;

    public MoreTrailerAdapter(Context context, List<TrailerResponse> trailerResponseList) {
        this.context = context;
        this.trailerResponseList = trailerResponseList;
    }

    @NonNull
    @Override
    public MoreTrailerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view  = LayoutInflater.from(parent.getContext()).inflate(R.layout.more_trailer_layout,parent,false);
        return new MoreTrailerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MoreTrailerViewHolder holder, int position) {

        TrailerResponse response = trailerResponseList.get(position);

        String videoUrl = Constants.YOUTUBE_BASE_URL + response.getKey();

        ThumbnailLoader.initialize(Constants.YOUTUBE_API_KEY);

        holder.thumbnailView.loadThumbnail(videoUrl, new ThumbnailLoadingListener() {
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

        holder.title.setText(response.getName());

        holder.itemView.setOnClickListener(v -> {

            ArrayList<TrailerResponse> movieThumbnailList = new ArrayList<>(trailerResponseList);
            YoutubeVideoFragmentDirections.ActionYoutubeVideoFragmentSelf youtubeVideoFragmentSelf =
                    YoutubeVideoFragmentDirections.actionYoutubeVideoFragmentSelf(movieThumbnailList,holder.getAbsoluteAdapterPosition());
            Navigation.findNavController(holder.itemView).navigate(youtubeVideoFragmentSelf);

        });

    }

    @Override
    public int getItemCount() {
        return trailerResponseList.size();
    }

    public static class MoreTrailerViewHolder extends RecyclerView.ViewHolder{

        private final ThumbnailView thumbnailView;
        private final TextView title;

        public MoreTrailerViewHolder(@NonNull View itemView) {
            super(itemView);

            thumbnailView = itemView.findViewById(R.id.trailer_img);
            title = itemView.findViewById(R.id.video_title);
        }
    }

}
