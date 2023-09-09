package com.example.data.remote.api

import com.example.data.remote.dto.GenreDto
import com.example.data.remote.dto.MovieDto
import com.example.domain.utils.Constant
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {

    @GET("genre/movie/list?api_key=${Constant.ApiKey}")
    suspend fun getCats(@Query("language") lang: String = "en"): Response<GenreDto>



    @GET("discover/movie?api_key=${Constant.ApiKey}")
    suspend fun getMovies(
        @Query("with_genres") genresId: String,
        @Query("page") page: Int,
        @Query("language") language: String = "en",
    ):Response <MovieDto>

}