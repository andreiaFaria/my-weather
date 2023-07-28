package com.af.myweather.ui.weather

import android.util.Log
import android.widget.Toast
import androidx.compose.animation.expandHorizontally
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.af.myweather.R
import com.af.myweather.data.WeatherCityData
import com.af.myweather.data.repository.WeatherRepository
import com.af.myweather.model.WeatherViewModel
import com.af.myweather.ui.theme.MyWeatherTheme
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment
import java.time.format.TextStyle


@Composable
fun WeatherDisplay(weatherViewModel: WeatherViewModel, modifier: Modifier = Modifier) {

    Column(
        modifier = modifier
            .background(colorResource(id = R.color.teal_700))
            .padding(top = 10.dp)
            .padding(5.dp)
            .fillMaxHeight()
    ) {
        //weatherViewModel.currentWeather?.let { WeatherCity(it) } //vou receber weatherCityData ... porque vou usar o get do repository

        WeatherCity(weatherViewModel)
        WeatherWeekScrollable(weatherViewModel.weekWeather)//passar lista

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeatherCity (weatherViewModel : WeatherViewModel,modifier: Modifier = Modifier){

    Row(
        modifier = modifier.fillMaxWidth(),//width(200.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column() {
            var isExpanded by remember { mutableStateOf(false) }
            var localCitySelected by remember { mutableStateOf(weatherViewModel.currentCity) }

            weatherViewModel.currentWeather =  weatherViewModel.getCurrentWeather(stringResource(id =localCitySelected)) //atualizar para enviar por parametro do dia da semana no getCurrentWeather
            weatherViewModel.currentCity = localCitySelected
            weatherViewModel.weekWeather = weatherViewModel.getCurrentWeekWeather(stringResource(id =weatherViewModel.currentCity))

            Box(modifier = Modifier
                .widthIn(max = 230.dp)
                .padding(end = 8.dp)
            ){
                ExposedDropdownMenuBox(
                    expanded = isExpanded,
                    onExpandedChange = {isExpanded = it}
                ) {
                    //val context = LocalContext.current
                    OutlinedTextField(
                        value = stringResource(id = weatherViewModel.currentCity),
                        onValueChange = {},
                        readOnly = true,
                        label = { Text(text = "City")},
                        trailingIcon = {
                            ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded)
                        },
                        modifier = Modifier.menuAnchor(),
                        colors = ExposedDropdownMenuDefaults.outlinedTextFieldColors(),
                    )

                    ExposedDropdownMenu(
                        expanded = isExpanded,
                        onDismissRequest = { isExpanded = false }
                    ) {
                        weatherViewModel.citiesWeatherDatabase.forEach{selectionOption ->
                            DropdownMenuItem(
                                text = { Text (text = stringResource(id = selectionOption)) },
                                onClick = {
                                    localCitySelected = selectionOption
                                    isExpanded = false

                                    Log.i("Ola","New City selected $localCitySelected")
                                    Log.i("Ola","New City selected viewModel ${weatherViewModel.currentCity}")}
                            )
                        }

                    }
                }

            }


            /*LazyColumn(){
                items(weatherViewModel.citiesWeatherDatabase){item ->
                    Text(
                        text = "City ${stringResource(id = item)}!",
                        fontSize = 45.sp,
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier
                            .padding(end = 8.dp)
                    )
                }
            }*/

            /*Text(
                text = "City ${weatherViewModel.currentWeather?.let { stringResource(id = it.city) }}!",
                fontSize = 45.sp,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier
                    .padding(end = 8.dp)
            )*/

            Card(modifier = Modifier
                .padding(top = 20.dp),
                shape = RoundedCornerShape(6.dp),
            ) {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Text(
                        text = "${weatherViewModel.currentWeather?.temperature}",//nao e boa pratica, porque se for null vai mostrar vazio e quebra layout
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

        weatherViewModel.currentWeather?.let {
            Card(
                colors = CardDefaults.cardColors(Color.Transparent)
            ) {
                Image(
                    painter = painterResource(it.weatherIcon),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(150.dp, 150.dp)
                )
            }
        }
    }
}

//atualizar com a cidade selecionada
@Composable
fun WeatherWeekScrollable( weatherWeek: List<WeatherCityData> ,modifier : Modifier = Modifier){

    //val state = rememberLazyListState()
    // tenho que guardar numa lista no max 7 posiçoes de temperatura
    // é essa variavel que o LazyColum vai usar

    var weatherWeekSevenDays = if (weatherWeek.size > 7){
        weatherWeek.filterIndexed { index, x -> index < (weatherWeek.size-1) }
    }else{
        weatherWeek
    }
    LazyColumn(
        modifier = modifier
            .padding(top = 10.dp)
            .padding(10.dp)
            // .background(colorResource(id = R.color.black))
            .size(width = 500.dp, height = 400.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(0.dp)
    ){
        items(weatherWeekSevenDays){weatherItem ->
            weekCard(weekWeather = weatherItem)
        }

    }
}

@Composable
fun weekCard(weekWeather : WeatherCityData, modifier: Modifier = Modifier){

    Card(
        modifier = modifier
            .padding(10.dp)
            .width(200.dp),
        colors = CardDefaults.cardColors(colorResource(id = R.color.teal_200))
    ) {
        Column {
            Text(
                text =weekWeather.date.toString().lowercase(),
                color = Color.White,
                fontStyle = FontStyle.Italic,
                modifier = Modifier.padding(start = 8.dp)
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxSize()
            ) {
                Text(
                    text =stringResource(weekWeather.city)
                )
                Image(
                    painter = painterResource(weekWeather.weatherIcon),
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
    
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyWeatherTheme {
        WeatherDisplay( WeatherViewModel(WeatherRepository(LocalContext.current)))
    }
}