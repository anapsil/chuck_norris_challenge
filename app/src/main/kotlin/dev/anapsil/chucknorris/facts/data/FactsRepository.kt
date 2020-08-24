package dev.anapsil.chucknorris.facts.data

import dev.anapsil.chucknorris.database.daos.CategoriesDao
import dev.anapsil.chucknorris.database.entities.CategoryEntity
import dev.anapsil.chucknorris.facts.data.remote.ChuckNorrisApi
import io.reactivex.rxjava3.core.Observable

class FactsRepository(private val api: ChuckNorrisApi, private val dao: CategoriesDao) {

    fun getCategories() = api.getCategories().flatMapObservable { Observable.fromIterable(it) }

    fun getRandomJokesFromCategory(category: String) = api.getRandomJokesFromCategory(category)

    fun searchJokes(query: String) = api.searchJokes(query).flatMapObservable { Observable.fromIterable(it.result) }

    suspend fun insertCategories(category: String) {
        dao.insertAll(CategoryEntity(category))
    }
}