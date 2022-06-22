package com.example.taghiveapp

import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.taghiveapp.data.TickerResponse
import com.example.taghiveapp.repository.BaseRemoteRepository
import android.app.Application

class TickerViewModel(application: Application) : AndroidViewModel(application) {
    private var baseRemoteRepo: BaseRemoteRepository? = null
    private var responseLiveData: LiveData<TickerResponse?>? = null

    fun init() {
        baseRemoteRepo = BaseRemoteRepository()
    }

    fun getTicker() {
        baseRemoteRepo?.getTickers()

        responseLiveData = baseRemoteRepo?.getTickerResponseLiveData()
    }

    fun getTickerResponseLiveData(): LiveData<TickerResponse?>? {
        return responseLiveData
    }
}