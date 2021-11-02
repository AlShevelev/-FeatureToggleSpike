package com.example.key_value_storage_impl

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.key_value_storage_api.KeyValueStorage
import com.example.key_value_storage_impl.shared_preferences.SharedPrefKeyValueStorage
import org.junit.Assert.*
import org.junit.BeforeClass
import org.junit.Test
import org.junit.runner.RunWith
import java.util.*

/**
 * Contains tests for [SharedPrefKeyValueStorage] class
 */
@RunWith(AndroidJUnit4::class)
class SharedPrefKeyValueStorageTest {
    /**
     * Checks sequential "write: and "contains" operations
     */
    @Test
    fun writeAndContains() {
        // Arrange
        val key = UUID.randomUUID().toString()

        val value1 = "value1"
        val value2 = "value2"
        val set = setOf(value1, value2)

        // Act
        testSubject.writer.update {
            it.setSetString(key, set)
        }

        val result = testSubject.reader.contains(key)

        // Assert
        assertTrue(result)
    }

    /**
     * Checks sequential "write" and "remove" operations
     */
    @Test
    fun writeAndRemove() {
        // Arrange
        val key = UUID.randomUUID().toString()

        val value1 = "value1"
        val value2 = "value2"
        val set = setOf(value1, value2)

        // Act
        testSubject.writer.update {
            it.setSetString(key, set)
            it.remove(key)
        }

        val result = testSubject.reader.contains(key)

        // Assert
        assertFalse(result)
    }

    /**
     * Checks sequential "write" and "clear" operations
     */
    @Test
    fun writeAndClear() {
        // Arrange
        val key1 = UUID.randomUUID().toString()
        val key2 = UUID.randomUUID().toString()

        val value1 = "value1"
        val value2 = "value2"
        val set = setOf(value1, value2)

        // Act
        testSubject.writer.update {
            it.setSetString(key1, set)
            it.setSetString(key2, set)
        }

        testSubject.writer.update {
            it.clear()
        }

        val result1 = testSubject.reader.contains(key1)
        val result2 = testSubject.reader.contains(key2)

        // Assert
        assertFalse(result1)
        assertFalse(result2)
    }

    /**
     * Checks sequential "write" and "read" operation
     */
    @Test
    fun writeAndRead() {
        // Arrange
        val key = UUID.randomUUID().toString()

        val value1 = "value1"
        val value2 = "value2"
        val set = setOf(value1, value2)

        // Act
        testSubject.writer.update {
            it.setSetString(key, set)
        }

        val result = testSubject.reader.getStringSet(key)

        // Assert
        assertNotNull(result)
        with(result!!) {
            assertEquals(2, size)
            assertTrue(contains(value1))
            assertTrue(contains(value2))
        }
    }

    /**
     * Checks a case when we try to read a value that doesn't exist
     */
    @Test
    fun readNoExist() {
        // Arrange
        val key = UUID.randomUUID().toString()

        // Act
        val result = testSubject.reader.getStringSet(key)

        // Assert
        assertNull(result)
    }

    companion object {
        private lateinit var testSubject: KeyValueStorage

        @BeforeClass
        @JvmStatic
        fun setUp() {
            val appContext = InstrumentationRegistry.getInstrumentation().context
            testSubject = SharedPrefKeyValueStorage(appContext, UUID.randomUUID().toString())
        }
    }
}