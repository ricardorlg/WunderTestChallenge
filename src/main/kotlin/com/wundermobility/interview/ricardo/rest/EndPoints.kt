package com.wundermobility.interview.ricardo.rest

import com.wundermobility.interview.ricardo.utils.EMPLOYEE_ID

enum class EndPoints(val path:String) {
    REGISTER_EMPLOYEE_ENDPOINT("/create"),
    GET_EMPLOYEE_INFORMATION_ENDPOINT("/employee/{$EMPLOYEE_ID}"),
    DELETE_EMPLOYEE_INFORMATION_ENDPOINT("/delete/{$EMPLOYEE_ID}")
}