package com.openplay.tech.myapplication.di

import android.app.Application
import androidx.room.Room
import com.openplay.tech.myapplication.commonutils.Constants.Companion.DATABASE_NAME
import com.openplay.tech.myapplication.data.local.MoviesDAO
import com.openplay.tech.myapplication.data.local.MoviesDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataBaseModule {

    @Provides
    @Singleton
    fun provideDataBase(application: Application): MoviesDataBase {
        return Room.databaseBuilder(
            context = application.applicationContext,
            klass = MoviesDataBase::class.java,
            name = DATABASE_NAME
        ).fallbackToDestructiveMigration(false)
            .build()
    }

    @Provides
    @Singleton
    fun provideFoodStoreDao(dataBase: MoviesDataBase): MoviesDAO {
        return dataBase.getMoviesDao()
    }
}