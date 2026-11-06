# Pocket Quest - Your Tasks

Pocket Quest already works. You will make it **yours**, test it, and put it on a phone.

Do the **7 required tasks** in order. Each task has a matching `TODO` comment in the code:
in Android Studio press `Ctrl+6` (Windows/Linux) or `Cmd+6` (macOS) to open the **TODO** panel and jump to them.

| # | Task | Where | Time |
|---|------|-------|------|
| 1 | Your app name and your greeting | `strings.xml`, `QuestScreen.kt` | 5 min |
| 2 | Your colors and look | `QuestScreen.kt` | 10 min |
| 3 | Add 4 original quests | `Quests.kt` | 5 min |
| 4 | Never the same quest twice in a row | `Quests.kt` | 15 min |
| 5 | Run it in the emulator and test it | Android Studio | 10 min |
| 6 | Build a debug APK | terminal | 10 min |
| 7 | Install the APK on a phone | your phone | 10 min |

Tip: after each task, press the green **Run ▶** button and look at the result. Small steps = few errors.

---

## Required tasks

### Task 1 - Your name

1. Open `app/src/main/res/values/strings.xml`. Change the text of `app_name` (for example `Anna's Quests`).
   This is the name under the icon on the phone **and** the big title on the screen.
2. Open `app/src/main/java/com/example/pocketquest/QuestScreen.kt`. Find `PLAYER_NAME` and write your name.
3. Run the app. You should see your title and `Hello, <your name>! Ready for a quest?`

Done when: the screen shows your app name and your name.

> If the name has an apostrophe, write it with a backslash in `strings.xml`: `Anna\'s Quests`.

### Task 2 - Your colors

1. In `QuestScreen.kt` find `LightColors`. Change `primary` (buttons and title) and `primaryContainer` (the quest card).
   A color looks like `Color(0xFFE91E63)`: `0xFF` and then 6 hex digits (try [a color picker](https://www.google.com/search?q=color+picker)).
2. Change at least **one more** thing, for example the card corner `RoundedCornerShape(24.dp)` (try `4.dp` and `48.dp`).
3. Run the app. If your phone or emulator is in **dark mode**, also change `DarkColors`, or switch the emulator to light mode.

Done when: the app looks clearly different from the starter.

### Task 3 - New quests

1. Open `Quests.kt`. Add **at least 4 new quests** of your own to the list `quests`.
2. Every quest is text in `"double quotes"` and the line ends with a comma `,`.
3. Run the app. Press **New quest** many times until you see your quests.

Done when: you can see all your new quests on the screen (there are now 12 or more).

### Task 4 - No repeats

Right now `pickNextQuest` chooses a random quest. Sometimes you get **the same quest twice in a row**. Fix it.

1. Open `Quests.kt` and read the function `pickNextQuest`. It already receives `previousQuest`, the quest that is on the screen now.
2. Change **only this function**. The new quest must **never** be equal to `previousQuest`.
3. Think about the very first quest: `previousQuest` is `null` then, so any quest is fine.
4. Think about a list with only one quest. What should happen? (Do not crash, do not loop forever.)

Hints (try without them first):

- `list.filter { ... }` makes a new list with only the items that match. Example: `numbers.filter { it > 10 }`.
- Two strings are compared with `==` in Kotlin (it works like `equals` in Java).
- `list.random()` takes a random item from a list.

Done when: you press **New quest** 50 times and never see the same text twice in a row.

### Task 5 - Run and test

1. Create an emulator and run the app (see `README.md`, "Run the app in the emulator").
2. Go through the **manual test checklist** in `README.md`, column "After the TODOs".
3. Write down any step that does not match. Fix it before you go on.

Done when: every row of the checklist matches.

### Task 6 - Build a debug APK

1. Open the terminal in the project folder.
2. macOS / Linux: `./gradlew assembleDebug`  Windows: `gradlew.bat assembleDebug`
3. Wait for `BUILD SUCCESSFUL`. The file is here: `app/build/outputs/apk/debug/app-debug.apk`

Done when: the file `app-debug.apk` exists. Copy it somewhere safe and rename it `pocketquest-yourname.apk`.

### Task 7 - Install on a phone

1. Android phone: send the APK to the phone and install it (see `README.md`, "Install the APK on your phone").
2. Open the app **without** the cable and the computer. Test 3 quests and the counter.
3. iPhone: an APK **cannot** be installed on an iPhone. Show your app in the emulator instead.

Done when: the app runs on your phone (or you show it in the emulator, if you have an iPhone).

---

## What to hand in

1. Your **source code**: the project folder as a ZIP, **without** the `build` and `.gradle` folders.
2. Your **APK** file.
3. A short **demonstration** (2 minutes, live or a screen recording): your name, your colors, your new quests, press **New quest** 10 times, **Mark completed**, **Reset**.

---

## Optional challenges (not required, for fast students)

Do these only after all 7 tasks are done. Ask your teacher for help if you get stuck.

| Challenge | Idea |
|-----------|------|
| A. Categories | Give every quest a category (Body, Mind, Fun). Change `quests` to a list of `data class Quest(val text: String, val category: String)`. Show the category on the card. |
| B. Favorite button | Add a ☆ / ★ button. Remember the favorite state with `rememberSaveable`. |
| C. Cheer message | When the counter reaches 5, show a special message. |
| D. Skip limit | Allow only 3 **New quest** presses without completing a quest. |
| E. A unit test | Write a test that calls `pickNextQuest` 1000 times and checks that you never get the previous quest. (Needs a `testImplementation` line for JUnit in `app/build.gradle.kts`.) |
