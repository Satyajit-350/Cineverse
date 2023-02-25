package com.example.amazonprimeclone.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.amazonprimeclone.R
import com.example.amazonprimeclone.modal.SeriesDetail.Season

class SeriesSeasonsAdapter(private val seasonsList: List<Season>):  RecyclerView.Adapter<SeriesSeasonsAdapter.SeasonsViewHolder?>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SeriesSeasonsAdapter.SeasonsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_seasons,parent,false)
        return SeasonsViewHolder(view)
    }

    override fun getItemCount(): Int {
        return seasonsList.size
    }

    override fun onBindViewHolder(holder: SeasonsViewHolder, position: Int) {
        val response = seasonsList[position]
        Glide.with(holder.posterImg).load(response.posterPath).into(holder.posterImg)
        holder.seasonName.text = response.name
        holder.episodes.text = response.episodeCount
    }

    class SeasonsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
         val posterImg: ImageView  = itemView.findViewById(R.id.image_view_movie)
         val seasonName: TextView = itemView.findViewById(R.id.season_name)
         val episodes:TextView = itemView.findViewById(R.id.episode_name)
    }

}