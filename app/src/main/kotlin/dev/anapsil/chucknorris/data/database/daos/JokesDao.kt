package dev.anapsil.chucknorris.data.database.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import dev.anapsil.chucknorris.data.database.entities.JokeEntity
import io.reactivex.Single

@Dao
interface JokesDao {

    @Query("SELECT * FROM jokes ORDER BY RANDOM() LIMIT :limit")
    fun getJokes(limit: Int): Single<List<JokeEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(jokes: List<JokeEntity>): Single<List<Long>>
}