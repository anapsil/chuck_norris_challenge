package dev.anapsil.chucknorris.data.database.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import dev.anapsil.chucknorris.data.database.entities.SearchTermEntity
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface SearchTermsDao {
    @Query("SELECT * FROM search_terms ORDER BY creation_date DESC")
    fun getAll(): Single<List<SearchTermEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(termEntities: SearchTermEntity): Completable
}