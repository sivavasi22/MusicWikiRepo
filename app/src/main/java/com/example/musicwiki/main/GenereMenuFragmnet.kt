package com.example.musicwiki.main

import android.annotation.SuppressLint
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.musicwiki.R
import com.google.gson.GsonBuilder
import org.json.JSONArray

class GenereMenuFragmnet(position: Int, val album: String?) : Fragment() {

    var dialog: Dialog? = null
    var dialog2: Dialog? = null
    var dialog1: Dialog? = null
    val position = position
    var gson = GsonBuilder().serializeNulls().create()
    private lateinit var viewModelAlbums: AlbumViewModel
    private lateinit var viewModelArtists: ArtistViewModel
    private lateinit var viewModelTracks: TracksViewModel

    @SuppressLint("WrongConstant")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?{
        viewModelAlbums = ViewModelProviders.of(this)[AlbumViewModel::class.java]
        viewModelArtists = ViewModelProviders.of(this)[ArtistViewModel::class.java]
        viewModelTracks = ViewModelProviders.of(this)[TracksViewModel::class.java]

        val view = inflater.inflate(R.layout.fragment_chat_menu_fragmnet, container, false)

        val recyclerView = view.findViewById(R.id.chat_menu_recycler_view) as RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(activity, LinearLayout.VERTICAL, false)

        setupObserversAlbums(view)
        setupObserversArtists(view)
        setupObserversTracks(view)


        if(position==0){
            album?.let { viewModelAlbums.albumdatavm(it) }

        }
        if(position==1){
            album?.let { viewModelArtists.artistdatavm(it) }
        }
        if(position==2){
viewModelTracks.trackdatavm(album)
        }

        return view

    }

    @SuppressLint("WrongConstant")
    private fun setupObserversAlbums(view: View) {

        val recyclerView = view.findViewById(R.id.chat_menu_recycler_view) as RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(activity, LinearLayout.VERTICAL, false)
        val user = ArrayList<MainActivity2.Userdetails>()
        viewModelAlbums.AlbumresponseLiveWeatherData.observe(this, Observer {
            if (it!= null){
                    val array = JSONArray(gson.toJson(it.albums.album))
                    for (i in 0 until array.length()){
                        val obj = array.getJSONObject(i)
                        val specs = listOf<Any>().toMutableList()
                        specs.add(it.albums.album[i].name)
                        for (j in it.albums.album[i].image){
                            if (j.size.equals("large")){
                                specs.add(j.text)
                                break
                            }
                        }

                        specs.add(it.albums.album[i].artist.name)

                        user.add(MainActivity2.Userdetails(specs))
                    }

                    val adapter_recycler = GenereMenuRecyclerView( activity,user)
                    recyclerView.adapter = adapter_recycler
                    adapter_recycler.notifyDataSetChanged()

            }
        })
        viewModelAlbums.AlbumDataApiCallStatus.observe(this, Observer {
            processStatus1(it)
        })

    }

