package com.wundermobility.interview.ricardo.features.steps

import com.wundermobility.interview.ricardo.screenplay.actors.ApiActorsCast
import com.wundermobility.interview.ricardo.screenplay.actors.MobileActorsCast
import com.wundermobility.interview.ricardo.utils.objectMapperFactory
import cucumber.api.java.Before
import io.restassured.config.ObjectMapperConfig
import io.restassured.filter.Filter
import io.restassured.internal.RestAssuredResponseOptionsImpl
import net.serenitybdd.rest.SerenityRest
import net.serenitybdd.screenplay.actors.OnStage
import net.thucydides.core.util.EnvironmentVariables


class Hooks {

    private lateinit var environmentVariables: EnvironmentVariables

    @Before("@api", order = 0)
    fun setTheStageApiPlay() {
        SerenityRest.setDefaultConfig(SerenityRest
                .config()
                .objectMapperConfig(ObjectMapperConfig
                        .objectMapperConfig()
                        .jackson2ObjectMapperFactory(objectMapperFactory)))
        SerenityRest.enableLoggingOfRequestAndResponseIfValidationFails()
        SerenityRest.filters(Filter { requestSpec, responseSpec, ctx ->
            val response = ctx.next(requestSpec, responseSpec)
            (response as RestAssuredResponseOptionsImpl<*>).setContentType("application/json")
            response
        })
        OnStage.setTheStage(ApiActorsCast(environmentVariables))
    }


    @Before("@mobile", order = 0)
    fun setTheStageMobilePlay() {
        OnStage.setTheStage(MobileActorsCast(environmentVariables))
    }
}