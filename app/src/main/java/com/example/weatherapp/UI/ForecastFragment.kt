package com.example.weatherapp.UI

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weatherapp.Adapter.ForecastRecyclerViewAdapter
import com.example.weatherapp.Data.Forecast.Forecast
import com.example.weatherapp.R
import com.example.weatherapp.Util.LocationUtil
import com.example.weatherapp.ViewModel.CurrentWeatherViewModel
import com.example.weatherapp.ViewModel.CurrentWeatherViewModelFactory
import com.example.weatherapp.ViewModel.ForecastViewModel
import com.example.weatherapp.ViewModel.ForecastViewModelFactory
import com.example.weatherapp.databinding.FragmentForecastBinding
import com.google.android.gms.location.LocationServices
import kotlinx.coroutines.launch

class ForecastFragment : Fragment() {

    private val PERMISSION_REQUEST_ACCESS_FINE_LOCATION: Int = 0
    private var _binding : FragmentForecastBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel : ForecastViewModel
    private lateinit var viewModelFactory: ForecastViewModelFactory

    private var forecastList : MutableList<Forecast> = mutableListOf()

    private lateinit var forecastRecyclerViewAdapter : ForecastRecyclerViewAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentForecastBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModelFactory = ForecastViewModelFactory()
        viewModel = ViewModelProvider(requireActivity(), viewModelFactory).get(ForecastViewModel::class.java)




        val fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)

        if (ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(requireActivity(),arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                PERMISSION_REQUEST_ACCESS_FINE_LOCATION)
            return
        }
        fusedLocationClient.lastLocation
            .addOnSuccessListener { location : Location? ->
                location?.let {
                    lifecycleScope.launch {
                        viewModel.getForecast(location).forcastList?.let {
                            forecastList.addAll(it)
                            initRecyclerViewAdapter()
                        }
                    }
                }

            }

        initRecyclerViewAdapter()
    }

    fun initRecyclerViewAdapter() {
        forecastRecyclerViewAdapter = ForecastRecyclerViewAdapter(requireContext(), forecastList)

        Log.d("ForecastFragment", "forecast list size ${forecastList.size}")

        binding.forecastRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.forecastRecyclerView.adapter = forecastRecyclerViewAdapter

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}