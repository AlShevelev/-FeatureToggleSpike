package com.example.feature_toggle_impl.core

/**
 * One local toggle record stored inside the app
 * @property key a key of the toggle
 * @property isOn is this toggle turned on? - may be undefined
 */
class LocalAppToggle(
    val key: String,
    val isOn: Boolean?
)