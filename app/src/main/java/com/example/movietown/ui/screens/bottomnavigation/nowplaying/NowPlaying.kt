package com.example.movietown.ui.screens.bottomnavigation.nowplaying

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.movietown.model.GenreId
import com.example.movietown.model.moviedetail.Genre
import com.example.movietown.ui.component.MovieItemList

@Composable
fun NowPlaying(
    navController: NavController,
    genres: ArrayList<Genre>? = null,
) {
    val nowPlayViewModel = hiltViewModel<NowPlayingViewModel>()
    MovieItemList(
        navController = navController,
        movies = nowPlayViewModel.nowPlayingMovies,
        genres = genres,
        selectedName = nowPlayViewModel.selectedGenre.value
    ){
        nowPlayViewModel.filterData.value = GenreId(it?.id.toString())
        it?.let {
            nowPlayViewModel.selectedGenre.value = it
        }
    }
}