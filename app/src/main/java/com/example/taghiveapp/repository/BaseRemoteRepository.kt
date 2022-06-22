package com.example.taghiveapp.repository

import com.example.taghiveapp.data.TickerInterface
import androidx.lifecycle.MutableLiveData
import com.example.taghiveapp.data.TickerResponse
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.converter.gson.GsonConverterFactory;
import androidx.lifecycle.LiveData
import com.example.taghiveapp.data.SymbolResponse
import retrofit2.Retrofit


class BaseRemoteRepository {
    private val TICKER_BASE_URL = "https://api.wazirx.com/sapi/v1/"
    lateinit var tickerInterface: TickerInterface
    private var tickerResponseLiveData: MutableLiveData<TickerResponse?>? = null
    private var individualTickerResponseLiveData: MutableLiveData<SymbolResponse?>? = null

    fun baseRemoteRepository() {
        tickerResponseLiveData = MutableLiveData<TickerResponse?>()
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()
        tickerInterface = Retrofit.Builder()
            .baseUrl(TICKER_BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(TickerInterface::class.java)
    }

    fun getTickers() {
        baseRemoteRepository()
        tickerInterface.getTickers()?.enqueue(object : Callback<TickerResponse?> {
                override fun onResponse(
                    call: Call<TickerResponse?>?,
                    response: Response<TickerResponse?>
                ) {
                    if (response.body() != null) {
                        tickerResponseLiveData?.postValue(response.body())
                    }
                }

                override fun onFailure(call: Call<TickerResponse?>?, t: Throwable?) {
                    tickerResponseLiveData?.postValue(null)
                }
            })
    }

    fun getIndividualTickers(symbol: String?) {
        individualBaseRemoteRepository()
        tickerInterface.getIndividualTickers(symbol)?.enqueue(object : Callback<SymbolResponse?> {
            override fun onResponse(
                call: Call<SymbolResponse?>?,
                response: Response<SymbolResponse?>
            ) {
                if (response.body() != null) {
                    individualTickerResponseLiveData?.postValue(response.body())
                }
            }

            override fun onFailure(call: Call<SymbolResponse?>?, t: Throwable?) {
                individualTickerResponseLiveData?.postValue(null)
            }
        })
    }

    fun individualBaseRemoteRepository() {
        individualTickerResponseLiveData = MutableLiveData<SymbolResponse?>()
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()
        tickerInterface = Retrofit.Builder()
            .baseUrl(TICKER_BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(TickerInterface::class.java)
    }

    fun getTickerResponseLiveData(): LiveData<TickerResponse?>? {
        return tickerResponseLiveData
    }

    fun getIndividualTickerResponseLiveData(): LiveData<SymbolResponse?>? {
        return individualTickerResponseLiveData
    }
}