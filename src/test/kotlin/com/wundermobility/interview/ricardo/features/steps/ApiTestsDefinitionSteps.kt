package com.wundermobility.interview.ricardo.features.steps

import com.github.javafaker.Faker
import com.wundermobility.interview.ricardo.models.EmployeeModel
import com.wundermobility.interview.ricardo.screenplay.facts.RegisteredEmployee
import com.wundermobility.interview.ricardo.screenplay.questions.LastRegisteredEmployee.Companion.theLastRegisteredEmployee
import com.wundermobility.interview.ricardo.screenplay.tasks.rest.DeleteInformationOfEmployee
import com.wundermobility.interview.ricardo.screenplay.tasks.rest.GetInformationOfEmployee
import com.wundermobility.interview.ricardo.screenplay.tasks.rest.RegisterEmployee
import com.wundermobility.interview.ricardo.utils.LAST_REGISTERED_EMPLOYEE
import com.wundermobility.interview.ricardo.utils.REGISTERED_EMPLOYEE_INFORMATION
import cucumber.api.java.en.And
import cucumber.api.java.en.Given
import cucumber.api.java.en.Then
import cucumber.api.java.en.When
import net.serenitybdd.screenplay.Actor
import net.serenitybdd.screenplay.GivenWhenThen.seeThat
import net.serenitybdd.screenplay.actors.OnStage
import net.serenitybdd.screenplay.rest.questions.ResponseConsequence
import org.hamcrest.CoreMatchers.*
import org.hamcrest.Matchers.emptyString
import java.time.LocalDateTime
import java.time.ZoneOffset

class ApiTestsDefinitionSteps {
    private val faker = Faker()
    private val currentActor: Actor
        get() = OnStage.theActorInTheSpotlight()

    @Given("{word} wants to register a new Employee information")
    fun `actor wants to register a new Employee information`(actorName: String) {
        val employeeInformation = EmployeeModel(
                name = "${faker.name().fullName()}-${LocalDateTime.now().toEpochSecond(ZoneOffset.UTC)}",
                age = faker.number().numberBetween(18, 45).toString(),
                salary = faker.number().numberBetween(1500, 3500).toString())
        OnStage.theActorCalled(actorName)
                .remember(REGISTERED_EMPLOYEE_INFORMATION, employeeInformation)
    }

    @Given("{word} has registered a new Employee information")
    fun `actor has registered an employee information`(actorName: String) {
        val employeeInformation = EmployeeModel(
                name = "${faker.name().fullName()}-${LocalDateTime.now().toEpochSecond(ZoneOffset.UTC)}",
                age = faker.number().numberBetween(18, 45).toString(),
                salary = faker.number().numberBetween(1500, 3500).toString())
        OnStage.theActorCalled(actorName).has(
                RegisteredEmployee.withInformation(employeeInformation)
        )
    }

    @When("(S/s)he/He makes a post with the employee information")
    fun `actor makes a post to register employee information`() {
        val employeeInformation = currentActor.recall<EmployeeModel>(REGISTERED_EMPLOYEE_INFORMATION)
        currentActor.attemptsTo(
                RegisterEmployee.withInformation(employeeInformation)
        )
    }

    @When("(S/s)he/He makes a request to get the information of the employee")
    fun `actor makes a request to get employee information`() {
        val lastEmployeeRegisteredId = currentActor.recall<EmployeeModel>(LAST_REGISTERED_EMPLOYEE).id
                .ifEmpty { error("Employee Id must not be null") }
        currentActor.attemptsTo(
                GetInformationOfEmployee.withId(lastEmployeeRegisteredId)
        )
    }

    @When("(S/s)he/He deletes the employee information")
    fun `actor deletes employee information`() {
        val lastEmployeeRegisteredId = currentActor.recall<EmployeeModel>(LAST_REGISTERED_EMPLOYEE).id
                .ifEmpty { error("Employee Id must not be null") }
        currentActor.attemptsTo(
                DeleteInformationOfEmployee.withId(lastEmployeeRegisteredId)
        )
    }

    @Then("the employee should be registered")
    fun `actor should see that the employee was registered successfully`() {
        val expectedData = currentActor.recall<EmployeeModel>(REGISTERED_EMPLOYEE_INFORMATION)
        currentActor.should(
                ResponseConsequence.seeThatResponse("the response status code should be 200") { response ->
                    response.statusCode(200)
                }
        )
        currentActor.should("Validate response Data",
                ResponseConsequence.seeThatResponse("Employee Id should not be empty") { response ->
                    response.body("id", not(emptyString()))
                },
                ResponseConsequence.seeThatResponse("Employee name should be **${expectedData.name}**") { response ->
                    response.body("name", equalTo(expectedData.name))
                },
                ResponseConsequence.seeThatResponse("Employee age should be **${expectedData.age}**") { response ->
                    response.body("age", equalTo(expectedData.age))
                },
                ResponseConsequence.seeThatResponse("Employee salary should be **${expectedData.salary}**") { response ->
                    response.body("salary", equalTo(expectedData.salary))
                }
        )
    }

    @Then("(S/s)he/He should get the correct information")
    fun `actor should get the correct employee information`() {
        val expectedData = currentActor.recall<EmployeeModel>(LAST_REGISTERED_EMPLOYEE)
        currentActor.should(
                ResponseConsequence.seeThatResponse("the response status code should be 200") { response ->
                    response.statusCode(200)
                }
        )
        currentActor.should(seeThat(theLastRegisteredEmployee(), equalTo(expectedData)).because("Employee information should be $expectedData")
        )
    }

    @Then("(S/s)he/He should get a success deleted response message")
    fun `actor should get a success deleted response message`() {
        currentActor.should(
                ResponseConsequence.seeThatResponse("the response status code should be 200") { response ->
                    response.statusCode(200)
                }
        )

        currentActor.should(
                ResponseConsequence.seeThatResponse("response message should be **successfully! deleted Records**") { response ->
                    response.body("success.text", containsStringIgnoringCase("successfully! deleted Records"))
                }
        )
    }

    @And("The employee should not exists")
    fun `employee should not exists in server`() {
        val lastEmployeeRegisteredId = currentActor.recall<EmployeeModel>(LAST_REGISTERED_EMPLOYEE).id.ifEmpty { error("Employee Id must not be null") }
        currentActor.attemptsTo(
                GetInformationOfEmployee.withId(lastEmployeeRegisteredId)
        )
        currentActor.should(
                ResponseConsequence.seeThatResponse("the response status code should be 200") { response ->
                    response.statusCode(200)
                }
        )
        currentActor.should(seeThat(theLastRegisteredEmployee(), nullValue()).because("The employee should not be found"))
    }
}