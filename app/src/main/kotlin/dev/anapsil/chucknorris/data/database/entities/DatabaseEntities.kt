package dev.anapsil.chucknorris.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "search_terms")
data class SearchTermEntity(
    @PrimaryKey @ColumnInfo(name = "search_term") val searchTerm: String,
    @ColumnInfo(name = "creation_date") val creationDate: Long
)

@Entity(tableName = "categories")
data class CategoryEntity(
    @PrimaryKey @ColumnInfo(name = "category_name") val category: String
)

@Entity(tableName = "jokes")
data class JokeEntity(
    @PrimaryKey @ColumnInfo(name = "joke_id") val id: String,
    val url: String,
    val value: String,
    val category: String?
)