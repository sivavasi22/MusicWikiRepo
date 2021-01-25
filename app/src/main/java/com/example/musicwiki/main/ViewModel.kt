package com.example.musicwiki.main

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.musicwiki.data.generedata.DataRequest
import com.example.musicwiki.model.Repository


class ViewModel: ViewModel() {
    var DataApiCallStatus = MutableLiveData<ResourceStatus>()
    var responseLiveData = MutableLiveData<DataRequest>()

    fun callWeatherData(context: Context) {

        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val nInfo: NetworkInfo? = connectivityManager.getActiveNetworkInfo()
        val connected = nInfo != null && nInfo.isAvailable && nInfo.isConnected

        if (connected) { //check user has an active internet
            DataApiCallStatus.value =
                ResourceStatus.loading()//change the value of api call status to check in main activity
            Repository.weatherSummaryRepoonResult() { isSuccess, response ->

                if (isSuccess) {
                    DataApiCallStatus.value =
                        ResourceStatus.success("")//change the value of api call status to check in main activity
                    responseLiveData.postValue(response) //store the response in response live data
                }
                else {
                    if (response?.toptags?.tag?.size!=0) { // check if the response message is successful one
                        DataApiCallStatus.value =
                            ResourceStatus.sessionexpired() //change the value of api call status to check in main activity
                    } else {
                        DataApiCallStatus.value =
                            ResourceStatus.error("") //change the value of api call status to check in main activity
                    }

                }

            }
        }
        else {
            Log.e("CallStatus","No netwrok")
            DataApiCallStatus.value = ResourceStatus.nonetwork()
        }

    }
}