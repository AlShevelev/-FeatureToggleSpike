package com.example.feature_toggle_impl.provider

import com.example.feature_toggle_impl.data.LocalAppToggleLoader
import com.example.feature_toggle_impl.data.model.LocalAppToggleRecord
import com.example.feature_toggle_impl.domain.LocalTogglesProvider
import com.example.key_value_storage_api.KeyValueStorage
import com.example.key_value_storage_api.KeyValueStorageReader
import com.example.key_value_storage_api.KeyValueStorageWriter
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.MockK
import org.junit.Before

/**
 * A base class for [LocalTogglesProvider]'s tests
 */
abstract class LocalTogglesProviderTestBase {
    @MockK
    protected lateinit var kvStorage: KeyValueStorage

    @MockK
    protected lateinit var kvStorageReader: KeyValueStorageReader

    @MockK
    protected lateinit var kvStorageWriter: KeyValueStorageWriter

    @MockK
    protected lateinit var localAppToggleLoader: LocalAppToggleLoader

    protected lateinit var testSubject: LocalTogglesProvider

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        testSubject = LocalTogglesProvider(kvStorage, localAppToggleLoader)
    }

    /**
     * Turns Mockito to any object and return null
     * @param T the object's type
     * @return fake result - null
     */
//    @Suppress("UNCHECKED_CAST")
//    protected fun <T> anyObject(): T {
//        Mockito.any<T>()
//        return null as T
//    }

    /**
     * Gets rid of null checking during an argument interception
     */
//    protected fun <T> capture(argumentCaptor: ArgumentCaptor<T>): T = argumentCaptor.capture()

    protected fun createAppToggle(key: String, enabled: Boolean?) =
        LocalAppToggleRecord(
            key = key,
            title = "",
            description = "",
            enabled = enabled,
            version = ""
        )
}