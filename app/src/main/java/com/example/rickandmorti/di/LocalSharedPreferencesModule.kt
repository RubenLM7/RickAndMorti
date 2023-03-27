package com.example.rickandmorti.di

import android.content.Context
import android.content.SharedPreferences
import com.example.rickandmorti.R
import org.koin.dsl.module
import org.koin.android.ext.koin.androidContext

val localSharedPreferencesModule = module {
    factory { provideLocalSharedPreferences(androidContext()) }
}

private fun provideLocalSharedPreferences(context: Context): SharedPreferences = context.getSharedPreferences(context.getString(R.string.Main_prefs), Context.MODE_PRIVATE)
