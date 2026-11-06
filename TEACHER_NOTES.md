# Pocket Quest - Teacher Notes

For the teacher only. Remove this file from the students' copy if you prefer.

## Goal

Java students meet Kotlin, Jetpack Compose and Android Studio in one small, finished app. They change it in small steps, test it in an emulator, build a debug APK and install it on their own phone. Time: **60-90 minutes after the tools are installed**.

The full solution of Task 4 is **not** in this repository. The starter chooses the quest with plain `quests.random()` on purpose.

## Before the lesson (important)

Do this the day before. It is the part that fails most often.

- Students install **Android Studio (stable, Rabbit 2 / 2026.2.2 or newer)** and finish the Setup Wizard at home or on a fast network. The first Gradle sync downloads several hundred MB; 25 students on one Wi-Fi will be slow.
- Ask everyone to open the project and wait for the first successful sync **before** the lesson. Also build once (`./gradlew assembleDebug`), so Gradle's cache is warm.
- Everyone creates one emulator image beforehand (1-2 GB). Weak laptops: use a real phone with *USB debugging* instead.
- School networks sometimes block `dl.google.com`, `maven.google.com`, `services.gradle.org`. Test from a student account.
- Windows: antivirus can make Gradle very slow. Ask IT to exclude the `.gradle` folder if possible.
- Ask who has an **iPhone**. They cannot install an APK; they show the emulator (Task 7).
- Phones: tell students which Android version is needed (8.0 or newer) and that "Install unknown apps" must be allowed by them.

## Suggested lesson schedule (90 minutes)

| Time | Part | What happens |
|------|------|--------------|
| 0-10 | Intro | Show the finished app on a projector. Explain: Kotlin runs on the JVM, Compose describes the screen with functions. Show the project map in `README.md`, section 10. |
| 10-20 | Read the code together | Open `Quests.kt`, then `QuestScreen.kt`, then `MainActivity.kt` (small to big). Run the starter in the emulator. |
| 20-30 | Tasks 1-2 | Name, greeting, colors. Fast, visible success. |
| 30-40 | Task 3 | New quests. Explain `listOf`, trailing comma, immutable list. |
| 40-60 | Task 4 | The logic task. Let students find the bug first by pressing **New quest** until a repeat happens. Give hints in this order: (1) use `previousQuest`, (2) `filter`, (3) the one-quest edge case. |
| 60-70 | Task 5 | Manual test checklist (`README.md`, section 8). |
| 70-80 | Task 6 | `assembleDebug`, find the APK. |
| 80-90 | Task 7 | Install on phones, short demos. iPhone students demo the emulator. |

**Two shorter lessons (2 x 45 minutes):** Lesson 1 = intro + Tasks 1-4. Lesson 2 = Tasks 5-7 + demos. Students who are slow do the Tasks 6-7 as homework.

**Fast students:** optional challenges at the end of `TODO.md` (categories, favorite button, cheer message...).

## Discussion questions: Kotlin and Java

1. In Java you write `final String name = "Anna";`. What is the Kotlin line? What is the difference between `val` and `var`?
2. `var currentQuest: String? = null`. What does the `?` mean? How does Kotlin avoid `NullPointerException`? Where does the code handle `null` (`currentQuest ?: "..."`)?
3. `"Hello, $PLAYER_NAME!"` vs `"Hello, " + name + "!"` in Java. Which one is easier to read and why?
4. `quests.random()` and `quests.filter { ... }`: these look like Java Streams. What is a lambda here, and what is it in Java? What is `it`?
5. `fun pickNextQuest(...)` is outside any class. In Java every function lives in a class. Where does the Kotlin compiler put it? (A static method in a class named `QuestsKt`.)
6. `listOf(...)` returns a read-only list. Which Java type is similar to `List.of(...)`? What would you use for a list that grows?
7. There is no `new` in `Modifier.fillMaxSize()` or `Column(...)`. What do you gain? What is lost?
8. In Java you would write a `Button` and a `setOnClickListener(new View.OnClickListener() {...})`. In Compose: `Button(onClick = { ... })`. What is the difference between an anonymous class and a lambda?
9. `var isCompleted by rememberSaveable { mutableStateOf(false) }`: when the value changes, who redraws the screen? Why is a normal `var` in a function not enough? (Compose calls the function again; local variables would start from the first value.)
10. Compose describes **what** the screen looks like for the current state. How is this different from Swing or JavaFX, where you change components?
11. Task 4: what are the possible tests for `pickNextQuest`? How do you test a random function?
12. An APK is a ZIP file. What is inside? (Compiled code in `.dex` files, resources, the manifest.) How is that different from a JAR?

## Assessment rubric (100 points)

| Criterion | Points | Full marks when |
|-----------|--------|-----------------|
| Task 1 - Personalization | 10 | App name in `strings.xml` and the name in the greeting are changed and visible |
| Task 2 - Look | 10 | Primary colors changed and at least one more visual change (shape, spacing, text style) |
| Task 3 - New quests | 15 | At least 4 new, original, sensible quests (not copies of the starter quests, no duplicates) that appear in the app |
| Task 4 - No repeat | 25 | Never the same quest twice in a row, also not after many presses; first quest (`null`) works; **no crash and no endless loop** with a one-quest list. Partial points (about 12) for "works most of the time" or a solution that loops until it gets another quest without protection for one quest |
| Task 5 - Testing | 10 | Checklist filled in honestly; found problems are mentioned |
| Task 6 - APK | 15 | A working `app-debug.apk` that was built from **their** modified source (shows their name and quests) |
| Task 7 - Install and demo | 15 | Runs on a phone (or in the emulator for iPhone students); the demonstration shows name, colors, new quests, the no-repeat behavior, **Mark completed** and **Reset** |
| **Total** | **100** | |

Optional challenges: up to **+10** bonus points, at your choice.

Grade bands you can use: 90-100 excellent, 75-89 good, 60-74 satisfactory, below 60 needs repeat.

### What students submit

1. Source code: the project folder as a ZIP **without** `build`, `.gradle`, `.idea` and `local.properties`.
2. The APK file.
3. A short demonstration (live or a screen recording of about 2 minutes).

### Quick teacher checks

- Task 4: open `Quests.kt`. Look for the use of `previousQuest`. Install the APK or run the project and press **New quest** about 50 times.
- One-quest edge case: temporarily change the list to one quest and press **New quest**. The app must not freeze.
- The APK is really theirs: install it and look at the name and the quests. You can also list the files of the APK with any ZIP tool.
- Plagiarism: identical APKs or identical new quests in several submissions.

## Known cautions

- **Versions are new and fixed.** They are listed in `README.md`, section 9. Students must not accept the "upgrade AGP" suggestions of Android Studio. If a student has an older Android Studio, the error says that the plugin needs a newer Studio; the fix is to update Studio.
- The project uses the **Gradle Wrapper** (`gradlew`). It downloads Gradle 9.6.0 on the first run. No Gradle installation is needed.
- The unused-parameter warning in `pickNextQuest` is expected in the starter.
- The counter is **not saved** when the app is closed. This is on purpose (no storage). It is also a good discussion point for a follow-up lesson (`SharedPreferences` / DataStore).
- The debug APK is signed with a debug key, which is fine for sideloading. It cannot be published on Google Play.
- Android phones show an *"unknown source"* and sometimes a *Play Protect* warning. This is normal for a self-built APK.
