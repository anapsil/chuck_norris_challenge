package dev.anapsil.chucknorris.data

import dev.anapsil.chucknorris.data.database.daos.CategoriesDao
import dev.anapsil.chucknorris.data.database.daos.JokesDao
import dev.anapsil.chucknorris.data.database.daos.SearchTermsDao
import dev.anapsil.chucknorris.data.database.entities.CategoryEntity
import dev.anapsil.chucknorris.data.database.entities.JokeEntity
import dev.anapsil.chucknorris.data.database.entities.SearchTermEntity
import dev.anapsil.chucknorris.data.remote.ChuckNorrisApi
import dev.anapsil.chucknorris.facts.data.model.JokeModel
import io.reactivex.Observable
import java.util.Date

class ChuckNorrisFactsRepository(
    private val api: ChuckNorrisApi,
    private val categoriesDao: CategoriesDao,
    private val jokesDao: JokesDao,
    private val searchTermsDao: SearchTermsDao,
) {
    fun getLocalJokes(limit: Int) = jokesDao.getJokes(limit).map { jokes -> jokes.map { JokeModel(it.id, it.url, it.value, listOf(it.category)) } }

    fun searchJokes(query: String) = api.searchJokes(query).flatMapObservable { Observable.fromIterable(it.result) }

    fun insertJokes(jokeModel: List<JokeModel>) =
        jokesDao.insertAll(jokeModel.map { JokeEntity(it.id, it.url, it.value, it.categories.firstOrNull() ?: "") })

    fun getAllTerms() = searchTermsDao.getAll().map { entity -> entity.map { it.searchTerm } }

    fun insertTerms(term: String, now: Date) = searchTermsDao.insert(SearchTermEntity(term, now.time))

    fun getCategoriesFromApi() = api.getCategories()

    fun getLocalCategories(limit: Int) = categoriesDao.getAll(limit).map { entity -> entity.map { it.category } }

    fun insertCategories(categories: List<String>) = categoriesDao.insert(categories.map { CategoryEntity(it) })
}
