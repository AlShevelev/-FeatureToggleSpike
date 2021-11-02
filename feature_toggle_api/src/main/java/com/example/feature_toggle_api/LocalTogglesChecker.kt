package com.example.feature_toggle_api

/**
 * An interface for local toggles existence checking
 */
interface LocalTogglesChecker {
    /**
     * Checks, that a toggle is turned on
     * @param toggleKey the checking toggle's key
     */
    fun isOn(toggleKey: String): Boolean
}