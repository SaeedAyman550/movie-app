package com.example.domain.repository

import com.example.domain.models.GenreModel
import com.example.domain.models.MovieModel
import com.example.domain.utils.Constant
import com.example.domain.utils.Resource

interface MovieRepo {



    suspend fun getCats( lang: String = "en"):Resource <GenreModel>


    suspend fun getMovies(genresId: String, page: Int, language: String = "en"):Resource <MovieModel>


}