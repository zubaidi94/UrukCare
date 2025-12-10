# UrukCare - Medicine Information App

## Overview
UrukCare is an offline-first Android application built with Kotlin and Jetpack Compose. It allows users to search for medicines, browse by category, view detailed information, and save favorites.

## Prerequisites
- **Android Studio**: Hedgehog (2023.1.1) or newer recommended.
- **JDK**: Java 17 or newer (usually bundled with Android Studio).

## How to Open and Run

1.  **Launch Android Studio**.
2.  Click on **Open** (or "Open an existing Android Studio Project").
3.  Navigate to the folder where this project is saved:
    `/Users/zubaidi/.gemini/antigravity/scratch/UrukCare`
4.  Select the `UrukCare` folder and click **Open**.
5.  **Wait for Gradle Sync**: Android Studio will automatically download the necessary dependencies. This might take a few minutes the first time.
    - If you see a "Plugin not found" error, make sure your Android Studio is up to date or click "Update" in the suggestion prompts.
6.  **Select a Device**:
    - Connect a physical Android device via USB (enable USB Debugging).
    - OR create/select an Android Emulator (e.g., Pixel 6 API 34) from the Device Manager.
7.  **Run the App**: Click the green **Play** button (Run 'app') in the toolbar.

## Troubleshooting
- **Gradle Sync Failed**: Check your internet connection. Try `File > Sync Project with Gradle Files`.
- **SDK Location**: Ensure your `local.properties` file points to your Android SDK location (Android Studio usually handles this automatically).
