package com.wundermobility.interview.ricardo.screenplay.actors

import net.serenitybdd.core.environment.EnvironmentSpecificConfiguration
import net.thucydides.core.util.EnvironmentVariables

class TestEnvironment(private val environmentVariables: EnvironmentVariables) {

    val defaultTipPercentage: Double by lazy {
        EnvironmentSpecificConfiguration.from(environmentVariables)
                .getOptionalProperty("default.tip.percentage")
                .orElse("0.15")
                .toDoubleOrNull() ?: 0.15
    }

    val baseUrl: String by lazy {
        EnvironmentSpecificConfiguration.from(environmentVariables)
                .getProperty("restapi.baseurl")
    }

}
