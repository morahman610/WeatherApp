package com.example.weatherapp.Network

import com.example.weatherapp.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object WeatherServiceProvider {

    val baseURL : String = "https://api.openweathermap.org/data/2.5/"

    val weatherService : WeatherService

    init {
        val client : OkHttpClient = createOkHttpClient()
        weatherService = provideWeatherRetrofit(client)
    }

    private fun provideWeatherRetrofit(client: OkHttpClient): WeatherService {
        return Retrofit.Builder()
            .client(client)
            .baseUrl(baseURL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WeatherService::class.java)
    }

    private fun createOkHttpClient(): OkHttpClient {
        val requestInterceptor = Interceptor { chain ->
            val url = chain.request()
                .url
                .newBuilder()
                .addQueryParameter("appid", BuildConfig.WEATHER_KEY)
                .build()
            val request = chain.request()
                .newBuilder()
                .url(url)
                .build()

            return@Interceptor chain.proceed(request)
        }

        return OkHttpClient.Builder()
            .addInterceptor(requestInterceptor)
            .build()
    }



}