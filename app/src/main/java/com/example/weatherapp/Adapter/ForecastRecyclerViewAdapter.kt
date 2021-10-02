package com.example.weatherapp.Adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.weatherapp.Data.Forecast.Forecast
import com.example.weatherapp.R
import com.example.weatherapp.databinding.FiveDayForecastItemBinding

class ForecastRecyclerViewAdapter(private val context: Context,
    private val forecastList: List<Forecast>
) : RecyclerView.Adapter<ForecastRecyclerViewAdapter.ForecastViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForecastViewHolder {
        val binding = FiveDayForecastItemBinding.inflate(LayoutInflater.from(parent.context),
            parent, false)

        return ForecastViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ForecastViewHolder, position: Int) {

        val currentForecast = forecastList[position]
        holder.binding.forcastTemp.text = currentForecast.main?.temp.toString()

        holder.binding.dateTimeTxt.text = currentForecast.dtTxt.toString()

        Log.d("ForecastRecyclerView", "position : ${position}")

        setImage(currentForecast.weather?.get(0)?.icon, holder.binding.weatherIcon)

    }

    private fun setImage(icon: String?, weatherIcon: ImageView) {
        val uri = "https://openweathermap.org/img/wn/${icon}@2x.png"
        Glide.with(weatherIcon.context).load(uri)
            .placeholder(R.drawable.ic_launcher_foreground)
            .into(weatherIcon)
    }

    override fun getItemCount(): Int {
        return forecastList.size
    }

    inner class ForecastViewHolder(val binding : FiveDayForecastItemBinding) : RecyclerView.ViewHolder(binding.root)

}