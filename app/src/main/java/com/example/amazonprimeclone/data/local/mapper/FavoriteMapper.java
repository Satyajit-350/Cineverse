package com.example.amazonprimeclone.data.local.mapper;

import com.example.amazonprimeclone.data.local.modals.MovieAndSeriesData;
import com.example.amazonprimeclone.modal.MovieDetail.FavoriteMovie;

public class FavoriteMapper {

    public static MovieAndSeriesData favoriteToDatabaseFavorite(FavoriteMovie favoriteMovie) {
        return new MovieAndSeriesData(favoriteMovie.getId(), favoriteMovie.getVote_average(), favoriteMovie.getTitle(), favoriteMovie.getPoster_path(), favoriteMovie.getOriginal_language(),
                favoriteMovie.getOverview(), favoriteMovie.getRelease_date(),favoriteMovie.isMovie());
    }

    public static MovieAndSeriesData databaseFavoriteFavorite(MovieAndSeriesData movieAndSeriesData) {
        if (movieAndSeriesData == null)
            return null;
        return new MovieAndSeriesData(movieAndSeriesData.getId(),movieAndSeriesData.getVote_average(),movieAndSeriesData.getTitle()
        ,movieAndSeriesData.getPoster_path(),movieAndSeriesData.getOriginal_language(),movieAndSeriesData.getOverview(),movieAndSeriesData.getRelease_date(),movieAndSeriesData.isMovie());
    }
}
