package com.example.data.repository

import android.util.Log
import com.example.data.mapper.GenreMapper
import com.example.data.mapper.MovieMapper
import com.example.data.remote.api.MovieApi
import com.example.domain.models.GenreModel
import com.example.domain.models.MovieModel
import com.example.domain.repository.MovieRepo
import com.example.domain.utils.Resource
import javax.inject.Inject

class MovieRepoImp @Inject constructor(val api: MovieApi) : MovieRepo {

    override suspend fun getCats(lang: String): Resource<GenreModel> {
        return try {
            val result = api.getCats(lang)

            if (result.isSuccessful&&result.body()!=null)
                Resource.Success(GenreMapper.map(result.body()))
            else
                Resource.Error(result.message())

        } catch (e: Exception) {

            Resource.Error(e.message.toString())

        }
    }


    override suspend fun getMovies(
        genresId: String,
        page: Int,
        language: String
    ): Resource<MovieModel> {
        return try {
            val result = api.getMovies(genresId, page, language)

            if (result.isSuccessful&&result.body()!=null)
                Resource.Success(MovieMapper.map(result.body()))
            else
                Resource.Error(result.message())

        } catch (e: Exception) {
            Resource.Error(e.message.toString())

        }
    }
}