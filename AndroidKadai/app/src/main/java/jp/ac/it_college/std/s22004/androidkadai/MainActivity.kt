package jp.ac.it_college.std.s22004.androidkadai

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import jp.ac.it_college.std.s22004.androidkadai.api.Games
import jp.ac.it_college.std.s22004.androidkadai.ui.theme.AndroidKadaiTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AndroidKadaiTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScene("東京")
                }
            }
        }
    }
}

@Composable
fun MainScene(name: String, modifier: Modifier = Modifier) {
    var resultText by remember {
        mutableStateOf("結果表示")
    }
    // Composable な関数内でコルーチンを使用するためのコルーチンスコープ
    val scope = rememberCoroutineScope()
    val context = LocalContext.current

    Surface(modifier) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ElevatedButton(
                modifier = Modifier.padding(vertical = 8.dp),
                onClick = {
                    scope.launch {
                        resultText = Games.getGenerations(name).toString()
//                        initializeDatabase(context)
                    }
                }
            ) {
                Text(text = "API Test")
            }
            Surface(
                color = MaterialTheme.colorScheme.secondary,
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f)
            ) {
                Text(text = resultText)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainScenePreview() {
    AndroidKadaiTheme {
        MainScene("東京", Modifier.fillMaxSize())
//        Dropdown()
    }
}

@Preview(showBackground = true)
@Composable
fun DropdownPreview() {
    AndroidKadaiTheme {
//        Dropdown()
//        MainScene("東京", Modifier.fillMaxSize())

    }
}