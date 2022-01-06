package com.application.saveo.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.application.saveo.api.MovieApi
import javax.inject.Inject

class ResultRepo @Inject constructor( val movieApi: MovieApi) {

    fun getPageList() =
        Pager(
            config = PagingConfig(
                pageSize = 20,
                maxSize = 60
            ),
            pagingSourceFactory = { ResultPagingSource(movieApi) }
        ).liveData
}