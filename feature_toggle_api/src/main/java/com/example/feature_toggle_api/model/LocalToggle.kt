package com.example.feature_toggle_api.model

/**
 * Contains a local toggle data
 * @property key a key of the toggle
 * @property title a title of the toggle
 * @property description a description of the toggle
 * @property enabled is this toggle turned on? - may be undefined
 */
class LocalToggle (
    val key: String,
    val title: String,
    val description: String,
    val enabled: Boolean
)