package dev.anapsil.chucknorris.di

import androidx.room.Room
import dev.anapsil.chucknorris.database.AppDatabase
import dev.anapsil.chucknorris.facts.data.FactsRepository
import dev.anapsil.chucknorris.facts.data.remote.ChuckNorrisApi
import dev.anapsil.chucknorris.facts.ui.FactsViewModel
import dev.anapsil.chucknorris.search.data.local.SearchTermRepository
import dev.anapsil.chucknorris.search.ui.SearchFactViewModel
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.Date

const val BASE_URL = "https://api.chucknorris.io/"
val appModule = module {
    factory { Date() }

    viewModel { FactsViewModel(get()) }
    viewModel { SearchFactViewModel(get(), get()) }

    single { FactsRepository(get(), get()) }
    single { SearchTermRepository(get(), get()) }

    single { Room.databaseBuilder(androidContext(), AppDatabase::class.java, "chuck-norris-facts").build() }
    single { get<AppDatabase>().searchTermDao() }
    single { get<AppDatabase>().categoriesDao() }

    single { get<Retrofit>().create(ChuckNorrisApi::class.java) }

    single {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(OkHttpClient())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}