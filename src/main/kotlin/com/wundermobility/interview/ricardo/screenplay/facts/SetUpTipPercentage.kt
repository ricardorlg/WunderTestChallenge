package com.wundermobility.interview.ricardo.screenplay.facts

import com.wundermobility.interview.ricardo.screenplay.tasks.mobile.SetTheTipPercentage
import com.wundermobility.interview.ricardo.screenplay.ui.CalculateTipView
import net.serenitybdd.core.pages.WebElementState
import net.serenitybdd.screenplay.Actor
import net.serenitybdd.screenplay.GivenWhenThen.seeThat
import net.serenitybdd.screenplay.facts.Fact
import net.serenitybdd.screenplay.matchers.WebElementStateMatchers
import net.serenitybdd.screenplay.questions.WebElementQuestion.the
import net.serenitybdd.screenplay.questions.targets.TheTarget
import net.serenitybdd.screenplay.waits.Wait
import org.hamcrest.CoreMatchers.equalTo

class SetUpTipPercentage(private val tipPercentage: String) : Fact {
    override fun setup(actor: Actor) {
        val percentage = tipPercentage.toDoubleOrNull() ?: error("Invalid Percentage")
        actor.attemptsTo(
                SetTheTipPercentage.toBe(percentage),
                Wait.until(the(CalculateTipView.CALCULATE_TIP_BUTTON), WebElementStateMatchers.isVisible<WebElementState>()).forNoLongerThan(10).seconds()
        )
        actor.should(
                seeThat(TheTarget.textOf(CalculateTipView.TIP_PERCENTAGE_FIELD), equalTo("${percentage}%"))
        )
        actor.remember("tips_percentage", percentage / 100)
    }

    companion object {
        fun of(tipPercentage: String) = SetUpTipPercentage(tipPercentage)
    }

    override fun toString(): String {
        return "set up the tip percentage to $tipPercentage%"
    }
}