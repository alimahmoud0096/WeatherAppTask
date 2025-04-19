package com.alihafez.home.di

import com.alihafez.home.data.datasource.remote.RemoteDataSource
import com.alihafez.home.data.datasource.remote.RetrofitRemoteDataSource
import com.alihafez.home.data.repoImpl.HomeRepoImp
import com.alihafez.home.domain.repository.HomeRepository
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
    fun provideRepository(repo: HomeRepoImp): HomeRepository

    @Binds
    fun provideRemoteDataSource(dataSource: RetrofitRemoteDataSource): RemoteDataSource

}