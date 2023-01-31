package com.example.tepresto.di

import android.content.Context
import androidx.room.Room
import com.example.tepresto.data.local.TePrestoDb
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun providesDatabase( @ApplicationContext context: Context ): TePrestoDb{
        return Room.databaseBuilder(
            context,
            TePrestoDb::class.java,
            "RoomExample.db")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun providesOcupacionDao(db: TePrestoDb) = db.ocupacionDao


}