package com.example.amazonprimeclone.adapters.home.slider;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.bumptech.glide.Glide;
import com.example.amazonprimeclone.databinding.SlideItemBinding;
import com.example.amazonprimeclone.modal.SeriesResponseResults;
import com.smarteist.autoimageslider.SliderViewAdapter;

import java.util.List;

public class SeriesSliderAdapter extends SliderViewAdapter<SeriesSliderAdapter.SeriesSliderViewHolder> {

    private final List<SeriesResponseResults> mList;

    public SeriesSliderAdapter(List<SeriesResponseResults> mList) {
        this.mList = mList;
    }

    @Override
    public SeriesSliderViewHolder onCreateViewHolder(ViewGroup parent) {
        SlideItemBinding binding = SlideItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new SeriesSliderViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull SeriesSliderViewHolder holder, int position) {
        SeriesResponseResults responseResults = mList.get(position);
        Glide.with(holder.binding.slideImg).load(responseResults.getPoster_path()).into(holder.binding.slideImg);
        holder.binding.slideTitle.setText(responseResults.getName());

    }

    private final Runnable runnable = new Runnable() {
        @SuppressLint("NotifyDataSetChanged")
        @Override
        public void run() {
            mList.addAll(mList);
            notifyDataSetChanged();
        }
    };

    @Override
    public int getCount() {
        return mList.size();
    }

    public static class SeriesSliderViewHolder extends SliderViewAdapter.ViewHolder{
        private final SlideItemBinding binding;
        public SeriesSliderViewHolder(@NonNull SlideItemBinding itemView) {
            super(itemView.getRoot());
            this.binding = itemView;
        }
    }

}
