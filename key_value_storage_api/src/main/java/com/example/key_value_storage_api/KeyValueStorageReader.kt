package com.example.key_value_storage_api

/**
 * An interface for reading data from a key-value storage
 */
interface KeyValueStorageReader {
    /**
     * Get a set of strings from the storage by its key
     * @param key value's key
     * @return resulted value or null if the value has not been found
     */
    fun getStringSet(key: String): Set<String>?

    /**
     * Checks for the existence of a value in the store
     * @param key value's key
     * @return checking result
     */
    fun contains(key: String): Boolean
}