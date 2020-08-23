package dev.anapsil.chucknorris.facts.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dev.anapsil.chucknorris.facts.data.model.FactModel

class FactsViewModel : ViewModel() {
    val facts = MutableLiveData<List<FactModel>>()

    fun loadFacts() {
        facts.value = listOf(
            FactModel(
                "fnaksjdfasfas",
                "https://www.google.com",
                "Chuck Norris roundhouse kicked a man in a wheelchair in the ground people dug that fossil up it is now a " +
                        "handicap parking sign but really it is a warning that Chuck is coming",
                listOf("history")
            ),
            FactModel("fankljfajsdacsj", "https://www.google.com", "Religons: HINDU JEWISH CHUCK NORRIS", listOf())
        )
    }

    fun searchFacts(query: String) {
        loadFacts()
    }
}