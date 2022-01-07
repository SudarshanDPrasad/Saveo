package com.application.saveo.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.application.saveo.api.MovieApi
import com.application.saveo.response.ResponseDTO
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@HiltViewModel
class ResultViewModel @Inject constructor(val movieApi: MovieApi) : ViewModel() {

    val repo = ResultRepo(movieApi)
    fun movieload() =
        repo.getPageList()

    fun callApitop() : LiveData<Resource<ResponseDTO>> {
        return liveData(Dispatchers.IO) {
            val data = repo.callApi()
            emit(data)
        }
    }
}