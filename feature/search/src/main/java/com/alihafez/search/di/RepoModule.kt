package com.alihafez.search.di

import com.alihafez.search.data.datasource.local.LocalDataSource
import com.alihafez.search.data.datasource.local.RoomLocalDataSource
import com.alihafez.search.data.datasource.remote.RemoteDataSource
import com.alihafez.search.data.datasource.remote.RetrofitRemoteDataSource
import com.alihafez.search.data.repoImpl.SearchRepoImpl
import com.alihafez.search.domain.repo.SearchRepo
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * created by ِAli Mahmoud Abdelhafez on 17/4/25
 **/
@Module
@InstallIn(SingletonComponent::class)
interface RepoModule {

    @Binds
    fun provideRepository(repo: SearchRepoImpl): SearchRepo

    @Binds
    fun provideRemoteDataSource(dataSource: RetrofitRemoteDataSource): RemoteDataSource

    @Binds
    fun provideLocalDataSource(dataSource: RoomLocalDataSource): LocalDataSource

}