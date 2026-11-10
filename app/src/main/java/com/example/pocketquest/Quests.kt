package com.example.pocketquest

// The list of quests and the function that chooses the next one.
// No Android code here: it is plain Kotlin, almost like Java.

// TODO 3: Add at least 4 ORIGINAL quests to this list (your own ideas, not copies of the ones below).
//         Keep every item between double quotes and end each line with a comma.
val quests: List<String> = listOf(
    "Take a five-minute walk",
    "Read five pages of a book",
    "Explain one Java concept out loud",
    "Stretch for two minutes",
    "Drink a glass of water",
    "Tidy your desk for three minutes",
    "Write down one thing you learned today",
    "Send a kind message to a friend",
    "Learn one new English word",
    "Write a short journal entry",
    "Listen to a new song",
    "Organize your phone's home screen",
)

// Chooses the next quest to show.
// previousQuest is the quest that is on the screen now (null before the first quest).
//
// TODO 4: Right now this function picks a RANDOM quest. Sometimes you get the SAME quest twice in a row.
//         Change the function so the new quest is NEVER equal to previousQuest.
//         Hints: look at filter { }, or use a loop. Use the previousQuest parameter (it is not used yet).
//         Think: what happens if the list has only one quest?
fun pickNextQuest(previousQuest: String?): String {
    val availableQuests = quests.filter { it != previousQuest }

    return if (availableQuests.isEmpty()) {
        quests.random()
    } else {
        availableQuests.random()
    }
}

