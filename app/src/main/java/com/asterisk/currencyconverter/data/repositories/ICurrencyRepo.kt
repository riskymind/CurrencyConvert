package com.asterisk.currencyconverter.data.repositories

import com.asterisk.currencyconverter.data.models.CurrencyResponse
import com.asterisk.currencyconverter.others.Resource

interface ICurrencyRepo {

    suspend fun getRate(base: String): Resource<CurrencyResponse>
}