package com.example.key_value_storage_impl.shared_preferences

import android.content.SharedPreferences
import com.example.key_value_storage_api.KeyValueStorageReader

/**
 * An implementation of [KeyValueStorageReader] for Shared Preferences
 * @property sharedPreferences an entity for accessing to [SharedPreferences]
 */
class SharedPrefKeyValueStorageReader(private val sharedPreferences: SharedPreferences) : KeyValueStorageReader {
    /**
     * Get a set of strings from the storage by its key
     * @param key value's key
     * @return resulted value or null if the value has not been found
     */
    override fun getStringSet(key: String): Set<String>? = sharedPreferences.getStringSet(key, null)

    /**
     * Checks for the existence of a value in the store
     * @param key value's key
     * @return checking result
     */
    override fun contains(key: String): Boolean = sharedPreferences.contains(key)
}