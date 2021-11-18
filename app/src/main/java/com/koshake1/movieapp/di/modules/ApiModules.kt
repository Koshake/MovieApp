package com.koshake1.movieapp.di.modules

import android.widget.ImageView
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.koshake1.movieapp.api.ApiService
import com.koshake1.movieapp.model.data_sources.RemoteDataSource
import com.koshake1.movieapp.model.data_sources.RemoteDataSourceImpl
import com.koshake1.movieapp.model.repository.MoviesRepository
import com.koshake1.movieapp.model.repository.MoviesRepositoryImpl
import com.koshake1.movieapp.model.repository.image.GlideImageLoader
import com.koshake1.movieapp.model.repository.image.ImageLoader
import com.koshake1.movieapp.view_model.MoviesViewModel
import com.koshake1.movieapp.view_model.OverviewViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_API_URL = "https://api.themoviedb.org/3/"

val retrofitModule = module {
    single {
        val client = OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build()

        Retrofit.Builder()
            .baseUrl(BASE_API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .client(client)
            .build().create(ApiService::class.java)
    }
}

val dataSourceModule = module {
    single<RemoteDataSource> { RemoteDataSourceImpl(get()) }
}

val imageModule = module {
    single<ImageLoader<ImageView>> { GlideImageLoader() }
}

val repositoryModule = module {
    single<MoviesRepository> { MoviesRepositoryImpl(get()) }
}

val viewModelModule = module {
    viewModel { MoviesViewModel(get()) }
    viewModel { OverviewViewModel(get()) }
}