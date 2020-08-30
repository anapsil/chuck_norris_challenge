package dev.anapsil.chucknorris.search.ui

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dev.anapsil.chucknorris.common.AutoDisposable
import dev.anapsil.chucknorris.common.addTo
import dev.anapsil.chucknorris.data.ChuckNorrisFactsRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.Date

const val MAX_CATEGORIES = 8

class SearchFactViewModel(private val repository: ChuckNorrisFactsRepository, private val now: Date) : ViewModel() {
    val autoDisposable = AutoDisposable()
    val terms = MutableLiveData<List<String>>()
    val categories = MutableLiveData<List<String>>()

    fun loadAllTerms() {
        repository.getAllTerms()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(::onTermsLoaded)
            .addTo(autoDisposable)
    }

    fun loadLocalCategories() {
        repository.getLocalCategories(MAX_CATEGORIES)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(::onCategoriesLoaded, ::onError)
            .addTo(autoDisposable)
    }

    fun saveTerm(term: String) {
        repository.insertTerms(term, now)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()
            .addTo(autoDisposable)
    }

    private fun onTermsLoaded(termsLoaded: List<String>) {
        terms.value = termsLoaded
    }

    private fun onCategoriesLoaded(allCategories: List<String>) {
        categories.value = allCategories
    }

    private fun onError(error: Throwable) {
        Log.e("ERROR", error.message!!)
    }
}