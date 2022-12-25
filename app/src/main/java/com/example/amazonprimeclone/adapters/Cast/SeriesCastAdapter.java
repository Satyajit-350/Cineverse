package com.example.amazonprimeclone.adapters.Cast;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.amazonprimeclone.R;
import com.example.amazonprimeclone.fragments.home.details.series_detail.SeriesDetailFragmentDirections;
import com.example.amazonprimeclone.modal.CastandCredits.Series.SeriesCast;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class SeriesCastAdapter extends RecyclerView.Adapter<SeriesCastAdapter.ViewHolder>{


    private final Context context;
    private final List<SeriesCast> castList;

    public SeriesCastAdapter(Context context, List<SeriesCast> castList) {
        this.context = context;
        this.castList = castList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cast_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        SeriesCast movieCast = castList.get(position);
        Glide.with(holder.castImage).load(movieCast.getProfileImage()).into(holder.castImage);
        holder.castName.setText(movieCast.getName());
        holder.castRole.setText(movieCast.getCharacter());
        holder.itemView.setOnClickListener(v -> {
            SeriesDetailFragmentDirections.ActionSeriesDetailFragmentToPersonDetailFragment seriesDetailFragmentToPersonDetailFragment =
                    SeriesDetailFragmentDirections.actionSeriesDetailFragmentToPersonDetailFragment(String.valueOf(movieCast.getId()));
            Navigation.findNavController(holder.itemView).navigate(seriesDetailFragmentToPersonDetailFragment);
        });
    }

    @Override
    public int getItemCount() {
        return castList.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder{

        private final CircleImageView castImage;
        private final TextView castName,castRole;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            castImage = itemView.findViewById(R.id.cast_img);
            castName = itemView.findViewById(R.id.cast_name);
            castRole= itemView.findViewById(R.id.cast_role);
        }
    }

}
