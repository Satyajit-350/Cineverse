package com.example.amazonprimeclone.modal;

import java.util.List;

public class PersonResponseResults {

    private String adult;
    private int gender;
    private int id;
    private String name;
    private float popularity;
    private String profile_path;
    private String known_for_department;
    private List<PeopleResponseResultKnownFor> known_for;

    public PersonResponseResults(String adult, int gender, int id, String name, float popularity, String profile_path, String known_for_department, List<PeopleResponseResultKnownFor> known_for) {
        this.adult = adult;
        this.gender = gender;
        this.id = id;
        this.name = name;
        this.popularity = popularity;
        this.profile_path = profile_path;
        this.known_for_department = known_for_department;
        this.known_for = known_for;
    }

    public String getAdult() {
        return adult;
    }

    public void setAdult(String adult) {
        this.adult = adult;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
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

    public float getPopularity() {
        return popularity;
    }

    public void setPopularity(float popularity) {
        this.popularity = popularity;
    }

    public String getProfile_path() {

        String base_url = "https://image.tmdb.org/t/p/w500";

        return base_url+profile_path;
    }

    public void setProfile_path(String profile_path) {
        this.profile_path = profile_path;
    }

    public String getKnown_for_department() {
        return known_for_department;
    }

    public void setKnown_for_department(String known_for_department) {
        this.known_for_department = known_for_department;
    }

    public List<PeopleResponseResultKnownFor> getKnown_for() {
        return known_for;
    }

    public void setKnown_for(List<PeopleResponseResultKnownFor> known_for) {
        this.known_for = known_for;
    }
}
