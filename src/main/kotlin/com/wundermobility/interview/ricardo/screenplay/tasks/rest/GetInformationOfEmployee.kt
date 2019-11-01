package com.wundermobility.interview.ricardo.screenplay.tasks.rest

import com.wundermobility.interview.ricardo.rest.EndPoints
import com.wundermobility.interview.ricardo.utils.EMPLOYEE_ID
import io.restassured.http.ContentType
import net.serenitybdd.screenplay.Actor
import net.serenitybdd.screenplay.Task
import net.serenitybdd.screenplay.Tasks.instrumented
import net.serenitybdd.screenplay.rest.interactions.Get
import net.thucydides.core.annotations.Step

open class GetInformationOfEmployee(private val employeeId: String) : Task {
    @Step("{0} attempts to get the Information of the Employee with id **#employeeId**")
    override fun <T : Actor> performAs(actor: T) {
        actor.attemptsTo(
                Get.resource(EndPoints.GET_EMPLOYEE_INFORMATION_ENDPOINT.path).with { request ->
                    request.pathParam(EMPLOYEE_ID, employeeId)
                            .contentType(ContentType.JSON)
                }
        )
    }

    companion object {
        fun withId(employeeId: String): GetInformationOfEmployee = instrumented(GetInformationOfEmployee::class.java, employeeId)
    }
}