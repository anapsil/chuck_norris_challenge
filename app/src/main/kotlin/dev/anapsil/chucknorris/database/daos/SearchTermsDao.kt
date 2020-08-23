package dev.anapsil.chucknorris.database.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import dev.anapsil.chucknorris.database.entities.SearchTermEntity

@Dao
interface SearchTermsDao {
    @Query("SELECT * FROM search_terms ORDER BY creation_date DESC")
    suspend fun getAll(): MutableList<SearchTermEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg termEntities: SearchTermEntity)
}