package com.wundermobility.interview.ricardo.screenplay.tasks.mobile

import com.wundermobility.interview.ricardo.screenplay.ui.CalculateTipView
import com.wundermobility.interview.ricardo.screenplay.ui.SettingsView
import net.serenitybdd.core.pages.WebElementState
import net.serenitybdd.screenplay.Actor
import net.serenitybdd.screenplay.Task
import net.serenitybdd.screenplay.Tasks.instrumented
import net.serenitybdd.screenplay.actions.Click
import net.serenitybdd.screenplay.actions.Enter
import net.serenitybdd.screenplay.matchers.WebElementStateMatchers
import net.serenitybdd.screenplay.questions.WebElementQuestion.the
import net.serenitybdd.screenplay.waits.Wait
import net.thucydides.core.annotations.Step

open class SetTheTipPercentage(private val percentage: Double) : Task {
    @Step("{0} sets the tip percentage to be **#percentage**%")
    override fun <T : Actor> performAs(actor: T) {
        actor.attemptsTo(
                Click.on(CalculateTipView.SETTINGS_MENU_OPTION),
                Wait.until(the(SettingsView.SAVE_SETTINGS_BUTTON), WebElementStateMatchers.isVisible<WebElementState>()).forNoLongerThan(10).seconds(),
                Enter.theValue(percentage.toString()).into(SettingsView.TIP_PERCENTAGE_INPUT_FIELD),
                Click.on(SettingsView.SAVE_SETTINGS_BUTTON)
        )
    }

    companion object {
        fun toBe(percentage: Double): SetTheTipPercentage = instrumented(SetTheTipPercentage::class.java, percentage)
    }
}