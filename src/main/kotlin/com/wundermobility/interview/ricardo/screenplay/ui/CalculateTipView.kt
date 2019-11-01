package com.wundermobility.interview.ricardo.screenplay.ui

import net.serenitybdd.screenplay.targets.Target
import org.openqa.selenium.By

object CalculateTipView {
    val TOTAL_AMOUNT_FIELD: Target = Target.the("Total amount").located(By.id("org.traeg.fastip:id/totalAmtTextView"))
    val CALCULATE_TIP_BUTTON: Target = Target.the("calculate tip button").located(By.id("org.traeg.fastip:id/calcTipButton"))
    val BILL_AMOUNT_INPUT_FIELD: Target = Target.the("Bill amount field").located(By.id("org.traeg.fastip:id/billAmtEditText"))
    val TIP_AMOUNT_FIELD: Target = Target.the("Tips amount").located(By.id("org.traeg.fastip:id/tipAmtTextView"))
    val SETTINGS_MENU_OPTION: Target = Target.the("Settings menu option").located(By.id("org.traeg.fastip:id/menu_settings"))
    val TIP_PERCENTAGE_FIELD: Target = Target.the("Tip percentage used").located(By.id("org.traeg.fastip:id/tipPctTextView"))
}