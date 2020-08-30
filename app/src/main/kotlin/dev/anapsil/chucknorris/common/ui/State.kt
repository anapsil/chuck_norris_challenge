package dev.anapsil.chucknorris.common.ui

sealed class State<out T : Any> {
    object Loading : State<Nothing>()
    object NoData : State<Nothing>()
    class Success<out T : Any>(val data: T) : State<T>()
    class Error(val message: String?) : State<Nothing>()
}