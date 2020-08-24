package dev.anapsil.chucknorris.facts.data.model

open class BaseResponse {
    var message: String? = null
}

data class JokesResponse(
    val total: Int,
    val result: List<JokeModel>
) : BaseResponse()