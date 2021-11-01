package com.example.feature_toggle_impl.data

import com.example.feature_toggle_impl.data_model.LocalAppToggleRecord

/**
 * The loader for local toggle records stored inside the app
 */
interface LocalAppToggleLoader {
    /**
     * Loads the records
     * @return all loaded records
     */
    suspend fun loadAll(): List<LocalAppToggleRecord>
}