package com.wundermobility.interview.ricardo.plugins

import cucumber.api.event.ConcurrentEventListener
import cucumber.api.event.EventPublisher
import cucumber.api.event.TestRunFinished
import cucumber.api.event.TestRunStarted
import io.appium.java_client.service.local.AppiumDriverLocalService
import io.appium.java_client.service.local.AppiumServiceBuilder
import java.io.File


class BaseHooksPlugin : ConcurrentEventListener {

    private val service: AppiumDriverLocalService

    init {
        val serviceBuilder = AppiumServiceBuilder()
                .usingAnyFreePort()
                .withLogFile(File.createTempFile("tmp-appium-", ".txt"))
        service = AppiumDriverLocalService.buildService(serviceBuilder)
    }


    override fun setEventPublisher(eventPublisher: EventPublisher) {
        eventPublisher.registerHandlerFor(TestRunStarted::class.java) {
            beforeAll()
        }
        eventPublisher.registerHandlerFor(TestRunFinished::class.java) {
            afterAll()
        }
    }

    private fun beforeAll() {
        service.start()
    }

    private fun afterAll() {
        service.stop()
    }

}