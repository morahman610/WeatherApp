package com.example.weatherapp.ViewModel

import android.location.Location
import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.weatherapp.Data.CurrentWeatherResponse.CurrentWeatherResponse
import com.example.weatherapp.Data.Forecast.ForecastResponse
import com.example.weatherapp.Network.WeatherServiceProvider

class ForecastViewModel : ViewModel() {

    suspend fun getForecast(location: Location) : ForecastResponse {

        val lat = location.latitude
        val lon = location.longitude
        val forecastResponse = WeatherServiceProvider.weatherService.getForecast(lat, lon, 5)

        Log.d("ForecastViewModel", "${forecastResponse.forcastList}" )

        return forecastResponse
    }
}