package com.example.feature_toggle_impl.data

import com.example.feature_toggle_impl.data.model.LocalAppToggleRecord
import kotlinx.serialization.ExperimentalSerializationApi

/**
 * The loader for local toggle records stored inside the app
 */
interface LocalAppToggleLoader {
    /**
     * Loads the records
     * @return all loaded records
     */
    @ExperimentalSerializationApi
    suspend fun loadAll(): List<LocalAppToggleRecord>
}