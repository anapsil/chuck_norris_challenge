package dev.anapsil.chucknorris.data.remote

import dev.anapsil.chucknorris.facts.data.model.JokeModel

open class BaseResponse {
    var message: String? = null
}

data class JokesResponse(
    val total: Int,
    val result: List<JokeModel>
) : BaseResponse()