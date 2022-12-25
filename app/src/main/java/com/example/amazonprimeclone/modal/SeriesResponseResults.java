package com.example.amazonprimeclone.modal;

import java.util.List;

public class SeriesResponseResults {
    private long vote_count;
    private float popularity;
    private int id;
    private String name;
    private float vote_average;
    private String poster_path;
    private String original_language;
    private String original_title;
    private List<Integer> genre_ids;
    private List<String> origin_country;
    private String overview;
    private String backdrop_path;
    private String first_air_date;

    public SeriesResponseResults(long vote_count, float popularity, int id, String name, float vote_average, String poster_path, String original_language, String original_title, List<Integer> genre_ids, List<String> origin_country, String overview, String backdrop_path, String first_air_date) {
        this.vote_count = vote_count;
        this.popularity = popularity;
        this.id = id;
        this.name = name;
        this.vote_average = vote_average;
        this.poster_path = poster_path;
        this.original_language = original_language;
        this.original_title = original_title;
        this.genre_ids = genre_ids;
        this.origin_country = origin_country;
        this.overview = overview;
        this.backdrop_path = backdrop_path;
        this.first_air_date = first_air_date;
    }

    public long getVote_count() {
        return vote_count;
    }

    public void setVote_count(long vote_count) {
        this.vote_count = vote_count;
    }

    public float getPopularity() {
        return popularity;
    }

    public void setPopularity(float popularity) {
        this.popularity = popularity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getVote_average() {
        return vote_average;
    }

    public void setVote_average(float vote_average) {
        this.vote_average = vote_average;
    }

    public String getPoster_path() {

        String base_url = "https://image.tmdb.org/t/p/w500";

        return base_url+poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public String getOriginal_language() {
        return original_language;
    }

    public void setOriginal_language(String original_language) {
        this.original_language = original_language;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
    }

    public List<Integer> getGenre_ids() {
        return genre_ids;
    }

    public void setGenre_ids(List<Integer> genre_ids) {
        this.genre_ids = genre_ids;
    }

    public List<String> getOrigin_country() {
        return origin_country;
    }

    public void setOrigin_country(List<String> origin_country) {
        this.origin_country = origin_country;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }

    public String getFirst_air_date() {
        return first_air_date;
    }

    public void setFirst_air_date(String first_air_date) {
        this.first_air_date = first_air_date;
    }
}
