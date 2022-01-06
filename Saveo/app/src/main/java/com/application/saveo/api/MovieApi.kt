package com.application.saveo.api

import com.application.saveo.response.ResponseDTO
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {

    // https://api.themoviedb.org/3/movie/popular?api_key=23e22ddba5c8cb112a8613486167f192&language=en-US&page=2
    @GET("movie/popular?api_key=23e22ddba5c8cb112a8613486167f192&language=en-US")
    suspend fun apimovies(@Query("page") Page : Int ) : ResponseDTO
}