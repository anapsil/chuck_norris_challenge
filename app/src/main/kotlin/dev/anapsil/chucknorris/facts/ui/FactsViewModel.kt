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
    val state = MutableLiveData<State<List<JokeModel>>>()

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
            .subscribe(::handleSearchSuccess, ::handleError)
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

    private fun handleSearchSuccess(jokes: List<JokeModel>) {
        state.postValue(State.Success(jokes))
//        repository.insertJokes(jokes)
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .doOnError { Log.d("ERROR", it.message!!) }
//            .doOnSuccess { jokes -> jokes.forEach { Log.d("INDEX", it.toString()) } }
//            .subscribe()
//            .addTo(autoDisposable)
    }
}