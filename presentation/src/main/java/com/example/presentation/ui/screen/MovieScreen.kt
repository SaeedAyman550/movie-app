package com.example.presentation.ui.screen

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.Pager
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.domain.models.GenreItemModel
import com.example.domain.models.GenreModel
import com.example.domain.models.MovieItem
import com.example.domain.utils.Constant
import com.example.domain.utils.Resource
import com.example.presentation.R
import com.example.presentation.ui.component.coilImage
import com.example.presentation.ui.component.errorHolder
import com.example.presentation.ui.component.loadingIndicator
import com.example.presentation.ui.component.placeHolder



@Composable
fun movieScreen(viewModel:MovieViewModel= hiltViewModel()){

    movieContent(
        genreState = viewModel.genreState,
        selectedGenre = viewModel.selectedGenreState,
        movieState = viewModel.movieState,
        getMovie = { viewModel.getMovies(catId = it) }
    ){
        if (viewModel.selectedGenreState.value.id == it.id)
            viewModel.setSelectedGenre(GenreItemModel())
        else
            viewModel.setSelectedGenre(it)
    }

}


@Composable
private fun movieContent(
    genreState:State<Resource<GenreModel>>,
    selectedGenre: State<GenreItemModel>,
    movieState:State<Resource<Pager<Int, MovieItem>>>,
    getMovie: (catId: String) -> Unit,
    onMovieSelected: (movie: GenreItemModel) -> Unit
){


    Column(modifier = Modifier.fillMaxSize()) {

        Text(
            modifier = Modifier.padding(PaddingValues(8.dp)),
            text = stringResource(R.string.movieCategoryText),
            fontWeight = FontWeight.Bold
        )

        lazyGenreList(genreState =genreState , selectedGenre = selectedGenre, onSelected = onMovieSelected)


        lazyMovieList(movieState = movieState, selectedGenreState = selectedGenre,getMovie)

    }


}


@Composable
private fun lazyGenreList(
    genreState:State<Resource<GenreModel>>,
    selectedGenre: State<GenreItemModel>,
    onSelected: (movie: GenreItemModel) -> Unit){

    when (genreState.value) {
        is Resource.Loading -> loadingIndicator()
        is Resource.Success ->{
            val genreList=genreState.value.data!!.genres
            LazyRow(){

                items(genreList){item ->
                    chip(genre =item,selected=selectedGenre.value==item , onSelected = onSelected)
                }
            }
        }
        is Resource.Error -> errorHolder(text = genreState.value.message.toString())
    }
}



@Composable
fun lazyMovieList(
    movieState: State<Resource<Pager<Int,MovieItem>>>,
    selectedGenreState: State<GenreItemModel>,
    getMovie: (catId: String) -> Unit
){

    if (selectedGenreState.value.name.isNotEmpty()){

        LaunchedEffect(key1 = selectedGenreState.value ){
            getMovie(selectedGenreState.value.id.toString())

        }
        when (movieState.value) {
            is Resource.Loading -> loadingIndicator()
            is Resource.Success ->{

                val lazyPagingMovie= movieState.value.data!!.flow.collectAsLazyPagingItems()
                LazyColumn(){

                    items(lazyPagingMovie.itemCount){index ->
                    lazyPagingMovie[index]?.let { movieItem(item = it) }

                    }
                }
            }
            is Resource.Error -> errorHolder(text = movieState.value.message.toString())
        }
    }
    else{
        placeHolder()
    }
}


@Composable
private fun movieItem(item: MovieItem){
    Card(
        backgroundColor = Color.White,
        modifier = Modifier
            .padding(4.dp)
            .fillMaxSize(),
        elevation = 15.dp,
    ){
        Column() {
            coilImage(
                data = Constant.imageBaseUrl + item.posterUrl,
                contentDescription = item.name,
                contentScale = ContentScale.Crop
            )

            Text(
                text = item.name,
                fontWeight = FontWeight.Bold,
                fontSize = 12.sp,
                color = Color(0xFF000000),
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
            )
        }
    }



}

@Composable
private fun chip(
                genre: GenreItemModel,
                selected: Boolean ,
                onSelected: ((movie: GenreItemModel) -> Unit)
                ){

    Card(
        modifier = Modifier
            .clickable { onSelected(genre) }
            .padding(8.dp),
        shape = RoundedCornerShape(10.dp),
        backgroundColor = if (selected) MaterialTheme.colors.primary else MaterialTheme.colors.surface,
        elevation = if (selected) 5.dp else 0.dp
    ) {
        Text(
            modifier = Modifier.padding(8.dp),
            text = genre.name,
            fontWeight = if (selected) FontWeight.Bold else FontWeight.Normal
        )
    }


}



