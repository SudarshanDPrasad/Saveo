package com.application.saveo.data

import androidx.lifecycle.ViewModel
import com.application.saveo.api.MovieApi
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ResultViewModel @Inject constructor(val movieApi: MovieApi) : ViewModel() {

    val repo = ResultRepo(movieApi)
    fun movieload() =
        repo.getPageList()
}