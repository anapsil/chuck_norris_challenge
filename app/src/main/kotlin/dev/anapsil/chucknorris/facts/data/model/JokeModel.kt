package dev.anapsil.chucknorris.facts.data.model

data class JokeModel(
    val id: String,
    val url: String,
    val value: String,
    val categories: List<String>
)