package com.example.musicwiki.model

import com.example.musicwiki.data.albumdata.AlbumDataRequest
import com.example.musicwiki.data.artistdata.ArtistDataRequest
import com.example.musicwiki.remote.AlbumAoiServie
import com.example.musicwiki.remote.ArtistApiService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Response

object ArtistRepository {

    private val job = Job()
    private val scope = CoroutineScope(Dispatchers.Main + job)
    fun artistrepo(taggenere:String, onResult: (isSuccess: Boolean, response: ArtistDataRequest?) -> Unit){
        ArtistRepository.scope.launch {
            //call the interface to build the http call and execute
            ArtistApiService.instance.artistservice(taggenere).enqueue(object : retrofit2.Callback<ArtistDataRequest> {
                override fun onFailure(call: Call<ArtistDataRequest>, t: Throwable) {
                    onResult(false, null)//on failure
                }
                override fun onResponse(
                    call: Call<ArtistDataRequest>,
                    response: Response<ArtistDataRequest>
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