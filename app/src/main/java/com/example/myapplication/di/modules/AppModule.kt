package com.example.myapplication.di.modules

import android.content.Context
import androidx.room.Room
import com.example.geneticcalc.data.database.RelativesDataBase
import com.example.geneticcalc.data.database.dao.RelativesProfilesDao
import com.example.geneticcalc.data.repositories.RelativesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRelativesDatabase(@ApplicationContext context: Context): RelativesDataBase{
        return Room.databaseBuilder(
            context,
            RelativesDataBase::class.java,
            "relatives"
        ).build()
    }


    @Provides
    fun provideRelativesProfilesDao(database: RelativesDataBase): RelativesProfilesDao{
        return database.relativesDao()
    }


    @Provides
    @Singleton
    fun provideContext(@ApplicationContext context: Context): Context {
        return context
    }


    @Provides
    @Singleton
    fun provideRelativesRepository(relativesProfilesDao: RelativesProfilesDao): RelativesRepository {
        return RelativesRepository(relativesProfilesDao)
    }
}
