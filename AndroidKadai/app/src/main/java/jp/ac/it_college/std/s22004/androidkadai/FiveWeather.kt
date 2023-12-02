package jp.ac.it_college.std.s22004.androidkadai

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.navigation.Navigation
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
    var cityText by remember {
        mutableStateOf(cityName)
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
        Column {
            Button(
                onClick = {
                    buttonText = "Button Clicked!"
                    showText = true
                    scope.launch {
//                        resultText = Games.getGenerations(cityName).list[0].toString()
//                        var FiveList = mutableListOf<String>()
//                        for (i in 0 .. 4) {
//                            FiveList.add(Games.getGenerations(cityName).list[i].toString())
//                        }
//                        resultText = FiveList.toString()
                        cityText = cityName
                        dateText = Games.getGenerations(cityName).list[0].dt_txt
                        weatherText = Games.getGenerations(cityName).list[0].weather[0].main
                    }
                },
                enabled = !showText
            ) {
                Text(text = "apiの取得結果")
            }
            Text(text = "city : $cityText")
            Text(text = "$dateText の天気： $weatherText ")
//            scope.launch {
//                resultText = Games.getGenerations()
//            }
//            Text(text = "asdfk")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FiveWeatPreview() {
    AndroidKadaiTheme {
        FiveWeatScene(cityName = "a")
    }
}