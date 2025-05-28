package com.example.weather.viewmodel

class WeatherViewModel : ViewModel() {
    private val repository = WeatherRepository()
    val weatherData = MutableLiveData<WeatherResponse>()
    val errorMessage = MutableLiveData<String>()

    fun getWeather(city: String) {
        viewModelScope.launch {
            try {
                val response = repository.getWeather(city)
                weatherData.postValue(response)
            } catch (e: Exception) {
                errorMessage.postValue("Ошибка: ${e.message}")
            }
        }
    }
}