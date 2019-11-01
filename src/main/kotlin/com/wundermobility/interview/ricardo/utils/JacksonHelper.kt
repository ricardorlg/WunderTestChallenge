package com.wundermobility.interview.ricardo.utils

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.KotlinModule
import io.restassured.mapper.factory.Jackson2ObjectMapperFactory

object ObjectMapperConfigurator {
    private val objectMapper: ObjectMapper = ObjectMapper().registerModules(KotlinModule(), JavaTimeModule(), Jdk8Module())

    init {
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY)
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT)
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
    }

    fun get(): ObjectMapper {
        return objectMapper
    }
}

val objectMapperFactory = Jackson2ObjectMapperFactory { _, _ -> ObjectMapperConfigurator.get() }

object JsonHelper {
    val objectMapper by lazy {
        ObjectMapperConfigurator.get()
    }
}