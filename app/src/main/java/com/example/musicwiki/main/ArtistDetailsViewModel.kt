package com.example.musicwiki.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.musicwiki.data.artistdetails.ArtistDetailsRequest
import com.example.musicwiki.model.ArtistDetailsRepository

class ArtistDetailsViewModel: ViewModel() {
    var ArtistDetailsDataApiCallStatus = MutableLiveData<ResourceStatus>()
    var ArtistDetailsresponseLiveWeatherData = MutableLiveData<ArtistDetailsRequest>()

    fun albumdatavm(artist:String) {

        //check user has an active internet
        ArtistDetailsDataApiCallStatus.value =
            ResourceStatus.loading()//change the value of api call status to check in main activity
        ArtistDetailsRepository.albumrepo(artist) { isSuccess, response ->

            if (isSuccess) {
                ArtistDetailsDataApiCallStatus.value =
                    ResourceStatus.success("")//change the value of api call status to check in main activity
                ArtistDetailsresponseLiveWeatherData.postValue(response) //store the response in response live data
            }
            else {
                if (response?.artist?.image?.size!=0) { // check if the response message is successful one
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