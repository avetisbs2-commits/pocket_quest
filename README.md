# Pocket Quest

Pocket Quest is a small Android app written in **Kotlin** with **Jetpack Compose**.
It gives you a little real-life quest ("Take a five-minute walk"). You do the quest, press **Mark completed**, and the counter goes up.

You already know Java and OOP. In this project you learn how to **change a Kotlin app, run it in an emulator, build an APK and install it on your own phone**. It takes about 60-90 minutes after the tools are installed.

Your tasks are in **[TODO.md](TODO.md)**. This file explains the tools and the steps.

```text
Screen:   Pocket Quest                    <- title (your app name)
          Hello, Student! Ready for a quest?
          +---------------------------+
          |  Current quest            |
          |  Take a five-minute walk  |   <- the quest card
          +---------------------------+
          Quests completed: 0
          [ New quest ]  [ Mark completed ]  [ Reset ]
```

The app has **no internet, no database, no accounts and asks for no permissions**.
The counter is kept while the app is open (also when you rotate the phone) and starts again from 0 when you close the app.

---

## 1. What you need

| Tool | Notes |
|------|-------|
| **Android Studio** (stable) | Download from <https://developer.android.com/studio>. This project was prepared for **Android Studio Rabbit 2 (2026.2.2)** or newer. |
| About **10 GB** free disk space | Android Studio + SDK + emulator image + Gradle downloads |
| **Internet** for the first start | Studio and Gradle download files. Later it works offline. |
| macOS, Windows or Linux | 8 GB RAM is the minimum, 16 GB is comfortable for the emulator |
| An **Android phone** (optional) | For Task 7. No Android phone? Use the emulator. iPhone: see section 6. |

### Install Android Studio and the SDK parts

1. Install Android Studio and start it. In the **Setup Wizard** choose **Standard** and accept the downloads.
2. Open the **SDK Manager**: `More Actions > SDK Manager` (Welcome screen) or `Tools > SDK Manager`.
3. Tab **SDK Platforms**: tick **Android API 37** (the newest one with a `37` in it is fine).
4. Tab **SDK Tools**: tick **Android SDK Build-Tools**, **Android SDK Platform-Tools**, **Android Emulator**. Click **Apply**.
5. Accept the license agreements when Studio asks. (You must accept them yourself.)

If a part is missing, Gradle tries to download it on the first build and prints what it needs.

---

## 2. Open the project and let Gradle sync

1. Start Android Studio. Click **Open** (Welcome screen) or `File > Open`.
2. Choose the folder **`pocket-quest`** - the folder that contains `settings.gradle.kts` and `gradlew`.
   Not the `app` folder, and not the folder above.
3. If Studio asks *"Trust this project?"* click **Trust Project**.
4. Wait. Studio runs a **Gradle sync**. Look at the bar at the bottom: *"Gradle: ..."*.

**What is Gradle?** It is the build tool (like Maven in Java). It reads the files `build.gradle.kts`, downloads the libraries (Compose, Material 3...), compiles the Kotlin code and makes the APK.
**What is a sync?** Studio asks Gradle: *"What does this project contain?"* so it can show code completion and the Run button.

- The **first sync takes 3-10 minutes** and downloads some hundreds of MB. Be patient.
- The sync is OK when the **Build** panel says `BUILD SUCCESSFUL` / the Run button is active and there are no red errors.
- Did you change a `.gradle.kts` file? Press `File > Sync Project with Gradle Files` (the elephant icon).
- Studio may show a yellow banner *"Update the Android Gradle plugin?"*. Do **not** click it. Leave the versions as they are.

### Check the Gradle JDK

Gradle needs a JDK (Java Development Kit), version **17 or newer**. Android Studio brings its own, and this is the best choice.

1. Open the settings: Windows/Linux `File > Settings`, macOS `Android Studio > Settings`.
2. Go to `Build, Execution, Deployment > Build Tools > Gradle`.
3. At **Gradle JDK** choose the one named **`jbr-...`** or **Embedded JDK** (the JetBrains Runtime of Android Studio, version 17 or newer).
4. Click **OK**, then sync again.

