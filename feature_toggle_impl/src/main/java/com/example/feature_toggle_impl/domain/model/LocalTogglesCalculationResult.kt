package com.example.feature_toggle_impl.domain.model

import com.example.feature_toggle_impl.domain.LocalTogglesCalculator

/**
 * A calculation result for [LocalTogglesCalculator]
 * @property activeToggles a list of keys of active toggles
 * @property togglesToRemove a list of keys for toggles that must be removed from a device
 */
class LocalTogglesCalculationResult(
    val activeToggles: Set<String>,
    val togglesToRemove: Set<String>
)