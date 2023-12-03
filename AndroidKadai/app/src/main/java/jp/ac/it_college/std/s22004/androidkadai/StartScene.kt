package jp.ac.it_college.std.s22004.androidkadai

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import jp.ac.it_college.std.s22004.androidkadai.api.Games
import jp.ac.it_college.std.s22004.androidkadai.model.Weather
import jp.ac.it_college.std.s22004.androidkadai.ui.theme.AndroidKadaiTheme
import kotlinx.coroutines.launch
import androidx.compose.foundation.lazy.LazyColumn as LazyC

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Demo_ExposedDropdownMenuBox(
    modifier: Modifier = Modifier,
    onStartClick: (String) -> Unit = {}
) {

    var resultText by remember {
        mutableStateOf("結果表示")
    }
    var tempText by remember { mutableStateOf(0) }
    var feelsText by remember { mutableStateOf(0) }
    var pressureText by remember { mutableStateOf("") }
    var humidityText by remember { mutableStateOf("") }
    var mainText by remember { mutableStateOf("") }
    var descriptionText by remember { mutableStateOf("") }
    var iconImg by remember { mutableStateOf("") }
    var speedText by remember { mutableStateOf("") }
    var degText by remember { mutableStateOf("") }
    var gustText by remember { mutableStateOf("") }
    var dateText by remember { mutableStateOf("") }
    var popText by remember { mutableStateOf("") }
    var snowText by remember { mutableStateOf("") }

    var apiList by remember { mutableStateOf<Weather?>(null) }

    // Composable な関数内でコルーチンを使用するためのコルーチンスコープ
    val scope = rememberCoroutineScope()
//    val context = LocalContext.current


    val context = LocalContext.current
//    val coffeeDrinks = arrayOf("Tokyo", "Chiba", "Okinawa", "Akita", "Osaka")
//    val coffeeDrinks = arrayOf(city3List[2111834]!!, city3List[2111149]!!)
    val coffeeDrinks = mutableListOf<String>()

    for (i in 0 until cityList.size) {
        coffeeDrinks.add(cityList[i].name)
    }
    var expanded by remember { mutableStateOf(false) }
//    var selectedText by remember { mutableStateOf(coffeeDrinks[0]) }
    var selectedText by remember { mutableStateOf("都道府県を選ぶ") }
    var citySelect by remember { mutableStateOf("") }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

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
                    placeholder = { Text(text = "Placeholder")},
                    modifier = Modifier.menuAnchor()
                )

                ExposedDropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    coffeeDrinks.forEach { item ->
                        DropdownMenuItem(
                            text = { Text(text = item) },
                            onClick = {
                                selectedText = item
//                            resultText = item

                                expanded = false
//                            Toast.makeText(context, item, Toast.LENGTH_SHORT).show()
                                scope.launch {
                                    citySelect = item
//                                    apiList = Games.getGenerations(item)

                                    dateText = Games.getGenerations(item).list[0].dt_txt
                                    iconImg = Games.getGenerations(item).list[0].weather[0].icon
                                    mainText = Games.getGenerations(item).list[0].weather[0].main
                                    tempText = Games.getGenerations(item).list[0].main.temp.toInt() - 273
                                    feelsText = Games.getGenerations(item).list[0].main.feels_like.toInt() - 273
                                    humidityText = Games.getGenerations(item).list[0].main.humidity.toString()
                                    pressureText = Games.getGenerations(item).list[0].main.pressure.toString()
                                    speedText = Games.getGenerations(item).list[0].wind.speed.toString()
                                    degText = Games.getGenerations(item).list[0].wind.deg.toString()
                                    gustText = Games.getGenerations(item).list[0].wind.gust.toString()
                                    popText = Games.getGenerations(item).list[0].pop.toString()
                                    snowText = Games.getGenerations(item).list[0].snow.toString()
                                }
                            })
                    }
                }

            }
//            Box (
//                modifier = Modifier
//                    .wrapContentSize(Alignment.TopStart)
//                    .fillMaxHeight()
//                    .fillMaxWidth(),
//            ) {
//                LazyColumn {
//                    item(40) {index ->
//                        tempText =
//                    }
//                }
//            }

        }
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Column {
                Text(text = "日付時間: $dateText")
                Image(
                    painter = rememberAsyncImagePainter(
                        "https://api.openweathermap.org/img/w/$iconImg.png"
                    ),
                    contentDescription = null,
                    modifier = Modifier.size(122.dp)
                )
                Text(text = "　　天気: $mainText")
                Text(text = "　　温度: $tempText")
                Text(text = "体感温度: $feelsText")
                Text(text = "　　湿気: $humidityText")
                Text(text = "　　気圧: $pressureText")
                Text(text = "　　風速: $speedText")
                Text(text = "　　風向: $degText")
                Text(text = "瞬間風速: $gustText")
                Text(text = "降水確率: $popText")
                Text(text = "　積雪量: $snowText")

            }
        }
//        ExposedDropdownMenu(
        Column {
            Button(onClick = { onStartClick(citySelect) }) {
                Text(text = "3時間おき")
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun MainScenePreview() {
    AndroidKadaiTheme {
        Demo_ExposedDropdownMenuBox(Modifier.fillMaxSize())
    }
}