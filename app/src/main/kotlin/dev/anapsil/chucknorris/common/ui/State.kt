package dev.anapsil.chucknorris.common.ui

sealed class State {
    object Loading : State()
    class Success(val data: Any) : State()
    class Error(val message: String?) : State()
}