---

## 3. Run the app in the emulator

An emulator is a virtual phone on your computer.

1. Open the **Device Manager**: `Tools > Device Manager` (or the phone icon on the right side).
2. Click **+ > Create Virtual Device**. Choose a phone, for example **Pixel 8**, click **Next**.
3. Choose a **system image** with the newest Android version (API 37 or newer), click **Download** next to it (about 1-2 GB), then **Next > Finish**.
   - Apple Silicon Mac (M1-M4): choose an **arm64** image. Intel / Windows / Linux: choose **x86_64**.
   - Windows: virtualization must be on in the BIOS (Intel VT-x / AMD-V). If Studio shows a message about *hardware acceleration*, follow it.
4. At the top of Studio, choose your new device in the device list and press the green **Run ▶** button.
5. The emulator starts (the first start can take a minute) and the app opens by itself.

You can rotate the emulator, press the buttons with the mouse, and switch dark mode in the quick settings of the virtual phone.

---

## 4. Build a debug APK

An **APK** is the installable file of an Android app. A **debug APK** is signed automatically with a test key, so you can install it on your own phone. (It is not for Google Play.)

Open a terminal **in the project folder**. The easiest way: in Studio click the **Terminal** tab at the bottom (`View > Tool Windows > Terminal`). It already starts in the right folder.

**macOS and Linux**

```bash
./gradlew assembleDebug
```

If you get `Permission denied`, run `chmod +x gradlew` once and try again.

