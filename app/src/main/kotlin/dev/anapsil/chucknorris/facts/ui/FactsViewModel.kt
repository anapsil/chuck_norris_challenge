package dev.anapsil.chucknorris.facts.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dev.anapsil.chucknorris.common.AutoDisposable
import dev.anapsil.chucknorris.common.addTo
import dev.anapsil.chucknorris.common.ui.State
import dev.anapsil.chucknorris.data.ChuckNorrisFactsRepository
import dev.anapsil.chucknorris.facts.data.model.JokeModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

const val MAX_JOKES = 10

class FactsViewModel(private val repository: ChuckNorrisFactsRepository) : ViewModel() {
    val autoDisposable = AutoDisposable()
    val state = MutableLiveData<State<List<JokeModel>>>(State.NoData)

    fun loadCategories() {
        repository.getCategoriesFromApi()
            .flatMapCompletable { repository.insertCategories(it) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnError { handleError(it) }
            .subscribe()
            .addTo(autoDisposable)
    }

    fun getLocalJokes() {
        repository.getLocalJokes(MAX_JOKES)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ jokes -> handleSearchSuccess(jokes, false) }, ::handleError)
            .addTo(autoDisposable)
    }

    fun searchJokes(query: String) = repository.searchJokes(query)
        .toList()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .doOnSubscribe { state.value = State.Loading }
        .subscribe(::handleSearchSuccess, ::handleError)
        .addTo(autoDisposable)

    private fun handleError(throwable: Throwable) {
        state.value = State.Error(throwable.message)
    }

    private fun handleSearchSuccess(jokes: List<JokeModel>, save: Boolean = true) {
        if (jokes.isNotEmpty()) {
            state.value = State.Success(jokes)
        } else {
            state.value = State.NoData
        }
        if (save) {
            repository.insertJokes(jokes)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe()
                .addTo(autoDisposable)
        }
    }
}