package com.example.amazonprimeclone.di

import android.content.Context
import androidx.room.Room
import com.example.amazonprimeclone.data.local.MovieDatabase
import com.example.amazonprimeclone.retrofit.RetrofitApiServices
import com.example.amazonprimeclone.retrofit.network.RecommendationApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext

import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): MovieDatabase {
        return synchronized(this){
            Room.databaseBuilder(
                context.applicationContext,
                MovieDatabase::class.java,
                "movie_database"
            ).fallbackToDestructiveMigration().build()
        }
    }


    //base url
    //retrofit builder
    //posApiService

    @Provides
    @Singleton
    fun providesBaseUrl():String="https://api.themoviedb.org/3/"

    @Provides
    @Singleton
    @Named("Recommendation")
    fun providesRetrofitBuilder() : Retrofit =
        Retrofit.Builder()
            .baseUrl("https://recommendations-app-k2wt.onrender.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    @Named("Normal")
    fun providesMovieRetrofitBuilder(url: String) : Retrofit =
        Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun providesApiRecommendationApiService(@Named("Recommendation") retrofit: Retrofit): RecommendationApiService =
        retrofit.create(RecommendationApiService::class.java)


    @Provides
    @Singleton
    fun providesMovieApiService(@Named("Normal") retrofit: Retrofit): RetrofitApiServices = retrofit.create(RetrofitApiServices::class.java)

}