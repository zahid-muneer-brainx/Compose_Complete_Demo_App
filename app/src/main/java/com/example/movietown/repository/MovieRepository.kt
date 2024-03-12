package com.example.movietown.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.movietown.model.BaseModel
import com.example.movietown.model.Genres
import com.example.movietown.model.MovieItem
import com.example.movietown.model.artist.Artist
import com.example.movietown.model.artist.ArtistDetail
import com.example.movietown.model.moviedetail.MovieDetail
import com.example.movietown.remote.ApiService
import com.example.movietown.remote.paging.*
import com.example.movietown.repository.MovieRepositoryInterface
import com.example.movietown.utils.network.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MovieRepository @Inject constructor(
    private val apiService: ApiService
) : MovieRepositoryInterface {
    override suspend fun movieDetail(movieId: Int): Flow<DataState<MovieDetail>> = flow {
        emit(DataState.Loading)
        try {
            val searchResult = apiService.movieDetail(movieId)
            emit(DataState.Success(searchResult))

        } catch (e: Exception) {
            emit(DataState.Error(e))
        }
    }

    override suspend fun recommendedMovie(movieId: Int, page: Int): Flow<DataState<BaseModel>> =
        flow {
            emit(DataState.Loading)
            try {
                val searchResult = apiService.recommendedMovie(movieId, page)
                emit(DataState.Success(searchResult))

            } catch (e: Exception) {
                emit(DataState.Error(e))
            }
        }


    override suspend fun search(searchKey: String): Flow<DataState<BaseModel>> = flow {
        emit(DataState.Loading)
        try {
            val searchResult = apiService.search(searchKey)
            emit(DataState.Success(searchResult))

        } catch (e: Exception) {
            emit(DataState.Error(e))
        }
    }

    override suspend fun genreList(): Flow<DataState<Genres>> = flow {
        emit(DataState.Loading)
        try {
            val genreResult = apiService.genreList()
            emit(DataState.Success(genreResult))

        } catch (e: Exception) {
            emit(DataState.Error(e))
        }
    }

    override suspend fun movieCredit(movieId: Int): Flow<DataState<Artist>> = flow {
        emit(DataState.Loading)
        try {
            val artistResult = apiService.movieCredit(movieId)
            emit(DataState.Success(artistResult))

        } catch (e: Exception) {
            emit(DataState.Error(e))
        }
    }

    override suspend fun artistDetail(personId: Int): Flow<DataState<ArtistDetail>> = flow {
        emit(DataState.Loading)
        try {
            val artistDetailResult = apiService.artistDetail(personId)
            emit(DataState.Success(artistDetailResult))

        } catch (e: Exception) {
            emit(DataState.Error(e))
        }
    }

    override fun nowPlayingPagingDataSource(genreId: String?): Flow<PagingData<MovieItem>> = Pager(
        pagingSourceFactory = { NowPlayingPagingDataSource(apiService, genreId) },
        config = PagingConfig(pageSize = 1)
    ).flow

    override fun popularPagingDataSource(genreId: String?): Flow<PagingData<MovieItem>> = Pager(
        pagingSourceFactory = { PopularPagingDataSource(apiService, genreId) },
        config = PagingConfig(pageSize = 1)
    ).flow

    override fun topRatedPagingDataSource(genreId: String?): Flow<PagingData<MovieItem>> = Pager(
        pagingSourceFactory = { TopRatedPagingDataSource(apiService, genreId) },
        config = PagingConfig(pageSize = 1)
    ).flow

    override fun upcomingPagingDataSource(genreId: String?): Flow<PagingData<MovieItem>> = Pager(
        pagingSourceFactory = { UpcomingPagingDataSource(apiService, genreId) },
        config = PagingConfig(pageSize = 1)
    ).flow

    override fun genrePagingDataSource(genreId: String): Flow<PagingData<MovieItem>> = Pager(
        pagingSourceFactory = { GenrePagingDataSource(apiService, genreId) },
        config = PagingConfig(pageSize = 1)
    ).flow

}