package com.example.feature_toggle_impl.data.model

import com.google.gson.annotations.SerializedName

/**
 * One local toggle record stored inside the app
 * @property key a key of the toggle
 * @property title a title of the toggle
 * @property description a description of the toggle
 * @property enabled is this toggle turned on? - may be undefined
 * @property version an app version in which the toggle must be removed (for information only)
 */
class LocalAppToggleRecord (
    @SerializedName("key")
    val key: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("enabled")
    val enabled: Boolean?,
    @SerializedName("version")
    val version: String
)