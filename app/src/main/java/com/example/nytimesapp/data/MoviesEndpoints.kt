package com.example.nytimesapp.data

import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesEndpoints {
    @GET("/svc/movies/v2/reviews/all.json")
    suspend fun getMovies(
        @Query("api-key") key: String,
        @Query("offset") offset: Int,
        @Query("page") page: Int,
        @Query("per_page") itemsPerPage: Int
    ): MovieData
}