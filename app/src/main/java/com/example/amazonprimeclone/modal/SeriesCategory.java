package com.example.amazonprimeclone.modal;

import java.util.List;

public class SeriesCategory {

    private List<SeriesCategoryResults> genres;

    public SeriesCategory(List<SeriesCategoryResults> genres) {
        this.genres = genres;
    }

    public List<SeriesCategoryResults> getGenres() {
        return genres;
    }

    public void setGenres(List<SeriesCategoryResults> genres) {
        this.genres = genres;
    }
}
