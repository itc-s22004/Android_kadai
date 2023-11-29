package jp.ac.it_college.std.s22004.androidkadai.model

import kotlinx.serialization.Serializable

@Serializable
data class Weather(
    val cod: String?,
    val message: Int?,
    val cnt: Int?,
    val list: List<NamedApiResource>,
)

@Serializable
data class NamedApiResource(
    val dt: Int,
    val main: MainResource,
    val weather: List<WeatherResource>,
    val wind: WindResource,

    val dt_txt: String,
)

@Serializable
data class MainResource(
    val temp: Double,
    val feels_like: Double,
    val pressure: Int,
    val humidity: Int,
)

@Serializable
data class WeatherResource(
    val main: String,
    val description: String,
    val icon: String
)

@Serializable
data class WindResource(
    val speed: Double,
    val deg: Int,
    val gust: Double
)