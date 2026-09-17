package com.sheepblue.regrade3.data.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

// Preparar a injeção de dependencia necessario para o DataStore<Preferences> do ThemePreferencesDataStore
private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

@Module
@InstallIn(SingletonComponent::class) // o modulo fica vivo enquanto o app viver
object DataStoreModule {
    @Provides
    @Singleton // Garantir que o app inteiro use a mesma instancia do DataStore (evita corrupção de dados)
    fun provideDataStore(@ApplicationContext context: Context): DataStore<Preferences> {
        return context.dataStore
    }
}
