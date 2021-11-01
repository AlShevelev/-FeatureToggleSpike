package com.example.feature_toggle_impl.domain_model

/**
 * A brief version of local toggle record stored inside the app
 * @property key a key of the toggle
 * @property enabled is this toggle turned on? - may be undefined
 */
class LocalAppToggleRecordBrief(
    val key: String,
    val enabled: Boolean?
)