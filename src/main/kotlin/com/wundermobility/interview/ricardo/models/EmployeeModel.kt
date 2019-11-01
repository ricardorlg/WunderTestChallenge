package com.wundermobility.interview.ricardo.models

import com.fasterxml.jackson.annotation.JsonAlias
import com.fasterxml.jackson.annotation.JsonProperty

data class EmployeeModel(
        @field:JsonProperty("id")
        val id: String = "",

        @field:JsonProperty("name")
        @field:JsonAlias("employee_name")
        val name: String,

        @field:JsonAlias("employee_age")
        @field:JsonProperty("age")
        val age: String,

        @field:JsonProperty("salary")
        @field:JsonAlias("employee_salary")
        val salary: String,

        @field:JsonProperty("profile_image")
        val profileImage: String = ""
) {
    override fun toString() = "Employee (id = $id, name = $name, age = $age, salary = $salary profile_image = ${profileImage.ifEmpty { "No image" }})"
}