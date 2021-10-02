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
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.weatherapp.R
import com.example.weatherapp.Util.LocationUtil
import com.example.weatherapp.ViewModel.CurrentWeatherViewModel
import com.example.weatherapp.ViewModel.CurrentWeatherViewModelFactory
import com.example.weatherapp.databinding.FragmentCurrentWeatherBinding
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import kotlinx.coroutines.launch

class CurrentWeatherFragment : Fragment() {

    private val PERMISSION_REQUEST_ACCESS_FINE_LOCATION: Int = 2
    private var _binding: FragmentCurrentWeatherBinding? = null
    private val binding get() = _binding!!

    private val TAG = "CurrentWeatherFragment"


    private lateinit var viewModel : CurrentWeatherViewModel
    private lateinit var viewModelFactory: CurrentWeatherViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCurrentWeatherBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        viewModelFactory = CurrentWeatherViewModelFactory()
        viewModel = ViewModelProvider(requireActivity(), viewModelFactory).get(CurrentWeatherViewModel::class.java)

        displayLocation()

        binding.forcastBtn.setOnClickListener {
            findNavController().navigate(R.id.forecastFragment)
        }


    }

    private fun displayLocation() {
/*        val currentLocation = LocationUtil.getCurrentLocation(requireContext(), requireActivity())

        currentLocation?.let { location ->
            lifecycleScope.launchWhenCreated {
                val currentWeather = viewModel.getCurrentWeather(location)
                if (currentWeather.isSuccessful) {
                    binding.temperature.text = currentWeather.body()!!.current.temp.toString()
                }
            }
        }*/

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
                    val currentWeather = viewModel.getCurrentWeather(location)
                        binding.temperature.text = currentWeather.main!!.temp.toString()
                }
                    }
            }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}