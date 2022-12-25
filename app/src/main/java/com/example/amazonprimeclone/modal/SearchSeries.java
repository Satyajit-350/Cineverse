package com.example.amazonprimeclone.modal;

import java.util.List;

public class SearchSeries {

    private int page;
    private int total_result;
    private int total_pages;

    private List<SeriesResponseResults> results;

    public SearchSeries(int page, int total_result, int total_pages, List<SeriesResponseResults> results) {
        this.page = page;
        this.total_result = total_result;
        this.total_pages = total_pages;
        this.results = results;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getTotal_result() {
        return total_result;
    }

    public void setTotal_result(int total_result) {
        this.total_result = total_result;
    }

    public int getTotal_pages() {
        return total_pages;
    }

    public void setTotal_pages(int total_pages) {
        this.total_pages = total_pages;
    }

    public List<SeriesResponseResults> getResults() {
        return results;
    }

    public void setResults(List<SeriesResponseResults> results) {
        this.results = results;
    }
}
