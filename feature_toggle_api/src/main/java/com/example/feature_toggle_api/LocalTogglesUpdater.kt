package com.example.feature_toggle_api

import com.example.feature_toggle_api.model.LocalToggle

/**
 * An interface for local toggles updating
 */
interface LocalTogglesUpdater {
    /**
     * Turns on all given toggles (the rest are turned off automatically)
     * @param toggleKeys keys of toggles to be turned on
     */
    fun setTurnedOn(toggleKeys: Set<String>)

    /**
     * Returns list of all toggles
     */
    suspend fun getAll(): List<LocalToggle>
}