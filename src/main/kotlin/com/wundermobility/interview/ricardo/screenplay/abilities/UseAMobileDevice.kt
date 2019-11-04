package com.wundermobility.interview.ricardo.screenplay.abilities

import io.appium.java_client.android.AndroidDriver
import net.serenitybdd.screenplay.abilities.BrowseTheWeb
import net.thucydides.core.webdriver.WebDriverFacade
import org.openqa.selenium.WebDriver

class UseAMobileDevice(private val webDriver: WebDriver) : BrowseTheWeb(webDriver) {
    override fun toString(): String {
        return "Use a Mobile device"
    }

    fun getAndroidDriver(): AndroidDriver<*> {
        return ((webDriver as WebDriverFacade).proxiedDriver as AndroidDriver<*>)
    }

    companion object {
        fun with(webDriver: WebDriver) = UseAMobileDevice(webDriver)
    }
}