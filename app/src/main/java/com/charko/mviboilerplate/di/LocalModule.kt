package com.charko.mviboilerplate.di

import android.content.Context
import androidx.room.Room
import com.charko.mviboilerplate.feature_x.datasource.local.dao.AppDao
import com.charko.mviboilerplate.feature_x.datasource.local.db.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext applicationContext: Context): AppDatabase {
        return Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "my-app-db"
        )
            .fallbackToDestructiveMigration(true)
            .build()
    }

    @Provides
    fun provideAppDao(database: AppDatabase): AppDao {
        return database.appDao()
    }
}