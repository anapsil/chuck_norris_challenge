package dev.anapsil.chucknorris.database

import androidx.room.Database
import androidx.room.RoomDatabase
import dev.anapsil.chucknorris.database.daos.CategoriesDao
import dev.anapsil.chucknorris.database.daos.SearchTermsDao
import dev.anapsil.chucknorris.database.entities.CategoryEntity
import dev.anapsil.chucknorris.database.entities.SearchTermEntity

@Database(entities = [CategoryEntity::class, SearchTermEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun categoriesDao(): CategoriesDao
    abstract fun searchTermDao(): SearchTermsDao
}