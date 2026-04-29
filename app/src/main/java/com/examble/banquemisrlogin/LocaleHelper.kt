package com.examble.banquemisrlogin

import android.content.Context
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.edit
import androidx.core.os.LocaleListCompat

object LocaleHelper {
    private const val PREF_LANGUAGE = "selected_language"
    private const val PREF_NAME = "app_prefs"

    fun setLocale(languageCode: String) {
        AppCompatDelegate.setApplicationLocales(
            LocaleListCompat.forLanguageTags(languageCode)
        )
    }

    fun getCurrentLanguage(): String {
        val locales = AppCompatDelegate.getApplicationLocales()
        return if (locales.isEmpty) "en" else locales[0]?.language ?: "en"
    }

    fun saveLanguage(context: Context, languageCode: String) {
        context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
            .edit {
                putString(PREF_LANGUAGE, languageCode)
            }
    }

    fun getSavedLanguage(context: Context): String {
        return context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
            .getString(PREF_LANGUAGE, "en") ?: "en"
    }
}