package com.af.myweather.data.dummy

import android.content.Context
import com.af.myweather.R
import com.af.myweather.data.WeatherCityData
import java.time.LocalDate
import java.time.Month

class DummyWeatherDataSource (private val context: Context){

    private var weatherAllData = listOf(
        WeatherCityData(weatherIcon = R.drawable.ic_weather_rain, city = R.string.city_Porto, temperature = 25.2f, date = LocalDate.of(2023, Month.NOVEMBER, 7).dayOfWeek),
        WeatherCityData(weatherIcon = R.drawable.ic_weather_cloudy, city = R.string.city_Porto, temperature = 33.2f, date = LocalDate.of(2023, Month.NOVEMBER, 8).dayOfWeek),
        WeatherCityData(weatherIcon = R.drawable.ic_weather_sun, city = R.string.city_Porto, temperature = 22.2f, date = LocalDate.of(2023, Month.NOVEMBER, 9).dayOfWeek),
        WeatherCityData(weatherIcon = R.drawable.ic_weather_cloudy, city = R.string.city_Lisboa, temperature = 20.2f, date = LocalDate.of(2023, Month.NOVEMBER, 10).dayOfWeek),
        WeatherCityData(weatherIcon = R.drawable.ic_weather_rain, city = R.string.city_Lisboa, temperature = 23.2f, date = LocalDate.of(2023, Month.NOVEMBER, 11).dayOfWeek),
        WeatherCityData(weatherIcon = R.drawable.ic_weather_rain, city = R.string.city_Porto, temperature = 24.2f, date = LocalDate.of(2023, Month.NOVEMBER, 10).dayOfWeek),
        WeatherCityData(weatherIcon = R.drawable.ic_weather_cloudy, city = R.string.city_Porto, temperature = 19.2f, date = LocalDate.of(2023, Month.NOVEMBER, 11).dayOfWeek),
        WeatherCityData(weatherIcon = R.drawable.ic_weather_rain, city = R.string.city_Aveiro, temperature = 19.2f, date = LocalDate.of(2023, Month.NOVEMBER, 9).dayOfWeek),
        WeatherCityData(weatherIcon = R.drawable.ic_weather_sun, city = R.string.city_Porto, temperature = 35.2f, date = LocalDate.of(2023, Month.NOVEMBER, 12).dayOfWeek),
        WeatherCityData(weatherIcon = R.drawable.ic_weather_rain, city = R.string.city_Porto, temperature = 24.2f, date = LocalDate.of(2023, Month.NOVEMBER, 13).dayOfWeek),
        WeatherCityData(weatherIcon = R.drawable.ic_weather_rain, city = R.string.city_Porto, temperature = 22.5f, date = LocalDate.of(2023, Month.NOVEMBER, 14).dayOfWeek),

        )

    fun getWeather(cityWeather: String) : List<WeatherCityData> {
        return weatherAllData.filter { context.getString(it.city) == cityWeather }
    }

    fun getCurrentWeather(cityWeather: String): WeatherCityData? {
        return weatherAllData.find { context.getString(it.city) == cityWeather }
        //WeatherCityData(weatherIcon = R.drawable.ic_weather_sun, city = R.string.city_Porto, temperature = 22.2f)
    }

    fun getCitiesWeather(): List<Int> = weatherAllData.map { it.city }.distinct() //quando temos funçao que retorna uma linha

}