package com.wundermobility.interview.ricardo.plugins

import com.wundermobility.interview.ricardo.utils.AppiumServerConfigurator
import cucumber.api.event.ConcurrentEventListener
import cucumber.api.event.EventPublisher
import cucumber.api.event.TestRunFinished
import cucumber.api.event.TestRunStarted


class BaseHooksPlugin : ConcurrentEventListener {

    override fun setEventPublisher(eventPublisher: EventPublisher) {
        eventPublisher.registerHandlerFor(TestRunStarted::class.java) {
            beforeAll()
        }
        eventPublisher.registerHandlerFor(TestRunFinished::class.java) {
            afterAll()
        }
    }

    private fun beforeAll() {
        AppiumServerConfigurator.startServer(enableLoggingInConsole = false)
    }

    private fun afterAll() {
        AppiumServerConfigurator.stopServer()
    }

}