package com.example.feature_toggle_impl.provider

import com.example.feature_toggle_api.LocalTogglesUpdater
import com.example.feature_toggle_impl.domain.LocalTogglesProvider
import io.mockk.coEvery
import io.mockk.every
import io.mockk.verify
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Test
import java.util.*

/**
 * Contains tests for [LocalTogglesUpdater] interface
 */
class LocalTogglesProviderUpdaterTest : LocalTogglesProviderTestBase() {
    /**
     * Checks a case that we can't call [LocalTogglesUpdater.getAll] method without initialization
     * ([LocalTogglesProvider.load] method must be called first).
     */
    @Test(expected = UninitializedPropertyAccessException::class)
    fun isOnWithoutInitialization() {
        // Arrange
        val key = "key"
        coEvery { localAppToggleLoader.loadAll() } returns listOf(createAppToggle(key, true))

        // Act
        runBlocking { testSubject.getAll() }
    }

    /**
     * Checks behaviour of [LocalTogglesUpdater.setTurnedOn] method
     */
    @Test
    fun setTurnedOn() {
        // Arrange
        val key = UUID.randomUUID().toString()

        every { kvStorage.writer } returns kvStorageWriter
        every { kvStorageWriter.update(any()) } returns Unit

        // Act
        testSubject.setTurnedOn(setOf(key))
        val result = testSubject.isOn(key)

        // Assert
        verify(exactly = 1) { kvStorageWriter.update(any()) }
        assertTrue(result)
    }
}