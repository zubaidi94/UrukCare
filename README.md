# UrukCare - Medicine Information App

## Overview
UrukCare is an offline-first Android application built with Kotlin and Jetpack Compose. It allows users to search for medicines, browse by category, view detailed information, and save favorites.

## Prerequisites
- **Android Studio**: Hedgehog (2023.1.1) or newer recommended.
- **JDK**: Java 17 or newer (usually bundled with Android Studio).

## How to Open and Run

1. **Clone/Download** the repository.
2. **Launch Android Studio**.
3. Click **Open** (or **Open an existing Android Studio project**).
4. Select the project folder **UrukCare** (the folder that contains `settings.gradle.kts`) and click **Open**.
5. **Wait for Gradle Sync** to finish (Android Studio will download dependencies).
6. **Select a Device**:
   - Connect a physical Android device via USB (enable USB Debugging), **or**
   - Create/select an Android Emulator (e.g., Pixel 6 API 34) from Device Manager.
7. **Run the App**: Click the green **Play** button (Run `app`).

## Troubleshooting
- **Gradle Sync Failed**: Check your internet connection, then try `File > Sync Project with Gradle Files`.
- **SDK Location**: Android Studio will create `local.properties` automatically on your machine (do **not** commit it to Git).
