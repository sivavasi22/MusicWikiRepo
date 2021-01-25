package com.example.musicwiki.model

import com.example.musicwiki.data.artistdata.ArtistDataRequest
import com.example.musicwiki.data.tracksdata.TrackDataRequest
import com.example.musicwiki.remote.ArtistApiService
import com.example.musicwiki.remote.TrackApiService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Response

object TrackRepository {


    private val job = Job()
    private val scope = CoroutineScope(Dispatchers.Main + job)
    fun trackrepo(track:String, onResult: (isSuccess: Boolean, response: TrackDataRequest?) -> Unit){
        TrackRepository.scope.launch {
            //call the interface to build the http call and execute
            TrackApiService.instance.trackservice(track).enqueue(object : retrofit2.Callback<TrackDataRequest> {
                override fun onFailure(call: Call<TrackDataRequest>, t: Throwable) {
                    onResult(false, null)//on failure
                }
                override fun onResponse(
                    call: Call<TrackDataRequest>,
                    response: Response<TrackDataRequest>
                ) {
                    if (response != null && response.isSuccessful)
                        onResult(true, response.body()!!)//on success
                    else
                        onResult(false, null)//on failure
                }
            })
        }
    }
}