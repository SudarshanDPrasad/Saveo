package com.application.saveo.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.application.saveo.api.MovieApi
import com.application.saveo.module.MovieModule
import com.application.saveo.response.ResponseDTO
import javax.inject.Inject

class ResultRepo @Inject constructor(val movieApi: MovieApi) {

    val responseHandler = ResponseHandler()

    fun getPageList() =
        Pager(
            config = PagingConfig(
                pageSize = 20,
                maxSize = 60
            ),
            pagingSourceFactory = { ResultPagingSource(movieApi) }
        ).liveData

    suspend fun callApi(): Resource<ResponseDTO> {
        return try {
            val response = MovieModule.ProvidesApi().apimovies(1)
            responseHandler.handleSuccess(response)
        } catch (e: Exception) {
            responseHandler.handleException(e)
        }
    }
}