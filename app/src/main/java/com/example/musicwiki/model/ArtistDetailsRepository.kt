package com.example.musicwiki.model

import com.example.musicwiki.data.albumdetails.AlbumDataDetailsRequest
import com.example.musicwiki.data.artistdetails.ArtistDetailsRequest
import com.example.musicwiki.remote.AlbumDetailsApiService
import com.example.musicwiki.remote.ArtistDetailsApiService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Response

object ArtistDetailsRepository {
    private val job = Job()
    private val scope = CoroutineScope(Dispatchers.Main + job)
    fun albumrepo(artist:String, onResult: (isSuccess: Boolean, response: ArtistDetailsRequest?) -> Unit){
        ArtistDetailsRepository.scope.launch {
            //call the interface to build the http call and execute
            ArtistDetailsApiService.instance.albumservice(artist).enqueue(object : retrofit2.Callback<ArtistDetailsRequest> {
                override fun onFailure(call: Call<ArtistDetailsRequest>, t: Throwable) {
                    onResult(false, null)//on failure
                }
                override fun onResponse(
                    call: Call<ArtistDetailsRequest>,
                    response: Response<ArtistDetailsRequest>
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