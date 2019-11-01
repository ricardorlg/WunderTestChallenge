package com.wundermobility.interview.ricardo.screenplay.tasks.mobile

import com.wundermobility.interview.ricardo.screenplay.ui.CalculateTipView
import net.serenitybdd.screenplay.Actor
import net.serenitybdd.screenplay.Task
import net.serenitybdd.screenplay.Tasks.instrumented
import net.serenitybdd.screenplay.actions.Click
import net.serenitybdd.screenplay.actions.Enter
import net.thucydides.core.annotations.Step

open class CalculateTips(private val amount: Double) : Task {
    @Step("{0} attempts to calculate the tips for an amount of **#amount**")
    override fun <T : Actor> performAs(actor: T) {
        actor.attemptsTo(
                Enter.theValue(amount.toString()).into(CalculateTipView.BILL_AMOUNT_INPUT_FIELD),
                Click.on(CalculateTipView.CALCULATE_TIP_BUTTON)
        )
    }

    companion object {
        fun forAmount(amount: Double): CalculateTips = instrumented(CalculateTips::class.java, amount)
    }
}

