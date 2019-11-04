package com.wundermobility.interview.ricardo.screenplay.actors

import com.wundermobility.interview.ricardo.screenplay.abilities.UseAMobileDevice
import com.wundermobility.interview.ricardo.utils.AppiumServerConfigurator
import net.serenitybdd.core.Serenity
import net.serenitybdd.screenplay.Ability
import net.serenitybdd.screenplay.Actor
import net.serenitybdd.screenplay.actors.Cast
import net.serenitybdd.screenplay.rest.abilities.CallAnApi
import net.thucydides.core.util.EnvironmentVariables
import net.thucydides.core.webdriver.ThucydidesWebDriverSupport
import org.openqa.selenium.WebDriver
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import kotlin.streams.toList


class ApiActorsCast(environmentVariables: EnvironmentVariables, private val testEnvironment: TestEnvironment = TestEnvironment(environmentVariables)) : Cast() {
    override fun actorNamed(actorName: String, vararg abilities: Ability): Actor = super.actorNamed(actorName, CallAnApi.at(testEnvironment.baseUrl)).describedAs("An Employee Manager API user")
}

class MobileActorsCast(environmentVariables: EnvironmentVariables, private val testEnvironment: TestEnvironment = TestEnvironment(environmentVariables)) : Cast() {
    override fun actorNamed(actorName: String, vararg abilities: Ability?): Actor {
        val deviceId = getCurrentAndroidDevices().last()
        return super.actorNamed(actorName, UseAMobileDevice.with(theDefaultDriverFor(actorName, deviceId))).also { actor ->
            if (!actor.recallAll().containsKey("tips_percentage")) {
                actor.remember("tips_percentage", testEnvironment.defaultTipPercentage)
            }
        }
    }

    private fun theDefaultDriverFor(actorName: String, deviceId: String): WebDriver {
        val hub=AppiumServerConfigurator.getHubURL()
        return ThucydidesWebDriverSupport.getWebdriverManager()
                .withProperty("appium.hub",hub)
                .withOptions("deviceName=$deviceId;udid=$deviceId")
                .getWebdriverByName(actorName)
    }

    private fun getCurrentAndroidDevices(): List<String> {
        val process = ProcessBuilder("adb", "devices")
                .redirectErrorStream(true)
                .start()
        val output = readOutput(process.inputStream)
        process.waitFor()
        return output
                .mapNotNull {
                    it.substringBefore("\tdevice", "")
                            .ifEmpty { null }
                }
                .ifEmpty { error("There are no Android devices connected") }
    }

    private fun readOutput(inputStream: InputStream): List<String> {
        return BufferedReader(InputStreamReader(inputStream)).use {
            it.lines().toList()
        }
    }
}