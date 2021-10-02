package com.example.weatherapp.Data.Forecast


import com.google.gson.annotations.SerializedName

data class ForecastResponse(
    val city: City?,
    val cnt: Int?,
    val cod: String?,
    @SerializedName("list")
    val forcastList: List<Forecast>?,
    val message: Int?
)