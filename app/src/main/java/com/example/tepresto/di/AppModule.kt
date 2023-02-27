package com.example.tepresto.di

import android.content.Context
import androidx.room.Room
import com.example.tepresto.data.local.TePrestoDb
import com.example.tepresto.data.remote.TodoApi
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun providesDatabase(@ApplicationContext context: Context): TePrestoDb {
        return Room.databaseBuilder(
            context,
            TePrestoDb::class.java,
            "TePrestoDb.db"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun providesOcupacionDao(db: TePrestoDb) = db.ocupacionDao

    @Singleton
    @Provides
    fun providesMoshi(): Moshi {
        return Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
    }

    @Singleton
    @Provides
    fun providesTodoApi(moshi: Moshi): TodoApi {
        return Retrofit.Builder()
            .baseUrl("https://todoapi9.azurewebsites.net")
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(TodoApi::class.java)
    }

}
