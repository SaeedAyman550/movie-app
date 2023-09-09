package com.example.presentation.ui.screen

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import com.example.domain.models.GenreItemModel
import com.example.domain.models.GenreModel
import com.example.domain.models.MovieItem
import com.example.domain.use_cases.GenreUseCase
import com.example.domain.use_cases.MovieUseCase
import com.example.domain.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.time.Duration

@HiltViewModel
class MovieViewModel @Inject constructor(
   private val movieUseCase: MovieUseCase,
    private val genreUseCase: GenreUseCase
    ):ViewModel() {



    private val _movieState= mutableStateOf<Resource<Pager<Int, MovieItem>>>(Resource.Loading())
    val movieState: State<Resource<Pager<Int, MovieItem>>> get()=_movieState

    private val _genreState= mutableStateOf<Resource<GenreModel>>(Resource.Loading())
    val genreState: State<Resource<GenreModel>> get()=_genreState

    private var _selectedGenreState = mutableStateOf(GenreItemModel())
    val selectedGenreState :State<GenreItemModel> get() = _selectedGenreState

    init {
        getGenre()
    }


    fun setSelectedGenre(selectedGenre: GenreItemModel){

            _selectedGenreState.value = selectedGenre

    }

    fun getMovies( catId:String,lang:String="en")= movieUseCase(lang,catId).onEach {

                when(it){
                    is Resource.Loading-> _movieState.value=it
                    is Resource.Success-> _movieState.value=it
                    is Resource.Error-> _movieState.value=it
                }

            }.launchIn(viewModelScope)





     fun getGenre(lang:String="en")= genreUseCase(lang).onEach {

                when(it){
                    is Resource.Loading-> _genreState.value = it
                    is Resource.Success-> _genreState.value = it
                    is Resource.Error->  _genreState.value = it

                }

        }.launchIn(viewModelScope)





}