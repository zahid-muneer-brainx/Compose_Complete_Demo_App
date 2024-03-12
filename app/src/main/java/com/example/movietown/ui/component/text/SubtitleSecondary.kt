package com.example.movietown.ui.component.text

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.example.movietown.ui.theme.subTitleSecondary

@Composable
fun SubtitleSecondary(text:String) {
    Text(
        text = text,
        style = MaterialTheme.typography.subTitleSecondary
    )
}
