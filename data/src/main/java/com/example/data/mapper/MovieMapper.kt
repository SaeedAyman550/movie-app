package com.example.data.mapper

import com.example.data.remote.dto.MovieDto
import com.example.data.remote.dto.MovieItemDto
import com.example.domain.models.MovieItem
import com.example.domain.models.MovieModel

object MovieMapper:Mapper<MovieDto?,MovieModel> {
    override fun map(input: MovieDto?): MovieModel =
        if (input != null) {
            MovieModel(
                moveList = input.moveList?.map {
                    MovieItemMapper.map(it)
                } ?: emptyList(),
                page = input.page ?: 0L,
                totalPages = input.totalPages ?: 0L,
                totalResults = input.totalResults ?: 0L
            )
        } else
            MovieModel()


    object MovieItemMapper:Mapper<MovieItemDto?, MovieItem> {

        override fun map(input: MovieItemDto?): MovieItem =
            if (input != null) {
                MovieItem(
                    id = input.id ?: 0L,
                    posterUrl = input.posterPath ?: "",
                    name = input.title ?: ""
                )
            } else
                MovieItem()
    }
}