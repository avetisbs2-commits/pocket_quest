package com.example.pocketquest

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

// ---------------------------------------------------------------------------------------------
// Colors
// ---------------------------------------------------------------------------------------------

// TODO 2: Make the app look like YOURS. Change the colors below (format: 0xFF + 6 hex digits, e.g. 0xFFE91E63).
//         "primary" = buttons and title, "primaryContainer" = background of the quest card.
//         Also try a different card corner (RoundedCornerShape below) or spacing (dp numbers).
private val LightColors = lightColorScheme(
    primary = Color(0xFF3F51B5),
    onPrimary = Color.White,
    primaryContainer = Color(0xFFDDE1FF),
    onPrimaryContainer = Color(0xFF111B5E),
    background = Color(0xFFFFFFFF),
    onBackground = Color(0xFF1B1B1F),
)

// The same colors for phones that are in dark mode. Optional to change.
private val DarkColors = darkColorScheme(
    primary = Color(0xFFB7C4FF),
    onPrimary = Color(0xFF16248C),
    primaryContainer = Color(0xFF2C3A9E),
    onPrimaryContainer = Color(0xFFDDE1FF),
    background = Color(0xFF121316),
    onBackground = Color(0xFFE3E1E6),
)

@Composable
fun PocketQuestTheme(content: @Composable () -> Unit) {
    val colors = if (isSystemInDarkTheme()) DarkColors else LightColors
    MaterialTheme(colorScheme = colors, content = content)
}

// ---------------------------------------------------------------------------------------------
// The screen
// ---------------------------------------------------------------------------------------------

// TODO 1 (part B): Put YOUR name here. The greeting on the screen uses it.
//                  (Part A is in app/src/main/res/values/strings.xml: the app name.)
private const val PLAYER_NAME = "Student"

@Composable
fun QuestScreen() {
    // The 3 things the screen remembers. When one changes, Compose redraws the screen by itself.
    // rememberSaveable keeps them when you rotate the phone. They are lost when the app is closed.
    var currentQuest by rememberSaveable { mutableStateOf<String?>(null) }  // null = no quest yet
    var isCompleted by rememberSaveable { mutableStateOf(false) }           // is this quest done?
    var completedCount by rememberSaveable { mutableStateOf(0) }            // how many quests done

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background,
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .safeDrawingPadding()                  // keep away from the status bar and the camera
                .verticalScroll(rememberScrollState()) // lets small screens scroll
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            // Title and greeting
            Text(
                text = stringResource(R.string.app_name),
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary,
            )
            Text(
                text = "Hello, $PLAYER_NAME! Ready for a quest?",
                style = MaterialTheme.typography.titleMedium,
            )

            Spacer(modifier = Modifier.height(8.dp))

            // The card with the current quest
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(24.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    contentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                ),
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                ) {
                    Text(
                        text = "Current quest",
                        style = MaterialTheme.typography.labelLarge,
                    )
                    Text(
                        text = currentQuest ?: "Press \"New quest\" to get your first quest!",
                        style = MaterialTheme.typography.headlineSmall,
                        textAlign = TextAlign.Center,
                    )
                    if (isCompleted) {
                        Text(
                            text = "Completed!",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                        )
                    }
                }
            }

            // The counter
            Text(
                text = "Quests completed: $completedCount",
                style = MaterialTheme.typography.titleLarge,
            )

            // The buttons
            Button(
                onClick = {
                    currentQuest = pickNextQuest(currentQuest)
                    isCompleted = false
                },
                modifier = Modifier.fillMaxWidth(),
            ) {
                Text("New quest")
            }

            Button(
                onClick = {
                    isCompleted = true
                    completedCount = completedCount + 1
                },
                enabled = currentQuest != null && !isCompleted,  // only once for each quest
                modifier = Modifier.fillMaxWidth(),
            ) {
                Text("Mark completed")
            }

            OutlinedButton(
                onClick = {
                    currentQuest = null
                    isCompleted = false
                    completedCount = 0
                },
                modifier = Modifier.fillMaxWidth(),
            ) {
                Text("Reset")
            }
        }
    }
}

// The preview in Android Studio (the "Split" or "Design" view). It does not run on a phone.
@Preview(showBackground = true)
@Composable
private fun QuestScreenPreview() {
    PocketQuestTheme {
        QuestScreen()
    }
}
