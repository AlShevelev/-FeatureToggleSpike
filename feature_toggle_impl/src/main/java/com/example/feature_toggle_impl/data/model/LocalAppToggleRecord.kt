package com.example.feature_toggle_impl.data.model

import kotlinx.serialization.Serializable

/**
 * One local toggle record stored inside the app
 * @property key a key of the toggle
 * @property title a title of the toggle
 * @property description a description of the toggle
 * @property enabled is this toggle turned on? - may be undefined
 * @property version an app version in which the toggle must be removed (for information only)
 */
@Serializable
class LocalAppToggleRecord (
    val key: String,
    val title: String,
    val description: String,
    val enabled: Boolean? = null,
    val version: String
)