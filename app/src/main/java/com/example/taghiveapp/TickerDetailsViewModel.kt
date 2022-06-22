package com.example.taghiveapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.taghiveapp.data.SymbolResponse
import com.example.taghiveapp.repository.BaseRemoteRepository

class TickerDetailsViewModel : ViewModel() {

    private var baseRemoteRepo: BaseRemoteRepository? = null
    private var individualResponseLiveData: LiveData<SymbolResponse?>? = null

    fun init() {
        baseRemoteRepo = BaseRemoteRepository()
    }

    fun getIndividualTicker(symbol: String?) {
        try {
            baseRemoteRepo?.getIndividualTickers(symbol)
            individualResponseLiveData = baseRemoteRepo?.getIndividualTickerResponseLiveData()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun getIndividualTickerResponseLiveData(): LiveData<SymbolResponse?>? {
        return individualResponseLiveData
    }
}