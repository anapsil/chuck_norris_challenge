package dev.anapsil.chucknorris.facts.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.anapsil.chucknorris.common.AutoDisposable
import dev.anapsil.chucknorris.common.addTo
import dev.anapsil.chucknorris.common.ui.State
import dev.anapsil.chucknorris.facts.data.FactsRepository
import dev.anapsil.chucknorris.facts.data.model.JokeModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.launch

class FactsViewModel(private val repository: FactsRepository) : ViewModel() {
    val autoDisposable = AutoDisposable()
    val state = MutableLiveData<State>()

    fun loadCategories() {
        repository.getCategories()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { state.value = State.Loading }
            .subscribe(::handleCategoriesSuccess, ::handleError)
            .addTo(autoDisposable)
    }

    fun searchJokes(query: String) {
        repository.searchJokes(query)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { state.value = State.Loading }
            .subscribe(::handleSearchSuccess, ::handleError)
            .addTo(autoDisposable)
    }

    private fun handleCategoriesSuccess(category: String) {
        viewModelScope.launch {
            repository.insertCategories(category)
        }
    }

    private fun handleError(throwable: Throwable) {
        state.value = State.Error(throwable.message)
    }

    private fun handleSearchSuccess(jokes: JokeModel) {
        state.postValue(State.Success(jokes))
        // TODO persist jokes
    }
}