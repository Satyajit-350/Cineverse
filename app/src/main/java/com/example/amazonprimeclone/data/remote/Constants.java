package com.example.amazonprimeclone.data.remote;

import com.example.amazonprimeclone.retrofit.RetrofitClient;
import com.example.amazonprimeclone.retrofit.RetrofitService;

public class Constants {

    public static final String API_KEY = "5177b3d25448b9eec5d4b28b8dfabc83";

    public static final String YOUTUBE_API_KEY = "AIzaSyBE2b18Pvj5XpZiTK3Io1ctzzd9YgORQB8";

    public static final String BASE_URL = "https://api.themoviedb.org/3/";

    public static final String YOUTUBE_BASE_URL = "https://www.youtube.com/watch?v=";

    public static final RetrofitService retrofitService = RetrofitClient.getClient().create(RetrofitService.class);
}
