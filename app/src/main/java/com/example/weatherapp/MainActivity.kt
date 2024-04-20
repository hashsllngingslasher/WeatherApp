package com.example.weatherapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.weatherapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity(R.layout.activity_main) {
    private val binding by viewBinding(ActivityMainBinding::bind, R.id.container)
    private val viewModel: WeatherViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initClickers()
        observe()

    }

    private fun initClickers() {
        binding.etCity.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                viewModel.getWeather(s.toString())
            }
        })
    }

    private fun observe() {
        viewModel.weatherMutableLiveData.observe(this, Observer {
            with(binding) {
                tvTemperature.text = it.main.temp.toString()
                tvHumidity.text = it.main.humidity.toString()
                tvWeather.text = it.weather[0].main
                weatherPick()
            }
        })
    }

    private fun weatherPick() {
        with(binding) {
            when (tvWeather.text) {
                "Sunny" -> container.setBackgroundResource(R.drawable.sunny)
                "Rain" -> container.setBackgroundResource(R.drawable.rainy)
                "Clouds" -> container.setBackgroundResource(R.drawable.cloudy)
            }
        }
    }
}
