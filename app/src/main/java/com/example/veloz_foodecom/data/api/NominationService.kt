package com.example.veloz_foodecom.data.api

import com.example.veloz_foodecom.data.models.location.NominatimResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface NominationService {
    @GET("reverse")
    suspend fun reverseGeocode(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double,
        @Query("format") format: String = "json",
        @Query("addressdetails") addressDetails: Int = 1,
        @Header("User-Agent") userAgent: String = "Veloz/1.0 (pps41282@gmail.com)"
    ): NominatimResponse
    @GET("search")
    suspend fun searchNearby(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double,
        @Query("format") format: String = "json",
        @Query("addressdetails") addressDetails: Int = 1,
        @Header("User-Agent") userAgent: String = "VelozApp/1.0 (pps41282@gmail.com)"
    ):List<NominatimResponse>
    @GET("search")
    suspend fun searchInBoundingBox(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double,
        @Query("format") format: String = "json",
        @Query("addressdetails") addressDetails: Int = 1,
        @Query("viewbox") viewbox: String,
        @Query("bounded") bounded: Int = 1,
        @Header("User-Agent") userAgent: String = "VelozApp/1.0 (pps41282@gmail.com)"
    ): List<NominatimResponse>
}