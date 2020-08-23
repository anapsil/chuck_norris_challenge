package dev.anapsil.chucknorris.database

import androidx.room.Database
import androidx.room.RoomDatabase
import dev.anapsil.chucknorris.database.daos.SearchTermsDao
import dev.anapsil.chucknorris.database.entities.SearchTermEntity

@Database(entities = [SearchTermEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun searchTermDao(): SearchTermsDao
}