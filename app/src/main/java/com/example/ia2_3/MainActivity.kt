package com.example.ia2_3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.ia2_3.ui.theme.Ia2_3Theme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.TextField
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Ia2_3Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(modifier = Modifier.padding(innerPadding)) {
                        KotlinPracticeScreen()
                    }
                }
            }
        }
    }
}

@Composable
fun KotlinPracticeScreen() {
    // 1. user input
    var inputText by rememberSaveable { mutableStateOf("cat") }
    val animal = when (inputText.lowercase()) {
        "cat" -> "meow"
        "dog" -> "wang"
        "fish" -> "0.o"
        else -> "invalid input, please try 'cat', 'dog', or 'fish'."
    }

    // 2. null input
    var nullableMessage: String? by rememberSaveable { mutableStateOf("?.let")}

    // 3. counter
    var counter by rememberSaveable { mutableStateOf(0) }

    // layout
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // part 1
        Text(text = "Please enter an animal (cat/dog/fish): ", fontSize = 16.sp)
        TextField(
            value = inputText,
            onValueChange = { inputText = it },
            label = { Text("animalName")},
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(16.dp))

        Text(text = "your animal input is: $inputText")
        Text(
            text = "'$animal'",
            fontSize = 18.sp,
            textAlign = TextAlign.Center
        )
        Spacer(Modifier.height(16.dp))

        // part 2
        nullableMessage?.let { message ->
            Text(
                text = "nullable message: $message",
                fontSize = 16.sp,
                color = Color.Gray
            )
        }
        Button(onClick = {
            nullableMessage = if (nullableMessage == null) "message shows" else null
        }) {
            Text(if (nullableMessage == null) "shows message" else "hide message")
        }
        Spacer(Modifier.height(16.dp))

        // part 3
        Text(text = "counter: $counter", fontSize = 24.sp)
        Spacer(Modifier.height(8.dp))
        Button(
            onClick = {
                if (counter < 5) {
                    counter ++
                }
            },
            enabled = counter < 5
        ) {
            Text("counter increment")
        }
        Button(onClick = { counter = 0 }) { // 点击时将 counter 设置为 0
            Text("reset counter")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun KotlinPracticeScreenPreview() {
    Ia2_3Theme {
        KotlinPracticeScreen()
    }
}