**Windows** (Command Prompt, or PowerShell with `.\` in front)

```bat
gradlew.bat assembleDebug
```

At the end you must see `BUILD SUCCESSFUL`. The APK is here:

```text
app/build/outputs/apk/debug/app-debug.apk
```

(In full: `pocket-quest/app/build/outputs/apk/debug/app-debug.apk`. On Windows the slashes are `\`.)

Useful extra commands: `./gradlew clean` deletes the old build output. `./gradlew assembleDebug --stacktrace` gives more details after an error.

If the terminal says it cannot find Java or the Android SDK, see section 7 (Common problems).

You can also use the menu in Studio (`Build > Generate App Bundles or APKs > Generate APKs`; names change a little between versions). The Gradle command always works.

---

## 5. Install the APK on your Android phone

**Step 1 - get the file to the phone.** Choose one way:

- **USB cable:** connect the phone, pull down the notification and choose *File transfer* mode. Copy the APK into the phone's `Download` folder.
- **Cloud or chat:** send it to yourself (Google Drive, e-mail, Telegram...) and download it on the phone.

**Step 2 - allow the install.** Android blocks apps from outside Google Play by default:

1. On the phone, open the APK (with the **Files** app or from the download notification).
2. Android asks: *"For your security, your phone is not allowed to install unknown apps from this source."* Tap **Settings**, then turn on **Allow from this source** for the app you used (Files, Chrome, Drive...).
3. Go back and tap **Install**. If *Play Protect* says the app is not scanned, tap **Install anyway** / **More details > Install anyway**. It is your own app.
4. Tap **Open**. Pocket Quest is now on your phone like any other app.

**Alternative with a cable and `adb`** (for the curious): turn on *Developer options > USB debugging* on the phone, connect it, and run

```bash
adb install -r app/build/outputs/apk/debug/app-debug.apk
```

`adb` is in the SDK folder `platform-tools` (macOS `~/Library/Android/sdk/platform-tools`, Windows `%LOCALAPPDATA%\Android\Sdk\platform-tools`, Linux `~/Android/Sdk/platform-tools`). The command `adb devices` must show your phone first. After the change of the app, install again with `-r` (replace).

---

## 6. iPhone students

An **APK cannot be installed on an iPhone**. iPhones run iOS apps, which are built in a different way with different tools.
You do all tasks in the emulator. For Task 7 you **show the app running in the emulator** (live or a screen recording) instead of installing it. You still build the APK in Task 6.

---

## 7. Common problems and fixes

| Problem | What to do |
|---------|-----------|
| `SDK location not found` / `ANDROID_HOME` | Open the project once in Android Studio: it creates the file `local.properties` with your SDK path. Or set the `ANDROID_HOME` environment variable to your SDK folder. Do not share `local.properties`. |
| Sync fails: *"Could not resolve ..."*, *"Connection timed out"* | No internet or a school network/firewall blocks Google's servers. Try another Wi-Fi (a phone hotspot) and press *Sync* again. |
| `Unsupported class file major version` / *"requires Java 17"* / Java version errors | Gradle JDK is wrong. Set **Gradle JDK** to the Android Studio one (section 2). In a terminal outside Studio, install JDK 17 or newer and set `JAVA_HOME`. |
| *"Failed to install the following Android SDK packages... license"* | The SDK license is not accepted. Accept it in the SDK Manager (section 1). |
| *"Android Gradle plugin requires Android Studio ... or newer"* / *"incompatible with this version of Android Studio"* | Your Android Studio is too old. Update it (`Help > Check for Updates`) to Rabbit 2 (2026.2.2) or newer. |
| Studio offers to *upgrade the Android Gradle plugin* | Click *Remind me later*. Do not upgrade; the other versions were chosen to fit together. |
| Red code but the build works / yellow warnings, for example *"Parameter 'previousQuest' is never used"* | Warnings are OK. The starter has that warning on purpose; Task 4 removes it. Red errors are real. |
| `Unresolved reference: ...` in red | A missing `import`, or a spelling mistake. Put the cursor on the red word and press `Alt+Enter` (`Option+Enter` on macOS) to let Studio add the import. |
| The emulator is very slow or does not start | Close other programs. Check hardware acceleration (section 3). Make a new emulator with a newer image, or use your real phone instead (USB debugging, section 5). |
| `Permission denied` on `./gradlew` | Run `chmod +x gradlew`. |
| The terminal says `'gradlew.bat' is not recognized` | You are in the wrong folder. Go into the folder with the file `gradlew.bat` (`cd pocket-quest`). |
| *"Gradle build daemon disappeared"* / out of memory | Close other programs. Try again. You can lower or raise `org.gradle.jvmargs` in `gradle.properties`. |
| The phone says *"App not installed"* | Remove an older Pocket Quest from the phone first (long press the icon > App info > Uninstall), then install again. |
| *"There was a problem parsing the package"* | The APK was damaged during transfer. Copy it again. Check the file size is the same on both sides. |
| The first build is very slow | Normal. Gradle downloads libraries once. Next builds take seconds. |
| The wrapper cannot download Gradle (`services.gradle.org`) | Network problem, see "Could not resolve" above. |

If you are stuck for more than 10 minutes: copy the **first** red error message and ask your teacher.

---

## 8. Manual test checklist

Test on the emulator or on the phone. "Starter" = the project before your changes. "After the TODOs" = when Tasks 1-4 are done.

| # | What you do | Starter: expected | After the TODOs: expected |
|---|-------------|-------------------|---------------------------|
| 1 | Look at the home screen of the phone | Icon with a yellow star, name **Pocket Quest** | The name you chose (Task 1) |
| 2 | Open the app | Title **Pocket Quest** and `Hello, Student! Ready for a quest?` | Your title and your name |
| 3 | Look at the first screen | Card says `Press "New quest" to get your first quest!`. Counter `Quests completed: 0`. **Mark completed** is gray (disabled) | The same, in your colors (Task 2) |
| 4 | Press **New quest** | One of the 8 starter quests appears. **Mark completed** is now active | One of your 12+ quests appears |
| 5 | Press **Mark completed** | `Completed!` appears on the card. Counter is `1`. **Mark completed** is gray again | The same |
| 6 | Try to press **Mark completed** again | Nothing happens (button is disabled). Counter stays `1` | The same |
| 7 | Press **New quest** | New quest, `Completed!` disappears, counter stays `1` | The same |
| 8 | Complete 3 quests in total | Counter shows `3` | The same |
| 9 | Press **New quest** 30 times and watch the text | **The same quest sometimes appears twice in a row** (this is the known problem of the starter) | **Never** the same quest twice in a row (Task 4) |
| 10 | Press **New quest** about 40 times | You only see the 8 starter quests | You also see your 4 new quests (Task 3) |
| 11 | Rotate the phone / emulator | Quest, `Completed!` and counter stay | The same |
| 12 | Press **Reset** | Counter `0`, `Completed!` gone, card shows the first-quest text again, **Mark completed** is gray | The same |
| 13 | Close the app completely (swipe it away) and open it again | Starts like step 3 (the counter is not saved on purpose) | The same |
| 14 | Switch the phone to dark mode | App uses the dark colors | The same (your dark colors only if you changed them) |
| 15 | Installed APK: look at *App info > Permissions* | *No permissions requested* | The same |
| 16 | Installed APK: turn on airplane mode, use the app | Works exactly the same | The same |

---

## 9. Versions used

Chosen on **2026-11-06** from the official Android and Gradle documentation. All of them are in `gradle/libs.versions.toml`, `app/build.gradle.kts` and `gradle/wrapper/gradle-wrapper.properties`.

| Part | Version | Why |
|------|---------|-----|
| Android Gradle plugin (AGP) | **9.5.1** | Newest stable; supported by Android Studio Rabbit 2 (2026.2.2) ("AGP 7.1-9.5"). Kotlin support is built in. |
| Gradle (wrapper) | **9.6.0** | AGP 9.5 needs Gradle 9.6.0 or newer |
| JDK to run Gradle | **17 or newer** | AGP 9.5 minimum is 17; Android Studio's own JDK is used |
| Java/Kotlin bytecode target | 17 | `compileOptions` in `app/build.gradle.kts` |
| Kotlin + Compose compiler plugin | **2.4.10** | Compose compiler plugin has the same number as Kotlin |
| Jetpack Compose BOM | **2026.09.00** | "Latest Compose BOM" in the official Compose setup page |
| Activity Compose | 1.13.0 | From the same official page |
| compileSdk / targetSdk | **37** | Compose BOM 2026.09 needs compileSdk 37 and AGP 9; AGP 9.1.1 or newer supports API 37 |
| minSdk | 26 (Android 8.0) | Works on practically every phone students own |
| SDK Build-Tools | 36.0.0 | Default of AGP 9.x; Gradle downloads it if it is missing |

Official pages: [AGP and Gradle versions](https://developer.android.com/build/releases/about-agp), [AGP 9.5 release notes](https://developer.android.com/build/releases/gradle-plugin), [Built-in Kotlin](https://developer.android.com/build/migrate-to-built-in-kotlin), [Compose setup](https://developer.android.com/develop/ui/compose/setup-compose-dependencies-and-compiler), [Android Studio releases](https://developer.android.com/studio/releases).

---

## 10. Project map

```text
pocket-quest/
├── README.md, TODO.md, TEACHER_NOTES.md
├── settings.gradle.kts, build.gradle.kts, gradle.properties   Gradle settings (you do not change them)
├── gradlew, gradlew.bat, gradle/                              the Gradle Wrapper: the same Gradle on every computer
│   └── libs.versions.toml                                     all version numbers
└── app/
    ├── build.gradle.kts                                       the app's libraries and SDK versions
    └── src/main/
        ├── AndroidManifest.xml                                what the app is (no permissions)
        ├── res/values/strings.xml                             the app name  (Task 1)
        └── java/com/example/pocketquest/
            ├── MainActivity.kt                                starts the screen
            ├── QuestScreen.kt                                 the screen and the colors  (Tasks 1, 2)
            └── Quests.kt                                      the quest list and the chooser  (Tasks 3, 4)
```

Java readers: in Kotlin there is no `new`, no `;` at the end of lines, `val` is like a `final` variable and `var` is a normal variable, `String?` means "a String that may be `null`", and `"Hello, $name"` puts a variable inside a string.
