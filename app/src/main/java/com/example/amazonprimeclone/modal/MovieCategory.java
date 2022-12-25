package com.example.amazonprimeclone.modal;

import java.util.List;

public class MovieCategory {

    private List<MovieCategoryResults> genres;

    public MovieCategory(List<MovieCategoryResults> genres) {
        this.genres = genres;
    }

    public List<MovieCategoryResults> getGenres() {
        return genres;
    }

    public void setGenres(List<MovieCategoryResults> genres) {
        this.genres = genres;
    }
}
