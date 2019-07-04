package codes.zaak.showcase_mvvm_android.locationlist.data

import com.squareup.moshi.Json

data class Location(
    @Json(name = "woeid")
    val id: Int,
    val title: String,
    @Json(name = "location_type")
    val locationType: String
)