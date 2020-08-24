package dev.anapsil.chucknorris.search.data.local

import dev.anapsil.chucknorris.database.daos.CategoriesDao
import dev.anapsil.chucknorris.database.daos.SearchTermsDao
import dev.anapsil.chucknorris.database.entities.SearchTermEntity
import java.util.Date

const val MAX_CATEGORIES = 8

class SearchTermRepository(private val searchTermsDao: SearchTermsDao, private val categoriesDao: CategoriesDao) {

    suspend fun getAllTerms(): List<String> = searchTermsDao.getAll().map { it.searchTerm }

    suspend fun insertTerms(term: String, now: Date) = searchTermsDao.insertAll(SearchTermEntity(term, now.time))

    suspend fun getCategories(): List<String> = categoriesDao.getAll().apply { shuffle() }.map { it.category }.take(MAX_CATEGORIES)
}