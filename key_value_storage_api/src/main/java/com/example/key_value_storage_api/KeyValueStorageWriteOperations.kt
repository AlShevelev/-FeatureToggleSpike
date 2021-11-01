package com.example.key_value_storage_api

/**
 * A set of operations for updating data in a key-value storage
 */
interface KeyValueStorageWriteOperations {
    /**
     * Puts a value with given key to a storage. If the value exists, it'll be overwritten
     * @param key value's key
     * @param value to put
     */
    fun setSetString(key: String, value: Set<String>)

    /**
     * Removes a value with given key from a storage.
     */
    fun remove(key: String)

    /**
     * Remove all data from a storage
     */
    fun clear()
}