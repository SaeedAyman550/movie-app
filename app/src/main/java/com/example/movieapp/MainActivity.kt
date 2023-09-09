package com.example.movieapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.*
import com.example.presentation.theme.MovieAppTheme
import com.example.presentation.ui.component.topAppBar
import com.example.presentation.ui.screen.movieScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MovieAppTheme(){
                Scaffold(
                    topBar= { topAppBar() }
                ) {
                    movieScreen()
                }
                
            }
             
        }
    }
}






