package com.example.domain.use_cases

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.domain.models.MovieItem
import com.example.domain.paging_source.MoviePagingSource
import com.example.domain.repository.MovieRepo
import com.example.domain.utils.Resource
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MovieUseCase @Inject constructor( val repo:MovieRepo) {

    operator fun invoke(lang:String,catId:String)= flow<Resource<Pager<Int, MovieItem>>> {

        emit(Resource.Loading())
        try {
            val moviePaging= Pager(
                config = PagingConfig(pageSize = 10),
                pagingSourceFactory = {
                    MoviePagingSource(repo, catId, lang)
                }
            )
            emit(Resource.Success(moviePaging))
        }catch (e:Exception){
            emit(Resource.Error(e.message.toString()))

        }
    }
}