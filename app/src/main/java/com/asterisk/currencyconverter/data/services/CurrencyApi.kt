package com.asterisk.currencyconverter.data.services

import com.asterisk.currencyconverter.data.models.CurrencyResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CurrencyApi {

    @GET("latest")
    suspend fun getRates(
        @Query("base") base: String,
        @Query("access_key") accessKey: String = "096db3c8718ee28ec5aab8ef17dfd256",
    ): Response<CurrencyResponse>
}