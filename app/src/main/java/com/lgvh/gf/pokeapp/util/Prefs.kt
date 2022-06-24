package com.lgvh.gf.pokeapp.util

import android.content.Context
import android.content.SharedPreferences

class Prefs(context: Context) {
    private val catalog_saved = "catalog_saved"
    private val prefs: SharedPreferences = context.getSharedPreferences(catalog_saved, 0)

    var catalogSaved: Boolean
        get() = prefs.getBoolean(catalog_saved, false)
        set(value) = prefs.edit().putBoolean(catalog_saved, value).apply()
}