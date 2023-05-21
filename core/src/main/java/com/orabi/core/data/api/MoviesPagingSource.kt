package com.orabi.core.data.api


import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.orabi.core.domain.model.movies_list.Result
import retrofit2.HttpException
import java.io.IOException
import java.util.Locale

private const val TMDB_STARTING_PAGE_INDEX = 1

class MoviesPagingSource(
    private val service: ApiService,
    private val genre: String,
    private val query: String,
    private val isSearch: Boolean = false
) : PagingSource<Int, Result>() {


    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Result> {
        val page = params.key ?: TMDB_STARTING_PAGE_INDEX

        return try {

            val response = if (isSearch) {
                service.getSearchMovieAsync(
                    with_genres = genre,
                    language = Locale.getDefault().language, page = page, query = query
                )
            } else {
                service.getMoviesListAsync(
                    with_genres = genre,
                    language = Locale.getDefault().language, page = page, query = query
                )
            }

            val movies = response.results

            LoadResult.Page(
                data = movies,
                prevKey = if (page == 1) null else page.minus(1),
                nextKey = if (movies.isEmpty()) null else page.plus(1),
            )

        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }

    /**
     * The refresh key is used for subsequent calls to PagingSource.Load after the initial load.
     */
    override fun getRefreshKey(state: PagingState<Int, Result>): Int? {
        // We need to get the previous key (or next key if previous is null) of the page
        // that was closest to the most recently accessed index.
        // Anchor position is the most recently accessed index.
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}