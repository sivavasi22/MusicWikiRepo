package com.example.musicwiki.main

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.musicwiki.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {
    private lateinit var ViewModel: ViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ViewModel = ViewModelProviders.of(this)[com.example.musicwiki.main.ViewModel::class.java]

        ViewModel.callWeatherData(this)

setupObservers()
    }
    @SuppressLint("WrongConstant")
    fun setupObservers() {

        //observing the response received from the api
        ViewModel.responseLiveData.observe(this, Observer { data->
            //if not empty data initalizing the recyl view and setting the weather details
            if (data.toptags.tag.size!= 0) {

                var recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
                button2.setOnClickListener {
                    button3.visibility = View.VISIBLE
                    button2.visibility = View.GONE
                    val adapter = GenereRecyclerView(this,data.toptags.tag)
                    adapter.notifyDataSetChanged()
                    recyclerView.layoutManager = GridLayoutManager(this,3)
                    recyclerView.adapter = adapter
                }
                button3.setOnClickListener {
                    button2.visibility = View.VISIBLE
                    button3.visibility = View.GONE
                    val adapter = GenereRecyclerView(this,data.toptags.tag.take(10))
                    adapter.notifyDataSetChanged()
                    recyclerView.layoutManager = GridLayoutManager(this,3)
                    recyclerView.adapter = adapter
                }


                val adapter = GenereRecyclerView(this,data.toptags.tag.take(10))
                adapter.notifyDataSetChanged()

                recyclerView.layoutManager = GridLayoutManager(this,3)
                recyclerView.adapter = adapter


            } else {
                Toast.makeText(this, "Not Successful", Toast.LENGTH_SHORT).show()
            }
        })

        ViewModel.DataApiCallStatus.observe(this, Observer {
            processStatus(it)
        })
        //observe the status code of the api hit, whether it is a success or fail

    }

    private fun processStatus(resource: ResourceStatus) {

        when (resource.status) {
            StatusType.SUCCESS -> {
                dismissDialog()



            }
            StatusType.EMPTY_RESPONSE -> {
                dismissDialog()

            }
            StatusType.PROGRESSING -> {
                showDialog()

            }

            StatusType.LOADING_MORE -> {
                // CommonUtils().showSnackbar(binding.root, "Loading more..")
            }
            StatusType.NO_NETWORK -> {
                Toast.makeText(this, "Please check internet connection", Toast.LENGTH_SHORT).show()
            }
            StatusType.SESSION_EXPIRED -> {
                //CommonUtils().showSnackbar(login_button_login.rootView,"session expired")
            }
        }

    }
}