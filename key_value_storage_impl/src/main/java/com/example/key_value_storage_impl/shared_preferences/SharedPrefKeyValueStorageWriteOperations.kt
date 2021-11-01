package com.example.key_value_storage_impl.shared_preferences

import android.content.SharedPreferences
import com.example.key_value_storage_api.KeyValueStorageWriteOperations

/**
 * An implementation of [KeyValueStorageWriteOperations] for Shared Preferences
 * @property editor an entity for updating [SharedPreferences]
 */
class SharedPrefKeyValueStorageWriteOperations(private val editor: SharedPreferences.Editor) : KeyValueStorageWriteOperations {
    /**
     * Puts a value with given key to a storage. If the value exists, it'll be overwritten
     * @param key value's key
     * @param value to put
     */
    override fun setSetString(key: String, value: Set<String>) {
        editor.putStringSet(key, value)
    }

    /**
     * Removes a value with given key from a storage.
     */
    override fun remove(key: String) {
        editor.remove(key)
    }

    /**
     * Remove all data from a storage
     */
    override fun clear() {
        editor.clear()
    }
}