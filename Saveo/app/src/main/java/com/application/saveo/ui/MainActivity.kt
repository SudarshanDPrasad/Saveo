package com.application.saveo.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.paging.PagingData
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.application.saveo.adaptor.NewAdaptor
import com.application.saveo.adaptor.mainViewAdaptor
import com.application.saveo.clicklistener.onClick
import com.application.saveo.data.ResultViewModel
import com.application.saveo.data.Status
import com.application.saveo.databinding.ActivityMainBinding
import com.application.saveo.response.ResultDTO
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.jetbrains.anko.act
import java.lang.Math.abs

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), onClick {

    private lateinit var activityMainBinding: ActivityMainBinding
    private lateinit var resultViewModel: ResultViewModel
    private lateinit var mainViewAdaptor: mainViewAdaptor
    private var list = emptyList<ResultDTO>()


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
            }
        })

        resultViewModel.callApitop().observe(this, {
            when (it.status) {
                Status.ERROR -> {
                    Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
                }

                Status.SUCCESS -> {
                    list = it.data?.results as ArrayList<ResultDTO>
                    val adaptor = NewAdaptor(list, this)
                    activityMainBinding.rvTopView.adapter = adaptor
                    activityMainBinding.rvTopView.clipToPadding = false
                    activityMainBinding.rvTopView.clipChildren = false
                    activityMainBinding.rvTopView.offscreenPageLimit = 3
                    activityMainBinding.rvTopView.getChildAt(0).overScrollMode =
                        RecyclerView.OVER_SCROLL_NEVER

                    val comPosPageTarn = CompositePageTransformer()
                    comPosPageTarn.addTransformer(MarginPageTransformer(40))
                    comPosPageTarn.addTransformer { page, position ->
                        val r: Float = 1 - abs(position)
                        page.scaleY = 0.85f + r * 0.15f
                    }
                    activityMainBinding.rvTopView.setPageTransformer(comPosPageTarn)
                }
            }
        })
    }

    private fun setAdaptor() {
        mainViewAdaptor = mainViewAdaptor(this)
        val linearLayoutManager = GridLayoutManager(this, 3)
        activityMainBinding.rvListWithDetails.apply {
            adapter = mainViewAdaptor
            layoutManager = linearLayoutManager
        }
    }

    override fun clickListener(resultDTO: ResultDTO) {
        val intent: Intent = Intent(this, MovieDetailActivity::class.java)
        intent.putExtra("details", resultDTO)
        startActivity(intent)
    }
}