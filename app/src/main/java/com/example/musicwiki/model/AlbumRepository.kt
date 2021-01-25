package com.example.musicwiki.model

import com.example.musicwiki.data.albumdata.AlbumDataRequest
import com.example.musicwiki.remote.AlbumAoiServie
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Response

object AlbumRepository {
    private val job = Job()
    private val scope = CoroutineScope(Dispatchers.Main + job)
    fun albumrepo(rock:String, onResult: (isSuccess: Boolean, response: AlbumDataRequest?) -> Unit){
        AlbumRepository.scope.launch {
            //call the interface to build the http call and execute
            AlbumAoiServie.instance.albumservice(rock).enqueue(object : retrofit2.Callback<AlbumDataRequest> {
                override fun onFailure(call: Call<AlbumDataRequest>, t: Throwable) {
                    onResult(false, null)//on failure
                }
                override fun onResponse(
                    call: Call<AlbumDataRequest>,
                    response: Response<AlbumDataRequest>
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