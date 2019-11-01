package com.wundermobility.interview.ricardo.screenplay.facts

import com.wundermobility.interview.ricardo.models.EmployeeModel
import com.wundermobility.interview.ricardo.screenplay.tasks.rest.RegisterEmployee
import com.wundermobility.interview.ricardo.utils.LAST_REGISTERED_EMPLOYEE
import net.serenitybdd.screenplay.Actor
import net.serenitybdd.screenplay.EventualConsequence.eventually
import net.serenitybdd.screenplay.GivenWhenThen.seeThat
import net.serenitybdd.screenplay.facts.Fact
import net.serenitybdd.screenplay.rest.questions.LastResponse
import net.serenitybdd.screenplay.rest.questions.TheResponseStatusCode
import org.hamcrest.CoreMatchers.equalTo

class RegisteredEmployee(private val employeeInformation: EmployeeModel) : Fact {
    override fun setup(actor: Actor) {
        actor.wasAbleTo(
                RegisterEmployee.withInformation(employeeInformation)
        )
        actor.should(eventually(seeThat(TheResponseStatusCode(), equalTo(200))).withNoReporting())
        val registeredEmployee = actor.asksFor(LastResponse.received()).`as`(EmployeeModel::class.java)
        actor.remember(LAST_REGISTERED_EMPLOYEE, registeredEmployee)
    }

    override fun toString(): String {
        return "Registered a new Employee"
    }

    companion object {
        fun withInformation(employeeInformation: EmployeeModel) = RegisteredEmployee(employeeInformation)
    }
}