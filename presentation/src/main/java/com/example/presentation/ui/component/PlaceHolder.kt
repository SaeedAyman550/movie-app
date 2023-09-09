package com.example.presentation.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.presentation.R


@Composable
@Preview(showSystemUi = true, showBackground = true)
fun placeHolder(){

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.select),
            contentDescription = stringResource(R.string.movie_screen_no_image_description),
            modifier = Modifier.size(250.dp)
        )

        Text(
            text = stringResource(R.string.movie_screen_select_catogry_text),
            fontWeight = FontWeight.Bold
        )

    }


}