package com.example.amazonprimeclone.modal.MovieDetail;

public class SpokenLang {
    private String english_name;
    private String name;
    private String iso_639_1;

    public SpokenLang(String english_name, String name, String iso_639_1) {
        this.english_name = english_name;
        this.name = name;
        this.iso_639_1 = iso_639_1;
    }

    public String getEnglish_name() {
        return english_name;
    }

    public void setEnglish_name(String english_name) {
        this.english_name = english_name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIso_639_1() {
        return iso_639_1;
    }

    public void setIso_639_1(String iso_639_1) {
        this.iso_639_1 = iso_639_1;
    }
}
