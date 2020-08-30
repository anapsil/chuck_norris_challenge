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

class FactsViewModel(private val repositoryChuckNorris: ChuckNorrisFactsRepository) : ViewModel() {
    val autoDisposable = AutoDisposable()
    val state = MutableLiveData<State<List<JokeModel>>>()

    fun loadCategories() {
        repositoryChuckNorris.getCategoriesFromApi()
            .flatMapCompletable { repositoryChuckNorris.insertCategories(it) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnError { handleError(it) }
            .subscribe()
            .addTo(autoDisposable)
    }

    fun searchJokes(query: String) = repositoryChuckNorris.searchJokes(query)
        .toList()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .doOnSubscribe { state.value = State.Loading }
        .doOnSuccess { repositoryChuckNorris.insertJokes(it) }
        .subscribe(::handleSearchSuccess, ::handleError)
        .addTo(autoDisposable)

    private fun handleError(throwable: Throwable) {
        state.value = State.Error(throwable.message)
    }

    private fun handleSearchSuccess(jokes: List<JokeModel>) {
        state.postValue(State.Success(jokes))
    }
}