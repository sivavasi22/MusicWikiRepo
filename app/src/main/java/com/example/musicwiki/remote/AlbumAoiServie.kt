package com.example.musicwiki.remote

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import com.example.musicwiki.data.albumdata.AlbumDataRequest
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

interface AlbumAoiServie {
    @GET("/2.0/")
    fun albumservice(@Query("tag") album:String): Call<AlbumDataRequest>

    companion object {
        val instance: AlbumAoiServie by lazy {
            //initializing the http builder
            val okhttpClientBuilder = OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)

            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY

            // interceptor to build request and url
            val interceptor = Interceptor{chain ->
                val url = chain.request()
                    .url()
                    .newBuilder()
                    .addQueryParameter("method" ,"tag.gettopalbums")
                    .addQueryParameter("api_key","840925b98811280a9f907b6484523fae")
                    .addQueryParameter("format" ,"json")
                    .build()
                val request = chain.request()
                    .newBuilder()
                    .url(url)
                    .build()

                val response = chain.proceed(request)

                return@Interceptor response
            }

            okhttpClientBuilder.addInterceptor(logging)// add the interceptor to the http builder
            okhttpClientBuilder.addInterceptor(interceptor)

            val okHttpClient =okhttpClientBuilder.build()

            val gson = GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
                .setLenient()
                .create()
            Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("https://ws.audioscrobbler.com/")
                .addConverterFactory(GsonConverterFactory.create(gson)).addCallAdapterFactory(
                    CoroutineCallAdapterFactory()
                )
                .build()
                .create(AlbumAoiServie::class.java)

        }
    }
}