package com.example.data.mapper

import com.example.data.remote.dto.GenreDto
import com.example.data.remote.dto.GenreItemDto
import com.example.domain.models.GenreItemModel
import com.example.domain.models.GenreModel

object GenreMapper:Mapper<GenreDto?,GenreModel> {
    override fun map(input: GenreDto?): GenreModel =
        if (input!=null) {
            GenreModel(
                genres = input.genres?.map {
                    GenreItemMapper.map(it)
                }?: emptyList()
            )
        }else
            GenreModel()

    object GenreItemMapper:Mapper<GenreItemDto?, GenreItemModel> {
        override fun map(input: GenreItemDto?): GenreItemModel =
            if (input!=null)
                GenreItemModel(
                    id = input.id?:0,
                    name = input.name?:""
                )
            else
                GenreItemModel()

    }
}