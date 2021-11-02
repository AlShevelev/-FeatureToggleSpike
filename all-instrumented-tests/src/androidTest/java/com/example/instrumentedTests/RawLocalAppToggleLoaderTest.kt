package com.example.instrumentedTests

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.feature_toggle_impl.data.LocalAppToggleLoader
import com.example.feature_toggle_impl.data.RawLocalAppToggleLoader
import com.example.feature_toggle_impl.data.model.LocalAppToggleRecord
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.BeforeClass
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Contains tests for [RawLocalAppToggleLoader] class
 */
@RunWith(AndroidJUnit4::class)
class RawLocalAppToggleLoaderTest {
    /**
     * Checks that all items have been loaded without their content validation
     */
    @Test
    fun allItemsLoaded() {
        // Act
        val result = runBlocking {
            testSubject.loadAll()
        }

        // Assert
        assertEquals(2, result.size)
    }

    /**
     * Checks content of the items except [LocalAppToggleRecord.enabled] property
     */
    @Test
    fun checkContentWithoutEnabled() {
        // Act
        val result = runBlocking {
            testSubject.loadAll()
        }

        // Assert
        with(result[0]){
            assertEquals("feature1", key)
            assertEquals("feature1 title", title)
            assertEquals("feature1 description", description)
            assertEquals("feature1 version", version)
        }

        with(result[1]){
            assertEquals("feature2", key)
            assertEquals("feature2 title", title)
            assertEquals("feature2 description", description)
            assertEquals("feature2 version", version)
        }
    }

    /**
     * Checks a value of [LocalAppToggleRecord.enabled] property only
     */
    @Test
    fun checkEnabled() {
        // Act
        val result = runBlocking {
            testSubject.loadAll()
        }

        // Assert
        assertNotNull(result[0].enabled)
        assertTrue(result[0].enabled!!)

        assertNull(result[1].enabled)
    }

    companion object {
        private lateinit var testSubject: LocalAppToggleLoader

        @BeforeClass
        @JvmStatic
        fun setUp() {
            val appContext = InstrumentationRegistry.getInstrumentation().context
            testSubject = RawLocalAppToggleLoader(appContext)
        }
    }
}