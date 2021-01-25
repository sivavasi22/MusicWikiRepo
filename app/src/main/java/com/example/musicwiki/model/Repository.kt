package com.example.musicwiki.model

import com.example.musicwiki.remote.Interface
import com.example.musicwiki.data.generedata.DataRequest
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Response

object Repository {
    private val job = Job()
    private val scope = CoroutineScope(Dispatchers.Main + job)

    fun weatherSummaryRepoonResult( onResult: (isSuccess: Boolean, response: DataRequest?) -> Unit){
        scope.launch {
            //call the interface to build the http call and execute
            Interface.instance.weatherSummary().enqueue(object : retrofit2.Callback<DataRequest> {
                override fun onFailure(call: Call<DataRequest>, t: Throwable) {
                    onResult(false, null)//on failure
                }
                override fun onResponse(
                    call: Call<DataRequest>,
                    response: Response<DataRequest>
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
