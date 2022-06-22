package com.example.taghiveapp.data

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface TickerInterface {
    @GET("tickers/24hr")
    fun getTickers(): Call<TickerResponse?>?

    @GET("ticker/24hr")
    fun getIndividualTickers(@Query("symbol") symbol: String?): Call<SymbolResponse?>?
}