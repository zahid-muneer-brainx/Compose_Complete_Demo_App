package com.example.movietown.ui.screens.bottomnavigation.popular

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.movietown.model.GenreId
import com.example.movietown.model.moviedetail.Genre
import com.example.movietown.ui.component.MovieItemList
import com.piashcse.hilt_mvvm_compose_movie.ui.screens.bottomnavigation.popular.PopularViewModel

@Composable
fun Popular(
    navController: NavController,
    genres: ArrayList<Genre>? = null,
) {
    val popularViewModel = hiltViewModel<PopularViewModel>()
    MovieItemList(
        navController = navController,
        movies = popularViewModel.popularMovies,
        genres = genres,
        selectedName = popularViewModel.selectedGenre.value
    ){
        popularViewModel.filterData.value = GenreId(it?.id.toString())
        it?.let {
            popularViewModel.selectedGenre.value = it
        }
    }
}