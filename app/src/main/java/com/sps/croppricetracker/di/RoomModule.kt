package com.sps.croppricetracker.di

import android.content.Context
import com.sps.croppricetracker.data.room.LocalDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class RoomModule {
    @Singleton
    @Provides
    fun provideDataBase(@ApplicationContext context: Context) =
        LocalDatabase.getInstance(context = context)

    @Provides
    fun provideUserDao(localDatabase: LocalDatabase) = localDatabase.userDao()
}