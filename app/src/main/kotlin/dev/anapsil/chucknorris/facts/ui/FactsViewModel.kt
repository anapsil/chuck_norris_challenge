package dev.anapsil.chucknorris.facts.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dev.anapsil.chucknorris.facts.data.model.FactModel

class FactsViewModel : ViewModel() {
    val facts = MutableLiveData<List<FactModel>>()

    fun loadFacts() = Unit
}