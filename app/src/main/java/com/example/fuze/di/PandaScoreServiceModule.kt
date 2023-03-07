package com.example.fuze.di

import com.example.fuze.data.PandaScoreService
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


class PandaScoreServiceModule {

    private val client by lazy {
        OkHttpClient.Builder()
            .connectTimeout(300L, TimeUnit.SECONDS)
            .readTimeout(300L, TimeUnit.SECONDS)
            .addInterceptor(interceptor)
            .writeTimeout(300L, TimeUnit.SECONDS)
            .build()
    }

    private val interceptor by lazy {
        Interceptor { chain ->
            chain.proceed(
                chain.request().newBuilder().apply {
                    addHeader("accept", "application/json")
                    addHeader(
                        "authorization",
                        "Bearer ilQ6G8cXCGlJX-D-rLuv_qgYaV904fL5Lzo735591LbNwU-aaSE"
                    )
                }.build()
            )
        }
    }

    fun pandaScoreModule(): Module {
        return module {
            single<PandaScoreService> {
                Retrofit.Builder()
                    .baseUrl("https://api.pandascore.co/csgo/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                    .client(client)
                    .build()
                    .create(PandaScoreService::class.java)
            }
        }
    }
}