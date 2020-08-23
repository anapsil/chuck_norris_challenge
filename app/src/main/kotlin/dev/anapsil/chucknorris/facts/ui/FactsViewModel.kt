package dev.anapsil.chucknorris.facts.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dev.anapsil.chucknorris.facts.data.model.FactModel

class FactsViewModel : ViewModel() {
    val facts = MutableLiveData<List<FactModel>>()

    fun loadFacts() {
        facts.value = listOf(
            FactModel("fnaksjdfasfas", "https://www.google.com", "fbaiukfjbasdfbakjs", listOf()),
            FactModel("fankljfajsdacsj", "https://www.google.com", "bla bla bla bla bla", listOf())
        )
    }
}