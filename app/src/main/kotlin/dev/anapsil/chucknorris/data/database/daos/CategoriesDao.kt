package dev.anapsil.chucknorris.data.database.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import dev.anapsil.chucknorris.data.database.entities.CategoryEntity
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface CategoriesDao {
    @Query("SELECT * FROM categories ORDER BY RANDOM() LIMIT :limit")
    fun getAll(limit: Int): Single<List<CategoryEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(categoryEntities: List<CategoryEntity>): Completable
}