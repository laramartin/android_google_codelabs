package com.example.basicscodelab

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.basicscodelab.ui.theme.BasicsCodelabTheme

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
//      MyApp { Greeting("Hello Android!") }
      MyApp { MyScreenContent() }
    }
  }
}

@Composable
fun MyApp(content: @Composable () -> Unit) {
  BasicsCodelabTheme {
    // A surface container using the 'background' color from the theme
    Surface(color = MaterialTheme.colors.background) {
      content()
    }
  }
}

@Composable
private fun Greeting(name: String) {
  Surface(color = Color.Cyan) {
    Text(text = "Hello $name!", modifier = Modifier.padding(32.dp))
  }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
  BasicsCodelabTheme {
    Greeting("Android")
  }
}

@Composable
fun MyScreenContent(names: List<String> = List(size = 1000) { "Android #$it"}) {
  val counterState = remember { mutableStateOf(0) }

  Column(modifier = Modifier.fillMaxHeight()) {
    NameList(names, Modifier.weight(1f))
    Counter(
      count = counterState.value,
      updateCount = { newCount ->
        counterState.value = newCount
      }
    )
  }
}

@Composable
fun NameList(names: List<String>, modifier: Modifier = Modifier) {
  LazyColumn(modifier = modifier) {
    items(names) { name ->
      Greeting(name = name)
      Divider(color = Color.Black)
    }
  }
}

@Composable
fun Counter(count: Int, updateCount: (Int) -> Unit) {
  Button(onClick = { updateCount(count + 1) }) {
    Text("I've been clicked $count times")
  }
}
