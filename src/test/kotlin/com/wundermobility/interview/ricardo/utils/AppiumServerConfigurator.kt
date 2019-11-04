package com.wundermobility.interview.ricardo.utils

import io.appium.java_client.service.local.AppiumDriverLocalService
import io.appium.java_client.service.local.AppiumServiceBuilder
import java.io.File

object AppiumServerConfigurator {
    private val service: AppiumDriverLocalService

    init {
        val serviceBuilder = AppiumServiceBuilder()
                .usingAnyFreePort()
                .withLogFile(File.createTempFile("tmp-appium-", ".txt"))
        service = AppiumDriverLocalService.buildService(serviceBuilder)
    }

    fun startServer(enableLoggingInConsole: Boolean = true) {
        service.start()
        service.clearOutPutStreams()
        if (enableLoggingInConsole) {
            service.enableDefaultSlf4jLoggingOfOutputData()
        }
    }

    fun stopServer() {
        service.stop()
    }

    fun getHubURL() = service.url.toString()
}