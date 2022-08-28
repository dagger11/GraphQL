package com.ppk.graphql.presentation.view

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ppk.graphql.R
import com.ppk.graphql.presentation.adapter.LaunchListRecyclerAdapter
import com.ppk.graphql.presentation.contract.ViewContract
import com.ppk.graphql.presentation.viewModel.MainActivityViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi

@AndroidEntryPoint
@ExperimentalCoroutinesApi
class MainActivity : AppCompatActivity(),ViewContract {

    private lateinit var viewModel: MainActivityViewModel
    private lateinit var characterListRecycler: RecyclerView
    private lateinit var adapter: LaunchListRecyclerAdapter

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
        characterListRecycler = findViewById(R.id.launchList)
    }

    private fun initiate(){
        //initiate viewModel and inject the viewModel
        viewModel = ViewModelProvider(this)[MainActivityViewModel::class.java]

        observeCharacterList()
        val layoutManager = LinearLayoutManager(this)
        characterListRecycler.layoutManager = layoutManager
    }

    override fun nextActivity(id:String) {
        val intent = Intent(this,DetailActivity::class.java)
        intent.putExtra("id",id)
        startActivity(intent)
    }

    override fun provideContext(): Context {
        return this
    }

    override fun showToast(message: String) {
       Toast.makeText(this,message,Toast.LENGTH_SHORT).show()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun observeCharacterList() {
        viewModel.queryCharactersList()

        viewModel.characterList.observe(this) { response ->
            when (response) {
                is ViewState.Loading -> {
                    showToast("Loading Data")
                }
                is ViewState.Success -> {
                    if (response.value?.data?.characters?.results?.size == 0) {
                        showToast("No Data is Exist!!!")
                    } else {
                        showToast("Fetched Successfully!")
                    }
                    val results = response.value?.data?.characters?.results

                    if (results!=null){
                        adapter = LaunchListRecyclerAdapter(results,this)
                        characterListRecycler.adapter = adapter
                        adapter.notifyDataSetChanged()
                    }
                }
                is ViewState.Error -> {
                   showToast("Getting Data Error")
                }
            }
        }
    }


}