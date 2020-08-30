package dev.anapsil.chucknorris.facts.ui

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dev.anapsil.chucknorris.common.AutoDisposable
import dev.anapsil.chucknorris.common.addTo
import dev.anapsil.chucknorris.common.ui.State
import dev.anapsil.chucknorris.facts.data.FactsRepository
import dev.anapsil.chucknorris.facts.data.model.JokeModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class FactsViewModel(private val repository: FactsRepository) : ViewModel() {
    val autoDisposable = AutoDisposable()
    val state = MutableLiveData<State>()

    fun loadCategories() {
        repository.getCategories()
            .flatMapCompletable { repository.insertCategories(it) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { state.value = State.Loading }
            .doOnError { handleError(it) }
            .subscribe()
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

    private fun handleError(throwable: Throwable) {
        state.value = State.Error(throwable.message)
        Log.d("ERROR->", throwable.message!!)
    }

    private fun handleSearchSuccess(jokes: JokeModel) {
        state.postValue(State.Success(jokes))
        // TODO persist jokes
    }
}