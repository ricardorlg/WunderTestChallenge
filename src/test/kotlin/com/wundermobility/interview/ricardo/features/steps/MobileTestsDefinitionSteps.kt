package com.wundermobility.interview.ricardo.features.steps

import com.wundermobility.interview.ricardo.screenplay.facts.SetUpTipPercentage
import com.wundermobility.interview.ricardo.screenplay.tasks.mobile.CalculateTips
import com.wundermobility.interview.ricardo.screenplay.ui.CalculateTipView
import com.wundermobility.interview.ricardo.utils.round
import cucumber.api.java.en.And
import cucumber.api.java.en.Given
import cucumber.api.java.en.Then
import cucumber.api.java.en.When
import net.serenitybdd.core.pages.WebElementState
import net.serenitybdd.screenplay.Actor
import net.serenitybdd.screenplay.GivenWhenThen.seeThat
import net.serenitybdd.screenplay.actors.OnStage
import net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible
import net.serenitybdd.screenplay.questions.WebElementQuestion.the
import net.serenitybdd.screenplay.questions.targets.TheTarget
import net.serenitybdd.screenplay.waits.Wait
import org.hamcrest.CoreMatchers.equalTo

class MobileTestsDefinitionSteps {

    private val currentActor: Actor
        get() = OnStage.theActorInTheSpotlight()

    @Given("{word} wants to know the total amount to pay with Tips included")
    fun `actor wants to calculate the total amount with tips`(actorName: String) {
        OnStage.theActorCalled(actorName).attemptsTo(
                Wait.until(the(CalculateTipView.CALCULATE_TIP_BUTTON), isVisible<WebElementState>()).forNoLongerThan(20).seconds()
        )
    }

    @And("(S/s)he/He has defined the tip percentage to be {word}%")
    fun `actor defines the tips percentage to be used`(tipPercentage: String) {
        currentActor.has(
                SetUpTipPercentage.of(tipPercentage)
        )
    }

    @When("(S/s)he/He calculates the total amount for {word}")
    fun `actor calculates the total amount with tips for a current amount`(_amount: String) {
        val amount = _amount.toDoubleOrNull() ?: error("Invalid amount")
        currentActor.attemptsTo(
                CalculateTips.forAmount(amount)
        )
        currentActor.remember("amount", amount)
    }

    @Then("(S/s)he/He should see that the total amount is the expected one")
    fun `actor should see the correct values`() {
        val amount = currentActor.recall<Double>("amount")
        val tipsPercentage = currentActor.recall<Double>("tips_percentage")
        val expectedTips = amount * tipsPercentage
        val totalAmount = amount + expectedTips
        currentActor.should(
                seeThat(TheTarget.textOf(CalculateTipView.TIP_AMOUNT_FIELD), equalTo("$${expectedTips.round()}")).because("Then %s should be **%s**"),
                seeThat(TheTarget.textOf(CalculateTipView.TOTAL_AMOUNT_FIELD), equalTo("$${totalAmount.round()}")).because("Then %s should be **%s**")
        )

    }
}