package com.whitessmoke.melisearcher.di

import com.whitessmoke.melisearcher.data.detail.network.DetailApi
import com.whitessmoke.melisearcher.data.result.network.ResultApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ModuleProvider {

    /**
     * Provide module para obtener ResultApi para las peticiones retrofit del caso de uso
     * Results
     */
    @Provides
    @Singleton
    fun provideResultApi(retrofit: Retrofit): ResultApi {
        return retrofit.create(ResultApi::class.java)
    }

    /**
     * Provide module para obtener DetailApi para las peticiones retrofit del caso de uso
     * Detail
     */
    @Provides
    @Singleton
    fun provideDeatilApi(retrofit: Retrofit): DetailApi {
        return retrofit.create(DetailApi::class.java)
    }

    /**
     * Provide para injectar Retrofit como dependencia
     */
    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder().baseUrl("https://api.mercadolibre.com/")
            .addConverterFactory(GsonConverterFactory.create()).build()
    }


}