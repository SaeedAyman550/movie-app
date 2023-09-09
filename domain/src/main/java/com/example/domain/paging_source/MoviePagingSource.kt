package com.example.domain.paging_source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.domain.models.MovieItem
import com.example.domain.repository.MovieRepo
import com.example.domain.utils.Resource


class MoviePagingSource (
    private val repo: MovieRepo,
    val catId : String,
    val lang:String
    ) : PagingSource<Int, MovieItem>() {


    override fun getRefreshKey(state: PagingState<Int, MovieItem>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieItem> {
       return try {
              val page = params.key ?: 1
              val result=repo.getMovies(catId,page,lang)


           when(result){
               is Resource.Success->
                   LoadResult.Page(
                       data = result.data!!.moveList,
                       prevKey = if (page == 1) null else page,
                       nextKey = if (page==result.data.totalPages.toInt()) null else page.plus(1)
                   )

               else ->  LoadResult.Error( Exception("${result.message} error in paging source"))

           }

        }catch (e:Exception){
            LoadResult.Error(e)

        }
    }
}