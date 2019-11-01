package com.wundermobility.interview.ricardo.runners

import cucumber.api.CucumberOptions
import net.serenitybdd.cucumber.CucumberWithSerenity
import org.junit.runner.RunWith

@RunWith(CucumberWithSerenity::class)
@CucumberOptions(
        plugin = ["pretty", "rerun:target/rerun.txt","com.wundermobility.interview.ricardo.plugins.BaseHooksPlugin"],
        features = ["classpath:features"],
        tags = ["@current"],
        glue = ["com.wundermobility.interview.ricardo.features.steps"]
)
class CurrentTestRunner