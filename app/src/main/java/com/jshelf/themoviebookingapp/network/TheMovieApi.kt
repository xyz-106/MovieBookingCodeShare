package com.jshelf.themoviebookingapp.network

import com.jshelf.themoviebookingapp.data.vos.MovieVO
import com.jshelf.themoviebookingapp.network.responses.GetCreditByMovieResponses
import com.jshelf.themoviebookingapp.network.responses.MovieListResponses
import com.jshelf.themoviebookingapp.utils.*
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface
TheMovieApi {
    @GET(API_GET_NOW_PLAYING)
    fun getNowPlayingMovies(
        @Query(PARAM_API_KEY) apiKey: String = MOVIE_API_KEY,
        @Query(PARAM_PAGE) page: Int = 1,
    ): Call<MovieListResponses>

    @GET("$API_GET_MOVIE_DETAIL/{movie_id}")
    fun getMovieDetail(
        @Path("movie_id") movieId: String,
        @Query(PARAM_API_KEY) apiKey: String = MOVIE_API_KEY
    ): Call<MovieVO>

    @GET(API_GET_UPCOMING)
    fun getUpComingMovies(
        @Query(PARAM_API_KEY) apiKey: String = MOVIE_API_KEY,
        @Query(PARAM_PAGE) page: Int = 1,
    ): Call<MovieListResponses>

    @GET("$API_GET_CREDIT_BY_MOVIE/{movie_id}/credits")
    fun getCreditByMovie(
        @Path("movie_id") movieId: String,
        @Query(PARAM_API_KEY) apiKey: String = MOVIE_API_KEY,
        @Query(PARAM_PAGE) page: Int = 1,

        ): Call<GetCreditByMovieResponses>
}