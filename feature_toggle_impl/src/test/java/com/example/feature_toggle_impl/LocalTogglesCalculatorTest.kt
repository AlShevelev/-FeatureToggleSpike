package com.example.feature_toggle_impl

import com.example.feature_toggle_impl.domain_model.LocalAppToggleRecordBrief
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
        val appToggles = listOf<LocalAppToggleRecordBrief>()
        val deviceToggles = listOf<String>()

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
        val appToggles = listOf(LocalAppToggleRecordBrief(key, null))
        val deviceToggles = listOf(key)

        // Act
        val result = LocalTogglesCalculator.calculate(appToggles, deviceToggles)

        // Assert
        assertEquals(1, result.activeToggles.size)
        assertEquals(key, result.activeToggles[0])
    }

    /**
     * A case when an app toggle value is undefined, and a device toggle doesn't exist.
     */
    @Test
    fun appToggleNullAndDeviceToggleNotExist() {
        // Arrange
        val key = "key1"
        val appToggles = listOf(LocalAppToggleRecordBrief(key, null))
        val deviceToggles = listOf<String>()

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
        val appToggles = listOf(LocalAppToggleRecordBrief(key, true))
        val deviceToggles = listOf<String>()

        // Act
        val result = LocalTogglesCalculator.calculate(appToggles, deviceToggles)

        // Assert
        assertEquals(1, result.activeToggles.size)
        assertEquals(key, result.activeToggles[0])
    }

    /**
     * A case when an app toggle value is explicitly to "true", and a device toggle exists.
     */
    @Test
    fun appToggleTrueAndDeviceToggleExists() {
        // Arrange
        val key = "key1"
        val appToggles = listOf(LocalAppToggleRecordBrief(key, true))
        val deviceToggles = listOf(key)

        // Act
        val result = LocalTogglesCalculator.calculate(appToggles, deviceToggles)

        // Assert
        assertEquals(1, result.activeToggles.size)
        assertEquals(key, result.activeToggles[0])
    }

    /**
     * A case when an app toggle value is set explicitly to "false", and a device toggle doesn't exist.
     */
    @Test
    fun appToggleFalseAndDeviceToggleNotExist() {
        // Arrange
        val key = "key1"
        val appToggles = listOf(LocalAppToggleRecordBrief(key, false))
        val deviceToggles = listOf<String>()

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
        val appToggles = listOf(LocalAppToggleRecordBrief(key, false))
        val deviceToggles = listOf(key)

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
        val appToggles = listOf(LocalAppToggleRecordBrief(key1, null), LocalAppToggleRecordBrief(key2, true))
        val deviceToggles = listOf(key3)

        // Act
        val result = LocalTogglesCalculator.calculate(appToggles, deviceToggles)

        // Assert
        assertEquals(1, result.togglesToRemove.size)
        assertEquals(key3, result.togglesToRemove[0])
    }
}