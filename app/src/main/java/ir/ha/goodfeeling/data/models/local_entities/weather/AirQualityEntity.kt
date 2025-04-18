package ir.ha.goodfeeling.data.models.local_entities.weather

import com.google.gson.annotations.SerializedName

data class AirQualityEntity(
    val co: Double,
    val gbDefraIndex: Int,
    val no2: Double,
    val o3: Double,
    val pm10: Double,
    val pm2_5: Double,
    val so2: Double,
    val usEpaIndex: Int
)