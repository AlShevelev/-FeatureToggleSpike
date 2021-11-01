package com.example.feature_toggle_impl.domain

import com.example.feature_toggle_impl.domain_model.LocalAppToggleRecordBrief
import com.example.feature_toggle_impl.domain_model.LocalTogglesCalculationResult

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
    fun calculate(appToggles: List<LocalAppToggleRecordBrief>, deviceToggles: List<String>): LocalTogglesCalculationResult {
        val activeToggles = mutableListOf<String>()

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

        val togglesToRemove = deviceToggles.subtract(appToggles.map { it.key }).toList()

        return LocalTogglesCalculationResult(activeToggles, togglesToRemove)
    }
}