package com.af.myweather.ui.weather

import androidx.compose.animation.expandHorizontally
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.af.myweather.R
import com.af.myweather.ui.theme.MyWeatherTheme
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment

data class WeatherCard(
    var weather: Int = 0,
    var city: Int = 0,
    var temperature: Float = 0.0f
)

@Composable
fun WeatherDisplay(city_name: String, temperature: Float, modifier: Modifier = Modifier) {

    Column(
        modifier = modifier
            .background(colorResource(id = R.color.teal_700))
            .padding(top = 10.dp)
            .padding(5.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),//width(200.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column() {
                Text(
                    text = "City $city_name!",
                    fontSize = 45.sp,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier
                        .padding(end = 8.dp)
                )
                Card(modifier = Modifier
                    .padding(top = 20.dp),
                    shape = RoundedCornerShape(6.dp),
                ) {
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Text(
                            text = "$temperature",
                            fontSize = 26.sp,
                            style = MaterialTheme.typography.bodyMedium,
                            modifier = Modifier.padding(start = 5.dp)
                        )
                        Image(
                            painter = painterResource(R.drawable.ic_temperature_celsius),
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .size(32.dp, 56.dp)

                        )

                    }
                }

            }

            Card(
                colors = CardDefaults.cardColors(Color.Red)
            ) {
                Image(
                    painter = painterResource(R.drawable.ic_weather_sun),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(150.dp, 150.dp)
                )
            }
        }


        WeatherSlider()

    }


}

@Composable
fun WeatherSlider(modifier : Modifier = Modifier){

    val state = rememberLazyListState()

    val weatherWeek = listOf(
        WeatherCard(weather = R.drawable.ic_weather_sun, city = R.string.city_Porto, temperature = 22.2f),
        WeatherCard(weather = R.drawable.ic_weather_cloudy, city = R.string.city_Lisboa, temperature = 22.2f),
        WeatherCard(weather = R.drawable.ic_weather_rain, city = R.string.city_Lisboa, temperature = 22.2f),
        WeatherCard(weather = R.drawable.ic_weather_rain, city = R.string.city_Porto, temperature = 22.2f),
        WeatherCard(weather = R.drawable.ic_weather_cloudy, city = R.string.city_Porto, temperature = 22.2f),
        WeatherCard(weather = R.drawable.ic_weather_cloudy, city = R.string.city_Porto, temperature = 22.2f),
        WeatherCard(weather = R.drawable.ic_weather_sun, city = R.string.city_Porto, temperature = 22.2f),
        )

    LazyColumn(
        modifier = modifier
            .padding(top = 10.dp)
            .padding(10.dp)
            .background(colorResource(id = R.color.black))
            .size(width = 500.dp, height = 400.dp),
        state = state,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(0.dp)
    ){
        items(weatherWeek){weatherItem ->
            weekCard(weekWeather = weatherItem)
        }

    }
}

@Composable
fun weekCard(weekWeather : WeatherCard, modifier: Modifier = Modifier){

    Card(
        modifier = Modifier
            .background(colorResource(id = R.color.teal_200))
            .padding(10.dp)
            .width(200.dp),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .padding(8.dp)
                .fillMaxSize()
        ) {
            Text(
                text = stringResource(weekWeather.city)
            )
            Image(
                painter = painterResource(weekWeather.weather),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(70.dp, 70.dp)
                    .padding(start = 3.dp, end = 3.dp)
            )
            Text(
                text = weekWeather.temperature.toString()
            )
        }
    }
    
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyWeatherTheme {
        WeatherDisplay("Porto", 22.2f)
    }
}