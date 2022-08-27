package com.ppk.graphql.presentation.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ppk.graphql.BaseApplication
import com.ppk.graphql.R
import com.ppk.graphql.data.model.LaunchModel
import com.ppk.graphql.presentation.adapter.LaunchListRecyclerAdapter
import com.ppk.graphql.presentation.contract.ViewContract
import com.ppk.graphql.presentation.viewModel.MainActivityViewModel

class MainActivity : AppCompatActivity(),ViewContract {

    private lateinit var viewModel: MainActivityViewModel
    private lateinit var launchListRecycler: RecyclerView
    private lateinit var launchList: ArrayList<LaunchModel>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        widgets()
        initiate()
    }

    /**
     * declaration of ui widgets
     */
    private fun widgets(){
        launchListRecycler = findViewById(R.id.launchList)
    }


    private fun initiate(){
        //initiate viewModel and inject the viewModel
        viewModel = ViewModelProvider(this)[MainActivityViewModel::class.java]
        (applicationContext as BaseApplication).appComponent.inject(viewModel)

        launchList = ArrayList() //initiate the launch list
        viewModel.useCase.getLaunchList().observe(this) { launchList->
            this.launchList = launchList
        }

        val layoutManager = LinearLayoutManager(this)
        launchListRecycler.layoutManager = layoutManager

        val adapter = LaunchListRecyclerAdapter(launchList,this)
        launchListRecycler.adapter = adapter

    }

    override fun nextActivity(launchModel: LaunchModel) {
        val intent = Intent(this,DetailActivity::class.java)
        intent.putExtra("model",launchModel)
        startActivity(intent)
    }

    override fun showToast(message: String) {
        TODO("Not yet implemented")
    }


}