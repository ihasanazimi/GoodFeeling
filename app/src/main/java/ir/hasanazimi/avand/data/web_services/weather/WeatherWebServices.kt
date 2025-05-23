package ir.hasanazimi.avand.data.web_services.weather

import ir.hasanazimi.avand.data.entities.remote.weather.WeatherRemoteResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherWebServices {

    @GET("current.json")
    suspend fun getCurrentWeather(
        @Query("q") q : String,
        @Query("aqi") airQuality : String = "yes",
        @Query("key") key : String = "59683c52643e4c96ab780645251604"
    ) : Response<WeatherRemoteResponse>

}