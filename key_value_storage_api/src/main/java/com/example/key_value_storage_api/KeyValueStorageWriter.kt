package com.example.key_value_storage_api

/**
 * An interface for updating data in a key-value storage
 */
interface KeyValueStorageWriter {
    /**
     * Runs updating process
     * @param updateAction user's action for data updating
     */
    fun update(updateAction: (KeyValueStorageReader, KeyValueStorageWriteOperations) -> Unit)
}