package jp.ac.it_college.std.s22004.androidkadai

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import jp.ac.it_college.std.s22004.androidkadai.api.Games
import jp.ac.it_college.std.s22004.androidkadai.ui.theme.AndroidKadaiTheme
import kotlinx.coroutines.launch

@Composable
fun FiveWeatScene(modifier: Modifier = Modifier, cityName: String) {
    Surface(modifier) {
        FiveWeather(cityName)
    }
}

@Composable
fun FiveWeather(cityName: String) {
    var resultText by remember { mutableStateOf(listOf("", "", "")) }
    var result2Text by remember { mutableStateOf(listOf("", "", "")) }

//    for (i in 0 until 20) {
//        FiveList.add(Games.getGenerations(cityName).list[i].dt_txt)
//    }

    var cityText by remember {
        mutableStateOf(cityName)
    }
    var kakuText by remember {
        mutableStateOf("都道府県確認")
    }
    var dateText by remember {
        mutableStateOf("日付時間")
    }
    var weatherText by remember {
        mutableStateOf("天気")
    }
    var buttonText by remember { mutableStateOf("Click me!") }
    var showText by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()

    Surface {
        Column() {
            Button(
                onClick = {
                    buttonText = "Button Clicked!"
                    showText = true
                    scope.launch {
//                        resultText = Games.getGenerations(cityName).list[0].toString()
                        val FiveDateList = mutableListOf<String>()
                        val FiveWeatList = mutableListOf<String>()
                        for (i in 0 until 3) {
                            FiveDateList.add(Games.getGenerations(cityName).list[i].dt_txt)
                            FiveWeatList.add(Games.getGenerations(cityName).list[i].weather[0].main)
                        }
                        resultText = FiveDateList
                        result2Text = FiveWeatList
//                        cityText = cityName
//                        kakuText = Games.getGenerations(cityName).city.name
//                        dateText = Games.getGenerations(cityName).list[0].dt_txt
//                        weatherText = Games.getGenerations(cityName).list[0].weather[0].main
                    }
                },
                enabled = !showText
            ) {
                Text(text = "apiの取得結果")
            }
//            Text(text = "cityText : $cityText, 確認 : $kakuText")
//            Text(text = "$dateText の天気： $weatherText ")
//            for (i in 1..18) {

            Text(text = "3時間ごとの天気    $cityText")
//            Surface(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(8.dp)
//            ) {
                for (i in 0..2) {
                    Text(text = "${resultText[i]} : ${result2Text[i]}")
                }
//            }

//            Text(text = result2Text)
//            }
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