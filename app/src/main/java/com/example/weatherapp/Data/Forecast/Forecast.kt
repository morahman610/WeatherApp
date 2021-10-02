package com.example.weatherapp.Data.Forecast


import com.google.gson.annotations.SerializedName

data class Forecast (
    val clouds: Clouds?,
    val dt: Int?,
    @SerializedName("dt_txt")
    val dtTxt: String?,
    val main: Main?,
    val pop: Double?,
    val rain: Rain?,
    val sys: Sys?,
    val visibility: Int?,
    val weather: List<Weather>?,
    val wind: Wind?
)