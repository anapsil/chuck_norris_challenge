package dev.anapsil.chucknorris.facts.data

import dev.anapsil.chucknorris.database.daos.CategoriesDao
import dev.anapsil.chucknorris.database.entities.CategoryEntity
import dev.anapsil.chucknorris.facts.data.remote.ChuckNorrisApi
import io.reactivex.Observable

class FactsRepository(private val api: ChuckNorrisApi, private val dao: CategoriesDao) {

    fun getCategories() = api.getCategories()

    fun getRandomJokesFromCategory(category: String) = api.getRandomJokesFromCategory(category)

    fun searchJokes(query: String) = api.searchJokes(query).flatMapObservable { Observable.fromIterable(it.result) }

    fun insertCategories(categories: List<String>) = dao.insert(categories.map { CategoryEntity(it) })
}