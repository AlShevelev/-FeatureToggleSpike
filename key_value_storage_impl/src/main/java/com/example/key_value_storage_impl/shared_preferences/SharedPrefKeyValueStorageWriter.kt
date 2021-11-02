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
     * Runs updating process with reading data in a storage opportunity
     * @param updateAction user's action for data updating
     */
    override fun updateWithRead(updateAction: (KeyValueStorageReader, KeyValueStorageWriteOperations) -> Unit) =
        updateInternal {
            updateAction(reader, SharedPrefKeyValueStorageWriteOperations(it))
        }

    /**
     * Runs updating process
     * @param updateAction user's action for data updating
     */
    override fun update(updateAction: (KeyValueStorageWriteOperations) -> Unit) =
        updateInternal {
            updateAction(SharedPrefKeyValueStorageWriteOperations(it))
        }

    private fun updateInternal(updateAction: (SharedPreferences.Editor) -> Unit) {
        val editor = sharedPreferences.edit()
        updateAction(editor)
        editor.apply()
    }
}