    @SuppressLint("WrongConstant")
    private fun setupObserversArtists(view: View) {

        val recyclerView = view.findViewById(R.id.chat_menu_recycler_view) as RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(activity, LinearLayout.VERTICAL, false)
        val user = ArrayList<MainActivity2.Userdetails>()
        viewModelArtists.ArtistDetailsresponseLiveWeatherData.observe(this, Observer {
            if (it!= null){
                val array = JSONArray(gson.toJson(it.topartists.artist))
                for (i in 0 until array.length()){
                    val obj = array.getJSONObject(i)
                    val specs = listOf<Any>().toMutableList()
                    specs.add(it.topartists.artist[i].name)

                    for (j in it.topartists.artist[i].image){
                        if (j.size.equals("large")){
                            specs.add(j.text)
                            break
                        }
                    }

                    user.add(MainActivity2.Userdetails(specs))
                }

                val adapter_recycler = Genere2RecyclerView( activity,user)
                recyclerView.adapter = adapter_recycler
                adapter_recycler.notifyDataSetChanged()

            }
        })
        viewModelArtists.ArtistDetailsDataApiCallStatus.observe(this, Observer {
            processStatus3(it)
        })

    }
    @SuppressLint("WrongConstant")
    private fun setupObserversTracks(view: View) {

        val recyclerView = view.findViewById(R.id.chat_menu_recycler_view) as RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(activity, LinearLayout.VERTICAL, false)
        val user = ArrayList<MainActivity2.Userdetails>()
        viewModelTracks.responseLiveTracksData.observe(this, Observer {
            if (it!= null){
                val array = JSONArray(gson.toJson(it.tracks.track))
                for (i in 0 until array.length()){
                    val obj = array.getJSONObject(i)
                    val specs = listOf<Any>().toMutableList()
                    specs.add(it.tracks.track[i].name)

                    for (j in it.tracks.track[i].image){
                        if (j.size.equals("large")){
                            specs.add(j.text)
                            break
                        }
                    }


                    user.add(MainActivity2.Userdetails(specs))
                }

                val adapter_recycler = Genere3RecyclerView( activity,user)
                recyclerView.adapter = adapter_recycler
                adapter_recycler.notifyDataSetChanged()

            }
        })
        viewModelTracks.TracksDataApiCallStatus.observe(this, Observer {
            processStatus2(it)
        })

    }
    private fun processStatus1(
        resource: ResourceStatus
    ){
        when (resource.status) {
            StatusType.SUCCESS -> {
                dialog1?.let {
                    it.dismiss()
                }
            }
            StatusType.EMPTY_RESPONSE -> {
                dialog1?.let {
                    it.dismiss()
                }

            }
            StatusType.PROGRESSING -> {
                activity?.runOnUiThread {
                    dialog1 = CommonUtils().showDialog(activity!!)
                }
            }
            StatusType.LOADING_MORE -> {
                // CommonUtils().showSnackbar(binding.root, "Loading more..")
            }
            StatusType.NO_NETWORK -> {
                dialog1?.dismiss()
                Toast.makeText(activity, "Please check internet connection", Toast.LENGTH_SHORT).show()
            }
            StatusType.SESSION_EXPIRED -> {
                //CommonUtils().showSnackbar(revoke_interest_button.rootView,"session expired")
            }
        }
    }
    private fun processStatus2(
        resource: ResourceStatus
    ){
        when (resource.status) {
            StatusType.SUCCESS -> {
                dialog2?.let {
                    it.dismiss()
                }
            }
            StatusType.EMPTY_RESPONSE -> {
                dialog2?.let {
                    it.dismiss()
                }

            }
            StatusType.PROGRESSING -> {
                activity?.runOnUiThread {
                    dialog2= CommonUtils().showDialog(activity!!)
                }
            }
            StatusType.LOADING_MORE -> {
                // CommonUtils().showSnackbar(binding.root, "Loading more..")
            }
            StatusType.NO_NETWORK -> {
                dialog2?.dismiss()
                Toast.makeText(activity, "Please check internet connection", Toast.LENGTH_SHORT).show()
            }
            StatusType.SESSION_EXPIRED -> {
                //CommonUtils().showSnackbar(revoke_interest_button.rootView,"session expired")
            }
        }
    }
    private fun processStatus3(
        resource: ResourceStatus
    ){
        when (resource.status) {
            StatusType.SUCCESS -> {
                dialog?.let {
                    it.dismiss()
                }
            }
            StatusType.EMPTY_RESPONSE -> {
                dialog?.let {
                    it.dismiss()
                }

            }
            StatusType.PROGRESSING -> {
                activity?.runOnUiThread {
                    dialog = CommonUtils().showDialog(activity!!)
                }
            }


            StatusType.LOADING_MORE -> {
                // CommonUtils().showSnackbar(binding.root, "Loading more..")
            }
            StatusType.NO_NETWORK -> {
                dialog?.dismiss()
                Toast.makeText(activity, "Please check internet connection", Toast.LENGTH_SHORT).show()
            }
            StatusType.SESSION_EXPIRED -> {
                //CommonUtils().showSnackbar(revoke_interest_button.rootView,"session expired")
            }
        }
    }

    override fun onPause() {
        super.onPause()
        if (dialog != null && dialog!!.isShowing()) {
            dialog!!.dismiss();
        }
        if (dialog1 != null && dialog1!!.isShowing()) {
            dialog1!!.dismiss();
        }
        if (dialog2 != null && dialog2!!.isShowing()) {
            dialog2!!.dismiss();
        }
    }



}
