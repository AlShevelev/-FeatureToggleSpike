package com.example.feature_toggle_impl.domain

import com.example.feature_toggle_impl.data.model.LocalAppToggleRecord
import com.example.feature_toggle_impl.domain.model.LocalTogglesCalculationResult

/**
 * Calculates the summary list of local toggles that have been turned on
 */
object LocalTogglesCalculator {
    /**
     * The calculation method
     * @param appToggles a list of toggles, hard sealed in the app
     * @param deviceToggles a list of keys for toggles, stored on a device, that were turned on
     * @return calculation result
     */
    fun calculate(appToggles: List<LocalAppToggleRecord>, deviceToggles: Set<String>): LocalTogglesCalculationResult {
        val activeToggles = mutableSetOf<String>()

        appToggles.forEach { appToggle ->
            if(appToggle.enabled != null) {
                if(appToggle.enabled) {
                    activeToggles.add(appToggle.key)
                }
            } else {
                if(deviceToggles.contains(appToggle.key)) {
                    activeToggles.add(appToggle.key)
                }
            }
        }

        val togglesToRemove = deviceToggles.subtract(appToggles.map { it.key })

        return LocalTogglesCalculationResult(activeToggles, togglesToRemove)
    }
}