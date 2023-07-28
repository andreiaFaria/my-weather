package com.af.myweather.data

import java.time.DayOfWeek
import java.time.LocalDate

data class WeatherCityData(
    var weatherIcon: Int = 0,
    var city: Int = 0,
    var temperature: Float = 0.0f,
    var date: DayOfWeek = LocalDate.now().dayOfWeek
    //var date: DayOfWeek = DayOfWeek.SUNDAY
)

