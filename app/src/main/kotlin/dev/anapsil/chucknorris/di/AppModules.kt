package dev.anapsil.chucknorris.di

import androidx.room.Room
import dev.anapsil.chucknorris.data.ChuckNorrisFactsRepository
import dev.anapsil.chucknorris.data.database.AppDatabase
import dev.anapsil.chucknorris.data.remote.ChuckNorrisApi
import dev.anapsil.chucknorris.facts.ui.FactsViewModel
import dev.anapsil.chucknorris.search.ui.SearchFactViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.Date

const val BASE_URL = "https://api.chucknorris.io/"
val appModule = module {
    factory { Date() }

    viewModel { FactsViewModel(get()) }
    viewModel { SearchFactViewModel(get(), get()) }

    single { ChuckNorrisFactsRepository(get(), get(), get(), get()) }

    single { Room.databaseBuilder(androidContext(), AppDatabase::class.java, "chuck-norris-facts.db").build() }
    single { get<AppDatabase>().searchTermDao() }
    single { get<AppDatabase>().categoriesDao() }
    single { get<AppDatabase>().jokesDao() }

    single { get<Retrofit>().create(ChuckNorrisApi::class.java) }

    single {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(get())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    single {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build()
    }
}