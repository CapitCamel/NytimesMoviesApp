package com.example.nytimesapp.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import retrofit2.HttpException
import java.io.IOException

class MoviesPagingSource(
    private val api: MoviesEndpoints
): PagingSource<Int, Results>() {
    override fun getRefreshKey(state: PagingState<Int, Results>): Int? {
        val anchorPosition = state.anchorPosition ?: return null
        val anchorPage = state.closestPageToPosition(anchorPosition) ?: return null
        return anchorPage.prevKey?.plus(1) ?: anchorPage.nextKey?.minus(1)
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Results> {

        val pageNumber = params.key ?: INITIAL_PAGE_NUMBER
        val pageSize = params.loadSize
        val offset = (pageNumber-1)*20

        return try {
            val response = api.getMovies("ZG5rOg1XCxAFUdPgh9gve8UvqIEm9YKX", offset, pageNumber, pageSize )
            val movies = response.results

            LoadResult.Page(
                data = movies,
                prevKey = if (pageNumber == INITIAL_PAGE_NUMBER) null else pageNumber - 1,
                nextKey = if (movies.isEmpty()) null else pageNumber + 1
            )
        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }


    }


    companion object {
        const val INITIAL_PAGE_NUMBER = 1
    }
}