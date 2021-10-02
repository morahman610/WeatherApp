package com.example.weatherapp.ViewModel

import android.location.Location
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weatherapp.Data.CurrentWeatherResponse.CurrentWeatherResponse
import com.example.weatherapp.Network.WeatherServiceProvider
import retrofit2.Response

class CurrentWeatherViewModel() : ViewModel() {


    /*This function is to make a network call that retrieves the current weather from the API*/
    suspend fun getCurrentWeather(location: Location) : Response<CurrentWeatherResponse> {

        val lat = location.latitude
        val lon = location.longitude
        val currentLocation = WeatherServiceProvider.weatherService.getCurrentWeather(lat, lon)

        return currentLocation
    }

}