package com.example.key_value_storage_impl.shared_preferences

import android.content.SharedPreferences
import com.example.key_value_storage_api.KeyValueStorageReader
import com.example.key_value_storage_api.KeyValueStorageWriteOperations
import com.example.key_value_storage_api.KeyValueStorageWriter

/**
 * An implementation of [KeyValueStorageWriter] for Shared Preferences
 * @property sharedPreferences an entity for accessing to [SharedPreferences]
 * @property reader an interface for reading data from a key-value storage
 */
class SharedPrefKeyValueStorageWriter(
    private val reader: KeyValueStorageReader,
    private val sharedPreferences: SharedPreferences
) : KeyValueStorageWriter {
    /**
     * Runs updating process
     * @param updateAction user's action for data updating
     */
    override fun update(updateAction: (KeyValueStorageReader, KeyValueStorageWriteOperations) -> Unit) {
        val editor = sharedPreferences.edit()
        updateAction(reader, SharedPrefKeyValueStorageWriteOperations(editor))
        editor.apply()
    }
}