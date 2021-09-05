package com.android.doglistexample.di

import android.content.Context
import com.android.doglistexample.data.DogMapper
import com.android.doglistexample.data.network.DogApiClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DependenciesModule {

    private const val baseUrl:String = "https://dog.ceo/api/breed/"

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideDogApiClient(retrofit: Retrofit): DogApiClient {
        return retrofit.create(DogApiClient::class.java)
    }

    @Provides
    @Singleton
    fun provideMapper(): DogMapper {
        return DogMapper()
    }
}