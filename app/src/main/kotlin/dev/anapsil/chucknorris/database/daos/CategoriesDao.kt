package dev.anapsil.chucknorris.database.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import dev.anapsil.chucknorris.database.entities.CategoryEntity

@Dao
interface CategoriesDao {
    @Query("SELECT * FROM categories")
    suspend fun getAll(): MutableList<CategoryEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg categoryEntities: CategoryEntity)
}