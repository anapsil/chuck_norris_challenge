package dev.anapsil.chucknorris.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "search_terms")
data class SearchTermEntity(
    @PrimaryKey @ColumnInfo(name = "search_term") val searchTerm: String,
    @ColumnInfo(name = "creation_date") val creationDate: String
)