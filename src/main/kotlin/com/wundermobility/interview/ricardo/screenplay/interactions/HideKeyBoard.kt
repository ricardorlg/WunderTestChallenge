package com.wundermobility.interview.ricardo.screenplay.interactions

import com.wundermobility.interview.ricardo.screenplay.abilities.UseAMobileDevice
import net.serenitybdd.markers.IsHidden
import net.serenitybdd.screenplay.Actor
import net.serenitybdd.screenplay.Performable

class HideKeyBoard : Performable, IsHidden {
    override fun <T : Actor> performAs(actor: T) {
        actor.abilityTo(UseAMobileDevice::class.java).getAndroidDriver().hideKeyboard()

    }

    companion object {
        fun hideAndroidKeyBoard() = HideKeyBoard()
    }
}