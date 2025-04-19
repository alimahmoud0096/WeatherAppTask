# Weather App

A modern Weather App built with **Jetpack Compose**, following **Clean Architecture** principles, and using a **modularized architecture**. The app fetches real-time weather data from the **OpenWeather API**.

## 🌟 Features

- **Jetpack Compose UI** for a modern and declarative user experience.
- **Clean Architecture** with separation of concerns (UI, Domain, Data layers).
- **Modularization** with distinct features:
  - **Home**: Displays current weather and forecast.
  - **Search**: Allows users to search for weather in different locations.
  - **Settings**: Customize app preferences.
- **State Management** using **ViewModel & Flow**.
- **Dependency Injection** with **Hilt**.
- **Coroutines & Flow** for asynchronous data handling.
- **MVVM Pattern** for clear separation of concerns.

## 📦 Modular Structure

The app is structured into multiple feature modules for scalability:

```
weatherApp/
│── app/                # Main application module
│── core/               # Core utilities and common components
│── feature_home/       # Home screen module
│── feature_search/     # Search screen module
│── feature_settings/   # Settings screen module
```

## 🔧 Setup Instructions

1. **Clone the repository**:
   ```sh
   git clone https://github.com/alimahmoud0096/WeatherAppTask.git 
   cd WeatherAppTask
   ```

2. **Add your OpenWeather API Key**:
   - Create a `local.properties` file in the root Android project (if not exists).
   - Add the following line with your API key:
     ```properties
     API_KEY=your_openweather_api_key
     ```

3. **Sync the project** in Android Studio.

4. **Run the app**:
   ```sh
   ./gradlew installDebug
   ```

## 🚀 Technologies Used

- **Jetpack Compose** – Modern UI toolkit
- **Kotlin** – Primary programming language
- **Hilt** – Dependency Injection
- **Retrofit** – Network API calls
- **Coroutines & Flow** – Async operations
- **DataStore** – Persistent storage
- **Navigation Component** – Navigation between screens
- **JUnit & Mockk** – Unit testing

## ⚠️ Important Notes
- You **must** add `API_KEY="your_openweather_api_key"` in `local.properties`, or the app will fail to fetch weather data.
- The API key is **not** included in the project for security reasons.

