package dev.anapsil.chucknorris.di

import dev.anapsil.chucknorris.facts.ui.FactsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    viewModel { FactsViewModel() }
}