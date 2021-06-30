package com.example.nytimesapp.di

import com.example.nytimesapp.ui.movies.MoviesViewModel
import com.example.nytimesapp.data.MoviesEndpoints
import okhttp3.OkHttpClient
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


val apiModule = module {

    fun provideEndpoints(retrofit: Retrofit): MoviesEndpoints {
        return retrofit.create(MoviesEndpoints::class.java)
    }
    single { provideEndpoints(get()) }

}

val networkModule = module {

    fun provideHttpClient(): OkHttpClient {
        val okHttpClientBuilder = OkHttpClient.Builder()
        return okHttpClientBuilder.build()
    }

    fun provideRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.nytimes.com")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    single {
        provideRetrofit(get())
    }
    single { provideHttpClient() }
}


val viewModelModule = module {
    viewModel {
        MoviesViewModel(get())
    }
}

