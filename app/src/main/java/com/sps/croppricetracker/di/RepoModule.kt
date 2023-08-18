package com.sps.croppricetracker.di

import com.sps.croppricetracker.data.repo.UserRepo
import com.sps.croppricetracker.data.retrofit.RetrofitInterface
import com.sps.croppricetracker.data.room.dao.UserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class RepoModule {
    @Singleton
    @Provides
    fun provideUserRepo(retrofitInterface: RetrofitInterface, userDao: UserDao) =
        UserRepo(retrofitInterface = retrofitInterface, userDao)
}