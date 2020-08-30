package dev.anapsil.chucknorris.data.remote

import dev.anapsil.chucknorris.facts.data.model.JokeModel
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ChuckNorrisApi {

    @GET("jokes/categories")
    fun getCategories(): Single<List<String>>

    @GET("jokes/random")
    fun getRandomJokesFromCategory(@Query("category") category: String): Single<JokeModel>

    @GET("jokes/search")
    fun searchJokes(@Query("query") query: String): Single<JokesResponse>
}