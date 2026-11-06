package com.example.pocketquest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge

// The only activity of the app. Android starts it when you tap the app icon.
// It does one job: show our Compose screen. (Like main() in a Java program.)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()   // draw the app behind the status bar, like modern apps do
        setContent {
            PocketQuestTheme {   // colors and fonts: see QuestScreen.kt
                QuestScreen()    // the screen: see QuestScreen.kt
            }
        }
    }
}
