package com.example.musicwiki.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.musicwiki.data.artistdata.ArtistDataRequest
import com.example.musicwiki.model.ArtistRepository

class ArtistViewModel: ViewModel() {
    var ArtistDetailsDataApiCallStatus = MutableLiveData<ResourceStatus>()
    var ArtistDetailsresponseLiveWeatherData = MutableLiveData<ArtistDataRequest>()

    fun artistdatavm(taggenere:String) {

        //check user has an active internet
        ArtistDetailsDataApiCallStatus.value =
            ResourceStatus.loading()//change the value of api call status to check in main activity
        ArtistRepository.artistrepo(taggenere) { isSuccess, response ->

            if (isSuccess) {
                ArtistDetailsDataApiCallStatus.value =
                    ResourceStatus.success("")//change the value of api call status to check in main activity
                ArtistDetailsresponseLiveWeatherData.postValue(response) //store the response in response live data
            }
            else {
                if (response?.topartists?.artist?.size!=0) { // check if the response message is successful one
                    ArtistDetailsDataApiCallStatus.value =
                        ResourceStatus.sessionexpired() //change the value of api call status to check in main activity
                } else {
                    ArtistDetailsDataApiCallStatus.value =
                        ResourceStatus.error("") //change the value of api call status to check in main activity
                }

            }

        }


    }
}