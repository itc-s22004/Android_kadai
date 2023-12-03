package jp.ac.it_college.std.s22004.androidkadai

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import jp.ac.it_college.std.s22004.androidkadai.api.Games
import jp.ac.it_college.std.s22004.androidkadai.ui.theme.AndroidKadaiTheme
import kotlinx.coroutines.launch
import androidx.compose.foundation.layout.Box as Box

@Composable
fun FiveWeatScene(modifier: Modifier = Modifier, cityName: String) {
    Surface(modifier) {
        FiveWeather(cityName)
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FiveWeather(cityName: String) {

    var dateText by remember { mutableStateOf("日付時間") }
    var numText by remember { mutableStateOf("") }


    var tempText by remember { mutableStateOf(0) }
    var feelsText by remember { mutableStateOf(0) }
    var pressureText by remember { mutableStateOf("") }
    var humidityText by remember { mutableStateOf("") }
    var mainText by remember { mutableStateOf("") }
    var iconImg by remember { mutableStateOf("") }
    var speedText by remember { mutableStateOf("") }
    var degText by remember { mutableStateOf("") }
    var gustText by remember { mutableStateOf("") }
    var popText by remember { mutableStateOf("") }
    var snowText by remember { mutableStateOf("") }


    val scope = rememberCoroutineScope()


    val coffeeDrinks = mutableListOf<String>()
    var expanded by remember { mutableStateOf(false) }
    var selectedText by remember { mutableStateOf("日付選ぶ") }

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {


        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 30.dp)
        ) {
            Button(onClick = {
                scope.launch {
                    for (i in 0..39) {
                        dateText = Games.getGenerations(cityName).list[i].dt_txt
                        coffeeDrinks.add(dateText)
                        numText = i.toString()
                    }
                }
            }) {
                Text(text = "最初にボタン押して")

            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(32.dp)
            ) {
                ExposedDropdownMenuBox(
                    expanded = expanded,
                    onExpandedChange = {
                        expanded = !expanded
                    }
                ) {
                    TextField(
                        value = selectedText,
                        onValueChange = {},
                        readOnly = true,
                        trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                        modifier = Modifier.menuAnchor()
                    )

                    ExposedDropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false }
                    ) {
                        coffeeDrinks.forEachIndexed { index, element ->
                            DropdownMenuItem(
                                text = { Text(text = element) },
                                onClick = {
                                    selectedText = element
                                    expanded = false
                                    scope.launch {

                                        iconImg =
                                            Games.getGenerations(cityName).list[index].weather[0].icon
                                        mainText =
                                            Games.getGenerations(cityName).list[index].weather[0].main
                                        tempText =
                                            (Games.getGenerations(cityName).list[index].main.temp - 273).toInt()
                                        feelsText =
                                            (Games.getGenerations(cityName).list[index].main.feels_like -273).toInt()
                                        humidityText =
                                            Games.getGenerations(cityName).list[index].main.humidity.toString()
                                        pressureText =
                                            Games.getGenerations(cityName).list[index].main.pressure.toString()
                                        speedText =
                                            Games.getGenerations(cityName).list[index].wind.speed.toString()
                                        degText =
                                            Games.getGenerations(cityName).list[index].wind.deg.toString()
                                        gustText =
                                            Games.getGenerations(cityName).list[index].wind.gust.toString()
                                        popText =
                                            (Games.getGenerations(cityName).list[index].pop * 100).toString()
                                        snowText =
                                            Games.getGenerations(cityName).list[index].snow.toString()

                                    }
                                }
                            )
                        }
                    }
                }
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 50.dp),
            ) {
                Text(text = "---  $numText　個目 取得     ---")
                Text(
                    text = "$cityName の 天気",
                    modifier = Modifier.padding(start = 40.dp),
                    fontSize = 25.sp
                )

                Image(
                    painter = rememberAsyncImagePainter(
                        "https://api.openweathermap.org/img/w/$iconImg.png"
                    ),
                    contentDescription = null,
                    modifier = Modifier.size(122.dp)
                )
                Text(text = "　　天気: $mainText")
                Text(text = "　　温度: $tempText 度")
                Text(text = "体感温度: $feelsText 度")
                Text(text = "　　湿気: $humidityText ％")
                Text(text = "　　気圧: $pressureText hPa")
                Text(text = "　　風速: $speedText m/s")
                Text(text = "　　風向: $degText kt")
                Text(text = "瞬間風速: $gustText m/s")
                Text(text = "降水確率: $popText %")
                Text(text = "　積雪量: $snowText ")

            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun FiveWeatPreview() {
    AndroidKadaiTheme {
        FiveWeatScene(cityName = "")
    }
}

