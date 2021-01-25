package com.example.musicwiki.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.musicwiki.data.tracksdata.TrackDataRequest
import com.example.musicwiki.model.TrackRepository



class TracksViewModel: ViewModel() {
    var TracksDataApiCallStatus = MutableLiveData<ResourceStatus>()
    var responseLiveTracksData = MutableLiveData<TrackDataRequest>()

    fun trackdatavm(album: String?) {

        //check user has an active internet
        TracksDataApiCallStatus.value =
            ResourceStatus.loading()//change the value of api call status to check in main activity
        album?.let {
            TrackRepository.trackrepo(it) { isSuccess, response ->

                if (isSuccess) {
                    TracksDataApiCallStatus.value =
                        ResourceStatus.success("")//change the value of api call status to check in main activity
                    responseLiveTracksData.postValue(response) //store the response in response live data
                } else {
                    if (response?.tracks?.track?.size!=0) { // check if the response message is successful one
                        TracksDataApiCallStatus.value =
                            ResourceStatus.sessionexpired() //change the value of api call status to check in main activity
                    } else {
                        TracksDataApiCallStatus.value =
                            ResourceStatus.error("") //change the value of api call status to check in main activity
                    }

                }

            }
        }


    }
}