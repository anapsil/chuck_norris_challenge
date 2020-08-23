package dev.anapsil.chucknorris.search.data.local

import dev.anapsil.chucknorris.database.daos.SearchTermsDao
import dev.anapsil.chucknorris.database.entities.SearchTermEntity
import java.util.Date

class SearchTermRepository(private val dao: SearchTermsDao) {

    suspend fun getAllTerms(): List<String> = dao.getAll().map { it.searchTerm }

    suspend fun insertTerms(term: String, now: Date) = dao.insertAll(SearchTermEntity(term, now.time))
}