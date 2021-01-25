package com.example.musicwiki.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.musicwiki.data.albumdetails.AlbumDataDetailsRequest
import com.example.musicwiki.model.AlbumDetailsRepository

class AlbumDetailsViewModel: ViewModel() {

    var AlbumDetailsDataApiCallStatus = MutableLiveData<ResourceStatus>()
    var AlbumDetailsresponseLiveWeatherData = MutableLiveData<AlbumDataDetailsRequest>()

    fun albumdatavm(artist:String,album:String) {

        //check user has an active internet
        AlbumDetailsDataApiCallStatus.value =
            ResourceStatus.loading()//change the value of api call status to check in main activity
        AlbumDetailsRepository.albumrepo(artist,album) { isSuccess, response ->

            if (isSuccess) {
                AlbumDetailsDataApiCallStatus.value =
                    ResourceStatus.success("")//change the value of api call status to check in main activity
                AlbumDetailsresponseLiveWeatherData.postValue(response) //store the response in response live data
            }
            else {
                if (response?.album?.image?.size!=0) { // check if the response message is successful one
                    AlbumDetailsDataApiCallStatus.value =
                        ResourceStatus.sessionexpired() //change the value of api call status to check in main activity
                } else {
                    AlbumDetailsDataApiCallStatus.value =
                        ResourceStatus.error("") //change the value of api call status to check in main activity
                }

            }

        }


    }
}