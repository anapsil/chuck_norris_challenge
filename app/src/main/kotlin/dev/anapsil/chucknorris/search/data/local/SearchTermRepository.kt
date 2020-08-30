package dev.anapsil.chucknorris.search.data.local

import dev.anapsil.chucknorris.database.daos.CategoriesDao
import dev.anapsil.chucknorris.database.daos.SearchTermsDao
import dev.anapsil.chucknorris.database.entities.SearchTermEntity
import java.util.Date

class SearchTermRepository(private val searchTermsDao: SearchTermsDao, private val categoriesDao: CategoriesDao) {

    fun getAllTerms() = searchTermsDao.getAll().map { entity -> entity.map { it.searchTerm } }

    fun insertTerms(term: String, now: Date) = searchTermsDao.insert(SearchTermEntity(term, now.time))

    fun getCategories() = categoriesDao.getAll().map { entity -> entity.map { it.category } }
}