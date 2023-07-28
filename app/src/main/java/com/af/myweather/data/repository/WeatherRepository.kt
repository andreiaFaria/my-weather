package com.af.myweather.data.repository

import android.content.Context
import androidx.compose.ui.res.stringResource
import com.af.myweather.data.WeatherCityData
import com.af.myweather.data.dummy.DummyWeatherDataSource

class WeatherRepository (private val context: Context){

    private val dummyRepo = DummyWeatherDataSource(context)

    fun getCurrentWeather(cityWeather : String) : WeatherCityData? { // adicionar o paramentro da cidade

        //é o data source que vai procurar os dados da cidade!!!
        //porque se tivermos a trabalhar com um servidor ele nao nos vai dar todos os dados, apenas os que nós queremos
        //nao nos temos que preocupar com o que ele faz

        return dummyRepo.getCurrentWeather(cityWeather)

    }

    fun getWeekWeather(cityWeather : String) : List<WeatherCityData>{ // adicionar o paramentro da cidade
        //é o data source que vai procurar os dados da cidade!!!
        //porque se tivermos a trabalhar com um servidor ele nao nos vai dar todos os dados, apenas os que nós queremos
        //nao nos temos que preocupar com o que ele faz

        return dummyRepo.getWeather(cityWeather)

    }

    fun getCitiesWeather() : List<Int>{
        return dummyRepo.getCitiesWeather()
    }
}