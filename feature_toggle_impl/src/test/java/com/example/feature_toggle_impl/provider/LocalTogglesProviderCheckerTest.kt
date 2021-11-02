package com.example.feature_toggle_impl.provider

import com.example.feature_toggle_api.LocalTogglesChecker
import com.example.feature_toggle_impl.domain.LocalTogglesProvider
import io.mockk.coEvery
import io.mockk.every
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.Assert.*

/**
 * Contains tests for [LocalTogglesChecker] interface
 */
class LocalTogglesProviderCheckerTest : LocalTogglesProviderTestBase() {
    /**
     * Checks a case that we can't call [LocalTogglesProvider.isOn] method without initialization
     * ([LocalTogglesProvider.load] method must be called first).
     */
    @Test(expected = UninitializedPropertyAccessException::class)
    fun isOnWithoutInitialization() {
        // Act
        testSubject.isOn("")
    }

    /**
     * Checks a case when a key doesn't exist.
     */
    @Test
    fun hasNoKey() {
        // Arrange
        every { kvStorage.reader } returns kvStorageReader
        every { kvStorageReader.getStringSet(any()) } returns setOf()
        coEvery { localAppToggleLoader.loadAll() } returns listOf()

        // Act
        runBlocking { testSubject.load() }
        val result = testSubject.isOn("")

        // Assert
        assertFalse(result)
    }

    /**
     * Checks a case when a key doesn't exist.
     */
    @Test
    fun hasKey() {
        // Arrange
        val key = "toggleKey"

        every { kvStorage.reader } returns kvStorageReader
        every { kvStorageReader.getStringSet(any()) } returns setOf()
        coEvery { localAppToggleLoader.loadAll() } returns listOf(createAppToggle(key, true))

        // Act
        runBlocking { testSubject.load() }
        val result = testSubject.isOn(key)

        // Assert
        assertTrue(result)
    }
}