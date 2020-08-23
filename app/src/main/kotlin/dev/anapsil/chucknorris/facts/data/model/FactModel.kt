package dev.anapsil.chucknorris.facts.data.model

data class FactModel(
    val id: String,
    val url: String,
    val value: String,
    val categories: List<String>
)