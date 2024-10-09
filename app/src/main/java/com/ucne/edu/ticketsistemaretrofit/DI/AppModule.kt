package com.ucne.edu.ticketsistemaretrofit.DI


import dagger.Provides
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.ucne.edu.ticketsistemaretrofit.data.remote.SistemaAPI
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton
import dagger.Module
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    const val BASE_URL = "https://sistematicketapi-e6g7edckhwcuhedg.eastus2-01.azurewebsites.net/"

    @Provides
    @Singleton
    fun providesMoshi(): Moshi =
        Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

    @Provides
    @Singleton
    fun provideSistemaApi(): SistemaAPI {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL) // Replace with your API base URL
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(SistemaAPI::class.java)
    }
}