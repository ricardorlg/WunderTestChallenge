package com.wundermobility.interview.ricardo.screenplay.tasks.rest

import com.wundermobility.interview.ricardo.rest.EndPoints
import com.wundermobility.interview.ricardo.utils.EMPLOYEE_ID
import io.restassured.http.ContentType
import net.serenitybdd.screenplay.Actor
import net.serenitybdd.screenplay.Task
import net.serenitybdd.screenplay.Tasks
import net.serenitybdd.screenplay.rest.interactions.Delete
import net.thucydides.core.annotations.Step

open class DeleteInformationOfEmployee(private val employeeId: String) : Task {
    @Step("{0} attempts to delete the Information of the Employee with id **#employeeId**")
    override fun <T : Actor> performAs(actor: T) {
        actor.attemptsTo(
                Delete.from(EndPoints.DELETE_EMPLOYEE_INFORMATION_ENDPOINT.path).with { request ->
                    request.contentType(ContentType.JSON)
                            .pathParam(EMPLOYEE_ID, employeeId)

                }
        )
    }

    companion object {
        fun withId(employeeId: String): DeleteInformationOfEmployee = Tasks.instrumented(DeleteInformationOfEmployee::class.java, employeeId)
    }
}