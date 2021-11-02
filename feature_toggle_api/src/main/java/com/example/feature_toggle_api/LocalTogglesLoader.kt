package com.example.feature_toggle_api

/**
 * An interface for local toggles loading
 */
interface LocalTogglesLoader {
    /**
     * Loads all toggles. Must be called once.
     */
    suspend fun load()
}