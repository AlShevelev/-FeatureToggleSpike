package com.example.feature_toggle_impl.data

import android.content.Context
import com.example.feature_toggle_impl.R
import com.example.feature_toggle_impl.data.model.LocalAppToggleRecord
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import java.io.InputStreamReader

/**
 * An implementation for [LocalAppToggleLoader], loads toggles from a "raw" resource
 * @property context application context for access to resources
 */
class RawLocalAppToggleLoader(private val context: Context) : LocalAppToggleLoader {
    /**
     * Loads the records
     * @return all loaded records
     */
    override suspend fun loadAll(): List<LocalAppToggleRecord> =
        context.resources.openRawResource(R.raw.feature_toggles).use {
            InputStreamReader(it).use { reader ->
                GsonBuilder()
                    .create()
                    .fromJson(reader, object : TypeToken<List<LocalAppToggleRecord>>() {}.type)
            }
        }
}