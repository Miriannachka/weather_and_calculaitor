package com.example.weather.api

interface WeatherApi {
    @GET("weather")
    suspend fun getWeather(
        @Query("q") city: String,
        @Query("units") units: String = "metric",
        @Query("appid") apiKey: String = "6b1f7ac93f41b7bb31d051b0cc48d8f5" // Замените на ваш ключ от OpenWeatherMap
    ): WeatherResponse
}