package com.wundermobility.interview.ricardo.screenplay.ui

import net.serenitybdd.screenplay.targets.Target
import org.openqa.selenium.By

object SettingsView {
    val SAVE_SETTINGS_BUTTON: Target = Target.the("Save settings button").located(By.id("org.traeg.fastip:id/saveSettingsButton"))
    val TIP_PERCENTAGE_INPUT_FIELD: Target = Target.the("Tip percentage input field").located(By.id("org.traeg.fastip:id/tipPercentageEditText"))
}