package com.example.weatherapp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    val weatherMutableLiveData = MutableLiveData<WeatherModel>()

    fun getWeather(city: String) {
        repository.getWeather(city, weatherMutableLiveData)
    }
}