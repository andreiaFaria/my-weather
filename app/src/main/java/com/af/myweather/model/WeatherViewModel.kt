package com.af.myweather.model

import android.os.Bundle
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.savedstate.SavedStateRegistryOwner
import com.af.myweather.R
import com.af.myweather.data.WeatherCityData
import com.af.myweather.data.repository.WeatherRepository
import java.nio.file.Files.find

class WeatherViewModel (
    var weatherRepo: WeatherRepository
): ViewModel() {

    var currentCity by mutableStateOf(R.string.city_Porto)
    var currentWeather by mutableStateOf (weatherRepo.getCurrentWeather("Porto"))
    var weekWeather by mutableStateOf (weatherRepo.getWeekWeather("Porto"))
    var citiesWeatherDatabase by mutableStateOf( weatherRepo.getCitiesWeather())

    fun getCurrentWeather(cityWeather: String): WeatherCityData? {
        return weatherRepo.getCurrentWeather(cityWeather)
        //WeatherCityData(weatherIcon = R.drawable.ic_weather_sun, city = R.string.city_Porto, temperature = 22.2f)
    }

    fun getCurrentWeekWeather(cityWeather : String) : List<WeatherCityData>{
        return weatherRepo.getWeekWeather(cityWeather)

    }
    companion object {
        fun provideFactory(
            myRepository: WeatherRepository,
            owner: SavedStateRegistryOwner,
            defaultArgs: Bundle? = null,
        ): AbstractSavedStateViewModelFactory =
            object : AbstractSavedStateViewModelFactory(owner, defaultArgs) {
                @Suppress("UNCHECKED_CAST")
                override fun <T : ViewModel> create(
                    key: String,
                    modelClass: Class<T>,
                    handle: SavedStateHandle
                ): T {
                    return WeatherViewModel(myRepository) as T
                }
            }
    }
}