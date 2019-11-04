package com.wundermobility.interview.ricardo.screenplay.tasks.rest

import com.wundermobility.interview.ricardo.models.EmployeeModel
import com.wundermobility.interview.ricardo.rest.EndPoints
import com.wundermobility.interview.ricardo.utils.JsonHelper
import io.restassured.http.ContentType
import net.serenitybdd.core.Serenity
import net.serenitybdd.screenplay.Actor
import net.serenitybdd.screenplay.Task
import net.serenitybdd.screenplay.Tasks.instrumented
import net.serenitybdd.screenplay.rest.interactions.Post
import net.thucydides.core.annotations.Step

open class RegisterEmployee(private val employeeInformation: EmployeeModel) : Task {
    @Step("{0} attempts to register a new Employee")
    override fun <T : Actor> performAs(actor: T) {
        Serenity.recordReportData().withTitle("Employee Information")
                .andContents(JsonHelper.objectMapper.writeValueAsString(employeeInformation))
        actor.attemptsTo(
                Post.to(EndPoints.REGISTER_EMPLOYEE_ENDPOINT.path).with { request ->
                    request.contentType(ContentType.JSON)
                            .body(employeeInformation)


                }
        )
    }

    companion object {
        fun withInformation(employeeInformation: EmployeeModel): RegisterEmployee = instrumented(RegisterEmployee::class.java, employeeInformation)
    }
}