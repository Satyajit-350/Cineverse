package com.example.amazonprimeclone.modal;

import java.util.List;

public class PersonDetailsModel {

    private List<String> also_known_as;
    private String biography;
    private String birthday;
    private String known_for_department;
    private String place_of_birth;
    private String profile_path;
    private int gender;
    private int id;
    private String name;
    private float popularity;
    private String imdb_id;
    private String deathday;
    private String homepage;
    private String adult;

    public PersonDetailsModel(List<String> also_known_as, String biography, String birthday, String known_for_department, String place_of_birth, String profile_path, int gender, int id, String name, float popularity, String imdb_id, String deathday, String homepage, String adult) {
        this.also_known_as = also_known_as;
        this.biography = biography;
        this.birthday = birthday;
        this.known_for_department = known_for_department;
        this.place_of_birth = place_of_birth;
        this.profile_path = profile_path;
        this.gender = gender;
        this.id = id;
        this.name = name;
        this.popularity = popularity;
        this.imdb_id = imdb_id;
        this.deathday = deathday;
        this.homepage = homepage;
        this.adult = adult;
    }

    public List<String> getAlso_known_as() {
        return also_known_as;
    }

    public void setAlso_known_as(List<String> also_known_as) {
        this.also_known_as = also_known_as;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getKnown_for_department() {
        return known_for_department;
    }

    public void setKnown_for_department(String known_for_department) {
        this.known_for_department = known_for_department;
    }

    public String getPlace_of_birth() {
        return place_of_birth;
    }

    public void setPlace_of_birth(String place_of_birth) {
        this.place_of_birth = place_of_birth;
    }

    public String getProfile_path() {

        String base_url = "https://image.tmdb.org/t/p/w500";

        return base_url+profile_path;
    }

    public void setProfile_path(String profile_path) {
        this.profile_path = profile_path;
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

    public String getImdb_id() {
        return imdb_id;
    }

    public void setImdb_id(String imdb_id) {
        this.imdb_id = imdb_id;
    }

    public String getDeathday() {
        return deathday;
    }

    public void setDeathday(String deathday) {
        this.deathday = deathday;
    }

    public String getHomepage() {
        return homepage;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    public String getAdult() {
        return adult;
    }

    public void setAdult(String adult) {
        this.adult = adult;
    }
}
