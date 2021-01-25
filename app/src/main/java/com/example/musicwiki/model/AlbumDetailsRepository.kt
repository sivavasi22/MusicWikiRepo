package com.example.musicwiki.model

import com.example.musicwiki.data.albumdata.AlbumDataRequest
import com.example.musicwiki.data.albumdetails.AlbumDataDetailsRequest
import com.example.musicwiki.remote.AlbumAoiServie
import com.example.musicwiki.remote.AlbumDetailsApiService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Response

object AlbumDetailsRepository {

    private val job = Job()
    private val scope = CoroutineScope(Dispatchers.Main + job)
    fun albumrepo(artist:String,album:String, onResult: (isSuccess: Boolean, response: AlbumDataDetailsRequest?) -> Unit){
        AlbumDetailsRepository.scope.launch {
            //call the interface to build the http call and execute
            AlbumDetailsApiService.instance.albumservice(artist,album).enqueue(object : retrofit2.Callback<AlbumDataDetailsRequest> {
                override fun onFailure(call: Call<AlbumDataDetailsRequest>, t: Throwable) {
                    onResult(false, null)//on failure
                }
                override fun onResponse(
                    call: Call<AlbumDataDetailsRequest>,
                    response: Response<AlbumDataDetailsRequest>
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