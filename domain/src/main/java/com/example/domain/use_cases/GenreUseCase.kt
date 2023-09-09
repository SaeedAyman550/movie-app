package com.example.domain.use_cases

import com.example.domain.models.GenreModel
import com.example.domain.repository.MovieRepo
import com.example.domain.utils.Resource
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GenreUseCase @Inject constructor( private val repo: MovieRepo) {


    operator fun invoke(lan:String)= flow<Resource<GenreModel>> {

          emit(Resource.Loading())
        try {
            emit(repo.getCats(lan))
        }catch (e:Exception){
            emit(Resource.Error(e.message.toString()))
        }
    }
}