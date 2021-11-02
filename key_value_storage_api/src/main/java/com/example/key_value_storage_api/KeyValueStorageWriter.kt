package com.example.key_value_storage_api

/**
 * An interface for updating data in a key-value storage
 */
interface KeyValueStorageWriter {
    /**
     * Runs updating process with reading data in a storage opportunity
     * @param updateAction user's action for data updating
     */
    fun updateWithRead(updateAction: (KeyValueStorageReader, KeyValueStorageWriteOperations) -> Unit)

    /**
     * Runs updating process
     * @param updateAction user's action for data updating
     */
    fun update(updateAction: (KeyValueStorageWriteOperations) -> Unit)
}