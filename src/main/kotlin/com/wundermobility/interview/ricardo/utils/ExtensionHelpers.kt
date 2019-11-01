package com.wundermobility.interview.ricardo.utils

fun Double.round(decimals: Int = 2): String = "%.${decimals}f".format(this)