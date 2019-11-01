package com.wundermobility.interview.ricardo.screenplay.actors

import com.testinium.deviceinformation.DeviceInfoImpl
import com.testinium.deviceinformation.device.DeviceType
import com.wundermobility.interview.ricardo.screenplay.abilities.UseAMobileDevice
import net.serenitybdd.screenplay.Ability
import net.serenitybdd.screenplay.Actor
import net.serenitybdd.screenplay.actors.Cast
import net.serenitybdd.screenplay.rest.abilities.CallAnApi
import net.thucydides.core.util.EnvironmentVariables
import net.thucydides.core.webdriver.ThucydidesWebDriverSupport
import org.openqa.selenium.WebDriver

class ApiActorsCast(environmentVariables: EnvironmentVariables, private val testEnvironment: TestEnvironment = TestEnvironment(environmentVariables)) : Cast() {
    override fun actorNamed(actorName: String, vararg abilities: Ability): Actor = super.actorNamed(actorName, CallAnApi.at(testEnvironment.baseUrl)).describedAs("An Employee Manager API user")
}

class MobileActorsCast(environmentVariables: EnvironmentVariables, private val testEnvironment: TestEnvironment = TestEnvironment(environmentVariables)) : Cast() {
    override fun actorNamed(actorName: String, vararg abilities: Ability?): Actor {
        val deviceId = DeviceInfoImpl(DeviceType.ANDROID).firstDevice
        return super.actorNamed(actorName, UseAMobileDevice.with(theDefaultDriverFor(actorName, deviceId.uniqueDeviceID))).also { actor ->
            if (!actor.recallAll().containsKey("tips_percentage")) {
                actor.remember("tips_percentage", testEnvironment.defaultTipPercentage)
            }
        }
    }

    private fun theDefaultDriverFor(actorName: String, deviceId: String): WebDriver {
        return ThucydidesWebDriverSupport.getWebdriverManager().withOptions("deviceName=$deviceId;udid=$deviceId").getWebdriverByName(actorName)
    }
}