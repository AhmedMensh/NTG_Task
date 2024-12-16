package com.example.ntg_task.data.di

import com.example.ntg_task.data.remote.MarvelServices
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MarvelServicesModule {
    @Singleton
    @Provides
    fun provideServicesService(retrofit: Retrofit) =
        retrofit.create(MarvelServices::class.java)
}