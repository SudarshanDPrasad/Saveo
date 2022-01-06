package com.application.saveo.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.application.saveo.api.MovieApi
import com.application.saveo.module.MovieModule
import com.application.saveo.response.ResponseDTO
import com.application.saveo.response.ResultDTO
import javax.inject.Inject

class ResultPagingSource @Inject constructor(val movieApi: MovieApi ) : PagingSource<Int, ResultDTO>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ResultDTO> {
        val pageNumber = params.key ?: 1
        val responseDto: ResponseDTO = movieApi.apimovies(pageNumber)
        val result: List<ResultDTO> = responseDto.results

        return try {
            LoadResult.Page(
                data = result,
                prevKey = null,
                nextKey = if (result.isEmpty()) null else pageNumber + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, ResultDTO>): Int? {
        return state.anchorPosition
    }
}