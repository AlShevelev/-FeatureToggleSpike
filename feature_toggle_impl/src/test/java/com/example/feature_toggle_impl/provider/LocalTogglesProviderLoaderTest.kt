package com.example.feature_toggle_impl.provider

import com.example.feature_toggle_api.LocalTogglesLoader
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.verify
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.Assert.*
import com.example.key_value_storage_api.KeyValueStorageWriter

/**
 * Contains tests for [LocalTogglesLoader] interface
 */
class LocalTogglesProviderLoaderTest : LocalTogglesProviderTestBase() {
    /**
     * Checks that all main methods are called
     */
    @Test
    fun methodsCallMain() {
        // Arrange
        every { kvStorage.reader } returns kvStorageReader
        every { kvStorage.writer } returns kvStorageWriter
        every { kvStorageReader.getStringSet(any()) } returns setOf()
        coEvery { localAppToggleLoader.loadAll() } returns listOf()

        // Act
        runBlocking { testSubject.load() }

        // Assert
        coVerify(exactly = 1) { localAppToggleLoader.loadAll() }
        verify(exactly = 1) { kvStorageReader.getStringSet(any()) }
        verify(exactly = 0) { kvStorageWriter.update(any()) }
    }

    /**
     * Checks that [KeyValueStorageWriter.update] is called
     */
    @Test
    fun updateTogglesCall() {
        // Arrange
        val key = "key"

        every { kvStorage.reader } returns kvStorageReader
        every { kvStorage.writer } returns kvStorageWriter
        every { kvStorageReader.getStringSet(any()) } returns setOf(key)
        every { kvStorageWriter.update(any()) } returns Unit
        coEvery { localAppToggleLoader.loadAll() } returns listOf()

        // Act
        runBlocking { testSubject.load() }

        // Assert
        verify(exactly = 1) { kvStorageWriter.update(any()) }
    }

    /**
     * Checks that initialization is completed
     */
    @Test
    fun initializationCompleted() {
        // Arrange
        val key = "key"

        every { kvStorage.reader } returns kvStorageReader
        every { kvStorage.writer } returns kvStorageWriter
        every { kvStorageReader.getStringSet(any()) } returns setOf()
        every { kvStorageWriter.update(any()) } returns Unit
        coEvery { localAppToggleLoader.loadAll() } returns listOf(createAppToggle(key, true))

        // Act
        runBlocking { testSubject.load() }
        val result = testSubject.isOn(key)

        // Assert
        assertTrue(result)
    }
}