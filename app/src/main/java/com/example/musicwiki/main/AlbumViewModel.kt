package com.example.musicwiki.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.musicwiki.data.albumdata.AlbumDataRequest
import com.example.musicwiki.model.AlbumRepository



class AlbumViewModel: ViewModel() {
    var AlbumDataApiCallStatus = MutableLiveData<ResourceStatus>()


    var AlbumresponseLiveWeatherData = MutableLiveData<AlbumDataRequest>()

    fun albumdatavm(rock:String) {

         //check user has an active internet
            AlbumDataApiCallStatus.value =
                ResourceStatus.loading()//change the value of api call status to check in main activity
            AlbumRepository.albumrepo(rock) { isSuccess, response ->

                if (isSuccess) {
                    AlbumDataApiCallStatus.value =
                        ResourceStatus.success("")//change the value of api call status to check in main activity
                    AlbumresponseLiveWeatherData.postValue(response) //store the response in response live data
                }
                else {
                    if (response?.albums?.album?.size!=0) { // check if the response message is successful one
                        AlbumDataApiCallStatus.value =
                            ResourceStatus.sessionexpired() //change the value of api call status to check in main activity
                    } else {
                        AlbumDataApiCallStatus.value =
                            ResourceStatus.error("") //change the value of api call status to check in main activity
                    }

                }

            }


    }
}