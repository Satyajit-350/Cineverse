package com.example.amazonprimeclone.modal.Trailers;

import java.util.List;

public class TrailerModel {

    private int id;
    private List<TrailerResponse> results;

    public TrailerModel(int id, List<TrailerResponse> results) {
        this.id = id;
        this.results = results;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<TrailerResponse> getResults() {
        return results;
    }

    public void setResults(List<TrailerResponse> results) {
        this.results = results;
    }
}
