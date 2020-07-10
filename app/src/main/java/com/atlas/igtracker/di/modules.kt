package com.atlas.igtracker.di

import com.atlas.igtracker.http.GraphService
import com.atlas.igtracker.splash.intent.SplashIntentFactory
import com.atlas.igtracker.splash.model.SplashModelStore
import com.facebook.CallbackManager
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

const val GRAPH_URL = "https://graph.facebook.com"
const val GRAPH_VERSION = "v7.0"

val interceptor: HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
    this.level = HttpLoggingInterceptor.Level.BODY
}

val okHttpClient : OkHttpClient = OkHttpClient.Builder().apply {
    this.addInterceptor(interceptor)
}.build()

val providerModule = module {
    single { GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create() }
    single { provideRetrofit() }
    single { provideGraphApi(get()) }

    single { CallbackManager.Factory.create() }
}

val stateModule = module {
    single { SplashModelStore() }
    single { SplashIntentFactory(get()) }
}

fun provideRetrofit(): Retrofit {
    return Retrofit.Builder().baseUrl("${GRAPH_URL}/${GRAPH_VERSION}/")
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .client(okHttpClient)
        .build()
}

fun provideGraphApi(retrofit: Retrofit): GraphService = retrofit.create(GraphService::class.java)
