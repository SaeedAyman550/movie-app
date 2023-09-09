package com.example.presentation.ui.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.presentation.R


@Composable
fun coilImage(data: String, contentDescription: String, modifier: Modifier = Modifier, contentScale: ContentScale) {
    AsyncImage(
        model =
        ImageRequest.Builder(LocalContext.current)
            .data(data)
            .build(),
        placeholder = painterResource(R.drawable.placeholder),
        error = painterResource(R.drawable.placeholder),
        contentDescription = contentDescription,
        modifier = modifier,
        contentScale = contentScale,
    )
}