package dev.anapsil.chucknorris.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import dev.anapsil.chucknorris.data.database.daos.CategoriesDao
import dev.anapsil.chucknorris.data.database.daos.JokesDao
import dev.anapsil.chucknorris.data.database.daos.SearchTermsDao
import dev.anapsil.chucknorris.data.database.entities.CategoryEntity
import dev.anapsil.chucknorris.data.database.entities.JokeEntity
import dev.anapsil.chucknorris.data.database.entities.SearchTermEntity

@Database(entities = [JokeEntity::class, CategoryEntity::class, SearchTermEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun jokesDao(): JokesDao
    abstract fun categoriesDao(): CategoriesDao
    abstract fun searchTermDao(): SearchTermsDao
}