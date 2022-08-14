package com.example.retrofitandcoroutine.service

import com.example.retrofitandcoroutine.BuildConfig
import com.example.retrofitandcoroutine.model.Movie
import com.example.retrofitandcoroutine.model.TMDBResponse
import retrofit2.http.GET

public interface TheMovieDbService {
    @GET("movie/top_rated?api_key=${BuildConfig.TMDB_API_KEY}")
    suspend fun topMovies(): TMDBResponse<Movie>
}