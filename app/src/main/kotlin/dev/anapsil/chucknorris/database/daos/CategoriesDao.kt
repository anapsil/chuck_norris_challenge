package dev.anapsil.chucknorris.database.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import dev.anapsil.chucknorris.database.entities.CategoryEntity
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface CategoriesDao {
    @Query("SELECT * FROM categories")
    fun getAll(): Single<List<CategoryEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(categoryEntities: List<CategoryEntity>): Completable
}