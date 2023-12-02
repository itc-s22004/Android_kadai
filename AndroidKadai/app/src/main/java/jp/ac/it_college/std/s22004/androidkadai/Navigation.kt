package jp.ac.it_college.std.s22004.androidkadai

import android.content.Context
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


object Destinations {
    const val StartWeat = "start"
    const val Five = "five"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeatNavigation(
    navController: NavHostController = rememberNavController(),
) {
    var startText by remember { mutableStateOf("") }
    var citySelect by remember { mutableStateOf("") }

    val context = LocalContext.current
    val scope = rememberCoroutineScope()


    Scaffold(
        topBar = {
            TopAppBar(title = {
                Text(text = startText)
            })
        }
    ) {
        NavHost(
            navController = navController,
            startDestination = Destinations.StartWeat,
            modifier = Modifier.padding(it)
        ) {
            composable(Destinations.StartWeat) {
                Demo_ExposedDropdownMenuBox(
                    onStartClick = {
                        citySelect = it

//                        navController.navigate("${Destinations.Five}/$")
                        navController.navigate(Destinations.Five)
                    }
                )

            }
            composable(Destinations.Five) {
//                FiveWeather(item)
//                FiveWeather(citySelect)
                FiveWeatScene(cityName = citySelect)
            }

        }
    }

}

suspend fun cityNameSelect(context: Context, cityName: String) {
    withContext(Dispatchers.IO) {

    }
}