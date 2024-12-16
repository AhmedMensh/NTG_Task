package com.example.ntg_task.domain.di

import com.example.ntg_task.data.repositories.CharactersRepository
import com.example.ntg_task.domain.repositories.ICharactersRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DomainModule {

    @Singleton
    @Binds
    abstract fun bindCharactersRepository(
        plansReposRepositoryImpl: CharactersRepository
    ): ICharactersRepository


}