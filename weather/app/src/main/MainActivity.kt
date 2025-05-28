package com.example.weather

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.weather.ui.theme.WeatherTheme

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: WeatherViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this)[WeatherViewModel::class.java]

        getWeatherBtn.setOnClickListener {
            val city = cityInput.text.toString()
            if (city.isNotEmpty()) {
                viewModel.getWeather(city)
            } else {
                errorText.text = "Пожалуйста, введите город"
            }
        }

        viewModel.weatherData.observe(this) { weather ->
            weatherInfo.visibility = View.VISIBLE
            errorText.text = ""

            cityName.text = weather.name
            temperature.text = "Температура: ${weather.main.temp}°C"
            weatherDescription.text = "Погода: ${weather.weather[0].description}"
            minMaxTemp.text = "Мин: ${weather.main.temp_min}°C, Макс: ${weather.main.temp_max}°C"
            humidity.text = "Влажность: ${weather.main.humidity}%"
        }

        viewModel.errorMessage.observe(this) { error ->
            errorText.text = error
            weatherInfo.visibility = View.GONE
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    WeatherTheme {
        Greeting("Android")
    }
}