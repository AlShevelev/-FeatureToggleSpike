package com.example.feature_toggle_impl.domain

import com.example.feature_toggle_api.LocalTogglesChecker
import com.example.feature_toggle_api.LocalTogglesLoader
import com.example.feature_toggle_api.LocalTogglesUpdater
import com.example.feature_toggle_api.model.LocalToggle
import com.example.feature_toggle_impl.data.LocalAppToggleLoader
import com.example.key_value_storage_api.KeyValueStorage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * This class is an entry point for local toggles. It contains loading, storing, and accessing logic for them.
 * @property kvStorage key-value storage
 * @property appToggleLoader a loader for the application's toggles
 */
class LocalTogglesProvider(
    private val kvStorage: KeyValueStorage,
    private val appToggleLoader: LocalAppToggleLoader
) : LocalTogglesChecker, LocalTogglesLoader, LocalTogglesUpdater {

    // Keys only
    private lateinit var turnedOnToggles: Set<String>

    /**
     * Checks, that a toggle is turned on
     * @param toggleKey the checking toggle's key
     */
    override fun isOn(toggleKey: String) = turnedOnToggles.contains(toggleKey)

    /**
     * Loads all toggles. Must be called once.
     */
    override suspend fun load() {
        val appToggles = withContext(Dispatchers.IO) {
            appToggleLoader.loadAll()
        }

        val storedToggles = kvStorage.reader.getStringSet(TOGGLES_KV_STORAGE_KEY) ?: setOf()

        val calculationResult = LocalTogglesCalculator.calculate(appToggles, storedToggles)

        turnedOnToggles = calculationResult.activeToggles

        if(calculationResult.togglesToRemove.isNotEmpty()) {
            val newStoredToggles = storedToggles.toMutableSet().apply { removeAll(calculationResult.togglesToRemove) }

            kvStorage.writer.update {
                it.setSetString(TOGGLES_KV_STORAGE_KEY, newStoredToggles)
            }
        }
    }

    /**
     * Turns on all given toggles (the rest are turned off automatically)
     * @param toggleKeys keys of toggles to be turned on
     */
    override fun setTurnedOn(toggleKeys: Set<String>) {
        turnedOnToggles = toggleKeys

        kvStorage.writer.update {
            it.setSetString(TOGGLES_KV_STORAGE_KEY, toggleKeys)
        }
    }

    /**
     * Returns list of all toggles
     */
    override suspend fun getAll(): List<LocalToggle> {
        val appToggles = withContext(Dispatchers.IO) {
            appToggleLoader.loadAll()
        }

        return withContext(Dispatchers.Default) {
            appToggles.map {
                LocalToggle(
                    key = it.key,
                    title = it.title,
                    description = it.description,
                    enabled = turnedOnToggles.contains(it.key)
                )
            }
        }
    }

    companion object {
        private const val TOGGLES_KV_STORAGE_KEY = "LOCAL_TOGGLES"
    }
}
