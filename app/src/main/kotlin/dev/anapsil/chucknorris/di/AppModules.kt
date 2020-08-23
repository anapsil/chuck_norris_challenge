package dev.anapsil.chucknorris.di

import androidx.room.Room
import dev.anapsil.chucknorris.database.AppDatabase
import dev.anapsil.chucknorris.facts.ui.FactsViewModel
import dev.anapsil.chucknorris.search.data.local.SearchTermRepository
import dev.anapsil.chucknorris.search.ui.SearchFactViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import java.util.Date

val appModule = module {
    factory { Date() }

    viewModel { FactsViewModel() }
    viewModel { SearchFactViewModel(get(), get()) }

    single { SearchTermRepository(get()) }

    single { Room.databaseBuilder(androidContext(), AppDatabase::class.java, "chuck-norris-facts").build() }
    single { get<AppDatabase>().searchTermDao() }
}