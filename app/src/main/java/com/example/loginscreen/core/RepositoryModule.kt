package com.example.loginscreen.core

import android.content.Context
import androidx.datastore.DataStore
import androidx.datastore.preferences.Preferences
import androidx.datastore.preferences.createDataStore
import com.example.loginscreen.repositories.AppRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RepositoryModule {

    @Singleton
    @Provides
    fun provideJobDetailsRepository(
        @ApplicationContext context: Context
    ): AppRepository {
        return AppRepository(provideDataStorePreference(context))
    }

    @Singleton
    @Provides
    fun provideDataStorePreference(
        @ApplicationContext context: Context
    ): DataStore<Preferences> {
        return context.createDataStore(name = "userFiles")
    }
}