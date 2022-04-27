package com.asterisk.currencyconverter.data.repositories

import com.asterisk.currencyconverter.data.models.CurrencyResponse
import com.asterisk.currencyconverter.data.services.CurrencyApi
import com.asterisk.currencyconverter.others.Resource
import javax.inject.Inject

class CurrencyRepo @Inject constructor(
    private val api: CurrencyApi,
) : ICurrencyRepo {

    override suspend fun getRate(base: String): Resource<CurrencyResponse> {
        return try {
            val response = api.getRates(base)
            val result = response.body()
            if (response.isSuccessful && result != null) {
                Resource.Success(result)
            } else {
                Resource.Error(response.message())
            }
        } catch (e: Exception) {
            Resource.Error(e.message ?: "An Error occurred")
        }
    }

}