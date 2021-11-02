package com.example.feature_toggle_impl.calculator

import com.example.feature_toggle_impl.data.model.LocalAppToggleRecord
import com.example.feature_toggle_impl.domain.LocalTogglesCalculator
import org.junit.Test

import org.junit.Assert.*

/**
 * A test for [LocalTogglesCalculator]
 */
class LocalTogglesCalculatorTest {
    /**
     * A case when all source lists are empty
     */
    @Test
    fun allEmpty() {
        // Arrange
        val appToggles = listOf<LocalAppToggleRecord>()
        val deviceToggles = setOf<String>()

        // Act
        val result = LocalTogglesCalculator.calculate(appToggles, deviceToggles)

        // Assert
        assertTrue(result.activeToggles.isEmpty())
        assertTrue(result.togglesToRemove.isEmpty())
    }

    /**
     * A case when an app toggle value is undefined, and a device toggle exists.
     */
    @Test
    fun appToggleNullAndDeviceToggleExist() {
        // Arrange
        val key = "key1"
        val appToggles = listOf(createToggleRecord(key, null))
        val deviceToggles = setOf(key)

        // Act
        val result = LocalTogglesCalculator.calculate(appToggles, deviceToggles)

        // Assert
        assertEquals(1, result.activeToggles.size)
        assertTrue(result.activeToggles.contains(key))
    }

    /**
     * A case when an app toggle value is undefined, and a device toggle doesn't exist.
     */
    @Test
    fun appToggleNullAndDeviceToggleNotExist() {
        // Arrange
        val key = "key1"
        val appToggles = listOf(createToggleRecord(key, null))
        val deviceToggles = setOf<String>()

        // Act
        val result = LocalTogglesCalculator.calculate(appToggles, deviceToggles)

        // Assert
        assertTrue(result.activeToggles.isEmpty())
    }

    /**
     * A case when an app toggle value is set explicitly to "true", and a device toggle doesn't exist.
     */
    @Test
    fun appToggleTrueAndDeviceToggleNotExist() {
        // Arrange
        val key = "key1"
        val appToggles = listOf(createToggleRecord(key, true))
        val deviceToggles = setOf<String>()

        // Act
        val result = LocalTogglesCalculator.calculate(appToggles, deviceToggles)

        // Assert
        assertEquals(1, result.activeToggles.size)
        assertTrue(result.activeToggles.contains(key))
    }

    /**
     * A case when an app toggle value is explicitly to "true", and a device toggle exists.
     */
    @Test
    fun appToggleTrueAndDeviceToggleExists() {
        // Arrange
        val key = "key1"
        val appToggles = listOf(createToggleRecord(key, true))
        val deviceToggles = setOf(key)

        // Act
        val result = LocalTogglesCalculator.calculate(appToggles, deviceToggles)

        // Assert
        assertEquals(1, result.activeToggles.size)
        assertTrue(result.activeToggles.contains(key))
    }

    /**
     * A case when an app toggle value is set explicitly to "false", and a device toggle doesn't exist.
     */
    @Test
    fun appToggleFalseAndDeviceToggleNotExist() {
        // Arrange
        val key = "key1"
        val appToggles = listOf(createToggleRecord(key, false))
        val deviceToggles = setOf<String>()

        // Act
        val result = LocalTogglesCalculator.calculate(appToggles, deviceToggles)

        // Assert
        assertTrue(result.activeToggles.isEmpty())
    }

    /**
     * A case when an app toggle value is set explicitly to "false", and a device toggle exists.
     */
    @Test
    fun appToggleFalseAndDeviceToggleExists() {
        // Arrange
        val key = "key1"
        val appToggles = listOf(createToggleRecord(key, false))
        val deviceToggles = setOf(key)

        // Act
        val result = LocalTogglesCalculator.calculate(appToggles, deviceToggles)

        // Assert
        assertTrue(result.activeToggles.isEmpty())
    }

    /**
     * Checking for removing of old keys
     */
    @Test
    fun togglesRemoving() {
        // Arrange
        val key1 = "key1"
        val key2 = "key2"
        val key3 = "key3"
        val appToggles = listOf(createToggleRecord(key1, null), createToggleRecord(key2, true))
        val deviceToggles = setOf(key3)

        // Act
        val result = LocalTogglesCalculator.calculate(appToggles, deviceToggles)

        // Assert
        assertEquals(1, result.togglesToRemove.size)
        assertTrue(result.togglesToRemove.contains(key3))
    }

    private fun createToggleRecord(key: String, enabled: Boolean?) =
        LocalAppToggleRecord(
            key = key,
            title = "",
            description = "",
            enabled = enabled,
            version = ""
        )
}