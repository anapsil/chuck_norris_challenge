package dev.anapsil.chucknorris.search.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.anapsil.chucknorris.search.data.local.SearchTermRepository
import kotlinx.coroutines.launch

class SearchFactViewModel(private val repository: SearchTermRepository) : ViewModel() {
    val terms = MutableLiveData<List<String>>()

    fun loadAllTerms() {
        viewModelScope.launch {
            terms.value = repository.getAllTerms()
        }
    }

    fun saveTerm(term: String) {
        viewModelScope.launch {
            repository.insertTerms(term)
        }
    }
}