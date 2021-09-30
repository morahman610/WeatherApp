package com.example.weatherapp.ViewModel

import android.location.Location
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weatherapp.Data.WeatherResponse
import com.example.weatherapp.Network.WeatherService
import com.example.weatherapp.Network.WeatherServiceProvider
import retrofit2.Response

class CurrentWeatherViewModel() : ViewModel() {


    private val currentWeatherResponse : MutableLiveData<WeatherResponse> = MutableLiveData()

    suspend fun getCurrentWeather(location: Location) : Response<WeatherResponse> {

        val lat = location.latitude
        val lon = location.longitude
        val currentLocation = WeatherServiceProvider.weatherService.getCurrentWeather(lat, lon)

        return currentLocation
    }

}