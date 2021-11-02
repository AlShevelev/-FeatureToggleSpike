package com.example.feature_toggle_impl.data

import android.content.Context
import com.example.feature_toggle_impl.R
import com.example.feature_toggle_impl.data.model.LocalAppToggleRecord
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromStream

/**
 * An implementation for [LocalAppToggleLoader], loads toggles from a "raw" resource
 * @property context application context for access to resources
 */
class RawLocalAppToggleLoader(private val context: Context) : LocalAppToggleLoader {
    /**
     * Loads the records
     * @return all loaded records
     */
    @ExperimentalSerializationApi
    override suspend fun loadAll(): List<LocalAppToggleRecord> =
        context.resources.openRawResource(R.raw.feature_toggles).use { stream ->
            Json.decodeFromStream(stream)
//            InputStreamReader(it).use { reader ->
//                GsonBuilder()
//                    .create()
//                    .fromJson(reader, object : TypeToken<List<LocalAppToggleRecord>>() {}.type)
//            }
        }
}