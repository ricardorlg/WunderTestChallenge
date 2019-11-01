package com.wundermobility.interview.ricardo.runners

import cucumber.api.CucumberOptions
import net.serenitybdd.cucumber.CucumberWithSerenity
import org.junit.runner.RunWith

@RunWith(CucumberWithSerenity::class)
@CucumberOptions(
        plugin = ["pretty", "rerun:target/rerun.txt"],
        features = ["src/test/resources/features/api_automation"],
        tags = ["@api"],
        glue = ["com.wundermobility.interview.ricardo.features.steps"]
)
class ApiTestsRunner