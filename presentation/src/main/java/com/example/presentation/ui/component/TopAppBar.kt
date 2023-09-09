package com.example.presentation.ui.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.presentation.R


@Composable
fun topAppBar(){

    TopAppBar() {
        Row (){

            Text(
                stringResource(R.string.movie_app_tool_bar_text),
                fontSize = 20.sp,
                modifier = Modifier.padding(4.dp)
            )

        }
    }

}