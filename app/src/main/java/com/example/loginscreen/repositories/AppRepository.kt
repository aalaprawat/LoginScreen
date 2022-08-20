package com.example.loginscreen.repositories

import androidx.datastore.DataStore
import androidx.datastore.preferences.Preferences
import androidx.datastore.preferences.edit
import androidx.datastore.preferences.preferencesKey
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class AppRepository @Inject constructor(private val dataStore: DataStore<Preferences>) {

    suspend fun saveValuesInDataStore(key: String, value: String) {
        val dataStoreKey = preferencesKey<String>(key)
        dataStore.edit {
            it[dataStoreKey] = value
        }
    }

    suspend fun fetchValuesFromDataStore(key: String): String? {
        val dataStoreKey = preferencesKey<String>(key)
        val preferences = dataStore.data.first()
        return preferences[dataStoreKey]
    }
}