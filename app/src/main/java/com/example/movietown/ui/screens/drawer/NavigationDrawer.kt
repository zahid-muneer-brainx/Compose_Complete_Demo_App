package com.example.movietown.ui.screens.drawer

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Colors
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Movie
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.movietown.model.moviedetail.Genre
import com.example.movietown.navigation.Screen
import com.example.movietown.navigation.currentRoute

@Composable
fun DrawerUI(
    navController: NavController,
    genres: List<Genre>,
    closeDrawer: (genreName: String) -> Unit
) {
    val listState = rememberLazyListState()
    LazyColumn(
        state = listState,
        modifier = Modifier
            .fillMaxHeight()
    ) {
        items(items = genres, itemContent = { item ->
            DrawerItem(
                item = item,
                selected = currentRoute(navController) == "",
                onItemClick = {
                    if (it.id != null){
                        navController.navigate(Screen.NavigationDrawer.route.plus("/${it.id.toString()}")) {
                            launchSingleTop = true
                        }
                    }
                    item.isSelected = item.isSelected.not()
                    genres.forEach { genre ->
                        genre.isSelected = genre == item
                    }
                    // Close drawer
                    closeDrawer(it.name)

                })
        })
    }
}

@Composable
fun DrawerItem(item: Genre, selected: Boolean, onItemClick: (Genre) -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = { onItemClick(item) })
            .height(45.dp)
            .padding(start = 10.dp)
    ) {
        Icon(
            Icons.Outlined.Movie, "", modifier = Modifier
                .height(24.dp)
                .width(24.dp)
        )
        Spacer(modifier = Modifier.width(7.dp))
        val textColor = if (item.isSelected) Color.White else Color.White.copy(0.4f)
        Text(
            text = item.name,
            fontSize = 18.sp,
            color = textColor
        )
    }
}
