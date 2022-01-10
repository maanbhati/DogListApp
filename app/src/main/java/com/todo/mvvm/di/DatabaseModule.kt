package com.todo.mvvm.di

import android.app.Application
import androidx.room.Room
import com.todo.mvvm.data.local.DogListDao
import com.todo.mvvm.data.local.DogListDatabase
import com.todo.mvvm.utils.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        application: Application,
        callback: DogListDatabase.Callback
    ): DogListDatabase {
        return Room.databaseBuilder(application, DogListDatabase::class.java, DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .addCallback(callback)
            .build()
    }

    @Provides
    fun provideDogListDao(dogListDatabase: DogListDatabase): DogListDao {
        return dogListDatabase.getDogListDao()
    }
}