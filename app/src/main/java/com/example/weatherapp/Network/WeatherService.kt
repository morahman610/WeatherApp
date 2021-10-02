package com.example.weatherapp.Network

import com.example.weatherapp.Data.CurrentWeatherResponse.CurrentWeatherResponse
import com.example.weatherapp.Data.Forecast.ForecastResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {

    @GET("weather")
    suspend fun getCurrentWeather(
        @Query("lat") lat : Double,
        @Query("lon") lon : Double
    ) : CurrentWeatherResponse

    @GET("forecast")
    suspend fun getForecast(
        @Query("lat") lat : Double,
        @Query("lon") lon : Double,
        @Query("cnt") cnt : Int
    ) : ForecastResponse
}