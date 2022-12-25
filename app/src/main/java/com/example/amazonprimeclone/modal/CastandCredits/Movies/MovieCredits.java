package com.example.amazonprimeclone.modal.CastandCredits.Movies;

import java.util.List;

public class MovieCredits {

    private List<MovieCast> cast;
    private List<MovieCrew> crew;
    private int id;

    public MovieCredits(List<MovieCast> cast, List<MovieCrew> crew, int id) {
        this.cast = cast;
        this.crew = crew;
        this.id = id;
    }

    public List<MovieCast> getCast() {
        return cast;
    }

    public void setCast(List<MovieCast> cast) {
        this.cast = cast;
    }

    public List<MovieCrew> getCrew() {
        return crew;
    }

    public void setCrew(List<MovieCrew> crew) {
        this.crew = crew;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
