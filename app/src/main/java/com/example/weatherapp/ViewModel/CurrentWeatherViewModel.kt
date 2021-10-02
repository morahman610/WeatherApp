package com.example.weatherapp.ViewModel

import android.location.Location
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weatherapp.Data.CurrentWeatherResponse.CurrentWeatherResponse
import com.example.weatherapp.Network.WeatherServiceProvider

class CurrentWeatherViewModel() : ViewModel() {


    suspend fun getCurrentWeather(location: Location) : CurrentWeatherResponse {

        val lat = location.latitude
        val lon = location.longitude
        val currentLocation = WeatherServiceProvider.weatherService.getCurrentWeather(lat, lon)

        return currentLocation
    }

}