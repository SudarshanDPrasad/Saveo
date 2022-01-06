package com.application.saveo.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.application.saveo.adaptor.TopViewAdaptor
import com.application.saveo.adaptor.mainViewAdaptor
import com.application.saveo.clicklistener.onClick
import com.application.saveo.data.ResultViewModel
import com.application.saveo.databinding.ActivityMainBinding
import com.application.saveo.response.ResultDTO
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() , onClick {

    private lateinit var activityMainBinding: ActivityMainBinding
    private lateinit var resultViewModel: ResultViewModel
    private lateinit var mainViewAdaptor: mainViewAdaptor
    private lateinit var topViewAdaptor: TopViewAdaptor


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        resultViewModel = ViewModelProvider(this).get(ResultViewModel::class.java)
        setAdaptor()


        resultViewModel.movieload().observe(this, {
            it?.let {
                CoroutineScope(Dispatchers.Main).launch {
                    mainViewAdaptor.submitData(it)

                }
                CoroutineScope(Dispatchers.Main).launch {
                    topViewAdaptor.submitData(it)
                }
            }
        })

        resultViewModel.movieload().observe(this, {
            it?.let {
                CoroutineScope(Dispatchers.Main).launch {
                    topViewAdaptor.submitData(it)
                }
            }
        })
    }

    private fun setAdaptor() {
        mainViewAdaptor = mainViewAdaptor(this)
        val linearLayoutManager = GridLayoutManager(this,3)
        activityMainBinding.rvListWithDetails.apply {
            adapter = mainViewAdaptor
            layoutManager = linearLayoutManager
        }

        topViewAdaptor = TopViewAdaptor(this)
        val linearLayoutManagertop = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        activityMainBinding.rvTopView.apply {
            adapter = topViewAdaptor
            layoutManager = linearLayoutManagertop
        }
    }

    override fun clickListener( resultDTO: ResultDTO) {
        val intent : Intent = Intent(this,MovieDetailActivity::class.java)
        intent.putExtra("details",resultDTO)
        startActivity(intent)
    }


}