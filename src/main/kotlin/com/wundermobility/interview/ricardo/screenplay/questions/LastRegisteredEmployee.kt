package com.wundermobility.interview.ricardo.screenplay.questions

import arrow.core.Try
import com.wundermobility.interview.ricardo.models.EmployeeModel
import net.serenitybdd.screenplay.Actor
import net.serenitybdd.screenplay.Question
import net.serenitybdd.screenplay.rest.questions.LastResponse

class LastRegisteredEmployee() : Question<EmployeeModel?> {
    override fun answeredBy(actor: Actor): EmployeeModel? {
        return Try {
            actor.asksFor(LastResponse.received()).`as`(EmployeeModel::class.java)
        }.fold(
                ifFailure = { null },
                ifSuccess = { it }
        )
    }

    companion object {
        fun theLastRegisteredEmployee() = LastRegisteredEmployee()
    }
}