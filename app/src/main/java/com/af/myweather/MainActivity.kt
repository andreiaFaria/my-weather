package com.af.myweather

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.viewModelFactory
import com.af.myweather.data.repository.WeatherRepository
import com.af.myweather.model.WeatherViewModel
import com.af.myweather.ui.theme.MyWeatherTheme
import com.af.myweather.ui.weather.WeatherDisplay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyWeatherTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val weatherViewModel:WeatherViewModel by viewModels { WeatherViewModel.provideFactory (
                        WeatherRepository(this.applicationContext), this
                    ) }
                    //não é boa pratica -> val weatherViewModel = WeatherViewModel(repository)
                    Column(
                    ) {
                        WeatherDisplay(weatherViewModel)
                    }

                }
            }
        }
    }
}
